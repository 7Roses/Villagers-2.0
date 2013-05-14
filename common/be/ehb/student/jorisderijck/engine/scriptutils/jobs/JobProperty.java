package be.ehb.student.jorisderijck.engine.scriptutils.jobs;

import java.io.Serializable;

public class JobProperty implements Serializable{

    
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private String propertyName;
    private Object propertyValue;
    
    public JobProperty(String name,Object value)
    {
        propertyName = name;
        setValue(value);
    }
    
    public void setValue(Object newValue)
    {
        propertyValue = newValue;
    }
    public Object getValue()
    {
        return propertyValue;
    }
    public String getName()
    {
        return propertyName;
    }
    
    
}
