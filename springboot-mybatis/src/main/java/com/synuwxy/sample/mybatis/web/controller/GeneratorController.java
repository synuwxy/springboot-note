package com.synuwxy.sample.mybatis.web.controller;

import com.synuwxy.sample.mybatis.common.ResultCode;
import com.synuwxy.sample.mybatis.common.ResultObject;
import com.synuwxy.sample.mybatis.entity.generator.UserModel;
import com.synuwxy.sample.mybatis.web.service.GeneratorTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author wxy
 * create by 2019.08.24
 * <p>
 * desc:
 * mybatis-generator controller
 */
@RestController
@RequestMapping("/generator")
public class GeneratorController {

    private final GeneratorTestService generatorTestService;

    @Autowired
    public GeneratorController(GeneratorTestService generatorTestService) {
        this.generatorTestService = generatorTestService;
    }

    @GetMapping("/select")
    public Map<String, Object> selectUser(@RequestParam("id") String id) {
        return ResultObject.newInstance(ResultCode.SUCCESS, generatorTestService.select(id));
    }

    @PutMapping("/update")
    public Map<String, Object> updateUser(@RequestBody UserModel userModel) {
        return ResultObject.newInstance(ResultCode.SUCCESS, generatorTestService.update(userModel));
    }
}
