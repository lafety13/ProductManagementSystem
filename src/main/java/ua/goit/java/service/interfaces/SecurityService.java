package ua.goit.java.service.interfaces;

/**
 * Service for Security.
 *
 * @author Eugene Suleimanov
 * @version 1.0
 */

public interface SecurityService {

    String findLoggedInUsername();

    void autoLogin(String username, String password);
}
