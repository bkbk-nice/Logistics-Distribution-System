package com.bkbk.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bkbk.entity.Client;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SecureMapper extends BaseMapper<Client> {

    Client selectByName(String name);
}
