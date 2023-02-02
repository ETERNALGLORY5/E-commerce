package huffle.puff.wand.exception;

public class BadApiRequest extends RuntimeException{
    
  private static final long serialVersionUID = 1L;

public   BadApiRequest(String message)
  {
    super(message);
  }

  public BadApiRequest()
  {
    super("Bad Request !!");
  }

}
