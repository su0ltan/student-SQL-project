import java.sql.*;

public class SQL_oprations {

	private String user, pwd, url;
	Connection con;

	public SQL_oprations() {
		// establising connection with database
		user = "root";
		pwd = "root";
		url = "jdbc:mariadb://localhost:3306/studentDb";
		try {
			con = DriverManager.getConnection(url, user, pwd);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public boolean insert(Student s) {
		// preparing the SQL command
		String cmd = "INSERT INTO student" + "(studentID,studentName,department, GPA) " + "values(" + s.getStudentID()
				+ ",'" + s.getStudentName() + "','" + s.getDeparment() + "'," + s.getGPA() + ");";

		try {
			// creating statment for execution of command
			Statement stmt = con.createStatement();
			int row = stmt.executeUpdate(cmd);

			// checking the query by getting the rows effected by the command
			if (row > 0)
				System.out.println("Student inserted successfuly");
			else {
				// this indicating that some thing wrong happened
				System.out.println("Error while inserted the student row");
				return false;
			}
			stmt.close(); // closing statement

		} catch (SQLException e) {

			// code 1062 indicates that the primary key is already exist
			if (e.getErrorCode() == 1062)
				System.out.println("The primary key exist");
			else {
				// remaining errors
				System.out.println("Error while instered the student row");
				e.printStackTrace();
			}

			return false;
		} // end catch

		// reaching here means every thing is done successfully
		return true;
	}// end insert

	public void getAllstudent() {

		Statement stmt;
		try {
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM student;");

			// ptinting all records
			while (rs.next()) {
				System.out.println();
				System.out.println("Student Name: " + rs.getString("studentName"));
				System.out.println("Student ID: " + rs.getInt("studentID"));
				System.out.println("Department: " + rs.getString("department"));
				System.out.println("GPA: " + rs.getDouble("GPA"));
				System.out.println();

			}
			stmt.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public boolean isExist(int sid) {
		Statement stmt;

		try {
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM student where studentID=" + sid + ";");

			stmt.close();

			return rs.next(); // this will return true is student is exist in the set

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return false;// reaching here means the student is not exist
	}

	// this function changes the student GPA
	public void editGPA(double newGPA) {
		Statement stmt;

		try {
			stmt = con.createStatement();
			int rowsEffected = stmt.executeUpdate("UPDATE student\r\n"
			+ "SET GPA = \r\n" +
					"  CASE \r\n"
			+ "    WHEN GPA > " + newGPA + " THEN GPA - 0.10\r\n" + "    "
					+ "WHEN GPA < " + newGPA+ " THEN GPA + 0.10\r\n" + "    "
							+ "ELSE GPA\r\n" + 
					"  END;");

			if (rowsEffected > 0) { // if rows more that 0 that means the the gpa updated successfully
				System.out.println("GPA updated Successfully");
			} else
				System.out.println("GPA not updated");
			stmt.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void close() {
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
