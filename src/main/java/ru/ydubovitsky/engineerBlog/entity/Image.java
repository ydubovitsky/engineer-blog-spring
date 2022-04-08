package ru.ydubovitsky.engineerBlog.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;


@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    public Image(byte[] byteImage) {
        this.byteImage = byteImage;
    }

    @Column(columnDefinition = "text")
    private String name;

    @Lob
    private byte[] byteImage;

    @JsonIgnore
    private Integer userId;

    @JsonIgnore
    private Integer postId;

    @JsonIgnore
    private Long subPostId;

    public Image(String name, byte[] byteImage) {
        this.name = name;
        this.byteImage = byteImage;
    }
}
