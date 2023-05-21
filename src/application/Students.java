package application;

public class Students {
	private  String fullName;
	private int studentId;
	private double averge;
	private String gander;
	public Students(String fullName, int studentId, double averge, String gander) {
		super();
		this.fullName = fullName;
		this.studentId = studentId;
		this.averge = averge;
		this.gander = gander;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public int getStudentId() {
		return studentId;
	}
	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}
	public double getAverge() {
		return averge;
	}
	public void setAverge(double averge) {
		this.averge = averge;
	}
	public String getGander() {
		return gander;
	}
	public void setGander(String gander) {
		this.gander = gander;
	}
	@Override
	public String toString() {


		return fullName+" "+studentId+" "+averge+" "+gander;
	}

	
	

}
