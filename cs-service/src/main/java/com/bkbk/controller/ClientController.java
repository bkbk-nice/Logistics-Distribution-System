package com.bkbk.controller;

import com.bkbk.form.ClientForm;
import com.bkbk.service.ClientService;
import com.bkbk.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/client")
public class ClientController {


    @Autowired
    private ClientService clientService;


    @Secured( {"ROLE_admin" , "ROLE_cs"} )
    @GetMapping("/listPageByDynamics")
    public ResultVo listPageByDynamics( String keyword,
                                       @RequestParam(defaultValue = "1") Integer pageNumber,
                                       @RequestParam(defaultValue = "5") Integer pageSize  ){



        return clientService.listPageByDynamics(keyword,pageNumber,pageSize);
    }

    @Secured({"ROLE_admin" , "ROLE_cs"})
    @PostMapping("/addClient")
    public ResultVo add(@RequestBody @Valid ClientForm clientForm, BindingResult bindingResult){

        if (bindingResult.getErrorCount() >0) {
            return ResultVo.fail(bindingResult.getAllErrors().get(0).getDefaultMessage());
        }

        return   clientService.addClient(clientForm);
    }




    @Secured("ROLE_admin")
    @PostMapping("/editClient")
    public ResultVo edit(@RequestBody @Valid ClientForm clientForm, BindingResult bindingResult){

        if (bindingResult.getErrorCount() >0) {
            return ResultVo.fail(bindingResult.getAllErrors().get(0).getDefaultMessage());
        }

        return   clientService.editClient(clientForm);
    }


    @Secured("ROLE_admin")
    @GetMapping("/delClient")
    public ResultVo delClientById( Integer id){

        return clientService.delClient(id);
    }



}
