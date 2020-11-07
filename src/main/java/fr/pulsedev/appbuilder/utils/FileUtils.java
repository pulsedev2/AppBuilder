package fr.pulsedev.appbuilder.utils;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;


import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class FileUtils {

    public final static int DEFAULT_SUB_FILES_THRESHOLD = 50;

    /**
     *
     * @param directory the directory where the scan start
     * @param threshold the max number of file, when the returned list is bigger the method stop scanning
     * @return a {@link java.util.List} of files under directory
     */
    public static List<File> getSubFiles(File directory, int threshold){

        // Get all files
        File[] files = directory.listFiles();
        // Init final list
        List<File> subFiles = new ArrayList<>();

        // Check if given directory isn't empty
        if (files != null){
            //Iterate through files in the given directory
            for (File file : files) {
                if(file.isFile()){
                    // If it's file add it to final list
                    subFiles.add(file);
                }
                else if (file.isDirectory()){
                    // If it's a directory search through it for files
                    subFiles.addAll(getSubFiles(file, threshold));
                }

                if(subFiles.size() >= threshold){
                    break;
                }
            }
        }

        return subFiles;
    }

    /**
     * threshold will be {@link FileUtils#DEFAULT_SUB_FILES_THRESHOLD}
     * @see #getSubFiles(File, int threhold)
     */
    public static List<File> getSubFiles(File directory){
        return getSubFiles(directory, DEFAULT_SUB_FILES_THRESHOLD);
    }


    /**
     *
     * @param directory the directory where the scan start
     * @return a {@link java.util.List} of sub directory. <b>The returned directed are juste the first ones</b>
     */
    public static List<File> getSubFolders(File directory){

        // Get all first sub file
        File[] files = directory.listFiles();
        // Initiate the list that will be returned
        List<File> subDir = new ArrayList<>();

        // Check if the given directory is not empty
        if (files != null){
            // Iterate through files
            for (File file: files){
                // Check if it's a directory
                if (file.isDirectory()){
                    subDir.add(file);
                }
            }
        }

        return subDir;
    }

    /**
     *
     * @param xmlFile has to be an proper XML File
     * @return a {@link org.w3c.dom.Document} of the parsed xmlFile
     */
    public static Document getDocument(File xmlFile){
        try{
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(xmlFile);
            document.getDocumentElement().normalize();
            return document;
        }catch (ParserConfigurationException | IOException | SAXException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Used to know if a given directory is empty or not
     * @param directory a path to a directory
     * @return true if the directory is empty and false if it's a not a proper directory or if it's not empty
     */
    public static boolean isDirectoryEmpty(Path directory){
        try(DirectoryStream<Path> dirStream = Files.newDirectoryStream(directory)) {
            return !dirStream.iterator().hasNext();
        }catch (IOException ignored){
            return false;
        }
    }
}
