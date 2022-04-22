package ru.ydubovitsky.engineerBlog.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;


@Entity
@Builder
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(columnDefinition="TEXT")
    private String text;

    private String category;

    @Builder.Default
    private Integer views = 0;

    @Column(columnDefinition="TEXT")
    private String title;

    @JsonFormat(pattern = "yyyy-mm-dd")
    private LocalDateTime createAt;

    @JsonFormat(pattern = "yyyy-mm-dd")
    private LocalDateTime updatedAt;

    @Column(columnDefinition="TEXT")
    private String author;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private Image postImage;

    @Column(columnDefinition="TEXT")
    private String disclosure;

    @Column(columnDefinition="TEXT")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    private AppUser appUser;

    @Builder.Default
    private Boolean isDeleted = false;

    @OneToMany(
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER,
            mappedBy = "post",
            orphanRemoval = true
    )
    private Set<Comment> comments;

    @OneToMany(fetch = FetchType.EAGER)
    private List<SubPost> subPosts;

    @PrePersist
    private void setCreatedAt() {
        this.createAt = LocalDateTime.now();
    }

    @PreUpdate
    private void updateUpdatedAt() {
        this.updatedAt = LocalDateTime.now();
    }
}
