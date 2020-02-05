package com.wyh.unionfind;
/**
 * union find第二版，基于树的优化，比较慢是因为会形成链表
 * */
public class UnionFind2 implements UF{
	private int[] parent;
	
	public UnionFind2(int size) {
		parent=new int[size];
		
		for(int i=0;i<size;i++)
			parent[i]=i;
	}
	@Override
	public int getSize() {
		return parent.length;
	}
	//查找过程，查找元素p所对应的集合编号
	//O(h)复杂度，h为树的高度
	private int find(int p) {
		if(p<0 || p>=parent.length)
			throw new IllegalArgumentException("p is out of bound.");
		while(p!=parent[p])
			p=parent[p];
		return p;//此方法只限定索引值和对应的集合数值相同时的查找跟节点
	}
	//查看元素p和元素q是否属于一个集合
	//O(h)复杂度，h为树的高度
	@Override 
	public boolean isConnected(int p,int q) {
		return find(p)==find(q);
	}
	//合并元素p和元素q所属的集合
	//O(h)复杂度，h为树的高度
	@Override
	public void unionElements(int p,int q) {
		int pRoot=find(p);
		int qRoot=find(q);
		if(pRoot==qRoot)
			return;
		parent[pRoot]=qRoot;
	}
}
