package org.example.blog1.Controllers;

import org.example.blog1.service.PostService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BlogController {

    private final PostService postService;

    public BlogController(PostService postService) {
        this.postService = postService;
    }
    @GetMapping("/blog")
    public String blogMain(Model model) {
        model.addAttribute("posts", postService.getAllPosts()); // ← добавь это
        model.addAttribute("title", "Лента блогов");
        return "blog-main";
    }
}
