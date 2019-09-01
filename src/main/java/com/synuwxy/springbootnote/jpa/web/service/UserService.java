package com.synuwxy.springbootnote.jpa.web.service;

import com.synuwxy.springbootnote.jpa.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserService extends CrudRepository<User,String> {
}
