package initializ.blog.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import initializ.blog.entities.Category;
import initializ.blog.entities.Post;
import initializ.blog.entities.User;

public interface PostRepo extends JpaRepository  <Post,Integer> {
	
	
	List<Post> findByUser(User user); //custom finder methods in repo to get all post done by a user
	List<Post> findByCategory(Category category);//get all post from a category
    List<Post>findByTitleContaining(String title);
}
