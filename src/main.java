import java.util.Scanner;

public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		SQL_oprations sql = new SQL_oprations();

		Scanner input = new Scanner(System.in);
		int ch = 0;

		while (ch != -1) {
			System.out.println("Enter from the following list:");
			System.out.println("1) insert new student");
			System.out.println("2) Display All students");
			System.out.println("3) Alter GPA");
			System.out.println("4) Exit");

			System.out.print("Enter your choice: ");
			ch = input.nextInt();

			switch (ch) {
			case 1:
				String sName = "", sDept = "";
				int sId = 0;
				double gpa = 0.0;
				System.out.println("Student - INSERTION");

				System.out.println("Enter student ID: ");
				sId = input.nextInt();

				System.out.println("Enter student Name: ");
				sName = input.next();

				System.out.println("Enter student department");
				sDept = input.next();

				System.out.println("Enter student GPA");
				gpa = input.nextDouble();
				if (gpa > 5 || gpa < 1) {
					System.out.println("invalid GPA");
					return;
				}

				Student s = new Student(sId, sName, sDept, gpa);
				sql.insert(s);
				break;
			case 2:
				sql.getAllstudent();
				break;
			case 3:
				
				
				System.out.println("Enter the new GPA");
				double newGPA = input.nextDouble();

				if (newGPA > 5 || newGPA < 1) {
					System.out.println("Error GPA");
				} else
					sql.editGPA(newGPA);

				break;
			case 4:
				sql.close();
				return;
			}

		}
	}

}
