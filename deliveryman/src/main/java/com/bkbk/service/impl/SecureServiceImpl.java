package com.bkbk.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.bkbk.config.UserDetail;
import com.bkbk.entity.Deliveryman;
import com.bkbk.entity.Substation;
import com.bkbk.mapper.SecureMapper;
import com.bkbk.service.Iservice.SecureService;
import com.bkbk.util.JwtUtil;
import com.bkbk.vo.ResultVo;
import com.bkbk.vo.DeliverymanVo;
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

        QueryWrapper<Deliveryman> queryWrapper = new QueryWrapper<Deliveryman>();
        queryWrapper.eq("name",name);

        Deliveryman deliveryman = secureMapper.selectOne(queryWrapper);
        // 若没查询到一定要抛出该异常，这样才能被Spring Security的错误处理器处理

        if (deliveryman == null  ||  !passwordEncoder.matches(password, deliveryman.getPassword()) ){
            return ResultVo.fail("账号密码错误");
        }

        DeliverymanVo substationVo = new DeliverymanVo();
        substationVo.setId(deliveryman.getId());
        substationVo.setName(deliveryman.getName());
        substationVo.setToken(JwtUtil.generate(deliveryman.getId().toString()));



        return ResultVo.success(substationVo);
    }



    @Override
    public ResultVo register(Deliveryman deliveryman) {
        // 从数据库中查询出用户实体对象
        QueryWrapper<Deliveryman> queryWrapper = new QueryWrapper<Deliveryman>();
        queryWrapper.eq("name",deliveryman.getName());

        Deliveryman deliveryman1 = secureMapper.selectOne(queryWrapper);
        // 若没查询到一定要抛出该异常，这样才能被Spring Security的错误处理器处理
        if (deliveryman1 != null) {
            return ResultVo.fail("已存在此分站");
        }
        Date now = new Date();
        deliveryman.setCreateTime(now);
        deliveryman.setUpdateTime(now);


        return ResultVo.success(secureMapper.insert(deliveryman));
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
        QueryWrapper<Deliveryman> queryWrapper = new QueryWrapper<Deliveryman>();
        queryWrapper.eq("id",Integer.parseInt(s));

        Deliveryman deliveryman = secureMapper.selectOne(queryWrapper);
        // 若没查询到一定要抛出该异常，这样才能被Spring Security的错误处理器处理
        if (deliveryman == null) {
            throw new UsernameNotFoundException("没有找到该分站");
        }

        // 走到这代表查询到了实体对象，那就返回我们自定义的UserDetail
        return new UserDetail(deliveryman, Collections.emptyList());
    }




}
