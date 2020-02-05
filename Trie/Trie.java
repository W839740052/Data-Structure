package com.wyh.trie;

import java.util.TreeMap;

public class Trie {
	
	private class Node{
		public boolean isWord;
		public TreeMap<Character,Node> next;
		
		public Node(boolean isWord) {
			
			this.isWord=isWord;
			next=new TreeMap<>();
		}
		public Node() {//默认情况下为false
			this(false);
		}
	}
	
	private Node root;
	private int size;
	public Trie() {
		root=new Node();
		size=0;
	}
	//获得trie中的容量
	public int getSize() {
		return size;
	}
	//向trie中添加一个新的单词word
	public void add(String word) {
		Node cur=root;
		for(int i=0;i<word.length();i++) {
			char c=word.charAt(i);
			if(cur.next.get(c)==null)//看cur的下一个是否有指向key=c的节点
				cur.next.put(c, new Node());//如果没有，则用map中的put方法，创建一个key为c的value为new node（）的映射
			cur=cur.next.get(c);//这里get(c)才是节点，向后遍历
		}
		if(!cur.isWord) {//判断这个节点以前是否表示一个单词的结尾，如果已经表示，则无法进入循环
			cur.isWord=true;//到一个单词末尾
			size++;
		}
	}
	//查询单词word是否在trie中
	public boolean contains(String word) {
		Node cur=root;
		for(int i=0;i<word.length();i++) {
			char c=word.charAt(i);
			if(cur.next.get(c)==null)
				return false;
			cur=cur.next.get(c);
		}
		return cur.isWord;//不可以直接返回true,因为可能在panda中查询pan
	}
	//查询是否在Trie中有单词以prefix为前缀
	public boolean Prefix(String prefix) {
		Node cur=root;
		for(int i=0;i<prefix.length();i++) {
			char c=prefix.charAt(i);
			if(cur.next.get(c)==null)
				return false;
			cur=cur.next.get(c);
		}
		return true;//如果每个单词都可以找到，就直接返回true
	}
}
