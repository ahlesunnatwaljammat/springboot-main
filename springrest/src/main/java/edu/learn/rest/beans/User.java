package edu.learn.rest.beans;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

//@Data
public class User {
    private @Getter @Setter String userId;
    private @Getter @Setter String username;
    private @Getter @Setter String password;
    private @Getter @Setter String firstName;
    private @Getter @Setter String lastName;

}
