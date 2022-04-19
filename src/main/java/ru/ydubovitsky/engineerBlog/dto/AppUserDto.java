package ru.ydubovitsky.engineerBlog.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import ru.ydubovitsky.engineerBlog.entity.Contacts;
import ru.ydubovitsky.engineerBlog.entity.enums.Role;

import java.time.LocalDateTime;
import java.util.Set;

@Builder
@Getter @Setter
public class AppUserDto {

    private Integer id;
    private String jwttoken;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String about;
    private Contacts contacts;
    private Set<Role> roles;
    private LocalDateTime createdDate;

}
