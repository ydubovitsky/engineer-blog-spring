package ru.ydubovitsky.engineerBlog.service.user;

import ru.ydubovitsky.engineerBlog.entity.AppUser;

public interface AppUserService {

    AppUser getAppUserByName(String username);

}
