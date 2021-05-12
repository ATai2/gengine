package com.atai.module.gengine.utils;

import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

class EngineExcutorTest {


    @Test
    void invoke() {
    }

    @Test
    void invokeEngine() {
    }

    @Test
    void invokeShell() {
    }

    @Test
    void invokeFIle() {
        ResourceLoader resourceLoader = new DefaultResourceLoader();
        Resource resource = resourceLoader.getResource("classpath:static/scripts/hello.groovy");
        try {
            File file = resource.getFile();
            Object o = EngineExcutor.runFileWithCache(file, new Object[]{});
            int i = 2;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test
    void invokeFIleshell() {
        ResourceLoader resourceLoader = new DefaultResourceLoader();
        Resource resource = resourceLoader.getResource("classpath:static/scripts/helloarg.groovy");
        try {
            File file = resource.getFile();
            Map<String,Object> map=new HashMap<>();
            map.put("name", "jack");
            Object o = EngineExcutor.invokeShelFilelWithCache(file, map);
            int i = 2;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}