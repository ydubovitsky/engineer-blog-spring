package ru.ydubovitsky.engineerBlog.facade;

import ru.ydubovitsky.engineerBlog.dto.ProjectRequestDTO;
import ru.ydubovitsky.engineerBlog.dto.ProjectResponseDTO;
import ru.ydubovitsky.engineerBlog.entity.Project;

public class ProjectFacade {

    public static Project ProjectRequestDTOToProject(ProjectRequestDTO projectRequestDTO) {
        return Project.builder()
                .title(projectRequestDTO.getTitle())
                .imageSRC(projectRequestDTO.getImageSRC())
                .projectURL(projectRequestDTO.getProjectURL())
                .repositoryURL(projectRequestDTO.getRepositoryURL())
                .description(projectRequestDTO.getDescription())
                .build();
    }

    public static ProjectResponseDTO ProjectToProjectResponseDTO(Project project) {
        return ProjectResponseDTO.builder()
                .id(project.getId())
                .title(project.getTitle())
                .description(project.getDescription())
                .imageSRC(project.getImageSRC())
                .repositoryURL(project.getRepositoryURL())
                .projectURL(project.getProjectURL())
                .build();
    }

}
