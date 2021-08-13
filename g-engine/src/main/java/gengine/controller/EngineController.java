package gengine.controller;//package com.atai.module.gengine.controller;
//
//import com.atai.gcommon.utils.RequestResult;
//import com.atai.module.gengine.model.ScriptEntity;
//import com.atai.module.gengine.service.EngineService;
//import com.atai.module.gengine.utils.EngineExcutor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.core.io.DefaultResourceLoader;
//import org.springframework.core.io.Resource;
//import org.springframework.core.io.ResourceLoader;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.io.File;
//import java.util.HashMap;
//import java.util.Map;
//
//@Slf4j
//@RestController("/engine")
//public class EngineController {
//
//
//    @Autowired
//    private EngineService engineService;
//
//    @GetMapping(value = "/list")
//    public RequestResult<ScriptEntity> getScriptList(@RequestParam int times) {
//        log.info("param:" + times);
//        for (int i = 0; i < times; i++) {
//            Object[] param = {8, 7};
//            System.out.println("times:" + i);
//            try {
//                log.info(String.valueOf( EngineExcutor.invokeClearCache("def cal(int a, int b){\n" +
//                        "    return a+b\n" +
//                        "}", "cal", param).toString()));
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//
//
//        return RequestResult.withSuccess(null);
//    }
//    @GetMapping(value = "/list2")
//    public RequestResult<ScriptEntity> getScriptList2(@RequestParam int times) {
//
//        log.info("param:" + times);
//        log.info("threadname:" + Thread.currentThread().getName());
//        ResourceLoader resourceLoader = new DefaultResourceLoader();
//        Resource resource = resourceLoader.getResource("classpath:static/scripts/helloarg.groovy");
//        try {
//            File file = resource.getFile();
//            Map<String,Object> map=new HashMap<>();
//            map.put("name", "jack");
//            Object o = EngineExcutor.invokeShelFilelWithCache(file, map);
//            int i = 2;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//
//        return RequestResult.withSuccess(null);
//    }
//
//}
