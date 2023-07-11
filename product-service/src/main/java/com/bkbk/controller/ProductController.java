package com.bkbk.controller;


import com.bkbk.service.ProductService;
import com.bkbk.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("product")
public class ProductController {

    @Autowired
    private  ProductService productService;

    @GetMapping("/home")
    public ResultVo getHome(String keyword,Integer categoryId,
                            @RequestParam(defaultValue = "1") Integer pageNumber,
                            @RequestParam(defaultValue = "12") Integer pageSize){
           return productService.getHome(keyword,categoryId,pageNumber,pageSize);
    }

    @GetMapping("/category")
    public ResultVo getCategory(){
        return productService.getCategory();
    }

    @GetMapping("/getDetail")
    public ResultVo getProductById(Integer id){
        return productService.getProductById(id);
    }


    @Secured("ROLE_center")
    @GetMapping("/homeForCenter")
    public ResultVo homeForCenter(String keyword,Integer categoryId,
                            @RequestParam(defaultValue = "1") Integer pageNumber,
                            @RequestParam(defaultValue = "12") Integer pageSize){
        return productService.homeForCenter(keyword,categoryId,pageNumber,pageSize);
    }


}
