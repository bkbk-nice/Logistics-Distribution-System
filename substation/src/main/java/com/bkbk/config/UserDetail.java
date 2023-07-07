package com.bkbk.config;


import com.bkbk.entity.Substation;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class UserDetail extends User {

    public Substation getSubstation() {
        return substation;
    }

    public void setSubstation(Substation substation) {
        this.substation = substation;
    }

    /**
     * 我们自己的用户实体对象，要调取用户信息时直接获取这个实体对象。（这里我就不写get/set方法了）
     */
    private Substation substation;


    public UserDetail(Substation substation, Collection<? extends GrantedAuthority> authorities) {
        // 必须调用父类的构造方法，以初始化用户名、密码、权限
        super(substation.getName(), substation.getPassword(), authorities);
        this.substation = substation;
    }
}
