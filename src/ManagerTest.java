import static org.junit.Assert.*;

import org.junit.Test;

public class ManagerTest {

	Manager manager;
	
	@Test
	public void testManager() {
		assertNull(manager);
		manager = new Manager();
		assertNotNull(manager);
	}

	@Test
	public void testAddStudent() {
		Manager m1 = new Manager(); 
		m1.addStudent("a","a");
		assertEquals(1, m1.bst.size());
		m1.addStudent("a","a");
		assertEquals(1, m1.bst.size());
		
		m1.addStudent("b","b");
		m1.addStudent("c", "c");
		m1.addStudent("d", "d");
		m1.addStudent("e", "e");
		m1.addStudent("f", "f");
		m1.addStudent("g", "g");
		assertEquals(7, m1.bst.size());
	}

	@Test
	public void testAddAssignment() {
		Manager m2 = new Manager();
		m2.addAssignment(50);
		m2.addAssignment(50);
	
		assertEquals(2, m2.assignmentList.size());
		m2.addStudent("a", "a");
		assertEquals(2, m2.bst.root.value.assignmentList.size());
		
		m2.addAssignment(60);
		assertEquals(3,m2.bst.root.value.assignmentList.size());
	}

	@Test
	public void testUpdateStudentGrade() {
		Manager m3 = new Manager();
		m3.addAssignment(30);
		m3.addStudent("b","b");
		m3.updateStudentGrade("b", "b", 0, 29);
		//expected to be 29
		System.out.print(m3.bst.root.value.getScoreOfParticularAssignment(0));
		
	}

}
