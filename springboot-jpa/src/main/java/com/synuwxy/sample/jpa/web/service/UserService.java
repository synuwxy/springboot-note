package com.synuwxy.sample.jpa.web.service;


import com.synuwxy.sample.jpa.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserService extends CrudRepository<User, String> {
}
