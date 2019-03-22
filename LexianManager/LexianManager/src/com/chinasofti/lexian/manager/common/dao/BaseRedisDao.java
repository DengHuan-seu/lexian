package com.chinasofti.lexian.manager.common.dao;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import com.alibaba.fastjson.JSON;
import com.chinasofti.lexian.manager.common.util.ApplicationContextUtil;
import com.chinasofti.lexian.manager.common.util.CommonUtil;

@Repository
public class BaseRedisDao implements InitializingBean {

	@SuppressWarnings("rawtypes")
	private RedisTemplate redisTemplate;

	public <K> Boolean existKey(final K key) {
		return (Boolean) redisTemplate.execute(new RedisCallback<Boolean>() {
			@Override
			public Boolean doInRedis(RedisConnection arg0) throws DataAccessException {
				return arg0.exists(CommonUtil.serialize(key));
			}

		});
	}

	public <K, V> V getObject(final K key) {
		if (key == null)
			return null;
		return (V) redisTemplate.execute(new RedisCallback<V>() {
			@Override
			public V doInRedis(RedisConnection arg0) throws DataAccessException {
				return (V) CommonUtil.unserialize(arg0.get(CommonUtil.serialize(key)));
			}

		});

	}

	public <K, V> V setObject(final K key, final V v) {
		return (V) redisTemplate.execute(new RedisCallback<V>() {
			@Override
			public V doInRedis(RedisConnection arg0) throws DataAccessException {
				arg0.set(CommonUtil.serialize(key), CommonUtil.serialize(v));
				return v;
			}

		});
	}

	public <K, V> V setExObject(final K key, final long seconds, final V v) {
		return (V) redisTemplate.execute(new RedisCallback<V>() {
			@Override
			public V doInRedis(RedisConnection arg0) throws DataAccessException {
				arg0.setEx(CommonUtil.serialize(key), seconds, CommonUtil.serialize(v));
				return v;
			}
		});

	}

	public <K> Long delete(final K key) {
		return (Long) redisTemplate.execute(new RedisCallback<Long>() {
			@Override
			public Long doInRedis(RedisConnection arg0) throws DataAccessException {
				return arg0.del(CommonUtil.serialize(key));
			}
		});

	}

	public <K> boolean expire(final K key, final long seconds) {
		Boolean issuccess = (Boolean) redisTemplate.execute(new RedisCallback<Boolean>() {
			@Override
			public Boolean doInRedis(RedisConnection arg0) throws DataAccessException {
				return arg0.expire(CommonUtil.serialize(key), seconds);
			}
		});
		return issuccess != null && issuccess;
	}

	public <K, V> Long lpush(final K key, final V v) {
		return (Long) redisTemplate.execute(new RedisCallback<Long>() {
			@Override
			public Long doInRedis(RedisConnection arg0) throws DataAccessException {
				Long line = arg0.lPush(CommonUtil.serialize(key), JSON.toJSONString(v).getBytes());
				arg0.save();
				return line;
			}
		});
	}

	public <K> Long len(final K key) {
		return (Long) redisTemplate.execute(new RedisCallback<Long>() {
			@Override
			public Long doInRedis(RedisConnection arg0) throws DataAccessException {
				Long line = arg0.lLen(CommonUtil.serialize(key));
				return line;
			}
		});
	}

	public <K, V> V lindex(final K key, final long index, final Class<V> clazz) {
		return (V) redisTemplate.execute(new RedisCallback<V>() {
			@Override
			public V doInRedis(RedisConnection arg0) throws DataAccessException {
				return (V) JSON.parseObject(new String(arg0.lIndex(CommonUtil.serialize(key), index)), clazz);
			}
		});
	}

	public <K, V> void lset(final K key, final long index, final V v) {
		redisTemplate.execute(new RedisCallback<Object>() {
			@Override
			public Object doInRedis(RedisConnection arg0) throws DataAccessException {
			
				arg0.lSet(CommonUtil.serialize(key), index, JSON.toJSONString(v).getBytes());
				arg0.save();
				return null;
			}
		});
	}

	public <K> void lTrim(final K key, final long index) {
		redisTemplate.execute(new RedisCallback<Object>() {
			@Override
			public Object doInRedis(RedisConnection arg0) throws DataAccessException {
				arg0.lTrim(CommonUtil.serialize(key), index, index - 1);
				arg0.save();
				return null;
			}
		});
	}

	public RedisTemplate getRedisTemplate() {
		return redisTemplate;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		redisTemplate = ApplicationContextUtil.getBean(RedisTemplate.class);
	}
}
