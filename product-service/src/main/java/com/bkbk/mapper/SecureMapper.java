package com.bkbk.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bkbk.entity.Center;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SecureMapper extends BaseMapper<Center> {

    Center selectByName(String name);
}
