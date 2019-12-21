import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class A2BSTree<E> implements Tree<E>
{
	private Node head;
	private int size;

	private class Node
	{
		public Node parent, left, right;
		public int number, height;
		public E value;
		public boolean done;

		// sets all the values when creating a node
		public Node(Node parent, Node left, Node right, E value)
		{
			this.parent = parent;
			this.left = left;
			this.right = right;
			this.value = value;
			this.number = 1;
			this.done = false;
			this.height = 0;
		}

		// this method initializes the done variable to false in every Node
		public void init(Node node)
		{
			Node curNode = node;
			if (curNode == null)
				return;

			while (true)
			{
				if (curNode.parent == null && (curNode.left == null || !curNode.left.done)
						&& (curNode.right == null || !curNode.right.done))
					return;
				curNode.done = false;
				if (curNode.left != null && curNode.left.done)
					curNode = curNode.left;
				else if (curNode.right != null && curNode.right.done)
					curNode = curNode.right;
				else
					curNode = curNode.parent;
			}

		}

		// returns the node with the lowest value of the tree
		public Node lastLeft()
		{
			Node curNode = this;
			while (curNode.left != null)
				curNode = curNode.left;
			return curNode;
		}

		// returns the node with the highest value of the tree
		public Node lastRight()
		{
			Node curNode = this;
			while (curNode.right != null)
				curNode = curNode.right;
			return curNode;
		}
	}

	public A2BSTree()
	{
		this.head = null;
		this.size = 0;
	}

	// adds a new node holding the value e
	@Override
	public void add(E e)
	{
		// if there is no head, then the new value's node is the head
		if (this.head == null)
			this.head = new Node(null, null, null, e);

		else
		{
			// start at the top
			Node cur = this.head;
			boolean placed = false;
			// keep looping while the value has not been placed
			while (!placed)
			{
				// if the value we are inserting is equal to the current node's
				// value
				// add 1 to the number of that value, and we are done.
				if (e.equals(cur.value))
				{
					cur.number++;
					placed = true;
				}

				// if the new value is less than the current value go to the
				// left
				// if the left is empty place it there
				else if (((Comparable<E>) e).compareTo(cur.value) < 0)
				{
					if (cur.left == null)
					{
						cur.left = new Node(cur, null, null, e);
						placed = true;
					}
					else
						cur = cur.left;
				}

				// same but for right
				else
				{
					if (cur.right == null)
					{
						cur.right = new Node(cur, null, null, e);
						placed = true;
					}
					else
						cur = cur.right;
				}
			}
		}
		this.size++;
	}

	// adds all the values in a collection
	@Override
	public void addAll(Collection<? extends E> c)
	{
		// get the iterator, and add them one by one in a while loop
		Iterator<? extends E> it = c.iterator();
		while (it.hasNext())
			this.add(it.next());
	}

	// remove a value from the tree
	@Override
	public boolean remove(Object o)
	{
		// if the size is 0, then we are done, the removal fails
		if (this.size() == 0)
			return false;

		// start at the top
		Node cur = this.head;

		while (true)
		{

			// if the value is equal to the current node's value
			if (((E) o).equals(cur.value))
			{

				// subtract 1 to the number of that value
				cur.number--;

				// if the number of that value is 0, then remove it
				if (cur.number == 0)
				{
					// first check if the right is null, that is where we will
					// find the replacement
					if (cur.right != null)
					{
						// saving the current right node
						Node newRightNode = cur.right;

						// set the replacement to be the lowest number we can
						// (closest to the removed)
						Node replacement = newRightNode.lastLeft();

						Node newParentNode = null;
						// if the replacement is not equal to the right node,
						// then the new parent is equal to the last right node,
						// from the replacement
						if (replacement != newRightNode)
							newParentNode = replacement.lastRight();

						// if they are equal move the new right node to the
						// right one more time
						if (replacement == newRightNode)
							newRightNode = newRightNode.right;

						if (newRightNode != null && newRightNode.left == replacement)
							newRightNode.left = null;
						// if the removed value is not the head, make the
						// parents point to the
						// replacement
						if (cur != this.head)
						{
							if (cur.parent.right == cur)
								cur.parent.right = replacement;
							else
								cur.parent.left = replacement;
						}
						// if the removed value is the head, make the
						// replacement the new head
						else
							this.head = replacement;

						// set the replacements new parent to be the removed
						// values parent
						replacement.parent = cur.parent;

						if (newParentNode != null)
						{
							if (newParentNode != newRightNode)
								newParentNode.right = newRightNode;

							if (newRightNode != null && newRightNode != newParentNode)
								newRightNode.parent = newParentNode;
						}
						else
						{
							replacement.right = newRightNode;
							if (newRightNode != null)
								newRightNode.parent = replacement;
						}

						// set the replacement's left to be the removed value's
						// left
						// and the left's parent to be the replacement
						if (cur.left != null)
						{
							replacement.left = cur.left;
							cur.left.parent = replacement;
						}
					}
					// same but for left.
					else if (cur.left != null)
					{
						Node newLeftNode = cur.left;
						Node replacement = newLeftNode.lastRight();
						Node newParentNode = null;
						if (replacement != newLeftNode)
							newParentNode = replacement.lastLeft();

						if (replacement == newLeftNode)
							newLeftNode = newLeftNode.left;
						if (newLeftNode != null && newLeftNode.right == replacement)
							newLeftNode.right = null;
						if (cur != this.head)
						{
							if (cur.parent.right == cur)
								cur.parent.right = replacement;
							else
								cur.parent.left = replacement;
						}
						else
							this.head = replacement;

						replacement.parent = cur.parent;

						if (newParentNode != null)
						{
							if (newParentNode != newLeftNode)
								newParentNode.left = newLeftNode;

							if (newLeftNode != null && newLeftNode != newParentNode)
								newLeftNode.parent = newParentNode;
						}
						else
						{
							replacement.left = newLeftNode;
							if (newLeftNode != null)
								newLeftNode.parent = replacement;
						}
						if (cur.right != null)
						{
							replacement.right = cur.right;
							cur.right.parent = replacement;
						}
					}

					// if there is no left and right node, and this is the head,
					// then make
					// the head null
					else if (cur == this.head)
					{
						this.head = null;
					}
					// if the value is an end node, (has no children)
					// then set their parent's value to not point to it.
					else
					{
						if (cur.parent.right == cur)
							cur.parent.right = null;
						else
							cur.parent.left = null;
					}

				}
				// decrease the size, and return a success
				this.size--;
				return true;
			}

			// if the current node's value is higher than the attempted removed
			// value
			else if (((Comparable<E>) o).compareTo(cur.value) < 0)
			{
				// the value is not in this tree, so it fails
				if (cur.left == null)
					return false;

				// go to the left
				else
					cur = cur.left;
			}

			// same but for right
			else
			{
				if (cur.right == null)
					return false;

				else
					cur = cur.right;
			}
		}
	}

	// the tree's iterator
	public Iterator<E> iterator()
	{
		// create a list and add in order each value
		ArrayList<E> list = new ArrayList<E>();
		Node curNode = this.head;

		// if the tree is empty, then the empty iterator is returned
		if (this.head == null)
			return list.iterator();

		// resets the value of done to false
		this.head.init(this.head);

		while (true)
		{

			// if we are at the head, and the left and right nodes are done then
			// we are done
			// return the iterator
			if (curNode.parent == null && (curNode.left == null || curNode.left.done)
					&& (curNode.right == null || curNode.right.done))
			{
				if (!curNode.done)
					for (int i = 0; i < curNode.number; i++)
						list.add(curNode.value);
				return list.iterator();
			}

			// if the left is not done, go to the left
			if (curNode.left != null && !curNode.left.done)
				curNode = curNode.left;

			// if the current is not done, add to the list the value the number
			// of the value times
			else if (!curNode.done)
			{
				for (int i = 0; i < curNode.number; i++)
					list.add(curNode.value);
				curNode.done = true;
			}

			// if the right is not done, go to the right
			else if (curNode.right != null && !curNode.right.done)
				curNode = curNode.right;

			// otherwise go up the tree
			else
				curNode = curNode.parent;
		}
	}

	// the height of the tree
	@Override
	public int height()
	{
		Node curNode = this.head;
		// if the tree is empty return -1
		if (this.head == null)
		{
			return -1;
		}

		// reset the value of done for each node
		this.head.init(this.head);
		while (true)
		{
			// if we are at the head, and the left and right are done, then
			// return the
			// maximum of the height of the right and left + 1, and set it to
			// the node
			if (curNode.parent == null && (curNode.left == null || curNode.left.done)
					&& (curNode.right == null || curNode.right.done))
			{
				int rightHeight = curNode.right == null ? -1 : curNode.right.height;
				int leftHeight = curNode.left == null ? -1 : curNode.left.height;
				curNode.height = 1 + Math.max(rightHeight, leftHeight);
				return curNode.height;
			}

			// if the left is not done, go to the left
			if (curNode.left != null && !curNode.left.done)
			{
				curNode = curNode.left;
			}

			// else if the right is not done, go to the right
			else if (curNode.right != null && !curNode.right.done)
			{
				curNode = curNode.right;
			}

			// else if the current is not done, then get the max of the left and
			// right,
			// add 1 to it, and set the current node's height to that height
			else if (!curNode.done)
			{
				int rightHeight = curNode.right == null ? -1 : curNode.right.height;
				int leftHeight = curNode.left == null ? -1 : curNode.left.height;
				curNode.height = 1 + Math.max(rightHeight, leftHeight);
				curNode.done = true;
			}
			else
				curNode = curNode.parent;
		}
	}

	// return the size
	@Override
	public int size()
	{
		return size;
	}

	/*
	 * public void print() { infixPrint(this.head, 0); }
	 * 
	 * protected void infixPrint(Node t, int levelCount) { if (t == null)
	 * return;
	 * 
	 * infixPrint(t.right, levelCount + 1); for (int i = 0; i < levelCount; i++)
	 * System.out.print("    "); System.out.println(t.value); infixPrint(t.left,
	 * levelCount + 1); }
	 */
}
