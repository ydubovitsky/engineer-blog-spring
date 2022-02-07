package ru.ydubovitsky.engineerBlog.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(columnDefinition="TEXT")
    private String text;
    private String category;
    @Column(columnDefinition="TEXT")
    private String title;
    private String date;
    private String author;
    @Column(columnDefinition="TEXT")

    @Lob
    private byte[] postImage;
    @Column(columnDefinition="TEXT")
    private String disclosure;
    @Column(columnDefinition="TEXT")
    private String description;

    @OneToMany(fetch = FetchType.EAGER)
    private List<SubPost> subPosts;

    @ManyToOne
    private AppUser appUser;

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", category='" + category + '\'' +
                ", title='" + title + '\'' +
                ", date='" + date + '\'' +
                ", author='" + author + '\'' +
                ", imageSource='" + postImage + '\'' +
                ", disclosure='" + disclosure + '\'' +
                ", description='" + description + '\'' +
                ", subPosts=" + subPosts +
                ", appUser=" + appUser +
                '}';
    }
}
