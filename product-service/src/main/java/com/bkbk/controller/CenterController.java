package com.bkbk.controller;


import com.bkbk.entity.AllocationList;
import com.bkbk.entity.Inventory;
import com.bkbk.entity.TaskList;
import com.bkbk.entity.form.OrderForm;
import com.bkbk.entity.form.ProductForm;
import com.bkbk.service.CenterService;
import com.bkbk.util.JwtUtil;
import com.bkbk.vo.ResultVo;

import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping("center")
public class CenterController {

    @Autowired
    private CenterService centerService;

    @Secured( {"ROLE_admin" , "ROLE_center"} )
    @GetMapping("/listPageByDynamics")
    public ResultVo listPageByDynamics(
            String keyword,
            @RequestParam(defaultValue = "1") Integer pageNumber,
            @RequestParam(defaultValue = "5") Integer pageSize  ){

            return centerService.getAllocationList(keyword,pageNumber,pageSize);


    }

    @Transactional
    @Secured( {"ROLE_admin" , "ROLE_center"} )
    @PostMapping("/allocationStart")
    public ResultVo allocationStart(@RequestBody AllocationList allocationList ) {

        return   centerService.allocationStart(allocationList);

    }




    @Secured( {"ROLE_admin" , "ROLE_center"} )
    @GetMapping("/inventory")
    public ResultVo inventory( Integer productId ) {

        return   centerService.inventory(productId);

    }



    @Transactional
    @Secured( {"ROLE_admin" , "ROLE_center"} )
    @GetMapping("/addProductNum")
    public ResultVo addProductNum( Integer productId ,Integer num) {

        return   centerService.addProductNum(productId,num);

    }



    @Secured( {"ROLE_admin" , "ROLE_center"} )
    @GetMapping("/substationTaskListForCenter")
    public ResultVo substationTaskListForCenter(
            @RequestHeader("Authorization") String token,
            String keyword,
            @RequestParam(defaultValue = "1") Integer pageNumber,
            @RequestParam(defaultValue = "5") Integer pageSize  ){

        return centerService.substationTaskListForCenter(keyword,pageNumber,pageSize);


    }

    @Transactional
    @Secured( {"ROLE_admin" , "ROLE_center"} )
    @PostMapping("/newProduct")
    public ResultVo newProduct(   @RequestPart MultipartFile[] img,
                               @RequestPart Map<String, Object> postInfo     ){


        return centerService.newProduct(img,postInfo);


    }






}
