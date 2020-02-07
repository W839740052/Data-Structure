package com.wyh.avltree;

import java.util.ArrayList;

/**
 * 使用链表实现映射
 * */
public class LinkedListMap<K,V> implements Map<K,V>{
	private class Node{
		public K key;
		public V value;
		public Node next;
		
		public Node(K key,V value,Node next) {
			this.key=key;
			this.value=value;
			this.next=next;
		}
		public Node(K key) {
			this(key,null,null);
		}
		public Node() {
			this(null,null,null);
		}
		@Override
		public String toString() {
			return key.toString()+" : "+value.toString();
		}
		
	}
	
	private Node dummyHead;
	private int size;
	//构造函数
	public LinkedListMap() {
		dummyHead=new Node();
		size=0;
	}
	@Override
	public int getSize() {
		return size;
	}
	@Override
	public boolean isEmpty() {
		return size==0;
	}
	//构建辅助函数，返回获取对应键的node
	private Node getNode(K key) {
		Node cur=dummyHead.next;
		while(cur!=null) {
			if(cur.key.equals(key))
				return cur;
			cur=cur.next;
		}
		return null;//如果找完了还没有，返回空
	}
	@Override
	public boolean contains(K key) {
		return getNode(key)!=null;
	}
	@Override
	public V get(K key) {
		Node node=getNode(key);
		return node==null?null:node.value;
	}
	@Override
	//映射中的key不可以重复
	public void add(K key,V value) {
		Node node=getNode(key);//查看有没有key
		if(node==null) {//如果没有，则添加在头部
			dummyHead.next=new Node(key,value,dummyHead.next);
			size++;
		}
		else
			node.value=value;//如果添加重复的键，就更新用户传进来的值
	}
	@Override
	public void set(K key,V newValue) {
		Node node=getNode(key);
		if(node==null) {
			throw new IllegalArgumentException(key+" doesn't exist!");
		}
		node.value=newValue;//存在key就直接设置传入的值
	}
	@Override
	public V remove(K key) {
		Node pre =dummyHead;
		while(pre.next!=null) {
			if(pre.next.key.equals(key))
				break;
			pre=pre.next;
		}
		if(pre.next!=null) {
			Node delNode=pre.next;
			pre.next=delNode.next;
			delNode.next=null;
			size--;
			return delNode.value;
		}
		return null;//如果key不存在则无法删除
	}
	public static void main(String[] args) {
		System.out.println("pride-and-prejudice");
		ArrayList<String> words=new ArrayList<>();
		if(FileOperation.readFile("/Users/wangyinghao/Downloads/pride-and-prejudice.txt", words)) {
			System.out.println("Total words: "+words.size());
			LinkedListMap<String,Integer> map=new LinkedListMap<>();
			for(String word:words) {
				if(map.contains(word))//寻找有无这个单词
					map.set(word, map.get(word)+1);//如果有，对应key下的value值+1
				else
					map.add(word, 1);//无，将单词放入key中，对应value变为1，相当于记一次数
			}
			System.out.println("Total different words: "+map.getSize());
			System.out.println("Frequency of words: "+map.get("is"));
			
		}
	}

}
