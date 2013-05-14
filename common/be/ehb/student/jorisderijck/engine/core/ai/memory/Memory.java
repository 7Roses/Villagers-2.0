package be.ehb.student.jorisderijck.engine.core.ai.memory;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;
import java.util.logging.Logger;

import be.ehb.student.jorisderijck.Villagers2Js.lib.Reference;
import be.ehb.student.jorisderijck.engine.scriptutils.communication.Message;

public class Memory implements IMemory,IMessageSaver {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private static final Logger log = Logger.getLogger(Reference.MOD_ID);
    
    private UUID mySaveUUID;
    private HashMap<String,Object> mydata;
    private ArrayList<Message> messageMemory;
    
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

    @Override
    public Message getMessage(int time)
    {
        Message messageToReturn = null;
        for(Message m:messageMemory)
        {
            if (m.getDeliveryTime()<= time && (messageToReturn == null || messageToReturn.getDeliveryTime()>m.getDeliveryTime()))
            {
               messageToReturn = m; 
            }
        }
        
        messageMemory.remove(messageToReturn);
        return messageToReturn;
    }

    @Override
    public boolean addMessage(Message message)
    {
        return messageMemory.add(message);
    }

    @Override
    public int getMessageCount(int time)
    {
        int messages = 0;
        for(Message m:messageMemory)
        {
            if (m.getDeliveryTime()<= time)messages++;
        }
        return messages;
    }

    @Override
    public int getMessageCount()
    {
        return messageMemory.size();
    }

    


}
