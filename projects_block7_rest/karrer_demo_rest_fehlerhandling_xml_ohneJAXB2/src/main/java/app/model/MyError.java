package app.model;

public class MyError {
	
	private String ex;
	private String mesg;
	
	public MyError() {
		
	}
	
	public String getEx() {
		return ex;
	}

	public void setEx(String ex) {
		this.ex = ex;
	}

	public String getMesg() {
		return mesg;
	}

	public void setMesg(String mesg) {
		this.mesg = mesg;
	}

	public MyError(String string, String mesg) {
		super();
		this.ex = string;
		this.mesg = mesg;
	}

	@Override
	public String toString() {
		return "Error [ex=" + ex + ", mesg=" + mesg + "]";
	}
	
	

}
