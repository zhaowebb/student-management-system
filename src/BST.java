import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;


public class BST<T> implements BSTInterface {

	public BSTNode<T> root;
	public BSTNode<T> curr;
	private static int size = 0;
	public String grades = ""; // to store the final grades of all students

	public BST(){
		root = null;
	}

	/**
	 * Insert a Student object into the Binary Search Tree. You may
	 * replace the input type with Student once you have implemented that.
	 * 
	 * @param Student - the student to insert
	 */
	@Override
	public void insertStudent(Student student) {
		add(root, student);
		size++;
	}
	
	/**
	 * Insert a Student object from a loaded file, safely assume that each student is unique
	 * @param student - the student to insert
	 * @param ID - the student ID in the loaded file
	 */
	public void insertStudentFromFile(Student student, int ID){
		student.setID(ID);
		for(Assignment a : Manager.assignmentList){
			student.assignmentList.add(a);
		}
		add(root, student);
		size++;
	}

	/**
	 * helper function to add a Node with a new student into the tree
	 * @param compareNode - the Node to be compared with, usually starts with the root of the tree
	 * @param s - the student to with which the new Node will be constructed
	 */
	private void add(BSTNode<T> compareNode, Student s){
		if(compareNode == null){
			this.root = new BSTNode(s);
		}

		else if(s.compareTo((Student)compareNode.value) < 0 && compareNode.left == null){
			compareNode.left = new BSTNode(s);
			compareNode.left.parent = compareNode;
			selfBalancing(compareNode);
		}
		else if(s.compareTo((Student)root.value) > 0 && compareNode.right == null){
			compareNode.right = new BSTNode(s);
			compareNode.right.parent = compareNode;
			selfBalancing(compareNode);
		}
		else if(s.compareTo((Student)compareNode.value) < 0){
			add(compareNode.left, s);
		}
		else if(s.compareTo((Student)compareNode.value) > 0){
			add(compareNode.right, s);
		}
	}
		
	/**
	 * Returns true if the student is in the tree. Otherwise returns false
	 * @param s - the target student to look for
	 * @return true if target student is in one Node of the tree or false if it is not
	 */
	@Override
	public boolean findStudent(Student s) {
		//starts from the root
		curr = root;
		
		//iterate the tree until the student is found
		while(curr != null){
			if(s.compareTo((Student)curr.value) <= -1){
				curr = curr.left;
			}
			else if(s.compareTo((Student)curr.value) >= 1){
				curr = curr.right;
			}
			else{
				return true;
			}
		}
		return false;
	}

	/**
	 * Returns number of elements in tree
	 * @return # of element in tree
	 */
	
	/**
	 * Returns number of elements in tree
	 * @return # of element in tree
	 */
	@Override
	public int size() {
		// TODO Auto-generated method stub
		return root.getSize(root);

	}

	/**
	 * Return maximum depth of the tree
	 * @return the number of nodes from the root to the deepest leaf
	 */
	@Override
	public int maxDepth() {
		// TODO Auto-generated method stub
		return maxDepth(root);
	}
	
	/**
	 * Return the bigger number between nodes of left subtree and right subtree plus 1, the root
	 * @param root - from which root to find the depth of left and right subtree, usually from the root
	 * of the tree
	 * @return the deeper one between the left and right and plus the root itself
	 */
	private int maxDepth(BSTNode<T> root){
		if(root == null){
			return 0;
		}
		else{
			return 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
		}
	}

	/**
	 * to be used in manager class to assign new assignment to each student
	 * @param a - the newly created assignment
	 * @param root - the root of the existing binary search tree
	 */
	public void addAssignmentToStudents(Assignment a, BSTNode<Student> root){
		if(root == null ){
			return;
		}
		root.value.addAssignment(a);
		root.value.addScoreToAssignment(a.getAssignmentNumber(), 0);
		addAssignmentToStudents(a, root.left);
		addAssignmentToStudents(a, root.right);
	}

