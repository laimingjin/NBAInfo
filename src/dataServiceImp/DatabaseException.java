package dataServiceImp;

public class DatabaseException extends Exception{
	public DatabaseException(String message){
		super(message);
	}
    public DatabaseException(String message,Exception cause){
    	super(message, cause);
    }
}
