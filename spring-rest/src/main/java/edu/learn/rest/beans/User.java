package edu.learn.rest.beans;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

//@Data
public class User  implements Serializable {
    private @Getter @Setter String userId;
    private @Getter @Setter String username;
    private @Getter @Setter String password;
    private @Getter @Setter String firstName;
    private @Getter @Setter String lastName;

}