	/**
	 * Iterate each existing assignment and write their max scores into a file
	 * Iterate each node in the tree in preOrder and write them into a file
	 * @param root - the root from which to start
	 * @param fileName - the file name of the file to be written on
	 * @param assignmentList - the list of existing assignments
	 */
	public void logIntoFile(BSTNode<Student> root, String fileName, ArrayList<Assignment> assignmentList){

		try {
			PrintWriter out = new PrintWriter(fileName);
			for(Assignment a : assignmentList){
				out.print(a.maximunScore + " ");
			}
			out.println("");
			root.preOrderWriting(root, out);
			out.close();

		}								

		catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}					
	}
	
	/**
	 * To check rearrange the structure of certain subtrees
	 * @param targetNode - the node which is affected by the insertion, usually starts from the nearest
	 * and then recursively to the root of the tree root
	 */
	public void selfBalancing(BSTNode<T> targetNode){
		balanceFactor(targetNode);
		//Left subtree is longer
		if(targetNode.balance == 2){
			//left left unbalanced
			if(height(targetNode.left.left) > height(targetNode.left.right)){
				targetNode = rightRotation(targetNode);
			}
			//left right unbalanced
			else {
				targetNode = leftRightRotation(targetNode);
				
			}  
		}
		
		// Right subtree is longer
		else if(targetNode.balance == -2){
			//right right unbalanced
			if(height(targetNode.right.right) > height(targetNode.right.left)){
				targetNode = leftRotation(targetNode);
			}
			//right left rotation
			else{
				targetNode = rightLeftRotation(targetNode);
				
			}
		}
		
		// find out unbalanced node recursively until the root is reached
		if(targetNode.parent != null){
			selfBalancing(targetNode.parent);
		}
		else{
			this.root = targetNode;
		}
	}

	/**
	 * to calculate the difference between a certain node's left and right subtrees
	 * @param curr - the target node to examine
	 */
	public void balanceFactor(BSTNode<T> curr){
		curr.balance = height(curr.left) - height(curr.right);
	}
	
	/**
	 * to calculate the height of a tree, it's similar to the maxDepth method, however, for the purpose
	 * of convenience, this method is used specifically for self-balancing function of the tree 
	 * @param root - the root from which to find out the depth
	 * @return the height of a tree, it starts from -1, when the root is null 
	 */
	public int height(BSTNode<T> root){

		if(root == null){
			return -1;	
		}
		
		if(root.left == null && root.right == null){
			return 0;
		}
		else if(root.left == null){
			return 1 + height(root.right);
		}
		else if(root.right == null){
			return 1 + height(root.left);
		}
		else{
			return 1 + Math.max(height(root.left), height(root.right));
		}
		
	}

	/**
	 * helper function to rotate a node when it is right-right unbalanced
	 * @param rotatingNode - the unbalanced root
	 * @return the rotated node replacing the affected root
	 */
	private BSTNode<T> leftRotation(BSTNode<T> rotatingNode){
		BSTNode<T> temp = rotatingNode.right;
		temp.parent = rotatingNode.parent;
		
		
		if(temp.left != null){
			rotatingNode.right = temp.left;
			rotatingNode.left.parent = rotatingNode;
		}
		else{
			rotatingNode.right = null;
		}
		
		temp.left = rotatingNode;
		rotatingNode.parent = temp;
		
		if(temp.parent != null){
			if(temp.parent.right == rotatingNode){
				temp.parent.right = temp;
			}
			else if(temp.parent.left == rotatingNode){
				temp.parent.left = temp;
			}
		}
		
		balanceFactor(rotatingNode);
		balanceFactor(temp);
		
		return temp;
	}
	
	/**
	 * helper function to rotate a node when it is left-left unbalanced
	 * @param rotatingNode - the unbalanced root
	 * @return the rotated node replacing the affected root
	 */
	private BSTNode<T> rightRotation(BSTNode<T> rotatingNode){
		BSTNode<T> temp = rotatingNode.left;
		temp.parent = rotatingNode.parent;
		
		
		if(temp.right != null){
			rotatingNode.left = temp.right;
			rotatingNode.left.parent = rotatingNode;
		}
		else{
			rotatingNode.left = null;
		}
		
		temp.right = rotatingNode;
		rotatingNode.parent = temp;
		
		if(temp.parent != null){
			if(temp.parent.left == rotatingNode){
				temp.parent.left = temp;
			}
			else if(temp.parent.right == rotatingNode){
				temp.parent.right = temp;
			}
		}
		
		balanceFactor(rotatingNode);
		balanceFactor(temp);
		
		return temp;
	}
	
	/**
	 * helper function to rotate a node when it is left-right unbalanced
	 * @param targetNode - the unbalanced root
	 * @return the rotated node replacing the affected root
	 */
	private BSTNode<T> leftRightRotation(BSTNode<T> targetNode){
		targetNode.left = leftRotation(targetNode.left);
		return rightRotation(targetNode);
	}
	
	/**
	  * helper function to rotate a node when it is right-left unbalanced
	 * @param targetNode - the unbalanced root
	 * @return the rotated node replacing the affected root
	 */
	private BSTNode<T> rightLeftRotation(BSTNode<T> targetNode){
		targetNode.right = rightRotation(targetNode.right);
		return leftRotation(targetNode);
	}
	
	public void getStudentGradesPreOrder(BSTNode<T> root){
		if(root == null){
			return;
		}
		String studentGrade = ((Student)root.value).getFirstName() + " " + ((Student)root.value).getLastName() 
				+ " is " + ((Student)root.value).getGrade() + "."
						+ ". "; 
		grades += studentGrade;
		getStudentGradesPreOrder(root.left);
		getStudentGradesPreOrder(root.right);
	}
	//
	class BSTNode<T> {
		public T value;

		//left is less than, right is greater than
		public BSTNode<T> left, right, parent;
		public int balance;

		/**
		 * constructor
		 * @param s - the value to construct a new node
		 */
		public BSTNode(T s){
			value = s;
			left = null;
			right = null;
			parent = null;
			balance = 0;
		}

		/**
		 * to get the number of all nodes from a root
		 * @param root - from which to get the number of all nodes, usually from the troot of the tree
		 * @return the number of all nodes
		 */
		public int getSize(BSTNode<T> root){
			if(root == null){
				return 0;
			}
			return 1 + getSize(root.left) + getSize(root.right);
		}

		/**
		 * write information of each node in a tree into an PrintWriter object in preOrder
		 * @param root - from which node to start
		 * @param out - the PrintWriter object to be written on
		 */
		public void preOrderWriting(BSTNode<T> root, PrintWriter out){
			if(root == null){
				return;
			}
			out.print(((Student)root.value).getLastName() + " " + ((Student)root.value).getFirstName()+" "+((Student)root.value).getIDnumber()+" ");
			for(int score : ((Student)root.value).assignmentsAndScores){
				out.print(score + " ");
			}
			out.println("");
			preOrderWriting(root.left, out);
			preOrderWriting(root.right, out);
		}

	}
	


}
