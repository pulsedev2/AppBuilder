package fr.pulsedev.bootstrap;

import fr.pulsedev.bootstrap.Ui.Window;
import fr.pulsedev.bootstrap.utils.Downloader;

import javax.swing.*;
import java.io.File;
import java.io.IOException;

public class Main {
    private static final String fileLocation = System.getenv("APPDATA") + "\\AppBuilder\\Bootstrap\\AppBuilder.jar";
    private static Window window;

    public static void main(String[] args) {
        window = new Window();

        update();

        launch();

        window.close();
    }

    private static void update() {
        JProgressBar progressBar = window.getBootstrapPanel().progressBar;

        Downloader downloader = new Downloader("https://github.com/pulsedev2/AppBuilder/releases/latest/download/AppBuilder.jar", new File(fileLocation));
        downloader.connect();

        progressBar.setValue(0);
        progressBar.setMaximum(downloader.getSize());

        downloader.startAsync();

        while (progressBar.getValue() != progressBar.getMaximum() && downloader.getState() != 4) {
            progressBar.setValue(downloader.getDownloaded());

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static void launch() {
        try {
            Runtime.getRuntime().exec("cmd /c" + fileLocation);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
