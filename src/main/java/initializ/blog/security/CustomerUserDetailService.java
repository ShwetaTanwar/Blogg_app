package initializ.blog.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import initializ.blog.entities.User;
import initializ.blog.exceptions.ResourceNotFoundException;
import initializ.blog.repositories.UserRepo;

public class CustomerUserDetailService implements UserDetailsService{

	@Autowired
private  UserRepo userRepo;

	@Override   //loading user from database by username
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	User user=  this.userRepo.findByEmail(username).orElseThrow(()->new ResourceNotFoundException("User","email :"+username,0));
		return null;
	}

}
