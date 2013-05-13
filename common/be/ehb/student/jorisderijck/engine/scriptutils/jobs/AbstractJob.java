package be.ehb.student.jorisderijck.engine.scriptutils.jobs;


public abstract class AbstractJob {

	private String jobName;

	public String getName() {
		return jobName;
	}

	public void setName(String jobName) {
		this.jobName = jobName;
	}

}
