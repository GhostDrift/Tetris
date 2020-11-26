package DoubleLinkedList;

import java.io.Serializable;

public class DoubleLinkedList<T> extends LLBase<T> implements Serializable {

	//Default constructor
	DoubleLinkedList(){
		
	}
	
	@Override
	public int capacity() {
		return this.size();
	}

	@Override
	public boolean isEmpty() {
		if(this.size() == 0) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public int size() {
		return this.size;
	}

	@Override
	public boolean contains(T o) {
		int i = 0;
		Node<T> ptr = this.head;
		while(i < this.size()) {
			if(o.equals(ptr.getValue())) {
				return true;
			}
			else {
				i++;
				ptr = ptr.getNext();
			}
		}
		return false;
		
	}

	@Override
	public T[] toArray(T[] array) throws IllegalArgumentException {
		if(array.length < this.size()) {
			throw new IllegalArgumentException();
		}
		else {
			Node<T> ptr = this.head;
			for (int i = 0; i < this.size(); i++) {
				array[i] = ptr.getValue();
				ptr = ptr.getNext();
			}
			return array;
		}
	}

	@Override
	public void add(T e) {
		if(this.size() == 0) {
			Node<T> newNode = new Node<T>(e);
			this.head = newNode;
			this.tail = newNode;
			size++;
		}
		else if (this.size() == 1) {
			Node<T> newNode = new Node<T>(e);
			this.head.setNext(newNode);
			this.tail = newNode;
			newNode.setPrevious(this.head);
			this.size++;
		}
		else {
			Node<T> ptr = this.setPtr(this.size()-1);
			Node<T> newNode = new Node<T>(e);
			ptr.setNext(newNode);
			newNode.setPrevious(ptr);
			ptr.setNext(newNode);
			this.tail = newNode;
			this.size++;
		}
	}

	@Override
	public void add(int index, T element) throws IndexOutOfBoundsException {
		if(index < 0) {
			throw new IndexOutOfBoundsException();
		}
		else if (index == 0) {
			if (this.size() == 0) {
				Node<T> newNode = new Node<T>(element);
				this.head = newNode;
				this.tail = newNode;
				this.size++;
			}
			else if (this.size() == 1) {
				Node<T> newNode = new Node<T>(element);
				Node<T> ptr = this.head;
				this.tail = ptr;
				ptr.setPrevious(newNode);
				newNode.setNext(ptr);
				this.head = newNode;
				this.size++;
			}
			else {
				Node<T> newNode = new Node<T>(element);
				this.head.setPrevious(newNode);
				newNode.setNext(this.head);
				this.head = newNode;
				this.size++;
			}
			
		}
		else if (index >= this.size()) {
			this.add(element);
		}
		else {
			Node<T> newNode = new Node<T>(element);
			Node<T> ptr = this.setPtr(index);
			newNode.setNext(ptr);
			newNode.setPrevious(ptr.getPrevious());
			ptr.getPrevious().setNext(newNode);
			ptr.setPrevious(newNode);
			this.size++;
		}
		
	}

	@Override
	public boolean remove(T o) {
		int i = 0;
		Node<T> ptr = this.head.getNext();
		if(this.size() == 1) {
			if (o.equals(this.head.getValue())) {
				this.head = null;
				this.tail = null;
				this.size--;
				return true;
			}
		}
		if(o.equals(this.head.getValue())) {
			ptr.setPrevious(null);
			this.head = ptr;
			this.size--;
			return true;
		}
		else if(o.equals(this.tail.getValue())) {
			ptr = this.tail.getPrevious();
			ptr.setNext(null);
			this.tail = ptr;
			this.size--;
			return true;
		}
		else
		while(i < this.size()-1) {
			if(o.equals(ptr.getValue())) {
				Node<T> temp = ptr.getNext();
				temp.setPrevious(ptr.getPrevious());
				temp = ptr.getPrevious();
				temp.setNext(ptr.getNext());
				this.size--;
				return true;
			}
		}
		return false;
	}

	@Override
	public T remove(int index) throws IndexOutOfBoundsException {
		if(index < 0 || index >= this.size()) {
			throw new IndexOutOfBoundsException();
		}
		else if(index == 0) {
			Node<T> temp = this.head;
			temp.getNext().setPrevious(null);
			this.head = temp.getNext();
			this.size--;
			return temp.getValue();
		}
		else if (index == this.size()-1) {
			Node<T> temp = this.tail;
			temp.getPrevious().setNext(null);
			this.tail = temp.getPrevious();
			this.size--;
			return temp.getValue();
		}
		else if (this.size() == 1){
			Node<T> temp = this.head;
			this.head = null;
			this.tail = null;
			this.size = 0;
			return temp.getValue();
		}
		else {
			Node<T> ptr = this.setPtr(index);
			ptr.getNext().setPrevious(ptr.getPrevious());
			ptr.getPrevious().setNext(ptr.getNext());
			this.size--;
			return ptr.getValue();
		}
	}

	@Override
	public void clear() {
		this.size = 0;
		this.head = null;
		this.tail = null;
	}

	@Override
	public T get(int index) throws IndexOutOfBoundsException {
		if(index < 0 || index >= this.size()) {
			throw new IndexOutOfBoundsException();
		}
		else {
			Node<T> ptr = this.setPtr(index);
			return ptr.getValue();
		}
	}

	@Override
	public T set(int index, T element) throws IndexOutOfBoundsException {
		if(index < 0 || index >= this.size()) {
			throw new IndexOutOfBoundsException();
		}
		else {
			Node<T> ptr = this.setPtr(index);
			Node<T> newNode = new Node<T>(element);
			newNode.setNext(ptr.getNext());
			newNode.setPrevious(ptr.getPrevious());
			ptr.getPrevious().setNext(newNode);
			ptr.getNext().setPrevious(newNode);
			return ptr.getValue();
		}
	}

	@Override
	public int indexOf(T o) {
		Node<T> ptr = this.head;
		int i = 0;
		while(i < this.size()) {
			if(o.equals(ptr.getValue())) {
				return i;
			}
			else {
				i++;
				ptr.getNext();
			}
		}
		return -1;
	}

	@Override
	public int lastIndexOf(T o) {
		Node<T> ptr = this.tail;
		int i = this.size()-1;
		while(i >= 0) {
			if(o.equals(ptr.getValue())) {
				return i;
			}
			else {
				i--;
				ptr = ptr.getPrevious();
			}
		}
		return -1;
	}

	@Override
	public void printForward() {
		if(this.size() > 0) {
			Node<T> ptr = this.head;
			int i = 0;
			while(i< this.size()) {
				System.out.println(ptr);
				if(i < this.size() -1) {
					ptr = ptr.getNext();
				}
				i++;
			}
		}		
	}

	@Override
	public void printReverse() {
		Node<T> ptr = this.tail;
		int i = this.size();
		while(i > 0) {
			System.out.println(ptr);
			if(i > 1) {
				ptr = ptr.getPrevious();
			}
			i--;
		}
		
	}
	private Node<T> setPtr(int index ){
		if (index == 0) {
			return this.head;
		}
		else {
			Node<T> ptr = this.head;
			int i = 0;
			while(i < index) {
				ptr = ptr.getNext();
				i++;
			}
			return ptr;
		}
	}

}
