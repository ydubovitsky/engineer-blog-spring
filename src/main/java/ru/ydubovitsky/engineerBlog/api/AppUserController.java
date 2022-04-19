package ru.ydubovitsky.engineerBlog.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.ydubovitsky.engineerBlog.entity.AppUser;
import ru.ydubovitsky.engineerBlog.entity.Post;
import ru.ydubovitsky.engineerBlog.service.user.AppUserService;

import java.util.Collection;
import java.util.Set;

@Slf4j
@RestController
@RequestMapping("/api/v1/user")
public class AppUserController {

    private final AppUserService appUserService;

    public AppUserController(@Qualifier("mock") AppUserService appUserService) {
        this.appUserService = appUserService;
    }

    @GetMapping("/{username}/posts")
    public ResponseEntity<Collection<Post>> getUserPosts(@PathVariable(name = "username") String username) {
        AppUser appUser = appUserService.getAppUserByName(username);
        Set<Post> postList = appUser.getPostList();

        return ResponseEntity.ok(postList);
    }
}
