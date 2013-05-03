package be.jrose.villagers2.aiengine.core.Job;


public abstract class AbstractJob {

	private String jobName;

	public String getName() {
		return jobName;
	}

	public void setName(String jobName) {
		this.jobName = jobName;
	}

}
