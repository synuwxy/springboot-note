package com.synuwxy.springbootnote.jpa.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Data
@AllArgsConstructor
@Entity
@Table(name = "user")
public class User {

    @Id
    private String id;

    private String name;

    private String username;

    private String password;

    public User() {}
}
