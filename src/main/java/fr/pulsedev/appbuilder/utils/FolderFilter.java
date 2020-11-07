package fr.pulsedev.appbuilder.utils;

import javax.swing.filechooser.FileFilter;
import java.io.File;

public class FolderFilter extends FileFilter {

    boolean empty;

    public FolderFilter(boolean empty){
        this.empty = empty;
    }

    @Override
    public boolean accept(File f) {
        if(empty){
            return f.isDirectory() && FileUtils.isDirectoryEmpty(f.toPath());
        }else return f.isDirectory();
    }

    @Override
    public String getDescription() {
        if(empty){
            return "Accept only empty directories";
        }
        else return "Accept only directories";
    }
}
