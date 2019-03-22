package com.chinasofti.lexian.manager.common.util;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

import org.apache.ibatis.executor.parameter.DefaultParameterHandler;
import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.executor.statement.RoutingStatementHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;

// 分页查询拦截器
@Intercepts({ @Signature(method = "prepare", type = StatementHandler.class, args = { Connection.class }) })
public class PageInterceptor implements Interceptor {
	private final String MYSQL = "mysql";
	private final String ORACLE = "oracle";

	public Object intercept(Invocation invocation) throws Throwable {
		RoutingStatementHandler handler = (RoutingStatementHandler) invocation.getTarget();
		StatementHandler delegate = (StatementHandler) ReflectUtil.getFieldValue(handler, "delegate");
		BoundSql boundSql = delegate.getBoundSql();
		Object obj = boundSql.getParameterObject();
		if (obj instanceof PageHelper&&CommonUtil.startWithIgnoreCace(boundSql.getSql(), "select")) {
			PageHelper<Object> page = (PageHelper<Object>) obj;
			MappedStatement mappedStatement = (MappedStatement) ReflectUtil.getFieldValue(delegate, "mappedStatement");
			Connection connection = (Connection) invocation.getArgs()[0];
			String sql = boundSql.getSql();
			if (page.isIsgetTotal()) {
				this.setTotalRecord(page, mappedStatement, connection);
			}
			String pageSql = this.getPageSql(page, sql, connection.getMetaData().getDatabaseProductName());
			ReflectUtil.setFieldValue(boundSql, "sql", pageSql);
		}
		Object result = invocation.proceed();
		return result;
	}

	public Object plugin(Object target) {
		return Plugin.wrap(target, this);
	}

	public void setProperties(Properties properties) {
	}

	private String getPageSql(PageHelper<?> page, String sql, String databaseName) {
		StringBuffer sqlBuffer = new StringBuffer(sql);
		if (MYSQL.equalsIgnoreCase(databaseName)) {
			return getMysqlPageSql(page, sqlBuffer);
		} else if (ORACLE.equalsIgnoreCase(databaseName)) {
			return getOraclePageSql(page, sqlBuffer);
		}
		return sqlBuffer.toString();
	}

	private String getMysqlPageSql(PageHelper<?> page, StringBuffer sqlBuffer) {
		int beginPage = (page.getPage() - 1) * page.getRows();
		sqlBuffer.append(" limit ").append(beginPage).append(",").append(page.getRows());
		return sqlBuffer.toString();
	}

	private String getOraclePageSql(PageHelper<?> page, StringBuffer StringBuffer) {
		int offset = (page.getPage() - 1) * page.getRows() + 1;
		StringBuffer.insert(0, "select u.*, rownum r from (").append(") u where rownum < ")
				.append(offset + page.getRows());
		StringBuffer.insert(0, "select * from (").append(") where r >= ").append(offset);
		return StringBuffer.toString();
	}

	private void setTotalRecord(PageHelper<?> page, MappedStatement mappedStatement, Connection connection) {

		BoundSql boundSql = mappedStatement.getBoundSql(page);
		String sql = boundSql.getSql();
		String countSql = this.getCountSql(sql);
		List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();
		BoundSql countBoundSql = new BoundSql(mappedStatement.getConfiguration(), countSql, parameterMappings, page);
		ParameterHandler parameterHandler = new DefaultParameterHandler(mappedStatement, page, countBoundSql);
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = connection.prepareStatement(countSql);
			parameterHandler.setParameters(pstmt);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				int totalRecord = rs.getInt(1);
				page.setTotal(totalRecord);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	private String getCountSql(String sql) {
		return StringUtils.subFrom(sql);
	}
	
	public static class ReflectUtil {
		public static Object getFieldValue(Object obj, String fieldName) {
			Object result = null;
			Field field = ReflectUtil.getField(obj, fieldName);
			if (field != null) {
				field.setAccessible(true);
				try {
					result = field.get(obj);
				} catch (IllegalArgumentException e) {

					e.printStackTrace();
				} catch (IllegalAccessException e) {

					e.printStackTrace();
				}
			}
			return result;
		}

		private static Field getField(Object obj, String fieldName) {
			Field field = null;
			for (Class<?> clazz = obj.getClass(); clazz != Object.class; clazz = clazz.getSuperclass()) {
				try {
					field = clazz.getDeclaredField(fieldName);
					break;
				} catch (NoSuchFieldException e) {
				}
			}
			return field;
		}

		public static void setFieldValue(Object obj, String fieldName, Object fieldValue) {
			Field field = ReflectUtil.getField(obj, fieldName);
			if (field != null) {
				try {
					field.setAccessible(true);
					field.set(obj, fieldValue);
				} catch (IllegalArgumentException e) {

					e.printStackTrace();
				} catch (IllegalAccessException e) {

					e.printStackTrace();
				}
			}
		}
	}

}

