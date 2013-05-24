package be.ehb.student.jorisderijck.engine.core.ai.memory;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.logging.Logger;

import be.ehb.student.jorisderijck.Villagers2Js.lib.Reference;

/**
 * @see http://www.javapractices.com/topic/TopicAction.do?Id=57
 * class for loading and saving the data when requested
 * */
public class MemorySaveHandler {

    private static final Logger log = Logger.getLogger(Reference.MOD_ID);// + " MemorySaveHandler");
            
    public void save(Memory memoryToSave,String worlddir)
    {
        
        try{
            //use buffering
            
            File dir = new File(worlddir);
            dir.mkdirs();
            File file = new File(worlddir + File.separatorChar + memoryToSave.getUID()+".entity");
            if (Reference.DEBUGLOGGING)
            {
                log.info("saving to: " + file.getPath());
                log.info(file.getCanonicalPath());
            }
            OutputStream outputfile = new FileOutputStream(file);
            OutputStream buffer = new BufferedOutputStream(outputfile);
            ObjectOutput output = new ObjectOutputStream(buffer);
            try{
              output.writeObject(memoryToSave);
            }
            finally{
              output.close();
            }
          }  
          catch(IOException ex){
            log.severe("Cannot perform output."+ ex.toString());
          }
    }
    
    public Memory load(Memory memoryToLoadTo,String worlddir)
    {
        Memory loadedMemory = memoryToLoadTo;
        try{
            //use buffering
            File file = new File(worlddir + File.separatorChar + memoryToLoadTo.getUID()+".entity" );
            InputStream inputfile = new FileInputStream(file);
            InputStream buffer = new BufferedInputStream(inputfile);
            ObjectInput input = new ObjectInputStream (buffer);
            try{
              loadedMemory = (Memory) input.readObject();
            }
            finally{
              input.close();
            }
          }
          catch(ClassNotFoundException ex){
              log.severe("Cannot perform input. Class not found." + ex);
          }
          catch(IOException ex){
              log.severe("Cannot perform input."+ex);
          }
        return loadedMemory;
    }
    
    /**
     * function for removing the entities data.
     * this can be called when the entity dies or in further updates when a refresh system will smart remove unused files.
     * */
    public boolean remove(Memory memoryToRemove,String worlddir)
    {
        File file = new File(worlddir + File.separatorChar + memoryToRemove.getUID()+".entity");
        file.delete();
        return true;
    }
    
}
