package com.bkbk.config;

import com.bkbk.entity.Cs;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class UserDetail extends User {

    /**
     * 我们自己的用户实体对象，要调取用户信息时直接获取这个实体对象。（这里我就不写get/set方法了）
     */
    private Cs cs;

    public Cs getCs() {
        return cs;
    }

    public void setCs(Cs cs) {
        this.cs = cs;
    }

    public UserDetail(Cs cs, Collection<? extends GrantedAuthority> authorities) {
        // 必须调用父类的构造方法，以初始化用户名、密码、权限
        super(cs.getName(), cs.getPassword(), authorities);
        this.cs = cs;
    }
}
