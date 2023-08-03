package ru.ydubovitsky.engineerBlog.dto;

import lombok.*;

@Data
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProjectRequestDTO {

    private String title;

    private String description;

    private String projectURL;

    private String repositoryURL;

    private String imageSRC;

}
