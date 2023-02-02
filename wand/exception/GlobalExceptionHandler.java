package huffle.puff.wand.exception;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import huffle.puff.wand.dtos.ApiResponseMessage;

@RestControllerAdvice  // previously i used @RestController instead of this
public class GlobalExceptionHandler {
   
      //handle resource not found exception
  private  Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
   

     @ExceptionHandler(ResourceNotFoundException.class)
   public ResponseEntity<ApiResponseMessage> 
         resourceNotFoundExceptionHandler(ResourceNotFoundException ex)   
   {

    logger.info("Exception Handler Invoked !!");
     ApiResponseMessage response = ApiResponseMessage
                        .builder()
                        .message(ex.getMessage())
                        .status(HttpStatus.NOT_FOUND)
                        .success(true)
                        .build();
      return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
   }


   //MethodArgumentNotValidException
   @ExceptionHandler(MethodArgumentNotValidException.class)
   public ResponseEntity<Map<String,Object>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex)
{
      List<ObjectError> allErrors = ex.getBindingResult()
                                      .getAllErrors();
      Map<String,Object> response = new HashMap<>();
      allErrors.stream().forEach(objectError 
                               ->{String message = objectError.getDefaultMessage();
                                  String field= ((FieldError)objectError ).getField();
                                    response.put(field,message);});   
                                    
        return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
}

// handle api exception with bad request
@ExceptionHandler(BadApiRequest.class)
public ResponseEntity<ApiResponseMessage> 
      handleBadApiRequest(BadApiRequest ex)   
{

 logger.info("Bad Api Request !!");
  ApiResponseMessage response = ApiResponseMessage
                     .builder()
                     .message(ex.getMessage())
                     .status(HttpStatus.BAD_REQUEST)
                     .success(false)
                     .build();
   return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
}


}
