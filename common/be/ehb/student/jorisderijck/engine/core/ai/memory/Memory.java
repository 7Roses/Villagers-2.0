package be.ehb.student.jorisderijck.engine.core.ai.memory;

import java.io.Serializable;
import java.util.HashMap;
import java.util.UUID;
import java.util.logging.Logger;

import be.ehb.student.jorisderijck.Villagers2Js.lib.Reference;

public class Memory implements IMemory {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private static final Logger log = Logger.getLogger(Reference.MOD_ID);
    
    private UUID mySaveUUID;
    private HashMap<String,Object> mydata;
    
    
    public Memory(UUID myEntity)
    {
        this.mySaveUUID = myEntity;
        this.mydata = new HashMap<String,Object>();
    }
    
    @Override
    public Object getObject(String identifier)
    {
        return mydata.get(identifier);
    }

    /**
     * sets the object into the memory, but only if it implements the Serializable object
     * Else it will log warnings
     * */
    @Override
    public void setObject(String identifier, Object object)
    {
        if (object instanceof Serializable)
            mydata.put(identifier, object);
        else
        {
            log.warning("A non Serializable object wanted to be saved !!");
        }
    }

    public void setUUID(UUID saveUUID)
    {
        this.mySaveUUID = saveUUID;
    }
    
    @Override
    public UUID getUID()
    {
        return this.mySaveUUID;
    }

    public void remove(String identifier)
    {
        mydata.remove(identifier);
    }
    
    public boolean hasKey(String identifier)
    {
        return mydata.containsKey(identifier);
    }



}
