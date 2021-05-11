package com.atai.module.gengine.controller;

import com.atai.module.gengine.model.ScriptEntity;
import com.atai.module.gengine.service.EngineService;
import com.atai.module.gengine.utils.EngineExcutor;
import com.atai.module.gengine.utils.RequestResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController("/engine")
public class EngineController {


    @Autowired
    private EngineService engineService;

    @GetMapping(value = "/list")
    public RequestResult<ScriptEntity> getScriptList(@RequestParam int times) {
        log.info("param:" + times);
        for (int i = 0; i < times; i++) {
            Object[] param = {8, 7};
            System.out.println("times:" + i);
            try {
                log.info(String.valueOf( EngineExcutor.invokeEngine("def cal(int a, int b){\n" +
                        "    return a+b\n" +
                        "}", "cal", param).toString()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


        return RequestResult.withSuccess(null);
    }

}
