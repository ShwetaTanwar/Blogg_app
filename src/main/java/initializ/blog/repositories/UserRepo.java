package initializ.blog.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import initializ.blog.entities.User;

public interface UserRepo extends JpaRepository<User, Integer> {

	
	Optional<User>findByEmail(String email); //using email id as a user name
	//method used in customUserDetailsService
	
}
