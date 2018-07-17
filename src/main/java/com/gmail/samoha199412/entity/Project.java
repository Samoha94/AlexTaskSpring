package com.gmail.samoha199412.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.*;

@Entity
@Table(name = "Projects")
public class Project {

    public interface ShowProject {};


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonView(value = {ShowProject.class, User.ShowUser.class})
    private Long id;

    @Column(name = "ProjectName")
    @JsonView(value = {ShowProject.class, User.ShowUser.class})
    private String projectName;

    @Column(name = "FilePath")
    @JsonView(value = {ShowProject.class, User.ShowUser.class})
    private String filePATH;

    @Column(name = "WebPath")
    @JsonView(value = {ShowProject.class, User.ShowUser.class})
    private String WebPath;

    @Column(name = "FileName")
    @JsonView(value = {ShowProject.class, User.ShowUser.class})
    private String fileNAME;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
//    @JsonIgnore
    private User user;

    public Project(String projectName, User user) {
        this.projectName = projectName;
        this.user = user;
    }

    public  Project(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    public String getFilePATH() {
        return filePATH;
    }

    public void setFilePATH(String filePATH) {
        this.filePATH = filePATH;
    }

    public String getFileNAME() {
        return fileNAME;
    }

    public void setFileNAME(String fileNAME) {
        this.fileNAME = fileNAME;
    }

    public String getWebPath() {
        return WebPath;
    }

    public void setWebPath(String webPath) {
        WebPath = webPath;
    }

    @Override
    public String toString() {
        return "Project{" +
                "id=" + id +
                ", projectName='" + projectName + '\'' +
                ", fileURL='" + filePATH + '\'' +
                ", user=" + user +
                '}';
    }
}
