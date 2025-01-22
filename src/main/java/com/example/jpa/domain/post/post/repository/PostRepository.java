package com.example.jpa.domain.post.post.repository;

import com.example.jpa.domain.post.post.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post,Long> {
    List<Post> findByTitle(String title);

    List<Post> findByTitleAndBody(String title, String content);

    List<Post> findByTitleLike(String title);
    Page<Post> findByTitleLike(String title,Pageable pageable);

    List<Post> findByOrderByIdDesc();

    List<Post> findTop2ByTitleOrderByIdDesc(String title);
    Page<Post> findAll(Pageable pageable);
}
