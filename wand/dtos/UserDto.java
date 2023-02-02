package huffle.puff.wand.dtos;


import huffle.puff.wand.validate.ImageNameValid;
//import evon.tech.learning.validate.ImageNameValid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
//import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/*
 * we dont provide direct access to our entity layer User.java because it is unsafe
 * so we are creating another package of dto(data transfer object) to make 
 * contacts with service and controller layer.
 * 
 * we make/edit tables and column through User.java class only and not from Dto classes.
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {

    private String userId;

    
    @Size(min = 3, max=15, message = "Invalid Name !!")
    @NotBlank(message  ="Name is required !!")
   // @Column(name ="name")
    private String name;

  //  @Column(name = "user_name")
    private String username;

    //@Pattern(regexp = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\\\.[A-Za-z0-9-]+)*(\\\\.[A-Za-z]{2,})$" , message= "Invalid User Email")
    @Email(message = "invalid User Email !!")
    @NotBlank(message  ="Email is required !!")
  //  @Column(name = "user_email",unique=true)
    private String email;

    
    @NotBlank(message  ="Password is required !!")
  //  @Column(name = "user_password",length =500)
    private String password;


    @Size(min = 4, max = 6, message= "invalid User !!")
    @NotBlank(message  ="gender is required !!")
 //   @Column(name = "user_gender")
    private String gender;

 
    
    @ImageNameValid
    //  @Column(name = "user_image_name")
    private String imageName;


    // @Pattern
    // Custom Validator
}
