package ru.ydubovitsky.engineerBlog.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.ydubovitsky.engineerBlog.entity.Project;
import ru.ydubovitsky.engineerBlog.repository.ProjectRepository;

import java.util.List;
import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProjectService {

    private final ProjectRepository projectRepository;

    public void saveProject(Project project) {
        projectRepository.save(project);
        log.info(String.format("Project %s saved", project.getTitle()));
    }

    public List<Project> getProjectsList() {
        return projectRepository.findAll();
    }

    public Project getProjectById(Short id) {
        return projectRepository.findById(id).orElseThrow(
                () -> new RuntimeException(String.format("Project with id: %s not found!", id))
        );
    }

    public void deleteProjectById(Short id) {
        projectRepository.deleteById(id);
        log.info(String.format("Project with id %s deleted", id));
    }

    public void updateProjectById(Short id, Project project) {
        Project projectFromDatabase = projectRepository.findById(id).orElseThrow(
                () -> new RuntimeException(String.format("Project with id: %s not found!", id))
        );
        if(Objects.nonNull(project.getImageSRC())) {
            projectFromDatabase.setImageSRC(project.getImageSRC());
        }
        projectFromDatabase.setTitle(project.getTitle());
        projectFromDatabase.setDescription(project.getDescription());
        projectFromDatabase.setProjectURL(project.getProjectURL());
        projectFromDatabase.setRepositoryURL(project.getRepositoryURL());

        projectRepository.save(projectFromDatabase);
        log.info(String.format("Project %s updated", project.getTitle()));
    }
}
