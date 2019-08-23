package com.synuwxy.springbootnote.mybatis.web.service;

import com.synuwxy.springbootnote.mybatis.entity.generator.UserModel;

public interface GeneratorTestService {

    /**
     * 增
     * @param userModel user
     * @return 影响的行数
     */
    int intsert(UserModel userModel);

    /**
     * 删
     * @param id user表id
     * @return 影响的行数
     */
    int delete(String id);

    /**
     * 改
     * @param userModel user
     * @return 影响的行数
     */
    int update(UserModel userModel);

    /**
     * 根据ID查询
     * @param id user表id
     * @return user
     */
    UserModel select(String id);
}