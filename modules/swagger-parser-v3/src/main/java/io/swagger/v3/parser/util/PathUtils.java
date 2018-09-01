package io.swagger.v3.parser.util;

import java.net.URI;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PathUtils {


    public static Path getParentDirectoryOfFile(String location) {
        Path file = null;
        try {
            location = location.replaceAll("\\\\","/");
            final String fileScheme = "file:";
            if (location.toLowerCase().startsWith(fileScheme)) {
                file = Paths.get(URI.create(location)).toAbsolutePath();
            } else {
                file = Paths.get(location).toAbsolutePath();
            }
            if(!Files.exists(file)){
                return PathUtils.getResourceParent(location);
            }
        } catch (Exception e) {
            e.getMessage();
        }

        return file.toAbsolutePath().getParent();
    }

    public static Path getParentDirectoryByBasePath(String basePath) {
        Path path = null;
        try {
            basePath = basePath.replaceAll("\\\\","/");
            path = Paths.get(basePath).toAbsolutePath();
            if(!Files.exists(path)){
                return PathUtils.getResourceParent(basePath);
            }
        } catch (Exception e) {
            e.getMessage();
        }
        return path;
    }

    private static Path getResourceParent(String location) { 
        URL url = PathUtils.class.getClassLoader().getResource(location);
        Path file = Paths.get((URI.create(url.toExternalForm())));
        return file.getParent();
    }
}