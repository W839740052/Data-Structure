package com.wyh.priorityqueue;

public class MaxHeap<E extends Comparable<E>> {
	
	private Array<E> data;
	
	public MaxHeap(int capacity) {
		data=new Array<>(capacity);
	}
	public MaxHeap() {
		data=new Array<>();
	}
	public MaxHeap(E[] arr) {
		data=new Array<>(arr);//将传来的数组放到data的动态数组中
		for(int i=parent(arr.length-1);i>=0;i--)//从最后一个节点的父节点开始，依次下沉
			siftDown(i);
	}
	//返回堆中的元素个数
	public int size() {
		return data.getSize();
	}
	
	//返回一个布尔值，表示堆中是否为空
	public boolean isEmpty() {
		return data.getSize()==0;
	}
	//返回完全二叉树的数组表示中，一个索引所表示的该元素的父亲节点的索引
	private int parent(int index) {
		if(index==0)
			throw new IllegalArgumentException("index-0 dosen't have parent.");
		return (index-1)/2;
	}
	//返回返回完全二叉树的数组表示中，一个索引所表示的该元素的左孩子节点的索引
	private int leftChild(int index) {
		return index*2+1;
	}
	//返回返回完全二叉树的数组表示中，一个索引所表示的该元素的右孩子节点的索引
	private int rightChild(int index) {
		return index*2+2;
	}
	//向堆中添加元素
	public void add(E e) {
		data.addLast(e);//向数组末尾添加，相当于在完全二叉树的最后一层的最后一个添加
		siftUp(data.getSize()-1);//需要上浮的元素的索引，即数组的大小-1；
	}
	private void siftUp(int k) {
		//索引不可以等于0，即自己就是根节点，并且父节点的值和自己相比<0
		while(k>0 && data.get(parent(k)).compareTo(data.get(k))<0) {
			data.swap(k, parent(k));//交换
			k=parent(k);//对于下一轮循环，k就来到了新的位置，直到k=0或者k所在元素比父亲所在的元素值要小
		}
	}
	//看堆中的最大元素
	public E findMax() {
		if(data.getSize()==0)
			throw new IllegalArgumentException("Can not findMax when heap is empty.");
		return data.get(0);
	}
	//取出堆中的最大元素
	public E extractMax() {
		E ret=findMax();
		data.swap(0, data.getSize()-1);//首先将0位置的元素和最后一个元素位置互换
		data.removeLast();//再将最后一个元素删去
		siftDown(0);//将第一个元素做下沉操作
		return ret;
	}
	private void siftDown(int k) {
		while(leftChild(k)<data.getSize()) {//结束条件为左孩子比堆的大小还要大，因为右孩子比左孩子还要大
			int j=leftChild(k);//获取左孩子的索引
			if(j+1<data.getSize() && data.get(j+1).compareTo(data.get(j))>0)//如果存在右孩子且右孩子大于左边
				j++;
			//此时data[j]是liftChild 和rightChild 中的最大值
			if(data.get(k).compareTo(data.get(j))>=0)
				break;
			else
				data.swap(k, j);
			k=j;//进行下一轮循环
		}
	}
	//取出堆中的最大元素，并且替换成元素e
	public E replace(E e) {
		E ret=findMax();
		data.set(0, e);//将堆顶的元素替换成新元素
		siftDown(0);//下沉
		return ret;
	}
	
}
