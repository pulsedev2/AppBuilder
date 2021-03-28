package fr.pulsedev.bootstrap.utils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Downloader {
    private final File downloadLocation;
    private URL url;
    private int size;
    private int downloaded;
    /*0 = Connection en cours
    1 = Erreur de la connection
    2 = Connection réussi
    3 = Téléchargement en cours
    4 = Téléchargement terminé
    * */
    private int state;

    private HttpURLConnection connection;
    private RandomAccessFile file = null;
    private InputStream in = null;

    private boolean isConnected = false;

    public Downloader(String url, File downloadLocation) {
        try {
            this.url = new URL(url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        this.downloadLocation = downloadLocation;
        this.downloadLocation.getParentFile().mkdirs();

        this.size = -1;
        this.downloaded = 0;
    }

    public void connect() {
        this.state = 0;
        try {
            this.connection = (HttpURLConnection) this.url.openConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.connection.setRequestProperty("Rang", "bytes=" + this.downloaded + "-");

        try {
            this.connection.connect();
        } catch (IOException e) {
            e.printStackTrace();
        }

        int contentLength = this.connection.getContentLength();
        try {
            if ((this.connection.getResponseCode() / 100 != 2) || (contentLength < 1)) {
                this.state = 1;
                return;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (this.size == -1) this.size = contentLength;

        this.state = 2;
        this.isConnected = true;
    }

    public void start() {
        if (!this.isConnected) return;

        try {
            this.state = 3;

            this.file = new RandomAccessFile(this.downloadLocation, "rw");

            if (this.file.length() != this.size) {
                this.in = this.connection.getInputStream();

                while (true) {
                    byte[] buffer;
                    if (this.size - this.downloaded > 1024) {
                        buffer = new byte[1024];
                    } else {
                        buffer = new byte[this.size - this.downloaded];
                    }

                    int read = this.in.read(buffer);
                    if (read == -1)
                        break;

                    this.file.write(buffer, 0, read);
                    this.downloaded += read;
                }
            }

            this.state = 4;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (this.file != null) {
                    this.file.close();
                }

                if (this.in != null) in.close();

                if (this.connection != null) connection.disconnect();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void startAsync() {
        Thread thread = new Thread(this::start);

        thread.start();
    }

    public URL getUrl() {
        return url;
    }

    public File getDownloadLocation() {
        return downloadLocation;
    }

    public int getSize() {
        return size;
    }

    public int getDownloaded() {
        return downloaded;
    }

    public int getState() {
        return state;
    }

    public boolean isConnected() {
        return isConnected;
    }
}
