package fr.pulsedev.appbuilder.projects.errors;

import fr.pulsedev.appbuilder.projects.Project;

public class ProjectDirIsNotEmpty extends ProjectErrors{
    public ProjectDirIsNotEmpty(Project project) {
        super(project);
        System.err.println("Try to create a project in a non empty directory");
    }
}
