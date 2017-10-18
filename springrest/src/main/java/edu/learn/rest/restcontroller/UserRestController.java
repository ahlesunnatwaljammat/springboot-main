package edu.learn.rest.restcontroller;

import edu.learn.rest.beans.User;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserRestController {

    /**
     * This login is being called by form data
     * Note: header doesn't contain content-type
     * @param username - contains username
     * @param password - contains password
     * @return - return user entity
     */
    @RequestMapping( value = "/user/login", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public User login(@RequestParam("username") String username, @RequestParam("password") String password ){
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        return user;
    }

    /**
     * This login is being called by application/json
     * Note: header contains content-type: application/json
     * @param user - entity contains user information
     * @return
     */
    @RequestMapping( value = "/user/login", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public User login(@RequestBody User user){
        return user;
    }
}
