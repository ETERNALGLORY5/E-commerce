package huffle.puff.wand.dtos;

import org.springframework.http.HttpStatus;

//import huffle.puff.wand.dtos.ApiResponseMessage.ApiResponseMessageBuilder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
       
    
public class ImageResponse {
	
    private String imageName;
	private String message;
	private boolean success;
	private HttpStatus status;
   
//	public static Object builder() {
//        return null;
//    }
//	

}