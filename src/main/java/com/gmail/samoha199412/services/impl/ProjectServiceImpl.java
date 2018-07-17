package com.gmail.samoha199412.services.impl;

import com.gmail.samoha199412.dao.ProjectRepository;
import com.gmail.samoha199412.entity.Project;
import com.gmail.samoha199412.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@Service
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;

    @Autowired
    public ProjectServiceImpl(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @Override
    public List<Project> getAll() {
        return projectRepository.findAll();
    }

    @Override
    public Project save(Project project) {
        return projectRepository.save(project);
    }

    @Override
    public Optional<Project> findProject(Long id) {
        return projectRepository.findById(id);
    }

    @Override
    public Project refreshProject(Long id, Project project) {
        Project projectFromDB = projectRepository.getOne(id);
        if (projectFromDB != null) {
            projectFromDB.setProjectName(project.getProjectName());
            return projectRepository.saveAndFlush(projectFromDB);
        }
        return project;
    }

    @Override
    public void deleteProject(Long id) {
        projectRepository.deleteById(id);
    }

    @Override
    public void deleteAllProject() {
        projectRepository.deleteAll();
    }


    @Override
    public void addFile(Long id, MultipartFile file) throws IOException {
        Optional<Project> project = projectRepository.findById(id);
        Project prFrDB = project.get();
        String name = file.getOriginalFilename();
        prFrDB.setFileNAME(name);

        Path path = Paths.get("/home/samoha/My/Files/" + name);

        prFrDB.setFilePATH(path.toString());
        prFrDB.setWebPath("http://localhost:8080/demoProject/projects/"+id+"/path");

        Files.write(path, file.getBytes());

        projectRepository.save(prFrDB);

    }


    @Override
    public void resultFile(Long id, HttpServletResponse response) {
        Optional<Project> projectFromDB = projectRepository.findById(id);
        Project prFmDB = projectFromDB.get();

        File file = new File(prFmDB.getFilePATH());
        Path path = Paths.get(prFmDB.getFilePATH());

        long length = file.length() ;
        response.setContentType(prFmDB.getFileNAME());
        response.setHeader("Content-Range", "bytes 0-" + (length-1) +"/" + length);

        try {
            Files.copy(path, response.getOutputStream());
            response.getOutputStream().flush();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


}
