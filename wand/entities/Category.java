package huffle.puff.wand.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "categories")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder


public class Category {

	@Id
	@Column(name = "Id")
	private String categoryId;
	
	@Column(name= "Category_Title", length = 60, nullable = false)
	private String categoryTitle;
	
	@Column(name = "Category_Desc",length = 50)
	private String description;
		
	private String coverImage;
	
	
	
	
	
	
	
	
	
	
	
	
}
