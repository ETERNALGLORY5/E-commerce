package huffle.puff.wand.exception;

import lombok.Builder;

@Builder   //previously forgot to use this exception
public class ResourceNotFoundException extends RuntimeException{
    

    private static final long serialVersionUID = 1L;

	public ResourceNotFoundException()
    {
        super("Resource not found !!");
    }

    //parameterized exception, if we write msg in exception
    public ResourceNotFoundException (String message){
                     super(message);
    }
}
