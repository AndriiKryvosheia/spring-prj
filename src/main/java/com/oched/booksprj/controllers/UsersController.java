package com.oched.booksprj.controllers;

import com.oched.booksprj.requests.ActionRequest;
import com.oched.booksprj.requests.DeleteUserRequest;
import com.oched.booksprj.requests.NewUserRequest;
import com.oched.booksprj.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@RequiredArgsConstructor
@Controller
@RequestMapping("/users")
public class UsersController {
    private final UserService userService;

    @GetMapping(value = "/add")
    public String getNewUserForm() {
        return "/users/addUser";
    }

    @PostMapping(value = "/add")
    public ModelAndView addNewUser(@Validated @ModelAttribute("request") NewUserRequest request, ModelAndView modelAndView) {
        modelAndView.setViewName("/users/newUser");
        modelAndView.addObject("user", this.userService.addNewUser(request));

        return modelAndView;
    }

    @GetMapping(value = "/all")
    public ModelAndView getUsersList(ModelAndView modelAndView) {
        modelAndView.addObject("list", this.userService.getUsersList());
        modelAndView.setViewName("/users/allUsers");

        return modelAndView;
    }

    @GetMapping(value = "/delete")
    public String deleteUserForm() {
        return "/users/deleteUser";
    }

    @PostMapping(value = "/delete")
    public String deleteUser(final @ModelAttribute("request") DeleteUserRequest request) {
        this.userService.deleteUser(request);

        return "redirect:/users/all";
    }

}
