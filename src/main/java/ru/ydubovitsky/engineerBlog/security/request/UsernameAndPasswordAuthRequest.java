package ru.ydubovitsky.engineerBlog.security.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UsernameAndPasswordAuthRequest {

    private String username;
    private String password;

}
