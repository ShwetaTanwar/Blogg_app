package initializ.blog.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import initializ.blog.entities.User;
import initializ.blog.exceptions.ResourceNotFoundException;
import initializ.blog.repositories.UserRepo;
@Service
@Transactional
public class CustomUserDetailService implements UserDetailsService{

	@Autowired
private  UserRepo userRepo;

	@Override   //loading user from database by user name
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		//System.out.println("user is   ****" + username);
	User user=  this.userRepo.findByEmail(username).orElseThrow(()->new ResourceNotFoundException("User","email :"+username,0));
	    //System.out.println("user is   ** after userRepo" + username);
		return user;
	}

}
