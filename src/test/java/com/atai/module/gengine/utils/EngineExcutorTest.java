package com.atai.module.gengine.utils;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.function.Consumer;
import java.util.stream.Stream;

@Slf4j
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
    @Test
    void invokeFIleshellMultiThread() {

        List<Integer> list=new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(i);
        }
        List<Runnable> runnableList=new ArrayList<>();
        list.stream().forEach(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        log.info(integer+"");
                        ResourceLoader resourceLoader = new DefaultResourceLoader();
                        Resource resource = resourceLoader.getResource("classpath:static/scripts/helloarg.groovy");
                        try {
                            File file = resource.getFile();
                            Map<String,Object> map=new HashMap<>();
                            map.put("name", Thread.currentThread().getName());
                            Object o = EngineExcutor.invokeShelFilelWithCache(file, map);
                            int i = 2;
                            log.info("SSS"+o.toString());
                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
        });



    }
}