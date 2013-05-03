package be.jrose.helperclasses;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.List;

public class FileHelper {

    public static class ExtensionFilter implements FilenameFilter {
        
        private final String extension;
 
        public ExtensionFilter(String extension) {
            this.extension = extension;
        }
        public boolean accept(File dir, String name) {
            return (name.endsWith(extension));
        }
    }
    
    public static List<String> listFiles(String folder, String ext) {
        
        ExtensionFilter filter = new ExtensionFilter(ext);
 
        File dir = new File(folder);
 
        if(dir.isDirectory()==false){
            System.out.println("Directory does not exists : " + folder);
            return null;
        }
 
        ArrayList<String> fileList = new ArrayList<String>();

        for(File f:dir.listFiles(filter))
        {
            fileList.add(f.getPath());
        }
        
        if (fileList.size() == 0) {
            System.out.println("no files end with : " + ext + " in map: "+folder);
        }
        return fileList;
    }
 
   
    
}
