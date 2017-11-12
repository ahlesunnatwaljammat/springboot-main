package edu.learn.rest.restcontroller;

import edu.learn.rest.beans.User;
import edu.learn.security.SpringDataJpaUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

@RestController
public class UserRestController {

    @Value( "${allowed.domains}" )
    private String allowedDomains;

    @Autowired
    private SpringDataJpaUserDetailsService userDetailsService;

    public static final String uploadingdir = System.getProperty("user.dir") + "/uploadingdir/";

    /**
     * This login is being called by form data
     * Note: header doesn't contain content-type
     * @param username - contains username
     * @param password - contains password
     * @return - return user entity
     */
    @RequestMapping( value = "/user/login", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public User login(HttpServletRequest request, HttpServletResponse response, @RequestParam("username") String username, @RequestParam("password") String password ){
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);

        this.userDetailsService.loadUserByUsername(user.getUsername());
        request.getSession().setAttribute("loggedInUser", user);

        return user;
    }

    /**
     * This login is being called by application/json
     * Note: header contains content-type: application/json
     * @param user - entity contains user information
     * @return
     */
    @RequestMapping( value = "/user/login", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public User login(HttpServletRequest request, HttpServletResponse response, @RequestBody User user){
        request.getSession().setAttribute("loggedInUser", user);
        return user;
    }

    /**
     * This profile is being called by form data
     * Note: header contains content-type: application/json
     * @param profileId - contains profile id to load user profile
     * @return
     */
    @RequestMapping( value = "/user/profile", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public User profile(@RequestParam("profileId") String profileId){
        User user = new User();
        user.setUserId("profile-xxxxxxxxxxxx-1");
        user.setUsername("nabbasi");
        user.setFirstName("Noman Ali");
        user.setLastName("Abbasi");
        return user;
    }

    /**
     *
     * @param userId
     * @param files
     * @return
     * @throws IOException
     */
    @RequestMapping( value = "/user/upload", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public User upload(@RequestParam("userId") String userId, @RequestParam("files") MultipartFile[] files) throws IOException {
        User user = new User();
        user.setUserId("profile-xxxxxxxxxxxx-1");
        user.setUsername("nabbasi");
        user.setFirstName("Noman Ali");
        user.setLastName("Abbasi");

        File makeDirs = new File(uploadingdir);
        if(!makeDirs.exists()){
            makeDirs.mkdirs();
        }

        for(MultipartFile uploadedFile : files) {
            java.nio.file.Path path = FileSystems.getDefault().getPath(uploadingdir + uploadedFile.getOriginalFilename());
			/* Save InputStream as file */
            Files.copy(uploadedFile.getInputStream(), path, REPLACE_EXISTING);
        }

        return user;
    }
}
