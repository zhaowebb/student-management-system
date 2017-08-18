
public class main {

	int userInput = 0;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Manager m = new Manager();
		
		m.loadLogFile("input.txt");
		m.loadAssignmentGrades("Assignment.txt");
		m.createLogFile("output.txt");
		m.addStudent("v", "v");
		m.updateStudentGrade("v", "v", 1, 15);
		m.createLogFile("output.txt");
		
		System.out.print(m.getFinalGrades());
	}

}
