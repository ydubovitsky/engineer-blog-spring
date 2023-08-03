package ru.ydubovitsky.engineerBlog.api;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.ydubovitsky.engineerBlog.dto.PostUpdateRequestDTO;
import ru.ydubovitsky.engineerBlog.dto.ProjectRequestDTO;
import ru.ydubovitsky.engineerBlog.dto.ProjectResponseDTO;
import ru.ydubovitsky.engineerBlog.entity.Project;
import ru.ydubovitsky.engineerBlog.facade.ProjectFacade;
import ru.ydubovitsky.engineerBlog.service.ProjectService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/project")
@AllArgsConstructor
public class ProjectController {

    private ProjectService projectService;

    @PostMapping
    @PreAuthorize("hasAnyAuthority('ADMIN', 'SUPER_ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    public HttpStatus addNewProject(@RequestBody ProjectRequestDTO projectRequestDTO) {
        projectService.saveProject(ProjectFacade.ProjectRequestDTOToProject(projectRequestDTO));
        return HttpStatus.OK;
    }

    @GetMapping(params = "id")
    public ResponseEntity<ProjectResponseDTO> getProject(@RequestParam(name = "id") Short id) {
        Project projectById = projectService.getProjectById(id);
        return ResponseEntity.ok(ProjectFacade.ProjectToProjectResponseDTO(projectById));
    }

    @GetMapping
    public ResponseEntity<List<ProjectResponseDTO>> getAllProjects() {
        List<ProjectResponseDTO> projectsList = projectService.getProjectsList().stream()
                .map(project -> ProjectFacade.ProjectToProjectResponseDTO(project))
                .collect(Collectors.toList());
        return ResponseEntity.ok(projectsList);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAnyAuthority('ADMIN', 'SUPER_ADMIN')")
    public void deleteProjectById(@PathVariable Short id) {
        projectService.deleteProjectById(id);
    }

    @PutMapping("{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'SUPER_ADMIN')")
    @ResponseStatus(HttpStatus.OK)
    public void updatePost(@PathVariable Short id, @RequestBody ProjectRequestDTO projectRequestDTO) {
        Project project = ProjectFacade.ProjectRequestDTOToProject(projectRequestDTO);
        projectService.updateProjectById(id, project);
    }

}
