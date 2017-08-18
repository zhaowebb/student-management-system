
public interface BSTInterface {
	/**
	 * Insert a Student object into the Binary Search Tree. You may
	 * replace the input type with Student once you have implemented that.
	 * 
	 * @param Student - the student to insert
	 */
	public void insertStudent(Student s);
	
	/**
	 * Returns true if the student is in the tree. Otherwise returns false
	 * @param s
	 * @return
	 */
	public boolean findStudent(Student s);
	
	/**
	 * Returns number of elements in tree
	 * @return # of element in tree
	 */
	public int size();
	
	
	/**
	 * Return maximum depth of the tree
	 * @return
	 */
	public int maxDepth();
}
