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
    public ResultVo getHome(String keyword,Integer categoryId,
                            Integer pageNumber,
                           Integer pageSize) {
         QueryWrapper queryWrapper = new QueryWrapper();
         if(keyword!=null&&keyword.length()!=0){
             queryWrapper.select("id","name","category_id","price","main_image","sub_title")
               .like("name",keyword);
         }else if(categoryId>0) {
             queryWrapper.select("id","name","category_id","price","main_image","sub_title")
            .eq("category_id",categoryId);
        }else {
             queryWrapper.select("id","name","category_id","price","main_image","sub_title");
         }

         //分页信息


        return ResultVo.success(productMapper.selectList(queryWrapper));
    }





    @Override
    public ResultVo getCategory() {
        return ResultVo.success(productMapper.listRoot());
    }

    @Override
    public ResultVo getProductById(Integer id) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.select("id","name","category_id","price","main_image","sub_title","sub_images","detail")
                .eq("id",id);
        return ResultVo.success(productMapper.selectOne(queryWrapper));

    }
}
