************************************************
When you load a logfile, do you add the students
to the tree, or do you start a new tree? Why
did you make this decision.
************************************************
I start a new tree, because load a file after add student by calling the addStudent method would cause ID collision.

************************************************
What collection did you use to store assignments 
for each student? Why did you choose this?
************************************************
An ArrayList is used to store assignments for each student for the reason that the total number of assignments is unknown at the beginning of the program.

**************************************************
How did your balance your binary search tree (which
technique did you use??)
**************************************************
I use AVL tree. Every time a node inserted, the tree balance itself.

***************************************************
What is the runtime of (relative to number of students, s,
and number of assignments, a, to update a single score
for a single student on a single assignment)?
***************************************************
It’s O(s^a), because I create a list of assignments, and then for each student, I add the each assignment into their assignment list.

***********************************
Note
***********************************
Assignment file should be in the format:
number of assignment
max score(if it’s an existing assignment, can’t change the previous max score)
student name student score

Student file should be in the format:
max score of assignments
studentName studentID Scores(the number of scores should match the number of assignments)


***********************************
major usage of the program
***********************************
instantiate an object of the manager class

call the loadLogFile to load existing file, then add new student to insert student, addAssignment to add new assignment, updateAssignment one by one or load the assignment file to update assignment all in once, create LogFile to store the record. 

