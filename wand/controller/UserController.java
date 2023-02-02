package huffle.puff.wand.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import huffle.puff.wand.dtos.ApiResponseMessage;
import huffle.puff.wand.dtos.PageableResponse;
import huffle.puff.wand.dtos.UserDto;
import huffle.puff.wand.services.FileService;
import huffle.puff.wand.services.UserService;
import jakarta.validation.Valid;
//import lombok.Value;
import huffle.puff.wand.dtos.ImageResponse;

@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private UserService userService;
 
	@Autowired
	private FileService fileService;

	@Value("${user.profile.image.path}")
	private String imageUploadPath;
	
	
	private Logger logger = LoggerFactory.getLogger(UserController.class);
	
	
	
	// CREATE
	/*
	 * we use ResponseEntity bcoz with data we also share the response, we use UserDto
	 *  to send the data and it's name will be createUser. since we require data so we
	 *  use UserDto with name userDto with @RequestBody.
	 *  
	 *  the method we require to create user is held with UserService so @Autowired it.
	 *  
	 *  
	 */
	
	@PostMapping
	public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto)
	{
		UserDto user = userService.createUser(userDto);
		return new ResponseEntity<>(user, HttpStatus.CREATED);
		//
	}
	
	//UPDATE
	/*
	 * we get data and userId which we have to update.
	 * we return what we update. means UserDto.
	 * @PathVariable is used to fetch the value of {userId} into the variable "Sting
	 * userId" .
	 * the
	 * we get the new data in form of JSON in @RequestBody
	 */
	
	@PutMapping("/{userId}")
	public ResponseEntity<UserDto> updateUser
	     (@PathVariable("userId") String userId,
	    	@Valid @RequestBody UserDto userDto	 )
	{
		UserDto updatedUserDto = userService.updateUser(userDto, userId);
		return new ResponseEntity<>(updatedUserDto, HttpStatus.OK);
	}
	
	
	
	// DELETE
	/*we should not take String data type in ResponseEntity, will handle it later.
	 * since normal string is not the JSON data.
	 * since java method variable String userId and path variable {userId} are same 
	 * so we don't provide variable for PathVariable.
	 * 
	 */
	
	@DeleteMapping("/{userId}")
	public ResponseEntity<ApiResponseMessage> deleteUser(@PathVariable String userId)
	{
		userService.deleteUser(userId);
		ApiResponseMessage message =  ApiResponseMessage
				.builder()
				.message("User Is deleted successfully ")
				.success(true)
				.status(HttpStatus.OK)
				.build();
		return new ResponseEntity<ApiResponseMessage>(message ,HttpStatus.OK);
	}
	
	
	
	//GETALL
	/*we use list bcoz we get whole list of data
	 * 
	 */
	
	@GetMapping		
	public ResponseEntity<PageableResponse<UserDto>> getAllUser(
		@RequestParam (value = "pageNumber",defaultValue = "0",required = false) int pageNumber,
		@RequestParam(value = "pageSize",defaultValue = "10",required = false) int pageSize,

		@RequestParam (value = "sortBy",defaultValue = "name",required = false) String sortBy,
		@RequestParam(value = "sortDir",defaultValue = "ASC",required = false) String sortDir
	)
	{
		return new ResponseEntity<>(userService.getAllUser(pageNumber, pageSize,sortBy,sortDir),HttpStatus.OK);
	}
			
	
	
	//GET SINGLE USER BY ID
	/*
	 * 
	 */
	
	@GetMapping("/{userId}")
	public ResponseEntity<UserDto> getUser(@PathVariable String userId)
	{
		return new ResponseEntity<>(userService.getUserById(userId),HttpStatus.OK);
	}
	
	
	
	
	// GET SINGLE USER BY EMAIL
	
	@GetMapping("/email/{email}")
	public ResponseEntity<UserDto> getUserByEmail(@PathVariable String email)
	{
		return new ResponseEntity<>(userService.getUserByEmail(email),HttpStatus.OK);
	}
	
	
	//SEARCH USER
	/*
	 * if String and URL request value are same we don't required to provide value with
	 * @Pathvariable
	 * 
	 * when we search we get list of userDto so we use List<> .
	 */
	
	@GetMapping("/search/{keywords}")
	public ResponseEntity<List<UserDto>> searchUser(@PathVariable String keywords)
	{
		return new ResponseEntity<>(userService.searchUser(keywords),HttpStatus.OK);
	}


	//UPLOAD USER IMAGE
	
    @PostMapping("/image/{userId}")
	public ResponseEntity<ImageResponse> uploadUserImage
	  //we write the name of key of file we passed in RequestParameter
	                (@RequestParam("userImage") MultipartFile image,@PathVariable String userId) throws IOException
    	{

		String imageName =   fileService.uploadFile(image,imageUploadPath);

		 UserDto user = userService.getUserById((userId));
		 user.setImageName(imageName);
         UserDto userDto = userService.updateUser(user, userId);

	ImageResponse imageResponse = ImageResponse.builder()
			                      .imageName(imageName)
			                      .success(true)
			                  
			                      .status(HttpStatus.CREATED)
			                      .build();
//								 .success(true)
//								 .status(HttpStatus.CREATED)
//	                             .build();
	                               

		
			  return new ResponseEntity<>(imageResponse, HttpStatus.CREATED);
		
	}




	// SERVE USER IMAGE
    
    @GetMapping("/image/{userId}")
    public void serveUserImage(@PathVariable String userId, Http) throws FileNotFoundException
    {
    	UserDto user = userService.getUserById(userId);
    	logger.info("User Image Name : {}",user.getImageName());
    	InputStream resource = fileService.getResource(imageUploadPath, user.getImageName());
    	
    	
    }
    
    
    
    
}
