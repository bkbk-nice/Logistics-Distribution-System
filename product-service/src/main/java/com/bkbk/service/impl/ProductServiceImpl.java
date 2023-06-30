package com.bkbk.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bkbk.mapper.ProductMapper;
import com.bkbk.service.ProductService;
import com.bkbk.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductMapper productMapper;

    @Override
    public ResultVo getHome() {
      //  QueryWrapper queryWrapper = new QueryWrapper();
        return ResultVo.success(productMapper.selectList(null));
    }

    @Override
    public ResultVo getCategory() {
        return ResultVo.success(productMapper.listRoot());
    }
}
