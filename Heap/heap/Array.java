package com.wyh.heap;

public class Array<E> {
	private E[] data;
	private int size;
	
	//构造函数，传入数组的容量capacity构造Array
	public Array(int capacity) {
		data=(E[])new Object[capacity];//使用泛型，但是不可以直接new泛型，需要强制转化
		size=0;
	}
	//无参数的构造函数，默认数组的容量capacity=10
	public Array() {
		this(10);
	}
	//传来一个数组，将数组放入data的动态数组中
	public Array(E[] arr) {
		data=(E[])new Object[arr.length];//这里注意，需要这样写，同上
		for(int i=0;i<arr.length;i++) {
			data[i]=arr[i];
		}
		size=arr.length;
	}
	//获取数组中的元素个数
	public int getSize() {
		return size;
	}
	//获取数组中的容量
	public int getCapacity() {
		return data.length;
	}
	//返回数组是否为空
	public boolean isEmpty() {
		return size==0;
	}
	//向所有元素后添加一个新元素
	public void addLast(E e) {
		/*
		if(size==data.length) {
			throw new IllegalArgumentException("AddLast failed,array is full");
		}
		data[size]=e;
		size++;
		*/
		add(size,e);
	}
	//在所有元素前面添加一个新元素w
	public void addFirst(E e) {
		add(0,e);
	}
	//在第index位置插入一个新元素
	public void add(int index,E e) {
		if(index<0 || index>size) {
			throw new IllegalArgumentException("AddLast failed.Require index >= 0 and index <= size");
		}
		if(size==data.length) {
			resize(2*data.length);
		}
		for(int i=size-1;i>=index;i--) {
			data[i+1]=data[i];
		}
		data[index]=e;
		size++;
	}
	
	//获取index索引位置的元素
	E get(int index) {
		if(index<0 || index>=size) {
			throw new IllegalArgumentException("Get failed. Index is illegal.");
		}
		return data[index];
	}
	//修改index索引位置的元素为e
	void set(int index,E e) {
		if(index<0 || index>=size) {
			throw new IllegalArgumentException("Get failed. Index is illegal.");
		}
		data[index]=e;
	}
	
	//查找数组中是否有元素e
	public boolean contains(E e) {
		for(int i=0;i<size;i++) {
			if(data[i].equals(e)) {//使用泛型之后，data[i]和e都为类对象，之间的比较则要用equals,为“值比较”
				return true;
			}
		}
		return false;
	}
	
	//查找数组中元素e所在的索引，如果不存在元素e，则返回-1
	public int find(E e) {
		for(int i=0;i<size;i++) {
			if(data[i].equals(e)) {
				return i;
			}
		}
		return -1;
	}
	//从数组中删除index位置的元素，返回删除的元素
	public E remove(int index) {
		if(index<0 || index>=size) {
			throw new IllegalArgumentException("Get failed. Index is illegal.");
		}
		E ret=data[index];//将待删除元素存起来
		for(int i=index+1;i<size;i++) {
			data[i-1]=data[i];
		}
		size--;
		data[size]=null;//可以让此处的值被回收 loitering objects !=memory leak
		
		if(size==data.length/4 && data.length!=0) {//当减少到一定程度，将数组元素减少一半，动态数组，这里最后改为当缩小到1/4时，为了避免复杂度的震荡，数组在满的时候处于扩容和缩容的临界点
			resize(data.length/2);
		}
			return ret;
	}
	
	//从数组中删除第一个元素，返回删除的元素
	public E removeFirst() {
		return remove(0);
	}
	//从数组中删除最后一个个元素，返回删除的元素
	public E removeLast() {
		return remove(size-1);
	}
	//从数组中删除元素e
	public void removeElement(E e) {
		int index=find(e);
		if(index!=-1) {
			remove(index);
		}
	}
	//数组元素的交换
	public void swap(int i, int j) {
		if(i<0 || i>=size ||j<0 || j>=size)
			throw new IllegalArgumentException("Index is illegal.");
		E t=data[i];
		data[i]=data[j];
		data[j]=t;
	}
	@Override
	public String toString() {
		StringBuilder res=new StringBuilder();
		res.append(String.format("Array: size=%d , capacity=%d\n",size,data.length));
		res.append('[');
		for(int i=0;i<size;i++) {
			res.append(data[i]);
			if(i!=size-1) {
				res.append(',');
			}
		}
		res.append(']');
		return res.toString();
	}
	//私有的方法，用户不可以自己调用，只可以作为内部逻辑调用,数组的扩容，动态数组
	private void resize(int newCapacity) {
		E [] newData=(E [])new Object[newCapacity];//java不支持new E
		for(int i=0;i<size;i++) {
			newData[i]=data[i];
		}
		data=newData;
	}
}
