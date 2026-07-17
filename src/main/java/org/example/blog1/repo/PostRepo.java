package org.example.blog1.repo;


import org.example.blog1.models.Category;
import org.example.blog1.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface PostRepo extends JpaRepository<Post, Long> {

    // 1. Найти посты конкретного автора
    List<Post> findByAuthor(String author);

    // 2. Найти самые популярные посты (сортировка по просмотрам)
    List<Post> findAllByOrderByViewsDesc();

    // 3. Найти посты, созданные после определенной даты
    List<Post> findByCreatedAtAfter(LocalDateTime date);

    // 4. Поиск постов, где в заголовке или тексте есть ключевое слово (игнорируя регистр)
    List<Post> findByTitleContainingIgnoreCaseOrContentContainingIgnoreCase(String title, String content);

    // 5. Найти топ-3 самых залайканных поста
    List<Post> findTop3ByOrderByLikesDesc();

    // 6. Проверить, существует ли уже пост с таким заголовком (полезно при создании)
    boolean existsByTitle(String title);


    List<Post> findByCategory(Category category);







}
