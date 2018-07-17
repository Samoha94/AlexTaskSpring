package com.gmail.samoha199412.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.gmail.samoha199412.entity.Project;
import com.gmail.samoha199412.entity.User;
import com.gmail.samoha199412.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(path = "demoProject")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @PostMapping(path = "/projects")
    public @ResponseBody
    String addUserProject(@RequestBody Project project) {
        projectService.save(project);
        return "Well Done";
    }

    @GetMapping(path = "/projects/{id}")
    @JsonView(Project.ShowProject.class)
    public @ResponseBody
    Optional<Project> showProject(@PathVariable("id") Long id) {
        return projectService.findProject(id);
    }

    @GetMapping(path = "/projects")
    @JsonView(Project.ShowProject.class)
    public @ResponseBody
    List<Project> showAllProjects() {
        return projectService.getAll();

    }

    @PutMapping(path = "/projects/{id}")
    public @ResponseBody
    String refreshProject(@PathVariable("id") Long id, @RequestBody Project project) {
        projectService.refreshProject(id, project);
        return "Well done";
    }

    @DeleteMapping(path = "/projects/{id}")
    public @ResponseBody
    String deleteProject(@PathVariable("id") Long id) {
        projectService.deleteProject(id);
        return "Well done";
    }

    @DeleteMapping(path = "/projects")
    public @ResponseBody
    String deleteAllProjects() {
        projectService.deleteAllProject();
        return "Well Done";
    }

    @PostMapping(path = "/projects/{id}")
    public @ResponseBody
    String addFile(@PathVariable("id") Long id ,@RequestParam(value = "file", required = false) MultipartFile file) {
        try {
            projectService.addFile(id,file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "Ok";
    }

    @GetMapping(path = "/projects/{id}/path")
    public void getFileURL(@PathVariable("id") Long id, HttpServletResponse response){

        projectService.resultFile(id, response);

    }
}
