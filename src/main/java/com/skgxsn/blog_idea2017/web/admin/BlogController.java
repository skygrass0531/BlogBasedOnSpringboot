package com.skgxsn.blog_idea2017.web.admin;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class BlogController {

    @GetMapping("/blog-list")
    public String blogList() {
        return "admin/blog-list";
    }
}
