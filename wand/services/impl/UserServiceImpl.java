package huffle.puff.wand.services.impl;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
//import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import huffle.puff.wand.dtos.PageableResponse;
import huffle.puff.wand.dtos.UserDto;
import huffle.puff.wand.entities.User;
import huffle.puff.wand.exception.ResourceNotFoundException;
import huffle.puff.wand.helper.Helper;
import huffle.puff.wand.repositories.UserRepository;
import huffle.puff.wand.services.UserService;



@Service

public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ModelMapper mapper;

	public UserDto createUser(UserDto userDto) {
		//generate unique id in string format
		String userId = UUID.randomUUID().toString();
		userDto.setUserId(userId);
		//dto -> entity
		User user =dtoToEntity(userDto);
	    User savedUser = userRepository.save(user);
	    //entity -> dto
	    UserDto newDto = entityToDto(savedUser);
		return newDto;
	}

	
	public UserDto updateUser(UserDto userDto, String userId) {
		
		User user = userRepository.findById(userId).orElseThrow(( ) -> new ResourceNotFoundException("User not found by thi s Id"));
		
		user.setName(userDto.getName());
		user.setGender(userDto.getGender());
		user.setPassword(userDto.getPassword());
		user.setImageName(userDto.getImageName());
		
		
		//save data
		
		User updatedUser = userRepository.save(user);
		UserDto updatedDto = entityToDto(updatedUser);
		return updatedDto;
	}

	@Override
	public void deleteUser(String userId) {
	  
		User user = userRepository.findById(userId).orElseThrow(( ) -> new ResourceNotFoundException("User not found by thi s Id"));
		//delete user
		
		userRepository.delete(user);
	}

	@Override
	public PageableResponse<UserDto> getAllUser(int pageNumber,int pageSize,String sortBy, String sortDir) {

		//
		//Sort sort = Sort.by(sortBy); 
		Sort sort = (sortDir.equalsIgnoreCase("desc")) ? (Sort.by(sortBy).descending()) : (Sort.by(sortBy).ascending()) ; 
		//this page num default starts from 0
		Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
             /*
			  * //pageable will not return List direct
			      it will return obj of page
				  from which we can get content as List<>;
			  */
        Page<User> page = userRepository.findAll(pageable);
		 PageableResponse<UserDto> response = Helper.getPageableresponse(page , UserDto.class);
					return response;
		          // return dtoList;
	}

	@Override
	public UserDto getUserById(String userId) {
		User user = userRepository.findById(userId).orElseThrow(( ) -> new ResourceNotFoundException("User not found by thi s Id"));
		return entityToDto(user);
	}

	//very imp topic
	@Override
	public UserDto getUserByEmail(String email) {
	
	User user = userRepository.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("user not with given email ID and password"));
		return entityToDto(user);
	}

	public List<UserDto> searchUser(String keywords) {
	List<User> users=   userRepository.findByNameContaining(keywords);
	List<UserDto> dtoList = users.stream().map(user -> entityToDto(user)).collect(Collectors.toList());
		return dtoList;
	}
		
	
	private UserDto entityToDto(User savedUser) {
		
//	    UserDto userDto = UserDto.builder() 
//	             .userId(savedUser.getUserId())
//	             .name(savedUser.getName())
//	             .email(savedUser.getEmail())
//	             .password(savedUser.getPassword())
//	             .gender(savedUser.getGender())
//	             .imageName(savedUser.getImagename())
//	             .build();
		//return userDto;
		return mapper.map(savedUser,UserDto.class);
	}

	private User dtoToEntity(UserDto userDto) {
	
//		User user =  User.builder()
//		      .userId(userDto.getUserId())
//		      .name(userDto.getName())
//		      .email(userDto.getEmail())
//		      .password(userDto.getPassword())
//		      .gender(userDto.getGender())
//		      .imageName(userDto.getImagename())
//		      .build();
//		
//		return user;
		
		return mapper.map(userDto, User.class);   //(source , destination)
		
		
	}

}

