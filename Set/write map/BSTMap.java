package com.wyh.map;

import java.util.ArrayList;
/**
 * 平衡二叉树，模版是增加了键值关系的基于映射的二分搜索树改进而来的
 * */
public class BSTMap<K extends Comparable<K>, V> implements Map<K,V>{
	private class Node{
		public K key;
		public V value;
		
		public Node left,right;
		
		public Node(K key, V value) {
			this.key=key;
			this.value=value;
			this.left=left;
			this.right=right;
			
		}
	}
	
	private Node root;
	private int size;
	
	@Override
	public int getSize() {
		return size;
	}
	@Override
	public boolean isEmpty() {
		return size==0;
	}
	

	@Override
	//向二分搜索树中添加新的元素(Key, value)
	public void add(K key,V value) {
		root=add(root,key,value);
	}
	
	//向以node为根的二分搜索树中插入元素(key value),递归算法
	//返回插入新节点后二分搜索树的根
	private Node add(Node node, K key, V value) {
		if(node==null) {
			size++;
			return new Node(key,value);
		}
		if(key.compareTo(node.key)<0)
			node.left=add(node.left,key,value);
		else if(key.compareTo(node.key)>0)
			node.right=add(node.right,key,value);
		else//key.comparable(node.key)==0
			node.value=value;
		
	
		return node;	
	}
	//返回以node为根节点的二分搜索树中，key所在的节点
	private Node getNode(Node node, K key) {
		if(node==null)
			return null;
		if(key.compareTo(node.key)==0)
			return node;
		else if(key.compareTo(node.key)<0)
			return getNode(node.left,key);
		else
			return getNode(node.right,key);
			
	}
	@Override
	public boolean contains(K key) {
		return getNode(root,key)!=null;
	}
	@Override
	public V get(K key) {
		Node node=getNode(root,key);
		/*
		if(node!=null) {
			return null;
		}
		else
			return node.value;
			*/
		return node==null? null:node.value;
	}
	public void set(K key,V newValue) {
		Node node= getNode(root,key);
		if(node==null)
			throw new IllegalArgumentException(key+" dosen't exist!");
		else
			node.value=newValue;
	}
	//寻找二分搜索树的最小元素
		public V minimum() {
			if(size==0)
				throw new IllegalArgumentException("BST is empty.");
			
			return minimum(root).value;
		}
	//返回以node为根的二分搜索树的最小值所在的节点
	private Node minimum(Node node) {
		if(node.left==null)
			return null;
		return minimum(node.left);
	}
	//删除掉以node为根的二分搜索树中的最小节点
	//返回删除节点后的新的二分搜索树的根
	public Node removeMin(Node node) {
		if(node.left==null) {
			Node rightnode=node.right;
			node.right=null;
			size--;
			return rightnode;
		}
		node.left=removeMin(node.left);
		return node;		
	}
	//从二分搜索树中删除键为key的节点
	public V remove(K key) {
		Node node=getNode(root,key);
		if(node!=null) {
			root=remove(root,key);
			return node.value;
		}
		return null;//如果key不在map中
	}
	//删除掉以node 为根的二分搜索树中键为key的节点，递归算法
	//返回删除节点后新的二分搜索树的根
	private Node remove(Node node,K key) {
		if(node==null)
			return null;
		if(key.compareTo(node.key)<0) {
			node.left=remove(node.left,key);
			return node;
		}
		else if(key.compareTo(node.key)>0) {
			node.right=remove(node.right,key);
			return node;
		}
		else {
			//待删除节点左子树为空的情况
			if(node.left==null) {
				Node rightnode=node.right;
				node.right=null;
				size--;
				return rightnode;
			}
			//待删除节点右子树为空的情况
			if(node.right==null) {
				Node leftnode=node.left;
				node.left=null;
				size--;
				return leftnode;
			}
			//待删除节点左右子树均不为空的情况
			//找到比待删除节点大的最小节点，即待删除节点右子树的最小节点
			//用这个节点顶替待删除节点的位置
			Node successor=minimum(node.right);
			successor.right=removeMin(node.right);
			size++;
			successor.left=node.left;
			node.right=node.left=null;
			size--;
			return successor;
			
			
		}
	}
	public static void main(String[] args) {
		System.out.println("pride-and-prejudice");
		ArrayList<String> words=new ArrayList<>();
		if(FileOperation.readFile("/Users/wangyinghao/Downloads/pride-and-prejudice.txt", words)) {
			System.out.println("Total words: "+words.size());
			BSTMap<String,Integer> map=new BSTMap<>();
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
