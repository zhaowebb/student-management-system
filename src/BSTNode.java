
public class BSTNode<T> {
	public T value;
	
	//left is less than, right is greater than
	public BSTNode<T> left, right, parent;
	
	public BSTNode(T t){
		value = t;
		left = null;
		right = null;
		parent = null;
	}
}
