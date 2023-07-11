package com.bkbk.service;

import com.bkbk.vo.ResultVo;
import org.springframework.web.bind.annotation.RequestParam;

public interface ProductService {

    ResultVo getHome(String keyword,Integer categoryId,
                    Integer pageNumber,Integer pageSize);

    ResultVo getCategory();


    ResultVo getProductById(Integer id);

    ResultVo homeForCenter(String keyword,Integer categoryId,
                     Integer pageNumber,Integer pageSize);
}
