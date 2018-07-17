package com.gmail.samoha199412.services;

import com.gmail.samoha199412.entity.Project;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface ProjectService {
    List<Project> getAll();

    Optional<Project> findProject(Long id);

    Project save(Project project);

    Project refreshProject(Long id, Project project);

    void deleteProject(Long id);

    void deleteAllProject();

    void addFile(Long id, MultipartFile file) throws IOException;

    void resultFile(Long id, HttpServletResponse response);
}
