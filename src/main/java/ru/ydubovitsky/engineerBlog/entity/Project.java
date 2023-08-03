package ru.ydubovitsky.engineerBlog.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Short id;

    private String title;

    @Column(columnDefinition="TEXT")
    private String description;

    private String projectURL;

    private String repositoryURL;

    private String imageSRC;

}
