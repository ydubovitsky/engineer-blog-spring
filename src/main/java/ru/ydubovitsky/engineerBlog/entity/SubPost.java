package ru.ydubovitsky.engineerBlog.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class SubPost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public SubPost(String text, String sourceCode, String imageDescription) {
        this.text = text;
        this.sourceCode = sourceCode;
        this.imageDescription = imageDescription;
    }

    @ManyToOne
    @JsonIgnore
    private Post post;

    @Column(columnDefinition="TEXT")
    private String text;

    @Column(columnDefinition="TEXT")
    private String sourceCode;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private Image subPostImage;

    @Column(columnDefinition="TEXT")
    private String imageDescription;

    @Override
    public String toString() {
        return "SubPost{" +
                "id=" + id +
                ", post=" + post +
                ", text='" + text + '\'' +
                ", sourceCode='" + sourceCode + '\'' +
                ", image='" + subPostImage + '\'' +
                ", imageDescription='" + imageDescription + '\'' +
                '}';
    }
}
