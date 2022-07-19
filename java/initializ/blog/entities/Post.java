package initializ.blog.entities;



import java.util.ArrayList;
import java.util.Date;

import java.util.List;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
                @Table(name="post")//This table is sub-resource n having 2 parent-resource(category n user)
public class Post {
@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer postId;
@Column (name="post_title",length=100,nullable=false)
	private String title;
@Column(length=1000)
	private String content;
	private String imageName;

	  private Date addedDate;
@ManyToOne
@JoinColumn(name="category_id ") 
	  private Category category;
	 @ManyToOne
	  private User user;
	// @OneToMany(mappedBy="post",cascade=CascadeType.ALL,fetch=FetchType.LAZY)
//private Set<Comment>comments=new HashSet<>();
	 @OneToMany(mappedBy="post",cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	 private List<Comment> comments=new ArrayList<>();

}
