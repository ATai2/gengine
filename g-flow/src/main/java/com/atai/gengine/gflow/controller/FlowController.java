package com.atai.gengine.gflow.controller;

import com.atai.gcommon.utils.RequestResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FlowController {

    @GetMapping("/list")
    public RequestResult getProcessList() {


        return null;
    }
}
