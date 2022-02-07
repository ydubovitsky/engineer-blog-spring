package ru.ydubovitsky.engineerBlog.requests.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ru.ydubovitsky.engineerBlog.entity.Post;

import java.util.List;

@Getter @Setter
@AllArgsConstructor
public class PagingPostWithAllPostCount {

    private List<Post> pagingPosts;
    private Integer allPostsCount;

}
