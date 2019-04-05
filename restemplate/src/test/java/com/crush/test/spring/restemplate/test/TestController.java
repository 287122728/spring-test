package com.crush.test.spring.restemplate.test;

import com.crush.test.spring.restemplate.PostFormDomain;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Base64Utils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;

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
@Slf4j
public class TestController extends BaseTest {
    @Before
    public void init(){

    }
    @Test
    public void get(){
        String name="123";
        String url=baseUrl()+"/test/restemplate/get?name="+name;
        ResponseEntity<String> result=testRestTemplate.getForEntity(url,String.class);
        Assert.assertTrue(result.getBody().equals(name));
    }
    @Test
    public void getForEntityWithMap(){
        String name="123";
        String desc="desc";
        String url=baseUrl()+"/test/restemplate/get/json/result?name={name}&desc={desc}";
        PostFormDomain postFormDomain=new PostFormDomain();
        postFormDomain.setName(name);
        postFormDomain.setDesc(desc);
        Map<String,String> map=new HashMap<>();
        map.put("name",name);
        map.put("desc",desc);
        ResponseEntity<PostFormDomain> result=testRestTemplate.getForEntity(url,PostFormDomain.class,map);
        Assert.assertTrue(result.getBody().equals(postFormDomain));
    }
    @Test
    public void getForEntityWithUrlVariable(){
        String name="123";
        String desc="desc";
        String url=baseUrl()+"/test/restemplate/get/json/result?name={name}&desc={desc}";
        PostFormDomain postFormDomain=new PostFormDomain();
        postFormDomain.setName(name);
        postFormDomain.setDesc(desc);
        ResponseEntity<PostFormDomain> result=testRestTemplate.getForEntity(url,PostFormDomain.class,name,desc);
        Assert.assertTrue(result.getBody().equals(postFormDomain));
    }
    @Test
    public void postForm4MultiValueMap(){
        String name="123";
        String desc="desc";
        String url=baseUrl()+"/test/restemplate/post/form";

        PostFormDomain postFormDomain=new PostFormDomain();
        postFormDomain.setName(name);
        postFormDomain.setDesc(desc);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        MultiValueMap<String, String> params= new LinkedMultiValueMap<>();
        params.add("name", name);
        params.add("desc", desc);
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(params, headers);

        ResponseEntity<PostFormDomain> result=testRestTemplate.postForEntity(url,requestEntity,PostFormDomain.class);
        Assert.assertTrue(result.getBody().equals(postFormDomain));
    }
    @Test
    public void postForm4Param() throws JsonProcessingException {
        String name="123";
        String desc="desc";
        String url=baseUrl()+"/test/restemplate/post/form?name={name}&desc={desc}";

        PostFormDomain postFormDomain=new PostFormDomain();
        postFormDomain.setName(name);
        postFormDomain.setDesc(desc);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        //MultiValueMap<String, String> params= new LinkedMultiValueMap<>();
        //params.add("name", name);
        //params.add("desc", desc);
        //HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(null, headers);

        Map<String,String> map=new HashMap<>();
        map.put("name",name);
        map.put("desc",desc);

        //1、http headers
        ResponseEntity<PostFormDomain> result=testRestTemplate.postForEntity(url,headers,PostFormDomain.class,map);
        //2、request entity
        //ResponseEntity<PostFormDomain> result=testRestTemplate.postForEntity(url,requestEntity,PostFormDomain.class,map);
        log.info(new ObjectMapper().writeValueAsString(result.getBody()));
        Assert.assertTrue(result.getBody().equals(postFormDomain));
    }
    @Test
    public void postForm4Object() throws JsonProcessingException {
        //TODO
        //不支持，需要自定义 HttpMessageConverter for  application/x-www-form-urlencoded
        String name="123";
        String desc="desc";
        String url=baseUrl()+"/test/restemplate/post/form";

        PostFormDomain postFormDomain=new PostFormDomain();
        postFormDomain.setName(name);
        postFormDomain.setDesc(desc);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        HttpEntity<PostFormDomain> requestEntity = new HttpEntity<>(postFormDomain, headers);

        ResponseEntity<PostFormDomain> result=testRestTemplate.postForEntity(url,requestEntity,PostFormDomain.class);
        log.info(new ObjectMapper().writeValueAsString(result.getBody()));
        Assert.assertTrue(result.getBody().equals(postFormDomain));
    }
    @Test
    public void postFormWithBase64() throws JsonProcessingException {
        String name="123";
        String desc="memo=屠龙刀&requestId=201903301922060195710391&customerNo=289630000007&curType=1&accountNo=6214830287931058&businessType=20101&transAmt=2.00&bankCode=305584018176&sign=1D646058DEE97A1C56155384E56C920E&accountName=羊绍诚&reqTime=20190330&accountType=0&payType=3&transCode=006";
        PostFormDomain postFormDomain=new PostFormDomain();
        postFormDomain.setName(name);
        postFormDomain.setDesc(desc);

        desc=Base64Utils.encodeToString(new ObjectMapper().writeValueAsString(postFormDomain).getBytes());
        postFormDomain.setDesc(desc);

        String url=baseUrl()+"/test/restemplate/post/form/base64";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String,String> map=new LinkedMultiValueMap<>();
        map.add("name",postFormDomain.getName());
        map.add("desc",postFormDomain.getDesc());

        HttpEntity<MultiValueMap<String,String>> requestEntity = new HttpEntity<>(map, headers);


        ResponseEntity<PostFormDomain> result=testRestTemplate.postForEntity(url,requestEntity,PostFormDomain.class,map);
        Assert.assertTrue(result.getBody().equals(postFormDomain));
    }
    @Test
    public void postFormWithBase64AndUriEncode() throws JsonProcessingException, UnsupportedEncodingException {
        String name="123";
        String desc="memo=屠龙刀&requestId=201903301922060195710391&customerNo=289630000007&curType=1&accountNo=6214830287931058&businessType=20101&transAmt=2.00&bankCode=305584018176&sign=1D646058DEE97A1C56155384E56C920E&accountName=羊绍诚&reqTime=20190330&accountType=0&payType=3&transCode=006";
        PostFormDomain postFormDomain=new PostFormDomain();
        postFormDomain.setName(name);
        postFormDomain.setDesc(desc);

        desc=Base64Utils.encodeToString(new ObjectMapper().writeValueAsString(postFormDomain).getBytes());
        desc=URLEncoder.encode(desc,"UTF-8");
        postFormDomain.setDesc(desc);

        String url=baseUrl()+"/test/restemplate/post/form/base64";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String,String> map=new LinkedMultiValueMap<>();
        map.add("name",postFormDomain.getName());
        map.add("desc",postFormDomain.getDesc());

        HttpEntity<MultiValueMap<String,String>> requestEntity = new HttpEntity<>(map, headers);


        ResponseEntity<PostFormDomain> result=testRestTemplate.postForEntity(url,requestEntity,PostFormDomain.class,map);
        Assert.assertTrue(result.getBody().equals(postFormDomain));
    }
}
