import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Manager implements ManagerInterface {
	BST<Student> bst;
	public static ArrayList<Assignment> assignmentList = new ArrayList<>();
	String studentFinalGrades = "";
	
	public Manager(){
		bst = new BST();
	}

	/**
	 * Create a student and add it to the BST.
	 * If the student isn't added because they already exist, you
	 * do not need to print an error.
	 * @param firstName
	 * @param lastName
	 */
	@Override
	public void addStudent(String firstName, String lastName) {
		// TODO Auto-generated method stub
		Student student = new Student(firstName, lastName, false); 
		
		//if the student is in the data set
		if(bst.findStudent(student)){
			return;
		}
		
		//if the student is a real new student, instantiate an object with a new ID
		Student newStudent = new Student(firstName, lastName, true); 
		
		//add existing assignments to the new students assignment list
		for(Assignment a : assignmentList){
			newStudent.addAssignment(a);
			newStudent.addScoreToAssignment(a.getAssignmentNumber(), 0);
		}
		
		//insert the student into the tree
		bst.insertStudent(newStudent);
	}

	/**
	 * Create a new assignment with maximum possible score maxScore;
	 * Ensure every student in the BST is given a new assignment slot.
	 * (Initially set to zero)
	 */
	@Override
	public void addAssignment(int maxScore) {
		// TODO Auto-generated method stub
		Assignment a = new Assignment();
		a.maximunScore = maxScore;
		assignmentList.add(a);
		bst.addAssignmentToStudents(a, bst.root);
	}

	/**
	 * If the student is in the BST, and there is an assignment
	 * with that number, change the student's grade to that number.
	 * If newGrade > maxGrade, do not allow the change.
	 * Do not print an error message
	 */
	@Override
	public void updateStudentGrade(String firstName, String lastName, int assignmentNo, int newGrade) {
		// TODO Auto-generated method stub
		Student s = new Student(firstName, lastName, false);
		if(bst.findStudent(s)){
			if(assignmentNo>=0 && assignmentNo < assignmentList.size()){
				if(newGrade >= 0 && newGrade <= ((Student)bst.curr.value).assignmentList.get(assignmentNo).maximunScore){
					((Student) bst.curr.value).addScoreToAssignment(assignmentNo, newGrade);
				}
				else if(newGrade <= 0){
					System.out.println(s.getLastNameFirst() + "' assignment" + assignmentNo + " score is invalid,"
							+ " so it's set to be 0");
					((Student) bst.curr.value).addScoreToAssignment(assignmentNo, 0);
				}
				else if(newGrade >= ((Student)bst.curr.value).assignmentList.get(assignmentNo).maximunScore){
					System.out.println(s.getLastNameFirst() + "' assignment" + assignmentNo + " score is invalid,"
							+ " so it's set to be " + ((Student)bst.curr.value).assignmentList.get(assignmentNo).maximunScore);
					((Student) bst.curr.value).addScoreToAssignment(assignmentNo, 
							((Student)bst.curr.value).assignmentList.get(assignmentNo).maximunScore);
				}
			}
			else{
				System.out.println("This assignment doesn't exist!");
			}
		}
			else{
				System.out.println("This is not a CIT student");
			}
		
	}

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
	@Override
	public void createLogFile(String outputFilename)  {
		// TODO Auto-generated method stub
		
		bst.logIntoFile(bst.root, outputFilename, assignmentList);
		
	}

	/**
	 * read in the input LogFile and create the BST. This is loading a save.
	 * @param inputFilename
	 */
	@Override
	public void loadLogFile(String inputFilename)  {
		// TODO Auto-generated method stub
		if(bst.root != null){
			System.out.println("No file is allowed to load after adding student, so previous adding will "
					+ "be eliminated");
		}
		try{
			File inputFile = new File(inputFilename);
			Scanner in = new Scanner(inputFile);
			bst = new BST();
			if(in.hasNextLine()){
				String line = in.nextLine();
				ArrayList<String> myList = new ArrayList<String>(Arrays.asList(line.split(" ")));
				for(int i=0; i<myList.size(); i++){
					addAssignment(Integer.parseInt(myList.get(i)));
				}
			}
			while(in.hasNextLine()){
				String line = in.nextLine();
				ArrayList<String> myList = new ArrayList<String>(Arrays.asList(line.split(" ")));
				//addStudent(myList.get(1), myList.get(0));
				//!ID PROBLEM
				Student newStudent = new Student(myList.get(1), myList.get(0), false);
				//newStudent.setID(Integer.parseInt(myList.get(2)));
				bst.insertStudentFromFile(newStudent, Integer.parseInt(myList.get(2)));
				for(int i=3; i<myList.size(); i++){
					updateStudentGrade(myList.get(1), myList.get(0), i-3, Integer.parseInt(myList.get(i)));
				}
			}
			in.close();
		}
		catch(FileNotFoundException e){
			e.printStackTrace();
		}
	}

	/**
	 * Load an assignment file like assignmentExample.txt and assign all students
	 * their given grade. This is useful for loading assignments grades in bulk.
	 */
	@Override
	public void loadAssignmentGrades(String inputFilename) {
		
		try{
			File inputFile = new File(inputFilename);
			Scanner in = new Scanner(inputFile);
			int max = 0;
			int numOfAssignment = 0;
			
			//check if the number of assignment is legal
			if(in.hasNextLine()){
				numOfAssignment = Integer.parseInt(in.nextLine());
				if(numOfAssignment > assignmentList.size()){
					return;
				}
			}
			
			//get the max score of a legal assignment, if it's a new one, create a new one
			if(in.hasNextLine()){
				max = Integer.parseInt(in.nextLine());
				if(numOfAssignment == assignmentList.size()){
					addAssignment(max);
				}

			}
			
			//update students score of this assignment
			while(in.hasNextLine()){
				String line = in.nextLine();
				ArrayList<String> myList = new ArrayList<String>(Arrays.asList(line.split(" ")));
				
				if(0<=Integer.parseInt(myList.get(2)) && Integer.parseInt(myList.get(2)) <= max){
					updateStudentGrade(myList.get(1), myList.get(0), numOfAssignment, Integer.parseInt(myList.get(2)));
				}else{
					System.out.print(myList.get(1) + myList.get(0) + "'s score is invalid");
				}
			}
			in.close();
		}
		catch(FileNotFoundException e){
			e.printStackTrace();
		}
	}
	
	/**
	 * Return a string that is a set of lines of the format
	 * "lastName, firstName - letterGrade"
	 * for all students in alphabetical order. Use a pre- post- or in-order traversal
	 * of a Binary Search Tree to do this (whichever is most appropriate)
	 * return A String where each line is a student and their letter grade
	 */
	@Override
	public String getFinalGrades(){
		bst.getStudentGradesPreOrder(bst.root);
		return bst.grades;
	}
	
	
}
