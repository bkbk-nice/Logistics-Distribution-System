package com.bkbk.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bkbk.entity.Client;
import com.bkbk.form.ClientForm;
import com.bkbk.mapper.ClientMapper;
import com.bkbk.service.ClientService;
import com.bkbk.vo.ResultVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import java.util.Date;



@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientMapper clientMapper;



    @Override
    public ResultVo listPageByDynamics(String keyword, Integer pageNumber, Integer pageSize) {

        QueryWrapper<Client> queryWrapper = new QueryWrapper<>();
        if(keyword!=null && !keyword.isEmpty()){
            queryWrapper.select("id","name","phone","email","identity","address","image_url","createtime","updatetime")

             .like("name", keyword ).or().like("phone",keyword).or().like("identity",keyword);
        }
        System.out.println("keyword:"+keyword);


        PageHelper.startPage(pageNumber,pageSize);

//        PageInfo pageInfo = new PageInfo( clientMapper.selectdynamic(keyword));
        PageInfo pageInfo = new PageInfo( clientMapper.selectList(queryWrapper));

        return  ResultVo.success(pageInfo);


    }

    @Override
    public ResultVo addClient(ClientForm clientForm) {

        //验证手机号 身份证号码 邮箱 （验证唯一）
        QueryWrapper<Client> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("phone", clientForm.getPhone() ) ;
        if(clientMapper.selectOne(queryWrapper)!=null){
            return ResultVo.fail("手机号已存在");
        }


        QueryWrapper<Client> queryWrapper2 = new QueryWrapper<>();
        queryWrapper2.eq("identity",clientForm.getIdentityNumber());
        if(clientMapper.selectOne(queryWrapper2)!=null){
            return ResultVo.fail("身份证号码已存在");
        }


        QueryWrapper<Client> queryWrapper3 = new QueryWrapper<>();
        queryWrapper3.eq("email",clientForm.getEmail());
        if(clientMapper.selectOne(queryWrapper3)!=null){
            return ResultVo.fail("邮箱已存在");
        }

        //copy属性
        Client client = new Client();
        BeanUtils.copyProperties(clientForm,client);
        Date now = new Date();
        client.setCreateTime(now);
        client.setUpdateTime(now);
        clientMapper.insert(client);
        return ResultVo.success();
    }



    @Override
    public ResultVo editClient(ClientForm clientForm) {
        //验证手机号 身份证号码 邮箱 （验证唯一）
        QueryWrapper<Client> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("phone", clientForm.getPhone() ).ne("id",clientForm.getId());
        if(clientMapper.selectOne(queryWrapper)!=null){
            return ResultVo.fail("手机号已存在");
        }


        QueryWrapper<Client> queryWrapper2 = new QueryWrapper<>();
        queryWrapper2.eq("identity",clientForm.getIdentityNumber()).ne("id",clientForm.getId());
        if(clientMapper.selectOne(queryWrapper2)!=null){
            return ResultVo.fail("身份证号码已存在");
        }


        QueryWrapper<Client> queryWrapper3 = new QueryWrapper<>();
        queryWrapper3.eq("email",clientForm.getEmail()).ne("id",clientForm.getId());
        if(clientMapper.selectOne(queryWrapper3)!=null){
            return ResultVo.fail("邮箱已存在");
        }

        //copy属性
        Client client = new Client();
        BeanUtils.copyProperties(clientForm,client);
        Date now = new Date();

        client.setUpdateTime(now);
        clientMapper.updateById(client);
        return ResultVo.success();
    }



    @Override
    public ResultVo delClient(Integer id) {

        // 已经有订单不能删除

        clientMapper.deleteById(id);
        return ResultVo.success();
    }
}
