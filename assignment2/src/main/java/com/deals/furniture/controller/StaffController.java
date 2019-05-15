package com.deals.furniture.controller;


import com.deals.furniture.model.StaffAccount;
import com.deals.furniture.model.StaffAccountRepository;
import com.deals.furniture.model.UserAccount;
import com.deals.furniture.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/staff")
public class StaffController {

    @Autowired
    private StaffService staffAccountRepository;

    @GetMapping(path = "/all")
    public @ResponseBody Iterable<StaffAccount> getAll(){
        return staffAccountRepository.getAllUSers();
    }

    @PostMapping("/register")
    public String getData(@Valid @ModelAttribute("userAccount") StaffAccount staffAccount, BindingResult result){
        if(result.hasErrors()){
            return "/register";
        }
        staffAccountRepository.addUser(staffAccount);
        return "/hello";
    }
}
