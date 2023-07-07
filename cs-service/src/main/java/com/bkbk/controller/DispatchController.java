package com.bkbk.controller;


import com.bkbk.entity.AllocationList;
import com.bkbk.service.DispatchService;
import com.bkbk.util.JwtUtil;
import com.bkbk.vo.ResultVo;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/dispatch")

public class DispatchController {


    @Autowired
    private DispatchService dispatchService;


    @Transactional
    @PostMapping("/createAllocationList")
    public ResultVo createAllocationListAndTaskList(@RequestHeader("Authorization") String token,
                                                    @RequestBody AllocationList allocationList ) {
        //创建派遣单的同时创建任务单
        Claims claims = JwtUtil.parse(token);
        if (claims == null) {
            return ResultVo.fail("token错误");
        }else{
            String csId = claims.getSubject();
            return   dispatchService.createAllocationListAndTaskList(allocationList,Integer.parseInt(csId));

        }
    }

    @Secured("ROLE_cs")
    @GetMapping("/substationList")
    public ResultVo substationList( ) {

            return   dispatchService.getSubstation();


    }





}
