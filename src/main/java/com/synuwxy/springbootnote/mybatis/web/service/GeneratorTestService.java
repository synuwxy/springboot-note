package com.synuwxy.springbootnote.mybatis.web.service;

import com.synuwxy.springbootnote.mybatis.entity.generator.UserModel;

public interface GeneratorTestService {

    int intsert(UserModel userModel);

    int delete(String id);

    int update(UserModel userModel);

    UserModel select(String id);
}
