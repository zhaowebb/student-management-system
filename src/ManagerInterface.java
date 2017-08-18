
public interface ManagerInterface {
	
	/**
	 * Create a student and add it to the BST.
	 * If the student isn't added because they already exist, you
	 * do not need to print an error.
	 * @param firstName
	 * @param lastName
	 */
	public void addStudent(String firstName, String lastName);
	
	
	/**
	 * Create a new assignment with maximum possible score maxScore;
	 * Ensure every student in the BST is given a new assignment slot.
	 * (Initially set to zero)
	 */
	public void addAssignment(int maxScore);
	
	/**
	 * If the student is in the BST, and there is an assignment
	 * with that number, change the student's grade to that number.
	 * If newGrade > maxGrade, do not allow the change.
	 * Do not print an error message
	 */
	public void updateStudentGrade(String firstName, String lastname,
			                       int assignmentNo, int newGrade);
	
	/**
	 * Output the BST to a log file using a pre-order traversal.
	 * An example is in exampleOutput.txt.
	 * You can think of this like "saving" your BST.
	 * 
	 * NOTE - DO NOT store the letter grade in the log file 
	 * (since the letter grade can be determined on-the fly)
	 * 
	 * @param outputFilename - the name of the output log file in the working directory
	 */
	public void createLogFile(String outputFilename);
	
	/**
	 * read in the input LogFile and create the BST. This is loading a save.
	 * @param inputFilename
	 */
	public void loadLogFile(String inputFilename);
	
	/**
	 * Load an assignment file like assignmentExample.txt and assign all students
	 * their given grade. This is useful for loading assignments grades in bulk.
	 */
	public void loadAssignmentGrades(String inputFilename);
	
	/**
	 * Return a string that is a set of lines of the format
	 * "lastName, firstName - letterGrade"
	 * for all students in alphabetical order. Use a pre- post- or in-order traversal
	 * of a Binary Search Tree to do this (whichever is most appropriate)
	 * return A String where each line is a student and their letter grade
	 */
	public String getFinalGrades();
	
}
