import java.util.ArrayList;
import java.util.HashMap;

public class Student implements StudentInterface{
	private String firstName;
	private String lastName;
	private static int counter = 10000;
	public int studentID;
	private static ArrayList<Integer> existingID = new ArrayList<>();//a ID pool to store existing ID from file

	public ArrayList<Assignment> assignmentList = new ArrayList<>();
	public ArrayList<Integer> assignmentsAndScores = new ArrayList<>();
	
	/**
	 * constructor
	 * @param firstName - first name of the student
	 * @param lastName - last name of the student
	 * @param createID - does it need to create a new ID for the student
	 */
	public Student(String firstName, String lastName, boolean createID){
		this.firstName = firstName;
		this.lastName = lastName;
		
		if(createID){
			counter++;
			studentID = counter;
			
			//if the newly created ID is in the ID pool, continue to create a new one
			while(existingID.contains(studentID)){
				counter++;
				studentID = counter;
			}
		}
	}
	
	/**
	 * record ID of students from the file
	 * @param ID - ID of students from the file
	 */
	public void setID(int ID){
		studentID = ID;
		existingID.add(studentID);
	}
	

	/**
	 * compare two students by their last and first name combination
	 * @param - the student object to be compared
	 * @return - the result of comparison
	 */
	@Override
	public int compareTo(Student s) {
		return this.getLastNameFirst().compareToIgnoreCase(s.getLastNameFirst());
	}

	/**
	 * getter of last name
	 * @return the last name
	 */
	@Override
	public String getLastName() {
		return lastName;
	}

	/**
	 * getter of first name
	 * @return the first name
	 */
	@Override
	public String getFirstName() {
		
		return firstName;
	}

	/**
	 * getter of ID
	 * @return ID
	 */
	@Override
	public int getIDnumber() {
		
		return studentID;
	}

	/**
	 * Returns a String "Lastname,Firstname", used to sort the BST
	 * 
	 * @return A string concatenating Last name and first name
	 * with a comma between, i.e. "McBurney,Will" for a student
	 * named Will McBurney.
	 */
	@Override
	public String getLastNameFirst() {
		
		return lastName + "," + firstName;
	}

	/**Gets the class percentage. This is done by totaling
	 * the student's scores on individual assignments and
	 * assigning a letter grade based on overall percentage
	 * out of points possible.
	 * 
	 * >=90% - A
	 * >=80% - B
	 * >=70% - C
	 * >=60% - D
	 * <60% - F
	 * 
	 * @return A, B, C, D, or F letter grade in class
	 * Return "-" if there are no assignments (do not crash)
	 */
	@Override
	public char getGrade() {
		
		int totalScore = 0;
		int totalStudentScore = 0;
		double percentage;
		for(Assignment a : assignmentList){
			totalScore = totalScore + a.getMaxScore();
		}
		
		for(int i: assignmentsAndScores){
			totalStudentScore = totalStudentScore + i;
		}
		
		if(totalScore != 0){
			percentage = totalStudentScore / totalScore;
			if(percentage >= 0.9){
				return 'A';
			}
			else if(percentage >= 0.8){
				return 'B';
			}
			else if(percentage >= 0.7){
				return 'C';
			}
			else if(percentage >= 0.6){
				return 'D';
			}
			else if(percentage < 0.6){
				return 'F';
			}
		}
		return '-';
	}

	
	/**
	 * add assignment to the assignment list of the student
	 * @param assignment - the newly created assignment
	 */
	public void addAssignment(Assignment assignment){
		assignmentList.add(assignment);
	}
	
	/**
	 * pair up the assignment and its score of the student in a hashmap
	 * @param numOfAssignment: the index of the assignment in the assignment list
	 * @param score: the score of the assignment by the student
	 */
	public void addScoreToAssignment(int numOfAssignment, int score){
		//if the assignment exists, update the score by the student
		if(numOfAssignment < assignmentsAndScores.size()){
			assignmentsAndScores.set(numOfAssignment, score);
		}
		else if(numOfAssignment == assignmentsAndScores.size()){
			assignmentsAndScores.add(score);
		}
		//this assignment is existent
		else{
			System.out.print("Assignment" +  numOfAssignment + " is not existing");
		}
		
	}
	
	/**
	 * return a string indicating the score of a particular assignment by the student
	 * @param numOfAssignment: which assignment it is
	 * @return
	 */
	public String getScoreOfParticularAssignment(int numOfAssignment){
		if(numOfAssignment < assignmentList.size()){
			return lastName + " , " + firstName + "'s " + assignmentList.get(numOfAssignment).assignmentName + " is " + 
					assignmentsAndScores.get(numOfAssignment);
		}
		return "This assignment does not exist";
	}
}
