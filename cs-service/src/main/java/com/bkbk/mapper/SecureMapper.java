package com.bkbk.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bkbk.entity.Cs;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SecureMapper extends BaseMapper<Cs> {

    Cs selectByName(String name);
}
