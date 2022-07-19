package initializ.blog.payloads;





import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class PostDto {
    private Integer postId;
	private String title;
	private String content;
	private String imageName;
	  private Date addedDate;
	 private CategoryDto category;
	 private UserDto user;
		  

//private Set<CommentDto>comments=new HashSet<>();

private List<CommentDto> comments=new ArrayList<>();
//private Set<Comment>comments=new HashSet<>();

}
