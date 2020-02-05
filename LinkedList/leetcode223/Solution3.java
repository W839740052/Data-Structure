package com.wyh.leetcode223;
/**
 * 利用递归的知识来解决此问题
 * */
public class Solution3 {
	public ListNode removeElements(ListNode head,int val) {
		//如果链表的头节点为空
		if(head==null)
			return null;
		//此处是将head后面跟的链表中值为val的节点删除后得到的链表
		ListNode res=removeElements(head.next,val);
		//下面来看head，如果head需要删去，那么只要返回head后面的那一段res即可
		//节点的删除在以下代码中
		if(head.val==val)
			return res;
		else {
			//否则就将head后面的删去val的那一段接到head上，再返回
			head.next=res;
			return head;
		}
	}
	 public static void main(String[] args) {
	    	int[] nums= {1,2,6,3,4,5,6};
	    	ListNode head=new ListNode(nums);//调用tostring方法，则输入head可以打印整个链表
	    	System.out.println(head);
	    	
	    	ListNode res=(new Solution3()).removeElements(head, 6);//删去6这个元素
	    	
	    	System.out.println(res);
	    	
	    }
}
