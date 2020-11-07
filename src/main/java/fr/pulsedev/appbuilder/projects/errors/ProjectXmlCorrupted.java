package fr.pulsedev.appbuilder.projects.errors;

import fr.pulsedev.appbuilder.projects.Project;

public class ProjectXmlCorrupted extends ProjectErrors{

    public ProjectXmlCorrupted(Project project) {
        super(project);
        System.err.println("XML project files are unreadable or not existent.");
    }
}
