package com.bkbk.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bkbk.entity.Category;
import com.bkbk.entity.Product;

import java.util.List;

public interface ProductMapper  extends BaseMapper<Product> {

    List<Category> listRoot();
    List<Category> listByParentId(Integer id);
}
