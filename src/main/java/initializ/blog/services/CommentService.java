package initializ.blog.services;

import org.springframework.stereotype.Service;

import initializ.blog.payloads.CommentDto;

@Service
public interface CommentService {
CommentDto createComment(CommentDto commentDto, Integer postId);
void deleteComment(Integer commentId);
}
