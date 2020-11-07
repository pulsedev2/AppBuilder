package fr.pulsedev.appbuilder.projects.errors;

import fr.pulsedev.appbuilder.projects.Project;

public abstract class ProjectErrors extends Exception {

    Project project;

    public ProjectErrors(Project project) {
        this.project = project;
    }
}
