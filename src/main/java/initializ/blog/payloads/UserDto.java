package initializ.blog.payloads;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

	private int id;
	@NotBlank(message = "Name Should not ne null")
	@Size(min =4 , message="UserName must be min of 4 characters")
	private String name;
	@Email (message="Please Enetr the Vaild Email Address")
	private String email;
	 @Pattern(regexp="^[a-zA-Z0-9]{3}",message="length must be min of 3")
     private String password;
	private String about;


}

