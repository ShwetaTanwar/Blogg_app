package initializ.blog.controllers;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.hibernate.engine.jdbc.StreamUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import initializ.blog.config.AppConstansts;
import initializ.blog.payloads.ApiResponse;
import initializ.blog.payloads.PostDto;
import initializ.blog.payloads.PostResponse;
import initializ.blog.services.FileService;
import initializ.blog.services.PostService;

@RestController
@RequestMapping("/api/")
public class PostController {
	@Autowired
	private PostService postService;
	@Autowired
	private FileService fileService;
	@Value("${project.image}")
	private String path;
	
	@PostMapping("/user/{userId}/category/{categoryId}/posts")//create post
public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto,
		@PathVariable Integer userId,@PathVariable Integer categoryId) {
		PostDto createPost=this.postService.createPost(postDto, userId, categoryId);
	return new ResponseEntity<PostDto>(createPost,HttpStatus.CREATED);
}
	@GetMapping("/user/{userId}/posts")//all post  by a user
	public ResponseEntity<List<PostDto>>getPostsByUser(@PathVariable Integer userId){
		List<PostDto>posts=this.postService.getPostsByUser(userId);
		return new ResponseEntity<List<PostDto>>(posts,HttpStatus.OK);
		}
	
	@GetMapping("/category/{categoryId}/posts")//all post from  a category
	public ResponseEntity<List<PostDto>>getPostsByCategory(@PathVariable Integer categoryId){
		List<PostDto>posts=this.postService.getPostsByCategory(categoryId);
		return new ResponseEntity<List<PostDto>>(posts,HttpStatus.OK);
		}
	@GetMapping("/posts")//get all post
	public ResponseEntity<PostResponse> getAllPost(
			@RequestParam(value="pageNumber",defaultValue=AppConstansts.PAGE_NUMBER,required=false)Integer pageNumber,
			@RequestParam(value="pageSize",defaultValue=AppConstansts.PAGE_SIZE,required=false)Integer pageSize,
			@RequestParam(value="sortBy",defaultValue=AppConstansts.SORT_BY,required=false)String sortBy,
			@RequestParam(value="sortDir",defaultValue=AppConstansts.SORT_DIR,required=false)String sortDir){
		PostResponse PostResponse = this.postService.getAllPost(pageNumber,pageSize,sortBy,sortDir);
	return new ResponseEntity<PostResponse>(PostResponse,HttpStatus.OK);
	}
	@GetMapping("/posts/{postId}")
	public ResponseEntity<PostDto> getPostById(@PathVariable Integer postId) {
		PostDto postDto=postService.getPostById(postId);
	return new ResponseEntity<PostDto>(postDto,HttpStatus.OK);
	}
	@DeleteMapping("/posts/{postId}")
public ApiResponse deletePost(@PathVariable Integer postId) {
		this.postService.deletePost(postId);
	return new ApiResponse("post deleted",true);
	}
	@PutMapping("/posts/{postId}")//update post
	public ResponseEntity<PostDto>updatePost(@RequestBody PostDto postDto,@PathVariable Integer postId) {
			PostDto updatePost=this.postService.updatePost(postDto,postId);
		return new ResponseEntity<PostDto>(updatePost,HttpStatus.OK);
	}
	@GetMapping ("/posts/search/{keywords}")
	public ResponseEntity<List<PostDto>>searchPostByTitle(@PathVariable("keywords")String keywords){
		List<PostDto>result=this.postService.searchPosts(keywords);
		return new ResponseEntity<List<PostDto>>(result,HttpStatus.OK);
	}
	//post image upload
	@PostMapping("/post/image/upload/{postId}")
	public ResponseEntity<PostDto>uploadPostImage(@RequestParam("image")MultipartFile image,
			@PathVariable Integer postId) throws IOException{
		
		PostDto postDto = this.postService.getPostById(postId);
		String fileName =this.fileService.uploadImage(path, image);
	    postDto.setImageName(fileName);
		PostDto updatePost=this.postService.updatePost(postDto, postId);
		return new ResponseEntity <PostDto>(updatePost,HttpStatus.OK);
	}
	@GetMapping(value="/post/image/{imageName}",produces=MediaType.IMAGE_JPEG_VALUE)
	public void downloadImage(@PathVariable("imageName")String imageName,
	HttpServletResponse response ) throws IOException{
		InputStream resource=this.fileService.getResource(path, imageName);
		response.setContentType(MediaType.IMAGE_JPEG_VALUE);
		StreamUtils.copy(resource,response.getOutputStream());
	}
	
	
}
