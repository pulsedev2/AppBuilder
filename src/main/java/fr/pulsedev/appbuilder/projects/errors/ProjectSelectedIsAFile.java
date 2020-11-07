package fr.pulsedev.appbuilder.projects.errors;

import fr.pulsedev.appbuilder.projects.Project;

public class ProjectSelectedIsAFile extends ProjectErrors{

    public ProjectSelectedIsAFile(Project project) {
        super(project);
        System.err.println(project.getDirectory().getName() + " is a File !");
    }
}
