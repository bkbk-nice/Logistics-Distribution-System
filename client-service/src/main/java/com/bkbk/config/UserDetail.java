package com.bkbk.config;

import com.bkbk.entity.Client;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class UserDetail extends User {

    /**
     * 用户实体对象，要调取用户信息时直接获取这个实体对象
     */
    private Client client;

    public Client getCs() {
        return client;
    }

    public void setCs(Client client) {
        this.client = client;
    }

    public UserDetail(Client client, Collection<? extends GrantedAuthority> authorities) {
        // 必须调用父类的构造方法，以初始化用户名、密码、权限
        super(client.getName(), client.getPassword(), authorities);
        this.client = client;
    }
}
