import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class A1ArrayList<E> implements List<E> {

	private E[] array;
	private int pos;

	public A1ArrayList(){
		array = (E[]) new Object[1];
		pos = 0;
	}
	
	private void update() {
		if (array.length != 1) {
			if ((float) pos / (float) array.length <= 0.25) {
				E[] newArray = (E[]) new Object[array.length / 2];
				for (int i = 0; i < pos; i++)
					newArray[i] = array[i];
				array = newArray;
			}
		}
		if (pos == array.length) {
			E[] newArray = (E[]) new Object[array.length * 2];
			for (int i = 0; i < pos; i++)
				newArray[i] = array[i];
			array = newArray;

		}
	}

	@Override
	public boolean add(E e) {
		// I was very confused as to what sort of situation would cause false to
		// be given
		// so I just used a try catch, to make sure.
		try {
			array[pos++] = e;
			update();
			return true;
		} catch (Exception ex) {

		}
		return false;
	}

	@Override
	public void add(int index, E element) {
		if (index < 0 || index > pos)
			throw new IndexOutOfBoundsException();
		// System.out.println(pos);
		update();
		for (int i = pos; i > index; i--) {
			array[i] = array[i - 1];
		}
		pos++;
		array[index] = element;
	}

	@Override
	public void clear() {
		pos = 0;
		array = (E[]) new Object[1];
	}

	@Override
	public E remove(int index) {
		if (index < 0 || index >= pos)
			throw new IndexOutOfBoundsException();
		E value = array[index];
		for (int i = index; i < pos - 1; i++) {
			array[i] = array[i + 1];
		}
		pos--;
		array[pos] = null;
		update();
		return value;
	}

	@Override
	public boolean remove(Object o) {
		for (int i = 0; i < pos; i++) {
			if (array[i].equals((E) o)) {
				remove(i);
				return true;
			}
		}
		return false;
	}

	@Override
	public int size() {
		return pos;
	}

	public String toString() {
		String str = "[";
		boolean isFirst = true;
		for (int i = 0; i < pos; i++) {
			if (!isFirst)
				str += ", ";
			str += this.array[i];
			isFirst = false;
		}

		return str + "]";
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
