package com.wyh.segmenttree;

public class SegmentTree<E> {
	
	private E[] tree;
	private E[] data;
	private Merger<E> merger;
	
	public SegmentTree(E[] arr,Merger<E> merger) {
		this.merger=merger;
		
		data=(E[])new Object[arr.length];
		for(int i=0;i<data.length;i++) 
			data[i]=arr[i];
		
		tree=(E[])new Object[4*arr.length];//线段树的空间为数组元素的4倍
		buildSegmentTree(0,0,data.length-1);
	}
	//在treeIndex的位置创建表示区间[l....r]的线段树
	private void buildSegmentTree(int treeIndex,int l,int r) {
		
		if(l==r) {//递归到底的情况，只有一个元素
			tree[treeIndex] = data[l];
			return;
		}
		//如果没有递归到底，首先计算左右节点的子树的索引
		int leftTreeIndex=leftChild(treeIndex);
		int rightTreeIndex=rightChild(treeIndex);
		
		int mid=l+(r-l)/2;//区间中点的位置，这样写是为了防止l+r整型溢出
		buildSegmentTree(leftTreeIndex,l,mid);
		buildSegmentTree(rightTreeIndex,mid+1,r);
		
		tree[treeIndex]=merger.merge(tree[leftTreeIndex], tree[rightTreeIndex]);
		
	}
	public int getSize() {
		return data.length;
	}
	public E get(int index) {
		if(index<0 || index>=data.length)
			throw new IllegalArgumentException("Index is illegal.");
		return data[index];
	}
	//返回完全二叉树的数组表示中，一个索引所表示的元素的左孩子节点的索引
	private int leftChild(int index) {
		return 2*index+1;
	}
	//返回完全二叉树的数组表示中，一个索引所表示的元素的右孩子节点的索引
	private int rightChild(int index) {
		return 2*index+2;
	}
	//返回[queryL,queryR]的值
	public E query(int queryL,int queryR) {
		//边界检查
		if(queryL<0 || queryL>=data.length || queryR<0 || queryR>=data.length || queryL >queryR) {
			throw new IllegalArgumentException("Index is illegal.");
		}
		return query(0,0,data.length-1,queryL,queryR);
	}
	//在以treeID为根的线段树中[l...r]的范围里，搜索区间[queryL.....queryR]的值
	private E query(int treeIndex,int l,int r,int queryL,int queryR) {
		
		if(l==queryL && r==queryR) 
			return tree[treeIndex];
		
		int mid=l+(r-l)/2;
		int leftTreeIndex=leftChild(treeIndex);
		int rightTreeIndex=rightChild(treeIndex);
		if(leftTreeIndex>=mid+1)
			return query(rightTreeIndex,mid+1,r,queryL,queryR);
		else if(rightTreeIndex<=mid)
			return query(leftTreeIndex,l,mid,queryL,queryR);
		//当查找的范围在左右子树都有的时候
		E leftResult=query(leftTreeIndex,l,mid,queryL,mid);
		E rightResult=query(rightTreeIndex,mid+1,r,mid+1,queryR);
		return merger.merge(leftResult, rightResult);
	}
	
	//将index位置的值，更新为e
	public void set(int index,E e) {
		if(index<0 || index>=data.length)
			throw new IllegalArgumentException("Index is illegal.");
		data[index]=e;
		set(0,0,data.length-1,index,e);
	}
	//在以treeIndex为根的线段树中更新index的值为e
	private void set(int treeIndex,int l,int r,int index, E e) {
		if(l==r) {
			//递归到底的情况
			tree[treeIndex]=e;
			return;
		}
		int mid=l+(r-l)/2;
		int leftTreeIndex=leftChild(treeIndex);
		int rightTreeIndex=rightChild(treeIndex);
		if(index>=mid+1)
			set(rightTreeIndex,mid+1,r,index,e);
		else 
			set(leftTreeIndex,l,mid,index,e);
		tree[treeIndex]=merger.merge(tree[leftTreeIndex], tree[rightTreeIndex]);//将每个节点的值更新
	}
	@Override
	public String toString() {
		StringBuilder res=new StringBuilder();
		res.append("[");
		for(int i=0;i<tree.length;i++) {
			if(tree[i]!=null)
				res.append(tree[i]);
			else
				res.append("null");
			if(i!=tree.length-1)
				res.append(",");
		}
		res.append("]");
		return res.toString();
	}
}
