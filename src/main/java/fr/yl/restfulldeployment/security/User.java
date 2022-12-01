package fr.yl.restfulldeployment.security;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {
    private String login;
    private String password;
}
