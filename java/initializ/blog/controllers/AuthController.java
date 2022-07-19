package initializ.blog.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import initializ.blog.exceptions.ApiException;
import initializ.blog.payloads.JwtAuthRequest;
import initializ.blog.payloads.JwtAuthResponse;
import initializ.blog.security.JwtTokenHelper;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
	@Autowired
	private JwtTokenHelper jwtTokenHelper;
    @Autowired  
	private UserDetailsService userDetailsService;
	
    @Autowired
    private AuthenticationManager authenticationManager;
    @PostMapping("/login")
	public ResponseEntity<JwtAuthResponse>createToken(@RequestBody JwtAuthRequest request ) throws Exception{
	
    	this.authenticate(request.getUsername(),request.getPassword());
    	UserDetails userDetails = this.userDetailsService.loadUserByUsername(request.getUsername());
    	String token=this.jwtTokenHelper.generateToken(userDetails);
     JwtAuthResponse response =new JwtAuthResponse();
     response.setToken(token);
     return new ResponseEntity<JwtAuthResponse>(response,HttpStatus.OK);
    	
	}
    
   private void authenticate(String username, String password) throws Exception {
	   
	   UsernamePasswordAuthenticationToken authenticationToken=new UsernamePasswordAuthenticationToken(username, password);
try {
	   this.authenticationManager.authenticate(authenticationToken);
	   } catch (BadCredentialsException e) {
		     System.out.println("invalid Details");
		     throw new ApiException("Invaild username or password !!");
	   }
   }
   }
