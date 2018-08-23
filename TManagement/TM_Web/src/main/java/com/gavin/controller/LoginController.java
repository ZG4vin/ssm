package com.gavin.controller;

import com.gavin.bean.*;
import com.gavin.serivce.UserService;
import com.gavin.system.PageCodeEnum;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/login")
public class LoginController {


    @Autowired
    private UserService userService;

    @RequestMapping
    public String InitLogin(){
        return "login";
    }


    @PostMapping("/toIndex")
    public String toIndex(Model model, User user, RedirectAttributes attr, HttpSession session){
        User loginUser = userService.getUser(user);
        if (loginUser!=null){
            session.setAttribute("User",loginUser);
            Page<User> pageajax= PageHelper.startPage(1,3);
            List<User> userListajax=userService.getAllUser();
            PageInfo<User> pageInfoajax=new PageInfo<>(userListajax);
            model.addAttribute("userListajax",userListajax);
            model.addAttribute("pageajax",pageInfoajax);



            Page<User> page= PageHelper.startPage(1,3);
            List<User> userList=userService.getAllUser();
            PageInfo<User> pageInfo=new PageInfo<>(userList);
            model.addAttribute("userList",userList);
            model.addAttribute("page",pageInfo);



            return "index";
        }else{
            attr.addFlashAttribute(PageCodeEnum.KEY,PageCodeEnum.USER_ERROR);
            return "redirect:/login";
        }
    }
}
