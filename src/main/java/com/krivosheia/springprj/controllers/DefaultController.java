package com.krivosheia.springprj.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/homework01")
public class DefaultController {

    @GetMapping
    public ResponseEntity<String> index() {
        return ResponseEntity.ok("Hello World!");
    }

//    @GetMapping("/param")
//    public ResponseEntity<String> getParams(
//            @RequestParam String param1,
//            @RequestParam String param2,
//            HttpServletRequest request
//    ) {
//        String str = "param1 = " + param1 + " param2 = " + param2 +
//                "\nparam from servlet = " + request.getParameter("param2");
//
//        return ResponseEntity.ok(str);
//    }
}