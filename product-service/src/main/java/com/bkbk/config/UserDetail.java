package com.bkbk.config;


import com.bkbk.entity.Center;
import com.bkbk.entity.Substation;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class UserDetail extends User {

    /**
     * 我们自己的用户实体对象，要调取用户信息时直接获取这个实体对象
     */
    private Center center;


    public UserDetail(Center center, Collection<? extends GrantedAuthority> authorities) {
        // 必须调用父类的构造方法，以初始化用户名、密码、权限
        super(center.getName(), center.getPassword(), authorities);
        this.center = center;
    }
}
