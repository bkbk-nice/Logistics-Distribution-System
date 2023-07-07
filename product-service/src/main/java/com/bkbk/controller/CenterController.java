package com.bkbk.controller;


import com.bkbk.entity.AllocationList;
import com.bkbk.entity.TaskList;
import com.bkbk.service.CenterService;
import com.bkbk.vo.ResultVo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

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





}
