package nl.novi.homeprojects.exeptions;


public class IndexOutOfBounceException extends RuntimeException{

    public IndexOutOfBounceException() {
        super();
    }
    public IndexOutOfBounceException(String message) {
        super(message);
    }
}


