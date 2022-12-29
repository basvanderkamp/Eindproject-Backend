package nl.novi.homeprojects.exeptions;

public class UsernameNotFoundException extends RuntimeException {

    public UsernameNotFoundException(String username) {
        super("Cannot find user " + username);
    }

}