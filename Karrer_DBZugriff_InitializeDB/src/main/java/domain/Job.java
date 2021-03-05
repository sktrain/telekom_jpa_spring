package domain;

public class Job {
	
	private String jobid;
	private String jobtitle;
	private int minsal;
	private int maxsal;
	
	
	public Job() {
		super();
	}


	public String getJobid() {
		return jobid;
	}


	public void setJobid(String jobid) {
		this.jobid = jobid;
	}


	public String getJobtitle() {
		return jobtitle;
	}


	public void setJobtitle(String jobtitle) {
		this.jobtitle = jobtitle;
	}


	public int getMinsal() {
		return minsal;
	}


	public void setMinsal(int minsal) {
		this.minsal = minsal;
	}


	public int getMaxsal() {
		return maxsal;
	}


	public void setMaxsal(int maxsal) {
		this.maxsal = maxsal;
	}


	@Override
	public String toString() {
		return "Job [jobid=" + jobid + ", jobtitle=" + jobtitle + ", minsal=" + minsal + ", maxsal=" + maxsal + "]";
	}
	
	
	
	

}
