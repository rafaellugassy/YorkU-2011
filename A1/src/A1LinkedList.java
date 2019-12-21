import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class A1LinkedList<E> implements List<E> {

	private int size;
	private Node head;
	private Node tail;
	private Object curNode;

	public A1LinkedList() {
		size = 0;
		head = null;
		tail = null;
	}

	private class Node {
		Node next;
		Node prev;
		E cur;

		Node(Node next, E cur, Node prev) {
			this.next = next;
			this.cur = cur;
			this.prev = prev;
		}

		Node(E cur, Node prev) {
			this(null, cur, prev);
		}

		Node(Node next, E cur) {
			this(next, cur, null);
		}

		Node(E cur) {
			this(null, cur, null);
		}
	}

	@Override
	public boolean add(E e) {
		// added a try catch, confused on when it would ever return false.
		try {
			if (head == null) {
				head = new Node(e);
				tail = head;
			} else {
				tail.next = new Node(e, tail);
				Node ph = tail;
				tail = tail.next;
				tail.prev = ph;
			}
			size++;
			return true;
		} catch (Exception ex) {

		}
		return false;
	}

	@Override
	public void add(int index, E element) {
		if (index < 0 || index > size())
			throw new IndexOutOfBoundsException();
		if (head == null) {
			add(element);
			return;
		} else {
			if (index == size()) {
				add(element);
				return;
			}
			if (index == 0) {
				head.prev = new Node(head, element);
				head = head.prev;
				size++;
				return;
			}

		}

		if (size() / 2 > index) {
			Node curNode = head;
			for (int i = 0; i < index - 1; i++)
				curNode = curNode.next;
			curNode.next.prev = new Node(curNode.next, element, curNode);
			curNode.next = curNode.next.prev;
			size++;
			return;
		}

		Node curNode = tail;
		for (int i = size() - 1; i > index + 1; i--)
			curNode = curNode.prev;
		curNode.prev.next = new Node(curNode.prev, element, curNode.prev.prev);
		curNode.prev = curNode.prev.next;
		size++;
	}

	@Override
	public void clear() {
		head = null;
		tail = null;
		size = 0;
	}

	@Override
	public E remove(int index) {
		if (index < 0 || index >= size())
			throw new IndexOutOfBoundsException();

		if (size() == 1) {
			E value = head.cur;
			head = null;
			size = 0;
			return value;
		}

		if (size() / 2 > index) {
			Node curNode = head;
			for (int i = 0; i < index; i++)
				curNode = curNode.next;

			E value = curNode.cur;

			if (head == curNode) {
				head = curNode.next;
				head.prev = null;
				size--;
				return value;
			}

			curNode.next.prev = curNode.prev;
			curNode.prev.next = curNode.next;

			size--;
			return value;
		}

		Node curNode = tail;
		for (int i = size - 1; i > index; i--) {
			curNode = curNode.prev;
			// System.out.println(i + " " + curNode.cur + " " + index);
		}

		E value = curNode.cur;

		if (tail == curNode) {
			tail = curNode.prev;
			tail.next = null;
			size--;
			return value;
		}

		curNode.next.prev = curNode.prev;
		curNode.prev.next = curNode.next;

		size--;
		return value;
	}

	@Override
	public boolean remove(Object o) {
		Node curNode = head;
		for (int i = 0; i < size; i++) {
			if ((curNode.cur.equals((E) o))) {
				remove(i);
				return true;
			}
			curNode = curNode.next;
		}
		return false;
	}

	public String toString() {
		String str = "[";
		Node cur = this.head;
		boolean isFirst = true;
		while (cur != null) {
			if (!isFirst)
				str += ", ";
			str += cur.cur;
			cur = cur.next;
			isFirst = false;
		}

		return str + "]";

	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean addAll(Collection<? extends E> arg0) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean addAll(int index, Collection<? extends E> c) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean contains(Object o) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		throw new UnsupportedOperationException();
	}

	@Override
	public E get(int index) {
		throw new UnsupportedOperationException();
	}

	@Override
	public int indexOf(Object o) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean isEmpty() {
		throw new UnsupportedOperationException();
	}

	@Override
	public Iterator<E> iterator() {
		throw new UnsupportedOperationException();
	}

	@Override
	public int lastIndexOf(Object o) {
		throw new UnsupportedOperationException();
	}

	@Override
	public ListIterator<E> listIterator() {
		throw new UnsupportedOperationException();
	}

	@Override
	public ListIterator<E> listIterator(int index) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		throw new UnsupportedOperationException();
	}

	@Override
	public E set(int index, E element) {
		throw new UnsupportedOperationException();
	}

	@Override
	public List<E> subList(int fromIndex, int toIndex) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Object[] toArray() {
		throw new UnsupportedOperationException();
	}

	@Override
	public <T> T[] toArray(T[] a) {
		throw new UnsupportedOperationException();
	}

}
