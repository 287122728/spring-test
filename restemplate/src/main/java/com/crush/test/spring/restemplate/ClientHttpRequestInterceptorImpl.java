/**
 *
 */
package com.crush.test.spring.restemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;

import lombok.extern.slf4j.Slf4j;

/**
 * resttemplate 请求响应信息拦截器
 */
@Slf4j
public class ClientHttpRequestInterceptorImpl implements ClientHttpRequestInterceptor {

	/** aplication/json;charset=UTF-8 */
	private final MediaType application_json_utf8 = MediaType.parseMediaType("application/json;charset=UTF-8");

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * org.springframework.http.client.ClientHttpRequestInterceptor#intercept
	 * (org.springframework.http.HttpRequest, byte[],
	 * org.springframework.http.client.ClientHttpRequestExecution)
	 */
	@Override
	public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
		HttpHeaders headers = request.getHeaders();

		long start = System.currentTimeMillis();
		EnhancerClientHttpResponse resp = null;
		try {
			resp = new EnhancerClientHttpResponse(execution.execute(request, body));
		} finally {

			String respText = resp == null ? "" : resp.responseText();

			int status = resp == null ? 400 : resp.getRawStatusCode();

			log.info("请求地址: [{}], 请求头信息: [{}], 请求参数: [{}] => 响应码[{}], 返回结果: [{}]. 请求花费[{}]毫秒.",
					request.getURI().toString(),
					new ObjectMapper().writeValueAsString(headers.entrySet()),
					new String(body),
					status,
					respText,
					System.currentTimeMillis() - start);
		}

		return resp;
	}

}
