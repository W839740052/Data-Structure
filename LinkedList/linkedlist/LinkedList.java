package com.wyh.linkedlist;

/**
 * 链表的时间复杂度分析
 * 添加操作:
 * addFirst(e) O(1)
 * addLast(e)  O(n)
 * add(index,e)O(n/2)=O(n)
 * 
 * 删除操作
 * removeLast() O(n)
 * removeFirst() O(1)
 * remove(index,e) O(n/2)=O(n)
 * 
 * 修改操作
 * set(index,e)   O(n)
 * contains(e)    O(n)
 * find(e)		  O(n)
 * */

public class LinkedList<E> {
	private class Node{//将节点设置为链表中的内部类，私有的，即只有在链表这个数据结构内可以访问到Node
		public E e;
		public Node next;//这里是public 因为可以在linkedlist中随机访问和修改，不需要设置get和set方法
		//构造函数
		public Node(E e,Node next) {
			//因为Node中成员变量和用户传来的参数重名，因此需要加this
			this.e=e;//将用户传来的e赋值到当前节点的e
			this.next=next;//将用户传来的next赋值到当前节点的next
		}
		public Node(E e) { this(e,null);}
		
		public Node() { this(null,null);}
		
		@Override
		public String toString() { return e.toString();}	
	}
	private Node dummyHead;//创建虚拟头节点
	private int size;
	
	public LinkedList() {
		dummyHead=new Node(null,null);
		size=0;
	}
	
	//获取链表中元素的个数
	public int getSzie() {
		return size;
	}
	//返回链表是否为空
	public boolean isEmpty() {
		return size==0;
	}
	
	//在链表的index(0-based)位置添加新的元素
	//在链表中不是一个常用的操作:练习用
	public void add(int index,E e) {
		if(index<0 || index>size) {
			throw new IllegalArgumentException("Add failed.Illefal index.");
		}
		
			Node pre=dummyHead;
			for(int i=0;i<index;i++) {//加入了一个虚拟头节点后，遍历链表的次数从index-1--->index，这里是为了找index前一个位置，因此从dummyhead开始遍历
				pre=pre.next;
			}
				Node node=new Node(e);
				node.next=pre.next;
				pre.next=node;
				//pre.next=new Node(e,pre.next);//上述3句可以简化为这一句
				size++;
	}
	//在链表头添加新的元素
		public void addFirst(E e) {
			/*
			Node node=new Node(e);
			node.next=head;
			head=node;
			*/
			//head=new Node(e,head);//可以简化为这，创建一个新的node，元素为e，next为head，再把head等于这个新的node
			/**加了虚拟头节点后*/
			add(0,e);//直接调用add，因为有个虚拟头节点，此时直接调用按插入某个节点的方法即可
		}
	//在链表末尾添加新的元素
		public void addLast(E e) {
			add(size,e);
		}
		//获得链表的第index(0-based)个位置的元素
		//在链表中不是一个常用的操作:练习用
		public E get(int index) {
			if(index<0 || index>=size) {
				throw new IllegalArgumentException("Get failed.Illefal index.");
			}
			Node cur=dummyHead.next;//这里是要找index位置的元素，因此从dummyhead后一个开始遍历
			for(int i=0;i<index;i++) {
				cur=cur.next;
			}
			return cur.e;
		}
		//获得链表的第一个元素
		public E getFirst() {
			return get(0);
		}
		
		//获得链表的最后一个元素
		public E getLast() {
			return get(size-1);
		}
		//从链表中删除任意元素
		public void removeElement(E e) {
			Node pre=dummyHead;
			while(pre.next!=null) {
						
				if(pre.next.e.equals(e))
					break;
				pre=pre.next;
			}
			if(pre.next!=null) {
				Node delNode=pre.next;
				pre.next=delNode.next;
				delNode.next=null;
				size--;
			}
		}
		//修改链表的第index(0-based)位置的元素为e
		//在链表中不是一个常用的操作:练习用
		public void set(int index,E e) {
			if(index<0 || index>=size) {
				throw new IllegalArgumentException("Set failed.Illefal index.");
			}
			Node cur=dummyHead.next;
			for(int i=0;i<index;i++) {
				cur=cur.next;
			}
			cur.e=e;	
		}
		//查找链表中是否有元素e
		public boolean contains(E e) {
			Node cur=dummyHead.next;
			while(cur!=null) {
				if(cur.e.equals(e)) {
					return true;
				}
				cur=cur.next;
			}
			return false;
		}
		//删除链表中的元素，返回删除的元素
		//在链表中不是一个常用的操作，练习用
		public E remove(int index) {
			if(index<0 || index>=size) {
				throw new IllegalArgumentException("Remove failed.Illefal index.");
			}
			Node pre=dummyHead;
			for(int i=0;i<index;i++) {
				pre=pre.next;
			}
			Node retNode=pre.next;//将要删除的节点储存
			pre.next=retNode.next;
			retNode.next=null;
			size--;
			return retNode.e;
					
		}
		//从链表中删除最后一个元素
		public E removeLast() {
			return remove(size-1);
		}
		
		//从链表中删除第一个元素
		public E removeFirst() {
			return remove(0);
		}
		
		@Override
		public String toString() {
			StringBuilder res=new StringBuilder();
			//while循环
			/*
			Node cur=dummyHead.next;
			while(cur!=null) {
				res.append(cur+"->");
				cur=cur.next;
			}
			*/
			//for循环
			for(Node cur=dummyHead.next;cur!=null;cur=cur.next) {
				res.append(cur+"->");
			}
			res.append("NULL");
			return res.toString();
		}
		
}
