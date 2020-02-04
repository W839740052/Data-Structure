package com.wyh.set;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

//二分搜索树必须要满足可比较性，因此需要继承comparable接口
public class BST<E extends Comparable<E>>{
	//首先声明二分搜索树的节点类
	private class Node{
		public E e;
		public Node left;
		public Node right;
		
		public Node(E e) {
			this.e=e;
			left=null;
			right=null;
		}
	}
	private Node root;//根节点
	private int size;//记录二分搜索树存储了多少个元素
	
	public BST() {
		root=null;
		size=0;
	}
	//二分搜索树存储了多少个元素
	public int size() {
		return size;
	}
	public boolean isEmpty() {
		return size==0;
	}
	//向二分搜索树添加新的元素
	public void add(E e) {
			root=add(root,e);
	}
	//向以node为根的二分搜索树中插入元素E，递归算法
	//返回插入新节点后二分搜索树的根
	private Node add(Node node, E e) {
		if(node==null) {
			size++;
			return new Node(e);
		}
		if(e.compareTo(node.e)<0)
			node.left=add(node.left,e);//因为此时直接判断后就向子节点移动，当子节点是所在位置时，为了和根节点相连，需要用上一个node来接住新的子节点
		else if(e.compareTo(node.e)>0)//这里没有判断相等的情况，因此else if需要明确判断。没有写相等，那么当相等时就什么也不做
			node.right=add(node.right,e);
		return node;
	}
	//看二分搜索树中是否包含元素e
	public boolean contains(E e) {
		return contains(root,e);
	}
	//看以node为根的二分搜索树中是否包含元素e，递归算法
	private boolean contains(Node node, E e) {
		if(node==null)
			return false;
		
		if(e.compareTo(node.e)==0)
			return true;
		else if(e.compareTo(node.e)<0)
			return contains(node.left,e);
		else //e.compareTo(node.e)>0
			return contains(node.right,e);	
	}
	////////////////////////
	//         5          //
	//       /   \        //
	//     3      6       //
	//    / \       \     //
	//  2     4       8   //
    ////////////////////////

	//二分搜索树的前序遍历
	public void preOder() {
		preOder(root);
	}
	private void preOder(Node node) {
		if(node==null)
			return;
		System.out.println(node.e);//就是将node中的元素打印输出
		preOder(node.left);
		preOder(node.right);
		
	}
	//二分搜索树的中序遍历
	public void inOder() {
		inOder(root);
	}
	//中序遍历以node为根的二分搜索树
	private void inOder(Node node) {
		if(node==null)
			return;
		inOder(node.left);//先遍历左边，遍历到最后一个，再依次打印输出
		System.out.println(node.e);
		inOder(node.right);
	}
	//二分搜索树的后序遍历
	public void postOder() {
		postOder(root);
	}
	private void postOder(Node node) {
		if(node==null)
			return;
		postOder(node.left);
		postOder(node.right);
		System.out.println(node.e);
	}
	//二分搜索树的前序遍历的非递归写法
	public void preOderNR() {
		Stack<Node> stack=new Stack<>(); 
		stack.push(root);
		while(!stack.isEmpty()) {//只要不为空
			Node cur=stack.pop();//依次出栈
			System.out.println(cur.e);
			//根节点打印出后，就要访问左右子树，先右后左
			if(cur.right!=null)
				stack.push(cur.right);//如果右边子树为空，就不压入栈
			if(cur.left!=null)
				stack.push(cur.left);//如果左边子树为空，就不压入栈
			
		}
	}
	
	//二分搜索树的层序遍历,和前序遍历非递归写法一样，只不过改为用栈来写
	public void levelOder() {
		Queue<Node> q=new LinkedList<>();
		q.add(root);
		while(!q.isEmpty()) {
			Node cur= q.remove();
			System.out.println(cur.e);
			if(cur.left!=null)
				q.add(cur.left);
			if(cur.right!=null)
				q.add(cur.right);
		}
	}
	
	//寻找二分搜索树的最小元素
	public E minimum() {
		if(size==0)
			throw new IllegalArgumentException("BST is empty.");
		
		return minimum(root).e;
	}
	//返回以node为根的二分搜索树的最小值所在的节点
	private Node minimum(Node node) {
		if(node.left==null)
			return node;
		
		return minimum(node.left);
	}
	//寻找二分搜索树的最大元素
	public E maximum() {
		if(size==0)
			throw new IllegalArgumentException("BST is empty.");
		return maximum(root).e;
	}
	//返回以node为根的二分搜索树的最大值所在的节点
	public Node maximum(Node node) {
		if(node.right==null)
			return node;
		
		return maximum(node.right);
	}
	
