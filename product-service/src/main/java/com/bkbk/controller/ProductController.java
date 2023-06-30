package com.bkbk.controller;


import com.bkbk.service.ProductService;
import com.bkbk.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("product")
public class ProductController {

    @Autowired
    private  ProductService productService;

    @GetMapping("/home")
    public ResultVo getHome(){
           return productService.getHome();
    }

    @GetMapping("/category")
    public ResultVo getCategory(){
        return productService.getCategory();
    }
}
