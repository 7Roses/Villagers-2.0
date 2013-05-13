package be.ehb.student.jorisderijck.engine.core.ai.memory;

import java.io.Serializable;
import java.util.UUID;

public interface IMemory extends Serializable {

    public Object getObject(String identifier);
    public void setObject(String identifier,Object object);
    
    public UUID getUID();
}
