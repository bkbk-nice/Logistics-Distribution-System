package com.bkbk.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.bkbk.config.UserDetail;
import com.bkbk.entity.Center;
import com.bkbk.entity.Substation;
import com.bkbk.mapper.SecureMapper;

import com.bkbk.service.SecureService;
import com.bkbk.util.JwtUtil;
import com.bkbk.util.JwtUtilForCenter;
import com.bkbk.vo.ResultVo;
import com.bkbk.vo.CenterVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Date;


@Service
public class SecureServiceImpl implements SecureService, UserDetailsService {


    @Autowired
    private SecureMapper secureMapper;



    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public ResultVo login(String name, String password) {
        //登录校验

        QueryWrapper<Center> queryWrapper = new QueryWrapper<Center>();
        queryWrapper.eq("name",name);

        Center center = secureMapper.selectOne(queryWrapper);
        // 若没查询到一定要抛出该异常，这样才能被Spring Security的错误处理器处理

        if (center == null  ||  !passwordEncoder.matches(password, center.getPassword()) ){
            return ResultVo.fail("账号密码错误");
        }

        CenterVo centerVo = new CenterVo();
        centerVo.setId(center.getId());
        centerVo.setName(center.getName());
        centerVo.setToken(JwtUtilForCenter.generate(center.getId().toString()));



        return ResultVo.success(centerVo);
    }

    @Override
    public ResultVo register(Center center) {
        // 从数据库中查询出用户实体对象
        QueryWrapper<Center> queryWrapper = new QueryWrapper<Center>();
        queryWrapper.eq("name",center.getName());

        Center center1 = secureMapper.selectOne(queryWrapper);
        // 若没查询到一定要抛出该异常，这样才能被Spring Security的错误处理器处理
        if (center1 != null) {
            return ResultVo.fail("已存在此分站");
        }
        Date now = new Date();
        center.setCreateTime(now);
        center.setUpdateTime(now);


        return ResultVo.success(secureMapper.insert(center));
    }

    @Override
    public ResultVo setPassword(Integer id, String password) {
        UpdateWrapper updateWrapper = new UpdateWrapper();
        updateWrapper.eq("id", id);
        updateWrapper.set("password",  passwordEncoder.encode(password));
        secureMapper.update(null, updateWrapper);
        return ResultVo.success();
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {


        // 从数据库中查询出用户实体对象
        QueryWrapper<Center> queryWrapper = new QueryWrapper<Center>();
        queryWrapper.eq("id",Integer.parseInt(s));

        Center center = secureMapper.selectOne(queryWrapper);
        // 若没查询到一定要抛出该异常，这样才能被Spring Security的错误处理器处理
        if (center == null) {
            throw new UsernameNotFoundException("没有找到该分站");
        }

        // 走到这代表查询到了实体对象，那就返回我们自定义的UserDetail
        return new UserDetail(center, Collections.emptyList());
    }




}
