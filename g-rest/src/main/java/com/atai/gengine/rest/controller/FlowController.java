package com.atai.gengine.rest.controller;

import com.atai.gcommon.utils.RequestResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FlowController {

    @GetMapping("/list")
    public RequestResult getProcessList() {
        return RequestResult.withSuccess(null);
    }


}
