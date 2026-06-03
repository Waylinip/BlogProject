package org.example.blog1.Controllers;

import org.example.blog1.models.Post;
import org.example.blog1.service.PostService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class PostController {


    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/")
    public String homePage(Model model) {
        List<Post> posts = postService.getAllPosts()
                .stream().limit(3).toList();

        Map<String, Long> postsMap = postService.getAllPosts()
                .stream()
                .collect(Collectors.groupingBy(Post::getAuthor, Collectors.counting()));
        model.addAttribute("latestPosts", posts);
        model.addAttribute("authors", postsMap);
        return "home";
    }

    @GetMapping("/posts/add")
    public String addPostForm(Model model) {
        model.addAttribute("post", new Post());
        return "add-post";
    }

    // Обработка формы создания поста
    @PostMapping("/posts/add")
    public String addPostSubmit(@ModelAttribute Post post) {
        postService.savePost(post);
        return "redirect:/"; // После сохранения возвращаемся на главную
    }

    @GetMapping("/post/{id}")
    public String findPostById(Model model, @PathVariable Long id) {
        Post post = postService.getPostsByPostAndIncreaseViews(id);

        model.addAttribute("post", post);
        return "find-post";
    }

//    @GetMapping("/posts")
//    public String listPosts(Model model) {
//        model.addAttribute("title", "Лента блогов");
//        model.addAttribute("posts", postService.getAllPosts());
//        return "posts"; // ← теперь возвращает posts.html, не home
//    }
}

