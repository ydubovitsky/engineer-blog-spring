package ru.ydubovitsky.engineerBlog.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.xml.bind.v2.TODO;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //! Имя человека, который оставил комментарий
    private String name;

    @Column(columnDefinition="TEXT")
    private String message;

    private String email;

    private String website;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "post_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Post post;

    @JsonFormat(pattern = "yyyy-mm-dd")
    private LocalDateTime createdAt;

//    TODO Продумать логику не / зарегистированный ли пользователь
//    private Long userId;

}
