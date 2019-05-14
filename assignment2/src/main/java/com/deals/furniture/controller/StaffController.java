package com.deals.furniture.controller;


import com.deals.furniture.model.StaffAccount;
import com.deals.furniture.model.StaffAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/staff")
public class StaffController {

    @Autowired
    private StaffAccountRepository staffAccountRepository;

    @GetMapping(path = "/all")
    public @ResponseBody Iterable<StaffAccount> getAll(){
        return staffAccountRepository.findAll();
    }
}
