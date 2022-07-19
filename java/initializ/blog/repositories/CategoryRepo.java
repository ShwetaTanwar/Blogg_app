package initializ.blog.repositories;



import org.springframework.data.jpa.repository.JpaRepository;

import initializ.blog.entities.Category;


public interface CategoryRepo extends JpaRepository<Category, Integer> {

}
