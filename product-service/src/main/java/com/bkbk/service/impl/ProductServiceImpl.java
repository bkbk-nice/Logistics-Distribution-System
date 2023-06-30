package com.bkbk.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bkbk.mapper.ProductMapper;
import com.bkbk.service.ProductService;
import com.bkbk.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductMapper productMapper;

    @Override
    public ResultVo getHome(String keyword,Integer categoryId,
                            Integer pageNumber,
                           Integer pageSize) {
         QueryWrapper queryWrapper = new QueryWrapper();
         if(keyword!=null&&keyword.length()!=0){
             queryWrapper.like("name",keyword);
         }else if(categoryId>0) {
            queryWrapper.eq("category_id",categoryId);
        }

         //分页信息





        return ResultVo.success(productMapper.selectList(queryWrapper));
    }


    @Override
    public ResultVo getCategory() {
        return ResultVo.success(productMapper.listRoot());
    }
}
