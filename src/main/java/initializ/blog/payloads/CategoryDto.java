package initializ.blog.payloads;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class CategoryDto {

	private Integer categoryId;
	@NotBlank
	@Size(min=4 ,message="min size is 4")
	private String categoryTitle;
	@NotBlank
	@Size(min=10,message="min size is 10")
	private String categoryDescription;
	
}
