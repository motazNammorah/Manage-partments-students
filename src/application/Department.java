package application;


public class Department {
	String depName;
	HashTable st;
	public Department(String depNAme,HashTable st) {
		this.depName=depNAme;
		this.st=st;
	}
	public String getDepName() {
		return depName;
	}
	public void setDepName(String depName) {
		this.depName = depName;
	}
	@Override
	public String toString() {
		return "Department [depName=" + depName + "]";
	}
	

}
