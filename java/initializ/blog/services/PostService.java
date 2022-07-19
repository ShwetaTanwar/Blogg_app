package initializ.blog.services;

import java.util.List;


import initializ.blog.payloads.PostDto;
import initializ.blog.payloads.PostResponse;

public interface PostService {

	PostDto createPost (PostDto postDto, Integer userId,Integer categoryId);
	PostDto updatePost (PostDto postDto, Integer postId);
	void deletePost(Integer postId);
	//List<PostDto>getAllPost(Integer pageNumber,Integer pageSize);
	PostResponse getAllPost(Integer pageNumber,Integer pageSize,String sortBy, String sortDir);
	PostDto getPostById(Integer postId);
	List<PostDto>getPostsByCategory(Integer categoryId); //all posts by category
	List<PostDto>getPostsByUser(Integer userId);//all post by user
	List<PostDto> searchPosts(String keyword);	
	
}
