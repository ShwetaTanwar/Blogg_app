package initializ.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import initializ.blog.entities.Comment;

public interface CommentRepo extends JpaRepository<Comment, Integer> {

}
