package com.bkbk.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bkbk.config.UserDetail;
import com.bkbk.entity.Client;
import com.bkbk.entity.vo.ClientVo;
import com.bkbk.mapper.SecureMapper;
import com.bkbk.service.SecureService;
import com.bkbk.util.JwtUtil;
import com.bkbk.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;


@Service
public class SecureServiceImpl implements SecureService  , UserDetailsService {


    @Autowired
    private SecureMapper secureMapper;



    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public ResultVo login(String name, String password) {
        //登录校验

        QueryWrapper<Client> queryWrapper = new QueryWrapper<Client>();
        queryWrapper.eq("name",name);

        Client client = secureMapper.selectOne(queryWrapper);
        // 若没查询到一定要抛出该异常，这样才能被Spring Security的错误处理器处理

        if (client == null  ||  !passwordEncoder.matches(password, client.getPassword()) ){
            return ResultVo.fail("账号密码错误");
        }

        ClientVo csvo = new ClientVo();

        csvo.setId(client.getId());
        csvo.setName(client.getName());
        csvo.setToken(JwtUtil.generate(client.getId().toString()));

        return ResultVo.success(csvo);
    }

    @Override
    public ResultVo register(Client client) {
        // 从数据库中查询出用户实体对象
        QueryWrapper<Client> queryWrapper = new QueryWrapper<Client>();
        queryWrapper.eq("name",client.getName());

        Client client1 = secureMapper.selectOne(queryWrapper);
        // 若没查询到一定要抛出该异常，这样才能被Spring Security的错误处理器处理
        if (client1 != null) {
            return ResultVo.fail("已存在此客户");
        }

        Date now = new Date();


        client.setCreateTime(now);
        client.setUpdateTime(now);


        return ResultVo.success(secureMapper.insert(client));
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        System.out.println("exe loaduserbyusername");

        // 从数据库中查询出用户实体对象
        QueryWrapper<Client> queryWrapper = new QueryWrapper<Client>();
        queryWrapper.eq("id",Integer.parseInt(s));

        Client client = secureMapper.selectOne(queryWrapper);
        // 若没查询到一定要抛出该异常，这样才能被Spring Security的错误处理器处理
        if (client == null) {
            throw new UsernameNotFoundException("没有找到该用户");
        }

        // 走到这代表查询到了实体对象，那就返回我们自定义的UserDetail对象（这里权限暂时放个空集合，后面我会讲解）


        Set<GrantedAuthority> dbAuthsSet = new HashSet<>();
        //增加一个权限
        dbAuthsSet.add(new SimpleGrantedAuthority("ROLE_client"));

        return new UserDetail(client,dbAuthsSet);
    }




}
