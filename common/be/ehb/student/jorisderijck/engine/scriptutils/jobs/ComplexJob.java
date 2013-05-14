package be.ehb.student.jorisderijck.engine.scriptutils.jobs;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ComplexJob extends AtomicalJob implements Serializable{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private ArrayList<AbstractJob> jobs; // to put in some Atomical Jobs or ComplexJobs
	
    public ComplexJob(String jobName) {
        super(jobName);
        jobs = new ArrayList<AbstractJob>();
    }
    
	public boolean add(AbstractJob job)
	{
		return this.jobs.add(job);
	}
	
	public boolean remove(AbstractJob job)
	{
		return this.jobs.remove(job);
	}
	
	public List<AbstractJob> getJobs()
	{
	    return jobs;
	}
	
}
