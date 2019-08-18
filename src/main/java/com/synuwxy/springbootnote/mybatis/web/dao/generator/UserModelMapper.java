package com.synuwxy.springbootnote.mybatis.web.dao.generator;

import com.synuwxy.springbootnote.mybatis.entity.generator.UserModel;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface UserModelMapper {
    int deleteByPrimaryKey(String id);

    int insert(UserModel record);

    int insertSelective(UserModel record);

    UserModel selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(UserModel record);

    int updateByPrimaryKey(UserModel record);
}