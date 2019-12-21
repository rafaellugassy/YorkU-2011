import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

// most are the same as the BSTree
public class A2AVLTree<E> implements Tree<E>
{

	private Node head;
	private int size;

	private class Node
	{
		public Node parent, left, right;
		public int number, height;
		public E value;
		public boolean done = false;

		public Node(Node parent, Node left, Node right, E value)
		{
			this.parent = parent;
			this.left = left;
			this.right = right;
			this.value = value;
			this.number = 1;
			this.height = 0;
		}

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

		public Node lastLeft()
		{
			Node curNode = this;
			while (curNode.left != null)
				curNode = curNode.left;
			return curNode;
		}

		public Node lastRight()
		{
			Node curNode = this;
			while (curNode.right != null)
				curNode = curNode.right;
			return curNode;
		}
	}

	public A2AVLTree()
	{
		this.head = null;
		this.size = 0;
	}

	@Override
	public void add(E e)
	{
		if (this.head == null)
			this.head = new Node(null, null, null, e);

		else
		{
			Node cur = this.head;
			boolean placed = false;
			while (!placed)
			{
				if (e.equals(cur.value))
				{
					cur.number++;
					placed = true;
				}

				else if (((Comparable<E>) e).compareTo(cur.value) < 0)
				{
					if (cur.left == null)
					{
						cur.left = new Node(cur, null, null, e);
						placed = true;
						adjust(cur.parent);
					}
					else
						cur = cur.left;
				}

				else
				{
					if (cur.right == null)
					{
						cur.right = new Node(cur, null, null, e);
						placed = true;
						adjust(cur.parent);
					}
					else
						cur = cur.right;
				}
			}
		}
		this.size++;

	}

	@Override
	public void addAll(Collection<? extends E> c)
	{
		Iterator<? extends E> it = c.iterator();
		while (it.hasNext())
			this.add(it.next());
	}

