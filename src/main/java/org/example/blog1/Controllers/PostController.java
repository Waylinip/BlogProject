package org.example.blog1.Controllers;

import org.example.blog1.models.Category;
import org.example.blog1.models.Post;
import org.example.blog1.service.PostService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/")
    public String homePage(Model model) {

        model.addAttribute("posts", postService.getAllPosts()); // ← добавь это
       model.addAttribute("title", "Лента блогов");
        model.addAttribute("categories", Category.values());
      return "blog-main" ;
    }

    @GetMapping("/posts/add")
    public String addPostForm(Model model) {
        model.addAttribute("post", new Post());
        return "add-post";
    }

    @PostMapping("/posts/add")
    public String addPostSubmit(@ModelAttribute Post post) {
        postService.savePost(post);
        return "redirect:/"; // После сохранения возвращаемся на главную
    }

    @GetMapping("/posts/category/{category}")
    public String getPostsByCategory(@PathVariable String category, Model model) {

        Category cat = Category.fromTitle(category);

        model.addAttribute("posts", postService.getPostsByCategory(cat));

        return "posts";
    }

    @GetMapping("/posts/{id}")
    public String findPostById(Model model, @PathVariable Long id) {
        Post post = postService.getPostsByIdAndIncreaseViews(id);

        model.addAttribute("post", post);
        return "find-post";
    }

    @GetMapping("/posts/{id}/edit")
    public String editPost(@PathVariable Long id, Model model) {
        Post post = postService.getPostsByIdAndIncreaseViews(id);
        model.addAttribute("post", post);
        return "edit-post";
    }
    @PostMapping("/posts/{id}/edit")
    public String editPostSubmit(@PathVariable Long id,@RequestParam String title,
                                 @RequestParam String content) {
        postService.editPost(id, title, content);
        return "redirect:/posts/" + id;
    }
}






//    @GetMapping("/")
//    public String homePage(Model model) {
////        List<Post> posts = postService.getAllPosts()
////                .stream().limit(3).toList();
////
////        Map<String, Long> postsMap = postService.getAllPosts()
////                .stream()
////                .collect(Collectors.groupingBy(Post::getAuthor, Collectors.counting()));
////        model.addAttribute("latestPosts", posts);
////        model.addAttribute("authors", postsMap);
//        model.addAttribute("posts", postService.getAllPosts()); // ← добавь это
//        model.addAttribute("title", "Лента блогов");
//
//        return "blog-main" ;
//    }
//
//
//
//
//
//    @GetMapping("/posts/{id}")
//    public String findPostById(Model model, @PathVariable Long id) {
//        Post post = postService.getPostsByPostAndIncreaseViews(id);
//
//        model.addAttribute("post", post);
//        return "find-post";
//    }
//
//    @PostMapping("/posts/{id}/edit")
//    public String editPost(@PathVariable Long id,
//                           @RequestParam String title,
//                           @RequestParam String content) {
//
//        postService.editPost(id, title, content);
//
//        return "redirect:/posts/" + id;
//    }
//
//    @GetMapping("/posts/{id}/edit")
//    public String editPostPage(@PathVariable Long id,
//                               Model model) {
//
//        Post post = postService.getPostById(id);
//
//        model.addAttribute("post", post);
//
//        return "edit-post";
//    }
//
//    @GetMapping("/posts/category/{category}")
//    public String getPostsByCategory(@PathVariable  Category category, Model model) {
//        model.addAttribute("posts",
//                postService.getPostsByCategory(category));
//        return "find-post" ;
//    }




//    @GetMapping("/posts")
//    public String listPosts(Model model) {
//        model.addAttribute("title", "Лента блогов");
//        model.addAttribute("posts", postService.getAllPosts());
//        return "posts"; // ← теперь возвращает posts.html, не home
//    }


