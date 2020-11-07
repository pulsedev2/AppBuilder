package fr.pulsedev.appbuilder.projects;

public class ProjectOptions {

    double version = (double) ProjectOptionType.VERSION.defaultValue;

    public double getVersion() {
        return version;
    }

    public void setVersion(double version) {
        this.version = version;
    }
}
