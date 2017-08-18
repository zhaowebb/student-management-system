
public class Assignment implements AssignmentInterface {

	private static int counter = 0;
	private int numOfAssignment = counter;
	public String assignmentName;
	public int maximunScore;
	
	
	public Assignment(){
		assignmentName = "Assignment" + numOfAssignment;
		counter++;
	}
	
	/**
	 * Assignments, when created, need to be
	 * given a number 0, 1, 2, 3, etc. No numbers
	 * can be repeated, and no numbers should be skipped.
	 * 
	 * Assignments will never be removed, so you should
	 * be able to do this using a static variable.
	 * 
	 * @return the number of the assignment
	 */
	@Override
	public int getAssignmentNumber() {
	
		return numOfAssignment;
	}

	/**
	 * Get the maximum points on the assignment
	 * Used to calculate the final score for students.
	 * @return
	 */
	@Override
	public int getMaxScore() {
		
		return maximunScore;
	}

}
