package fr.pulsedev.appbuilder.projects;

public class ProjectOptions {

    double version = (double) ProjectOptionType.VERSION.defaultValue;
    String name = (String) ProjectOptionType.NAME.defaultValue;

    public double getVersion() {
        return version;
    }

    public void setVersion(double version) {
        this.version = version;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
