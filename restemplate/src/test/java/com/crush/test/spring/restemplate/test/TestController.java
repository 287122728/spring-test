package com.crush.test.spring.restemplate.test;

import com.crush.test.spring.restemplate.PostFormDomain;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.Assert;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

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
    @Test
    public void get(){
        String name="123";
        String url=baseUrl()+"/test/restemplate/get?name="+name;
        ResponseEntity<String> result=testRestTemplate.getForEntity(url,String.class);
        Assert.assertTrue(result.getBody().equals(name));
    }
    @Test
    public void getForUrlVariable(){
        String name="123";
        String url=baseUrl()+"/test/restemplate/get?name={name}";
        ResponseEntity<String> result=testRestTemplate.getForEntity(url,String.class,name);
        Assert.assertTrue(result.getBody().equals(name));
    }
    @Test
    public void getForMap(){
        String name="123";
        String url=baseUrl()+"/test/restemplate/get?name={name}";
        Map<String,String> map=new HashMap<>();
        map.put("name",name);
        ResponseEntity<String> result=testRestTemplate.getForEntity(url,String.class,map);
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


        ResponseEntity<PostFormDomain> result=testRestTemplate.postForEntity(url,requestEntity,PostFormDomain.class);
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


        ResponseEntity<PostFormDomain> result=testRestTemplate.postForEntity(url,requestEntity,PostFormDomain.class);
        Assert.assertTrue(result.getBody().equals(postFormDomain));
    }
    @Test
    public void postJson() throws JsonProcessingException {
        String name="123";
        String desc="desc";
        String url=baseUrl()+"/test/restemplate/post/json";

        PostFormDomain postFormDomain=new PostFormDomain();
        postFormDomain.setName(name);
        postFormDomain.setDesc(desc);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);

        HttpEntity<PostFormDomain> requestEntity = new HttpEntity<>(postFormDomain, headers);

        ResponseEntity<PostFormDomain> result=testRestTemplate.postForEntity(url,requestEntity,PostFormDomain.class);
        log.info(new ObjectMapper().writeValueAsString(result.getBody()));
        Assert.assertTrue(result.getBody().equals(postFormDomain));
    }
    @Test
    public void getJson() throws JsonProcessingException {
        String name="123";
        String desc="desc";
        String url=baseUrl()+"/test/restemplate/get/json";

        PostFormDomain postFormDomain=new PostFormDomain();
        postFormDomain.setName(name);
        postFormDomain.setDesc(desc);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);


        ResponseEntity<PostFormDomain> result=testRestTemplate.getForEntity(url,PostFormDomain.class);
        log.info(new ObjectMapper().writeValueAsString(result.getBody()));
        Assert.assertTrue(result.getBody().equals(postFormDomain));
    }
    @Test
    public void postXmlAndRespJson() throws JsonProcessingException {
        String name="123";
        String desc="desc";
        String url=baseUrl()+"/test/restemplate/post/xml/resp/json";

        PostFormDomain postFormDomain=new PostFormDomain();
        postFormDomain.setName(name);
        postFormDomain.setDesc(desc);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_XML);

        HttpEntity<PostFormDomain> requestEntity = new HttpEntity<>(postFormDomain, headers);

        ResponseEntity<PostFormDomain> result=testRestTemplate.postForEntity(url,requestEntity,PostFormDomain.class);
        log.info(new ObjectMapper().writeValueAsString(result.getBody()));
        Assert.assertTrue(result.getBody().equals(postFormDomain));
    }
    @Test
    public void postXmlAndRespXml() throws JsonProcessingException {
        String name="123";
        String desc="desc";
        String url=baseUrl()+"/test/restemplate/post/xml/resp/xml";

        PostFormDomain postFormDomain=new PostFormDomain();
        postFormDomain.setName(name);
        postFormDomain.setDesc(desc);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_XML);

        HttpEntity<PostFormDomain> requestEntity = new HttpEntity<>(postFormDomain, headers);

        ResponseEntity<PostFormDomain> result=testRestTemplate.postForEntity(url,requestEntity,PostFormDomain.class);
        log.info(new ObjectMapper().writeValueAsString(result.getBody()));
        Assert.assertTrue(result.getBody().equals(postFormDomain));
    }
    @Test
    public void postXmlStr() throws JsonProcessingException {
        String name="123";
        String desc="desc";
        String url=baseUrl()+"/test/restemplate/post/xml/str";

        PostFormDomain postFormDomain=new PostFormDomain();
        postFormDomain.setName(name);
        postFormDomain.setDesc(desc);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_XML);
        headers.setAccept(new ArrayList<MediaType>(){
            //如果produces 没有配置，需要添加accept，避免无法解析，使用其他的message converter处理
            { add(MediaType.APPLICATION_XML);}
        });

        HttpEntity<PostFormDomain> requestEntity = new HttpEntity<>(postFormDomain, headers);

        ResponseEntity<PostFormDomain> result=testRestTemplate.postForEntity(url,requestEntity,PostFormDomain.class);
        log.info(new ObjectMapper().writeValueAsString(result.getBody()));
        Assert.assertTrue(result.getBody().equals(postFormDomain));
    }
    @Test
    public void test1() throws JsonProcessingException {
        String name="123";
        String desc="desc";
        String url=baseUrl()+"/test/restemplate/post/xml/str";

        PostFormDomain postFormDomain=new PostFormDomain();
        postFormDomain.setName(name);
        postFormDomain.setDesc(desc);

        //log.info(new XmlMapper().configure(SerializationFeature.WRAP_ROOT_VALUE,true).writeValueAsString(postFormDomain));
    }
    @Test
    public void getTimeout() throws InterruptedException {
        int tmp=2;
        int sleepMilliSecond=500;
        AtomicInteger atomicInteger=new AtomicInteger();
        Map<Integer,AtomicInteger> map=new ConcurrentHashMap<>();

        ExecutorService threadPoolExecutor= Executors.newFixedThreadPool(tmp);
        CountDownLatch countDownLatch=new CountDownLatch(tmp);
        for(int i=0;i<tmp;i++){
            threadPoolExecutor.execute(() -> {
                int count=10;
                while(count>0){
                    try{
                        String name="123";
                        String url="https://oncheckout.keruyun.com//checkout-order-upload-wechat/test/sleep?sleepTime="+sleepMilliSecond;
                        ResponseEntity<String> result=testRestTemplate.getForEntity(url,String.class);
                        Integer httpCode=result.getStatusCodeValue();
                        incrementAndGet(map,httpCode);
                    }catch(Exception e){
                        atomicInteger.decrementAndGet();
                    }
                    finally {
                        count--;
                    }

                }
                countDownLatch.countDown();

            });
        }
        countDownLatch.await();
        System.out.print("errorCount:"+atomicInteger.get());
        System.out.println("map:"+map);
    }
    private synchronized void incrementAndGet(Map<Integer,AtomicInteger> map,Integer httpCode){
        if(map.containsKey(httpCode)){
            map.get(httpCode).incrementAndGet();
        }else{
            map.put(httpCode,new AtomicInteger());
            map.get(httpCode).incrementAndGet();
        }
    }
}