	//从二分搜索树中删除最小值所在的节点，返回最小值
	public E removeMin() {
		E ret=minimum();//调用上面查询最小值的方法
		
		root=removeMin(root);//递归调用,删除了以node为根的二分搜索树的最小值，之后又返回了删除之后的二分搜索树对应的根节点
		return ret;
	}
	//删除掉以node为根的二分搜索树中的最小节点
	//返回删除节点后新的二分搜索树的根
	private Node removeMin(Node node) {
		//递归到底的情况
		if(node.left==null) {
			Node rightnode=node.right;//因为当前节点可能存在右子树，因此需要创建节点来保存
			node.right=null;//使右子树和root脱离
			size--;
			return rightnode;//删除root后，返回删除节点后新的树的根，这里为右子树，如果右子树为空，则返回空	
		}
		//如果没有递归到底
		node.left=removeMin(node.left);//用前一个节点把后一个节点接住，为了删除后将剩余有用的部分连接到树中，将树与掉目标元素脱离，返回整个树
		return node;//再删除最小值后，返回当前node节点二分搜索树的根
	}
	
	//从二分搜索树中删除最大值所在的节点
	public E removeMax() {
		E ret=maximum();
		root=removeMax(root);
		return ret;
	}
	//删除掉以node为根的二分搜索树中的最大节点
	//返回删除节点后新的二分搜索树的根
	private Node removeMax(Node node) {
		//递归到底的情况,以及只有一个根节点的情况
		if(node.right==null) {
			Node leftnode=node.left;//可能存在左子树
			node.left=null;
			size--;
			return leftnode;//返回需要留下来的子树或者节点
		}
		node.right=removeMax(node.right);//如果是目标删除元素，删除掉后，将会返回空或者是删除后的其余子树，用上一个节点接住，从而达到删除目标元素的目的
		return node;//返回最终的树根
	}
	//从二分搜索树中删除元素为e的节点
	public void remove(E e) {
		root=remove(root,e);
	}
	//删除以node为根的二分搜索树中值为e的节点，递归算法
	//返回删除节点后新的二分搜索树的根
	private Node remove(Node node,E e) {
		
		if(node==null)
			return null;
		if(e.compareTo(node.e)<0) {
			node.left=remove(node.left,e);
			return node;
		}
		else if(e.compareTo(node.e)>0) {
			node.right=remove(node.right,e);
			return node;		
		}
		else //e==node.e
			//删除只有右孩子的节点，和删除最小元素一样的步骤
			if(node.left==null){
				Node rightnode=node.right;
				node.right=null;
				size--;
				return rightnode;
			}
			//删除只有左孩子的节点，和删除最大元素详细
			if(node.right==null) {
				Node leftnode=node.left;
				node.left=null;
				size--;
				return leftnode;
			}
			//删除节点左右子树均不为空的情况
			//找到比待删除节点大的最小节点，即待删除节点右子树的最小节点
			//用这个节点顶替待删除节点的位置
			Node successor =minimum(node.right);//找到该节点右边子树的最小值，调用找到最小值方法
			successor.right=removeMin(node.right);//找到目标节点的右边=删去右边最小节点后返回的根节点
			size++;//在removeMin中进行了一次size--;然而实际上并没有减，而是此时在sucessor指着
			
			successor.left=node.left;
			
			node.left=null;//原来节点的左边删去
			node.right=null;//原来节点的右边删去
			size--;//此时要在下方返回删去节点后的根，因此减少大小
			
			return successor;//返回删除根节点后的根
	}
	@Override
	//打印输出，需要用递归实现
	public String toString() {
		StringBuilder res=new StringBuilder();
		generateBSTString(root,0,res);
		return res.toString();
	}
	//生成以node为根节点，深度为depth的描述二叉树的字符串
	private void generateBSTString(Node node,int depth,StringBuilder res ) {//最后要生成字符串，所以要传入stringbuilder
		if(node==null) {
			res.append(generateDepthString(depth)+"null\n");//深度+null
			return;//结束
		}
		res.append(generateDepthString(depth)+node.e+"\n");//将根节点的深度以及元素输出
		generateBSTString(node.left,depth+1,res);
		generateBSTString(node.right,depth+1,res);
		
	}
	private String generateDepthString(int depth) {
		StringBuilder res=new StringBuilder();
		for(int i=0;i<depth;i++)
			res.append("--");
		return res.toString();
	}
}
