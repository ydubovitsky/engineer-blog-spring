package ru.ydubovitsky.engineerBlog.dto;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProjectResponseDTO {

    private Short id;

    private String title;

    private String description;

    private String projectURL;

    private String imageSRC;

    private String repositoryURL;

}
