package initializ.blog.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import initializ.blog.payloads.ApiResponse;
import initializ.blog.payloads.CategoryDto;
import initializ.blog.services.CategoryService;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

        @Autowired
	private CategoryService categoryService;
	
	@PostMapping("/")
	public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto createDto)
	{
		CategoryDto createCategory = this.categoryService.createCategory(createDto);
		return new ResponseEntity <CategoryDto>(createCategory , HttpStatus.CREATED);
	}
@PutMapping("/{catId}")
public ResponseEntity<CategoryDto>updateCategory(@Valid @RequestBody CategoryDto categoryDto,@PathVariable Integer catId)
{
	CategoryDto updatedCategory = this.categoryService.updateCategory(categoryDto,catId);
	return new ResponseEntity <CategoryDto>(updatedCategory , HttpStatus.OK);
}
@DeleteMapping("/{catId}")
public ResponseEntity<ApiResponse>deleteCategory(@PathVariable Integer catId)
{
 this.categoryService.deleteCategory(catId);
	return new ResponseEntity <ApiResponse>(new ApiResponse
			("category deleted sucessfully" ,true), HttpStatus.OK);
}
@GetMapping("/")
public ResponseEntity<List<CategoryDto>>getCategories()
{
 List<CategoryDto> categories=this.categoryService.getCategories();
return ResponseEntity.ok(categories); 
}

@GetMapping("/{catId}")
public ResponseEntity<CategoryDto>getCategory(@PathVariable Integer catId)
{
 CategoryDto categoryDto=this.categoryService.getCategory(catId);
	return new ResponseEntity <CategoryDto>(categoryDto, HttpStatus.OK);
}}