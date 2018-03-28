package Entities;

public class GradingFormat {
	private String Grade;
	private int NumValue;
	private boolean Pass;
	private int RequestID;
	
	public GradingFormat(String grade,
			             int NumValue,
			             boolean Pass,
			             int RequestID){
		this.Grade = grade;
		this.NumValue = NumValue;
		this.Pass = Pass;
		this.RequestID = RequestID;
	}
	
	public String getGrade(){
		return this.Grade;
	}
	
	public int getNumValue(){
		return this.NumValue;
	}
	
	public boolean getPass(){
		return this.Pass;
	}
	
	public int getRequestID(){
		return this.RequestID;
	}
}
