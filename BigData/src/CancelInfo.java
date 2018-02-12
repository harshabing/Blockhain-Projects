package models;

public class CancelInfo {
	
	private String cancelCode;
	private int count;
	
	
	public CancelInfo(String cancelCode, int count) {
		super();
		this.cancelCode = cancelCode;
		this.count = count;
	}


	public String getCancelCode() {
		return cancelCode;
	}


	public void setCancelCode(String cancelCode) {
		this.cancelCode = cancelCode;
	}


	public int getCount() {
		return count;
	}


	public void setCount(int count) {
		this.count = count;
	}
	

}
