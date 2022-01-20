package app.model;

public class Error {
	
	private Exception ex;
	private String mesg;
	
	public Error() {
		
	}
	
	public Exception getEx() {
		return ex;
	}

	public void setEx(Exception ex) {
		this.ex = ex;
	}

	public String getMesg() {
		return mesg;
	}

	public void setMesg(String mesg) {
		this.mesg = mesg;
	}

	public Error(Exception ex, String mesg) {
		super();
		this.ex = ex;
		this.mesg = mesg;
	}

	@Override
	public String toString() {
		return "Error [ex=" + ex + ", mesg=" + mesg + "]";
	}
	
	

}
