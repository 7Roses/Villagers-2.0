package be.ehb.student.jorisderijck.engine.scriptutils.jobs;

import java.util.ArrayList;

public class ComplexJob extends AbstractJob {

	private ArrayList<AbstractJob> jobs; // to put in some Atomical Jobs or ComplexJobs
	
	
	public boolean add(ComplexJob job)
	{
		return this.jobs.add(job);
	}
	
	public boolean remove(ComplexJob job)
	{
		return this.jobs.remove(job);
	}
	
}
