package com.gavin.controller;

import com.gavin.bean.*;
import com.gavin.dao.UserDao;
import com.gavin.system.PageCodeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private UserDao userDao;

    @RequestMapping
    public String InitLogin(){
        return "login";
    }


    @PostMapping("/toIndex")
    public String toIndex(User user, RedirectAttributes attr, HttpSession session){
        User loginUser = userDao.getUser(user);
        if (loginUser!=null){
            session.setAttribute("User",loginUser);
            return "index";
        }else{
            attr.addFlashAttribute(PageCodeEnum.KEY,PageCodeEnum.USER_ERROR);
            return "redirect:/login";
        }
    }
}
