package com.wyh.leetcode223;
/**
 * 在Solution3的基础上再简化
 * */
public class Solution4 {
	public ListNode removeElements(ListNode head,int val) {
		//如果链表的头节点为空
		if(head==null)
			return null;
		//这里可以将后面的链表直接方链接上head.next
		head.next=removeElements(head.next,val);
		//节点的删除在以下代码中
		return head.val==val? head.next:head;//使用三目运算符来简化下面代码
		/*
		//下面来看head，如果head需要删掉,就返回后面一部分
		if(head.val==val)
			return head.next;
		else {
		//如果不需要删掉head，就返回head
			return head;
		}
		*/
	}
	public static void main(String[] args) {
    	int[] nums= {1,2,6,3,4,5,6};
    	ListNode head=new ListNode(nums);//调用tostring方法，则输入head可以打印整个链表
    	System.out.println(head);
    	
    	ListNode res=(new Solution4()).removeElements(head, 6);//删去6这个元素
    	
    	System.out.println(res);
    	
    }
}
