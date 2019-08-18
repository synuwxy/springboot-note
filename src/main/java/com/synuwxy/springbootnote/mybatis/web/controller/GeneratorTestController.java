package com.synuwxy.springbootnote.mybatis.web.controller;

import com.synuwxy.springbootnote.common.ResultCode;
import com.synuwxy.springbootnote.common.ResultObject;
import com.synuwxy.springbootnote.mybatis.web.service.GeneratorTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/generator")
public class GeneratorTestController {

    private final GeneratorTestService generatorTestService;

    @Autowired
    public GeneratorTestController(GeneratorTestService generatorTestService) {
        this.generatorTestService = generatorTestService;
    }

    @GetMapping("/select")
    public Map<String, Object> selectUser(@RequestParam("id") String id) {
        return ResultObject.newInstance(ResultCode.SUCCESS,generatorTestService.select(id));
    }
}
