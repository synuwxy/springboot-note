package com.synuwxy.springbootnote.mybatis.web.service.impl;

import com.synuwxy.springbootnote.mybatis.entity.generator.UserModel;
import com.synuwxy.springbootnote.mybatis.web.dao.generator.UserModelMapper;
import com.synuwxy.springbootnote.mybatis.web.service.GeneratorTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GeneratorTestServiceImpl implements GeneratorTestService {

    private final UserModelMapper userModelMapper;

    @Autowired
    public GeneratorTestServiceImpl(UserModelMapper userModelMapper) {
        this.userModelMapper = userModelMapper;
    }

    @Override
    public int intsert(UserModel userModel) {
        return userModelMapper.insert(userModel);
    }

    @Override
    public int delete(String id) {
        return userModelMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int update(UserModel userModel) {
        return userModelMapper.updateByPrimaryKey(userModel);
    }

    @Override
    public UserModel select(String id) {
        return userModelMapper.selectByPrimaryKey(id);
    }
}
