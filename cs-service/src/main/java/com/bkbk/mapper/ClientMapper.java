package com.bkbk.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bkbk.entity.Client;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;


@Mapper
public interface ClientMapper  extends BaseMapper<Client> {


    @Select( "SELECT id,name,identity AS identityNumber,phone,address,email,image_url,createtime,updatetime FROM lds_client")
    List<Client> selectdynamic(String keyword);
}
