package com.crush.test.spring.restemplate.test;

import com.crush.test.spring.restemplate.ClientHttpRequestInterceptorImpl;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.xml.Jaxb2RootElementHttpMessageConverter;
import org.springframework.http.converter.xml.MappingJackson2XmlHttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * Title: TODO
 * </p>
 * <p>
 * Description: TODO
 * </p>
 * <p>
 * Copyright: Copyright (c) 2017
 * </p>
 * <p>
 * Company: 客如云
 * </p>
 *
 * @author crush_lee
 * @date 2019/4/3
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)  //RANDOM_PORT 启动Tomcat
public class BaseTest {
    @LocalServerPort
    protected int port;

    protected String baseUrl() {
        return "http://localhost:" + port;
    }

    @Before
    public void init() {
        testRestTemplate = new TestRestTemplate(getRestTemplate());
        List<ClientHttpRequestInterceptor> interceptors = new ArrayList<>();
        interceptors.add(new ClientHttpRequestInterceptorImpl());
        testRestTemplate.getRestTemplate().getInterceptors().addAll(interceptors);
        List<HttpMessageConverter<?>> list = testRestTemplate.getRestTemplate().getMessageConverters();
        list = list.stream().filter(e -> !e.getClass().equals(MappingJackson2XmlHttpMessageConverter.class)).collect(Collectors.toList());
        list.add(new Jaxb2RootElementHttpMessageConverter());
        testRestTemplate.getRestTemplate().setMessageConverters(list);
        //默认需要注册使用jaxb的AnnotationIntrospector才能生效 https://github.com/FasterXML/jackson-module-jaxb-annotations
    }

    private RestTemplate getRestTemplate() {
        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
        requestFactory.setConnectTimeout(2000);
        requestFactory.setReadTimeout(300);
        RestTemplate restTemplate = new RestTemplate(requestFactory);
        return restTemplate;
    }

    protected TestRestTemplate testRestTemplate;
}
