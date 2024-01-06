
public class Student {
	private int studentID;
	private String studentName , deparment;
	private double GPA;
	
	
	public Student(int studentID, String studentName, String department, double GPA ) {
		
		
		//This line to check the GPA value
		if( GPA > 5 || GPA < 1) {
			System.out.println("Enter valid GPA");
			return;
		}else
			this.GPA = GPA;
		this.deparment = department;
		this.studentID = studentID;
		this.studentName = studentName;
	}
	
	public Student(Student s) {
		studentID = s.studentID;
		studentName = s.studentName;
		deparment = s.deparment;
		GPA = s.GPA;
	}

	public int getStudentID() {
		return studentID;
	}

	public String getStudentName() {
		return studentName;
	}

	public String getDeparment() {
		return deparment;
	}

	public double getGPA() {
		return GPA;
	}
	public void print() {
		System.out.println(
				
				"Student Name: "+ getStudentName()+
				"\nStudent ID: "+ getStudentID()+
				"\nDepartment: "+getDeparment()+
				"\nGPA: "+getGPA()
				
				);
	}

}
