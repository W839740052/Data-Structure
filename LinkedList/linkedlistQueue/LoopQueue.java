package com.wyh.linkedlistqueue;
/**
 *   Queue<E>   <-----LooQueue<E>
 *   void enqueue(E)		O(1)均摊
 *   E dequeue()			O(1)均摊//循环队列相比于数组队列，它的复杂度降低
 *   E front()				O(1)
 *   int getSzie()			O(1)
 *   boolean isEmpty()		O(1)
 *   */
public class LoopQueue<E> implements Queue<E> {
	private E [] data;
	private int front,tail;//队首和队尾所在的索引
	private int size;//队列中有多少个元素
	
	public LoopQueue(int capacity) {
		data=(E [])new Object[capacity+1];
		front=0;
		tail=0;
		size=0;
	}
	public LoopQueue() {
		this(10);
	}
	public int getCapacity() {
		return data.length-1;
	}
	@Override
	public boolean isEmpty() {
		return front==tail;
	}
	@Override
	public int getSize() {
		return size;
	}
	//循环队列的入队过程
	@Override
	public void enqueue(E e) {
		if((tail+1)%data.length==front)
			resize(getCapacity()*2);
		data[tail]=e;
		tail=(tail+1)%data.length;
		size++;
	}
	//循环队列的出队
	@Override
	public E dequeue() {
		if(isEmpty())
			throw new IllegalArgumentException("Cannot dequeue from an empty queue.");
		E ret=data[front];//保存队首元素
		data[front]=null;//出队的数组元素为引用
		front=(front+1)%data.length;//再循环队列中，因此这样写
		size--;
		if(size==getCapacity()/4 && getCapacity()/2!=0)
			resize(getCapacity()/2);//如果缩小到容量的1/4，就进行缩容，到1/2是为了防止复杂度震荡
		return ret;
	}
	
	//循环队列的扩容
	private void resize(int newCapacity) {
		E [] newData=(E [])new Object [newCapacity+1];
		for(int i=0;i<size;i++)
			newData[i]=data[(front+i)%data.length];//当超过data数组的长度时，为了循环存放，%length就可以回到第0个索引位置
		data=newData;
		front=0;//data=新数组，front=索引为0
		tail=size;
	}
	@Override
	public E getfront() {
		if(isEmpty())
			throw new IllegalArgumentException("Queue is empty");
		return data[front];
	}
	@Override
	public String toString() {
		StringBuilder res=new StringBuilder();
		res.append(String.format("Queue: size=%d,capacity=%d\n",size,getCapacity()));
		res.append("front [");
		for(int i=front;i!=tail;i=(i+1)%data.length) {//因为循环数组，tail可能比front要小,所以条件为2者不想等即可
			res.append(data[i]);
			if((i+1)%data.length!=tail)//如果遍历到的索引不是tail
				res.append(",");
		}
		res.append("]tail");
		return res.toString();
	}
	public static void main(String[] args) {
		LoopQueue<Integer> queue=new LoopQueue<>();
		for(int i=0;i<10;i++) {
			queue.enqueue(i);
			System.out.println(queue);
			if(i%3==2) {
				queue.dequeue();
				System.out.println(queue);
			}
		}
	}
	
}
