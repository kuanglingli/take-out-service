package com.itaem.crazy.shirodemo.project.bean;

import lombok.Data;

import java.io.Serializable;

@Data
public class PasswordUp implements Serializable {

    private static final long serialVersionUID = -3027703805103358324L;

    private String oldPassword;

    private String password;
}
