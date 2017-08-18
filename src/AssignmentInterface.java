
public interface AssignmentInterface {
	
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
	public int getAssignmentNumber();
	
	/**
	 * Get the maximum points on the assignment
	 * Used to calculate the final score for students.
	 * @return
	 */
	public int getMaxScore();

}
