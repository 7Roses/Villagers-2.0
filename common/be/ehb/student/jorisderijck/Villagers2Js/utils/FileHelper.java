package be.ehb.student.jorisderijck.Villagers2Js.utils;

import java.io.File;
import java.io.FileFilter;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.List;

public class FileHelper {

    public static class ExtensionFilter implements FilenameFilter {
        private final String extension;

        public ExtensionFilter(String extension) {
            this.extension = extension;
        }

        public boolean accept(File dir, String name)
        {
            return (name.endsWith(extension));
        }
    }

    public static class DirectoryFilter implements FileFilter {

        @Override
        public boolean accept(File pathname)
        {
            return pathname.isDirectory();
        }
    }

    public static List<String> listFiles(String folder, String ext)
    {

        ExtensionFilter filter = new ExtensionFilter(ext);

        File dir = new File(folder);

        if (dir.isDirectory() == false) { return null; }

        ArrayList<String> fileList = new ArrayList<String>();

        for (File f : dir.listFiles(filter))
        {
            fileList.add(f.getPath());
        }
        return fileList;
    }

    public static List<String> listDirectories(String rootdirectory)
    {
        File dir = new File(rootdirectory);

        if (dir.isDirectory() == false) { return null; }

        ArrayList<String> dirList = new ArrayList<String>();

        for (File f : dir.listFiles(new DirectoryFilter()))
        {
            dirList.add(f.getPath());
        }
        return dirList;
    }

}
