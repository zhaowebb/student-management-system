
public interface StudentInterface extends Comparable<Student>{
	//self-explanatory getters
	public String getLastName();
	public String getFirstName();
	public int getIDnumber();
	
	/**
	 * Returns a String "Lastname,Firstname", used to sort the BST
	 * 
	 * @return A string concatenating Last name and first name
	 * with a comma between, i.e. "McBurney,Will" for a student
	 * named Will McBurney.
	 */
	
	public String getLastNameFirst();
	
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
	public char getGrade();
	
	/**
	 * Returns string of student formatted as:
	 * "FirstName LastName StudentNo TotalGrade ScoreAssignment0 ScoreAssignment1 ScoreAssignment2....ScoreAssignmentN"
	 * @return a string
	 * gives in order first name, last name, studentNo, currentGrade, and assignment scores from 0 to max
	 */
	public String toString();
}
