package be.ehb.student.jorisderijck.engine.scriptutils.jobs;

import java.io.Serializable;
import java.util.ArrayList;

public class AtomicalJob extends AbstractJob implements Serializable {
	/**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    
    
    private ArrayList<JobProperty> jobProperties;
    
    
    public AtomicalJob(String jobName){super(jobName);}
    
    public void addProperty(JobProperty property)
    {
        jobProperties.add(property);
    }
    public JobProperty[] getProperties()
    {
        return (JobProperty[]) jobProperties.toArray();
    }
}
