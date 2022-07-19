package initializ.blog.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name="categories")
public class Category {
@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer categoryId;
	
@Column(name="title",nullable=false,length=100)
	private String categoryTitle;

@Column(name="description")
private String categoryDescription;

//in one category we can have multiple post so one to many
@OneToMany(mappedBy="category",cascade=CascadeType.ALL,fetch=FetchType.LAZY)
private List<Post> posts=new ArrayList<>();
}
