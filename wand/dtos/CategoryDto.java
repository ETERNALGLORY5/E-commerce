package huffle.puff.wand.dtos;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDto {

	
	
	private String categoryId;
	
	 @Size(min = 3, max=15, message = "Invalid Category Title !!")
	 @NotBlank(message  ="Title is required, min 3 character !!")
	private String categoryTitle;
	
	 @Size(min = 3, max=15, message = "Invalid description !!")
	 @NotBlank(message  ="Description is required !!")
	private String description;
	
	 
	
	 @NotBlank(message  ="cover image is required !!")
	private String coverImage;
}
