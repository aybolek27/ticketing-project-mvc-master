package com.cydeo.controller;

import com.cydeo.dto.RoleDTO;
import com.cydeo.dto.UserDTO;
import com.cydeo.service.RoleService;
import com.cydeo.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {

    RoleService roleService;
    UserService userService;

    public UserController(RoleService roleService, UserService userService) {
        this.roleService = roleService;
        this.userService = userService;
    }

    @GetMapping("/create")
    public String createUser(Model model){


        model.addAttribute("user", new UserDTO()); // this line to capture data from form fields
        model.addAttribute("roles",roleService.findAll());  // bring me all roles
        model.addAttribute("users", userService.findAll()); //bring all users

        return "user/create";
    }

    @PostMapping("/create")
    public String insertUser(@ModelAttribute("user") UserDTO previousUser, Model model){
//        model.addAttribute("user", new UserDTO()); // to display empty form in user/create view
//        model.addAttribute("roles",roleService.findAll());  // bring me all roles
    userService.save(previousUser);
//        model.addAttribute("users", userService.findAll()); //bring all users
        return "redirect:/user/create";
    }


}