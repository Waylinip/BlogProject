package org.example.blog1.service;

import org.example.blog1.models.Post;
import org.example.blog1.repo.PostRepo;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class PostService {

    private final PostRepo postRepo; // Поле теперь final

    public PostService(PostRepo postRepo) {
        this.postRepo = postRepo;
    }

    public List<Post> getAllPosts() {
        return postRepo.findAll();
    }

    public void savePost(Post post) {
        if (post.getTitle() != null && !post.getTitle().isEmpty()) {
            post.setCreatedAt(LocalDateTime.now());
            postRepo.save(post);
        }
    }

    public Post getPostById(Long id) {
        return postRepo.findById(id).orElse(null);
    }

    public List<Post> findByAuthor(String author) {
        return postRepo.findByAuthor(author);
    }

    public List<Post> findAllByOrderByViewsDesc() {
        return postRepo.findAllByOrderByViewsDesc();
    }

//    public List<Post> getPostByMostViews(Integer mostViews) {
//        List<Post> posts = new ArrayList<>();
//
//    }

    public Post getPostsByPostAndIncreaseViews(Long id) {
        Post post = postRepo.findById(id).orElse(null);
        post.setViews(post.getViews() + 1);
        postRepo.save(post);
        return post;

    }



}
