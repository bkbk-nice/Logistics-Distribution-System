package com.bkbk.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.bkbk.config.UserDetail;
import com.bkbk.entity.Cs;
import com.bkbk.entity.vo.CsVo;
import com.bkbk.mapper.SecureMapper;
import com.bkbk.service.SecureService;
import com.bkbk.util.JwtUtil;
import com.bkbk.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class SecureServiceImpl implements SecureService  , UserDetailsService {


    @Autowired
    private SecureMapper secureMapper;



    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public ResultVo login(String csName, String csPassword) {
        //登录校验

        QueryWrapper<Cs> queryWrapper = new QueryWrapper<Cs>();
        queryWrapper.eq("name",csName);

        Cs cs = secureMapper.selectOne(queryWrapper);
        // 若没查询到一定要抛出该异常，这样才能被Spring Security的错误处理器处理

        if (cs == null  ||  !passwordEncoder.matches(csPassword, cs.getPassword()) ){
            return ResultVo.fail("账号密码错误");
        }

        CsVo csvo = new CsVo();
        csvo.setId(cs.getId());
        csvo.setName(cs.getName());
        csvo.setToken(JwtUtil.generate(cs.getId().toString()));



        return ResultVo.success(csvo);
    }

    @Override
    public ResultVo register(Cs cs) {
        // 从数据库中查询出用户实体对象
        QueryWrapper<Cs> queryWrapper = new QueryWrapper<Cs>();
        queryWrapper.eq("name",cs.getName());

        Cs cs1 = secureMapper.selectOne(queryWrapper);
        // 若没查询到一定要抛出该异常，这样才能被Spring Security的错误处理器处理
        if (cs1 != null) {
            return ResultVo.fail("已存在此客服");
        }
        Date now = new Date();
        cs.setCreateTime(now);
        cs.setUpdateTime(now);


        return ResultVo.success(secureMapper.insert(cs));
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {


        // 从数据库中查询出用户实体对象
        QueryWrapper<Cs> queryWrapper = new QueryWrapper<Cs>();
        queryWrapper.eq("id",Integer.parseInt(s));

        Cs cs = secureMapper.selectOne(queryWrapper);
        // 若没查询到一定要抛出该异常，这样才能被Spring Security的错误处理器处理
        if (cs == null) {
            throw new UsernameNotFoundException("没有找到该用户");
        }

        // 走到这代表查询到了实体对象，那就返回我们自定义的UserDetail对象（这里权限暂时放个空集合，后面我会讲解）
        return new UserDetail(cs, Collections.emptyList());
    }




}