	@Override
	public boolean remove(Object o)
	{
		if (this.size() == 0)
			return false;
		Node cur = this.head;
		while (true)
		{
			if (((E) o).equals(cur.value))
			{
				cur.number--;
				if (cur.number == 0)
				{
					if (cur.right != null)
					{
						Node newRightNode = cur.right;
						Node replacement = newRightNode.lastLeft();

						Node newParentNode = null;
						if (replacement != newRightNode)
							newParentNode = replacement.lastRight();

						if (replacement == newRightNode)
							newRightNode = newRightNode.right;
						if (newRightNode != null && newRightNode.left == replacement)
							newRightNode.left = null;
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
						if (cur.left != null)
						{
							replacement.left = cur.left;
							cur.left.parent = replacement;
						}
						adjust(replacement);
					}
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
						adjust(replacement);
					}
					else if (cur == this.head)
					{
						this.head = null;
					}
					else
					{
						if (cur.parent.right == cur)
							cur.parent.right = null;
						else
							cur.parent.left = null;
						adjust(cur.parent);
					}

				}
				this.size--;
				return true;
			}
			else if (((Comparable<E>) o).compareTo(cur.value) < 0)
			{
				if (cur.left == null)
					return false;

				else
					cur = cur.left;
			}
			else
			{
				if (cur.right == null)
					return false;

				else
					cur = cur.right;
			}
		}
	}

	@Override
	public Iterator<E> iterator()
	{
		ArrayList<E> list = new ArrayList<E>();
		Node curNode = this.head;
		if (this.head == null)
			return list.iterator();
		this.head.init(this.head);

		while (true)
		{
			if (curNode.parent == null && (curNode.left == null || curNode.left.done)
					&& (curNode.right == null || curNode.right.done))
			{
				if (!curNode.done)
					for (int i = 0; i < curNode.number; i++)
						list.add(curNode.value);
				return list.iterator();
			}

			if (curNode.left != null && !curNode.left.done)
				curNode = curNode.left;
			else if (!curNode.done)
			{
				for (int i = 0; i < curNode.number; i++)
					list.add(curNode.value);
				curNode.done = true;
			}
			else if (curNode.right != null && !curNode.right.done)
				curNode = curNode.right;
			else
				curNode = curNode.parent;
		}
	}

	@Override
	public int height()
	{
		Node curNode = this.head;
		if (this.head == null)
		{
			return -1;
		}

		this.head.init(this.head);
		while (true)
		{
			if (curNode.parent == null && (curNode.left == null || curNode.left.done)
					&& (curNode.right == null || curNode.right.done))
			{
				int rightHeight = curNode.right == null ? -1 : curNode.right.height;
				int leftHeight = curNode.left == null ? -1 : curNode.left.height;
				curNode.height = 1 + Math.max(rightHeight, leftHeight);
				return curNode.height;
			}
			if (curNode.left != null && !curNode.left.done)
			{
				curNode = curNode.left;
			}
			else if (curNode.right != null && !curNode.right.done)
			{
				curNode = curNode.right;
			}
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

	@Override
	public int size()
	{
		return size;
	}

	// adjusts the tree to stay as an AVL Tree
	private void adjust(Node node)
	{
		// if the node is null, return
		if (node == null)
			return;
		// if this is the head, return
		if (this.head == null)
			return;

		Node curNode = node;
		// set all the heights
		height();
		int leftHeight = 0, rightHeight = 0;
		boolean rightLarger = false;

		// set the current left and right height of the current node's children
		leftHeight = curNode.left == null ? -1 : curNode.left.height;
		rightHeight = curNode.right == null ? -1 : curNode.right.height;

		// if the left height is greater than the right by 2
		if (leftHeight >= rightHeight + 2)
		{
			// set the left to be larger
			rightLarger = false;
			// move to the left
			curNode = curNode.left;
		}

		// same for right
		else if (rightHeight >= leftHeight + 2)
		{
			rightLarger = true;
			curNode = curNode.right;
		}

		// otherwise, check the node up to see if it needs to adjust
		else
		{
			adjust(node.parent);
			return;
		}

		// set the new heights
		int leftHeight2 = curNode.left == null ? -1 : curNode.left.height;
		int rightHeight2 = curNode.right == null ? -1 : curNode.right.height;

		// left left case
		if (leftHeight2 > rightHeight2 && !rightLarger)
		{
			rotateRight(curNode.parent);
		}

		// left right case
		else if (leftHeight2 < rightHeight2 && !rightLarger)
		{
			leftRight(curNode.parent);
		}

		// right right case
		else if (leftHeight2 < rightHeight2 && rightLarger)
		{
			rotateLeft(curNode.parent);
		}

		// right left case
		else if (leftHeight2 > rightHeight2 && rightLarger)
		{
			rightLeft(curNode.parent);
		}

	}

	// rotates the node to the left
	private Node rotateLeft(Node node)
	{
		// sets the node2 to be the right of the node
		Node node2 = node.right;

		// changes the node2's parent to be the node's parent
		node2.parent = node.parent;

		// set's the node's right to be the node2's left
		node.right = node2.left;

		// if the node was the head, now, node2 is the head
		if (node == this.head)
			this.head = node2;

		// if the new right is not null, then the node's new right's parent
		// is equal to it
		if (node.right != null)
			node.right.parent = node;

		// the node's new parent is the node2, and the node2's new left is the
		// node
		node.parent = node2;
		node2.left = node;

		// change node2's new parents to point to it
		if (node2.parent != null)
		{
			if (node2.parent.right == node)
			{
				node2.parent.right = node2;
			}
			else
			{
				node2.parent.left = node2;
			}
		}
		return node2;
	}

	// same, but for right
	private Node rotateRight(Node node)
	{
		Node node2 = node.left;
		node2.parent = node.parent;
		node.left = node2.right;

		if (node == this.head)
			this.head = node2;

		if (node.left != null)
			node.left.parent = node;

		node.parent = node2;
		node2.right = node;

		if (node2.parent != null)
		{
			if (node2.parent.left == node)
			{
				node2.parent.left = node2;
			}
			else
			{
				node2.parent.right = node2;
			}
		}
		return node2;
	}

	// first rotate left, then right, so it can rebalance properly
	private Node leftRight(Node node)
	{
		node.left = rotateLeft(node.left);
		return rotateRight(node);
	}

	// first rotate right, then left, so it can rebalance properly
	private Node rightLeft(Node node)
	{
		node.right = rotateRight(node.right);
		return rotateLeft(node);
	}
}
