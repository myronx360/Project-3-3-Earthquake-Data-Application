import edu.uncc.cs.bridgesV2.base.BSTElement;
/** Binary Search Tree implementation for Dictionary ADT */
class BSTE<Key extends Comparable<? super Key>, E>
implements Dictionary<Key, E> {
	private BSTElement<Key,E> root; // Root of the BST
	private int nodecount; // Number of nodes in the BST
	/** Constructor */
	BSTE() { root = null; nodecount = 0; }
	/** Reinitialize tree */
	public void clear() { root = null; nodecount = 0; }

	/** Insert a record into the tree.
@param k Key value of the record.
@param e The record to insert. */
	public void insert(Key k, E e) {
		root = inserthelp(root, k, e);
		nodecount++;
	}

	/** Insert a record into the tree.
	@param k Key value of the record.
	@param e The record to insert. 
	@param label The node label*/
	public void insert2(String label, Key k, E e) {
		root = inserthelp2(label, root, k, e);
		nodecount++;
	}

	/** Remove a record from the tree.
@param k Key value of record to remove.
@return The record removed, null if there is none. */
	public E remove(Key k) {
		E temp = findhelp(root, k); // First find it
		if (temp != null) {
			root = removehelp(root, k); // Now remove it
			nodecount--;
		}
		return temp;
	}

	/** Remove and return the root node from the dictionary.
@return The record removed, null if tree is empty. */
	public E removeAny() {
		if (root == null) return null;
		E temp = root.getValue();
		root = removehelp(root, root.getKey());
		nodecount--;
		return temp;
	}
	/** @return Record with key value k, null if none exist.
@param k The key value to find. */
	public E find(Key k) { return findhelp(root, k); }
	/** @return The number of records in the dictionary. */
	public int size() { return nodecount; }


	private E findhelp(BSTElement<Key,E> rt, Key k) {
		if (rt == null) return null;
		if (rt.getKey().compareTo(k) > 0)
			return findhelp(rt.getLeft(), k);
		else if (rt.getKey().compareTo(k) == 0) return rt.getValue();
		else return findhelp(rt.getRight(), k);
	}

	/** @return The current subtree, modified to contain
the new item */
	private BSTElement<Key,E> inserthelp(BSTElement<Key,E> rt,
			Key k, E e) {
		if (rt == null) return new BSTElement<Key,E>(k, e);
		if (rt.getKey().compareTo(k) > 0)
			rt.setLeft(inserthelp(rt.getLeft(), k, e));
		else
			rt.setRight(inserthelp(rt.getRight(), k, e));
		return rt;
	}


	/** @return The current subtree, modified to contain
the new item */
	private BSTElement<Key,E> inserthelp2(String label, BSTElement<Key,E> rt,
			Key k, E e) {

		if (rt == null) return new BSTElement<Key,E>(label,k, e);
		if (rt.getKey().compareTo(k) > 0){
			rt.setLeft(inserthelp2(label,rt.getLeft() ,k, e));
		}else{
			rt.setRight(inserthelp2(label,rt.getRight(), k, e));
		}
		return rt;
	}

	private BSTElement<Key,E> deletemin(BSTElement<Key,E> rt) {
		if (rt.getLeft() == null) return rt.getRight();
		rt.setLeft(deletemin(rt.getLeft()));
		return rt;
	}

	private BSTElement<Key,E> getmin(BSTElement<Key,E> rt) {
		if (rt.getLeft() == null) return rt;
		return getmin(rt.getLeft());
	}

	/**traverse the tree and highlight the element with the largest key value */
	public BSTElement<Key,E> largest(BSTElement<Key,E> rt) {
		if (rt.getRight() == null){ 	
			rt.getVisualizer().setColor("yellow");
			return rt;
		}
		return largest(rt.getRight());
	}

	/** Remove a node with key value k
	@return The tree with the node removed */
	private BSTElement<Key,E> removehelp(BSTElement<Key,E> rt,Key k) {
		if (rt == null) return null;
		if (rt.getKey().compareTo(k) > 0)
			rt.setLeft(removehelp(rt.getLeft(), k));
		else if (rt.getKey().compareTo(k) < 0)
			rt.setRight(removehelp(rt.getRight(), k));
		else { // Found it
			if (rt.getLeft() == null) return rt.getRight();
			else if (rt.getRight() == null) return rt.getLeft();
			else { // Two children
				BSTElement<Key, E> temp = getmin(rt.getRight());
				rt.setValue(temp.getValue());
				rt.setKey(temp.getKey());
				rt.setRight(deletemin(rt.getRight()));
			}
		}
		return rt;
	}

	public void print(BSTElement<Key,E> rt) {
		if (rt == null) return;
		print(rt.getLeft());
		System.out.print(rt.getKey()+" | ");
		print(rt.getRight());

	}

	public BSTElement<Key, E> getRoot(){
		return root;
	}

	/** @return Record with key value k, null if none exist.
	@param k The key value to find. */
	public  BSTElement<Key, E> find2(Key k) { return (BSTElement<Key, E>) findhelp2(root, k); }

	private  BSTElement<Key, E> findhelp2(BSTElement<Key,E> rt, Key k) {
		if (rt == null) return null;
		if (rt.getKey().compareTo(k) > 0)
			return findhelp2(rt.getLeft(), k);
		else if (rt.getKey().compareTo(k) == 0) return rt;
		else return findhelp2(rt.getRight(), k);
	}

	public int getNodecount() {
		return nodecount;
	}

	/** reset all tree nodes with a default color.*/
	public void reset(BSTElement<Key,E> rt) {
		if (rt == null) return;
		reset(rt.getLeft());
		rt.getVisualizer().setColor("black");
		reset(rt.getRight());
	}

	/** Highlight the nodes with earthquake magnitude between
		min and max values in a unique color and highlight the 
		remaining visited nodes in a different color. */

//	public  BSTElement<Key, E> range(Key a, Key b) { return (BSTElement<Key, E>) rangeHelp(root, a, b); }

	public  void rangeHelp(BSTElement rt, Key min, Key max) {
		if (rt == null) return;
		if (((Comparable<? super Key>) rt.getKey()).compareTo(min) >= 0){
			rt.getVisualizer().setColor("red");
		}
		rangeHelp(rt.getLeft(), min, max);
		if (((Comparable<? super Key>) rt.getKey()).compareTo(max) <= 0) {
			rt.getVisualizer().setColor("red");
		}
		 rangeHelp(rt.getRight(), min, max);
		if ((((Comparable<? super Key>) rt.getKey()).compareTo(min) < 0) ||
		     ((Comparable<? super Key>) rt.getKey()).compareTo(max) > 0) {
			rt.getVisualizer().setColor("blue");
		}
	}

//	public  BSTElement<Key, E> rangeHelp(BSTElement rt, Key min, Key max) {
//		if (rt == null) return null;
//		if (((Comparable<? super Key>) rt.getKey()).compareTo(min) >= 0 &&
//				((Comparable<? super Key>) rt.getKey()).compareTo(max) < 0	){
//			rt.getVisualizer().setColor("red");
//			return rangeHelp(rt.getLeft(), min, max);
//		}else if (((Comparable<? super Key>) rt.getKey()).compareTo(max) >= 0 &&
//				((Comparable<? super Key>) rt.getKey()).compareTo(max) < 0	){
//			rt.getVisualizer().setColor("red");
//			return rangeHelp(rt.getRight(), min, max);
//			
//		}else {
//			rt.getVisualizer().setColor("blue");
//			return rt;
//		}
//	}


/** This command will take the location (string) and highlight
all the nodes that contain this.*/
public void findLocation2(BSTElement<Key,E> rt, String location) {
	if (rt == null) return;
	findLocation2(rt.getLeft(), location);
	if(rt.getLabel().toUpperCase().contains(location.toUpperCase()))
		rt.getVisualizer().setColor("yellow");
	findLocation2(rt.getRight(), location);
}


public void findMonthYear(BSTElement<Key,E> rt, String month, String year) {
	if (rt == null) return;
	findMonthYear(rt.getLeft(), month , year);
	if(rt.getLabel().contains(month) && rt.getLabel().contains(year))
		rt.getVisualizer().setColor("yellow");
	// else change to default
	findMonthYear(rt.getRight(), month , year);
}	


}