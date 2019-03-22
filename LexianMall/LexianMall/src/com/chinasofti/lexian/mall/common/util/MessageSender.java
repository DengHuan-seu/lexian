
package com.chinasofti.lexian.mall.common.util;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.chinasofti.lexian.mall.validation.constant.ValidateConstant;

// 短信发送器
public class MessageSender {
	private final Logger logger = Logger.getLogger(getClass());
	// 用户账号
	private String name;
	// 发送内容的模板
	private String contentTemplate;
	// 密码
	private String pwd;
	// 签名
	private String sign;
	// 发送请求的url地址
	private String url;
	// 短信类型
	private String type;

	private RestTemplate restTemplate;

	public String getContentTemplate() {
		return contentTemplate;
	}

	public ResultHelper sendMessageNotUseTemplate(final String phone, String... contents) {
		return sendMessage(phone, false,contents);
	}
	
	public ResultHelper sendMessage(final String phone, boolean useTemplate, String... contents) {
		if (StringUtils.isNullOrEmpty(phone) || null == contents || contents.length == 0) {
			return null;
		}
		int index = 0;
		String template = null;
		if (StringUtils.isNullOrEmpty(contentTemplate)||!useTemplate) {
			template = CommonUtil.joinList(",", contents);
		} else {
			template = new String(contentTemplate);
			for (String content : contents) {
				template = template.replaceAll("#" + (index++) + "#", content == null ? "" : content);
			}
		}
		
		ResponseEntity<String> res = null;
		try {
			StringBuilder sBuilder = new StringBuilder(url);
			sBuilder.append("?content=").append(template).append("&name=").append(name).append("&pwd=").append(pwd)
					.append("&type=").append(type).append("&sign=").append(sign).append("&mobile=").append(phone);
			res = restTemplate.postForEntity(sBuilder.toString(), null, String.class);
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e.getCause());
		}
		logger.info(res);
		if (res == null || (res.getStatusCode() != HttpStatus.OK && res.getStatusCode() != HttpStatus.NOT_MODIFIED)) {
			return new ResultHelper(Constant.failed_code, ValidateConstant.failed);
		}
		
		String body = res.getBody();
		if (logger.isDebugEnabled()) {
			logger.debug(body);
		}
		if (body == null || body.length() == 0) {
			return new ResultHelper(Constant.failed_code, ValidateConstant.failed);
		}
		String[] str = body.split(",");
		if (str == null || str.length < 2) {
			return new ResultHelper(Constant.failed_code, ValidateConstant.failed);
		}
		if (!"0".equals(str[0])) {
			return new ResultHelper(Constant.failed_code, str[1]);
		}
		return new ResultHelper(Constant.success_code, ValidateConstant.success);
	}

	public ResultHelper sendMessage(String phone, String content) {
		return sendMessage(phone,false, new String[] { content });
	}
	
	public void setContentTemplate(String contentTemplate) {
		this.contentTemplate = contentTemplate;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public void setRestTemplate(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}
