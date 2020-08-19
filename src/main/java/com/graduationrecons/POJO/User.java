package com.graduationrecons.POJO;


import lombok.Data;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Data
@ToString
public class User
{
    private String username;
    private String mm;
    private int permission;
    private int primary_key;
    private int pass;
    private String email;
    private String code;


}
