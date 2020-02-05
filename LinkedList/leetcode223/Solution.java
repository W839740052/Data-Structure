package com.wyh.leetcode223;

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
/**
 * 题目:
 * Remove all elements from a linked list of integers that have value val.

Example:

Input:  1->2->6->3->4->5->6, val = 6
Output: 1->2->3->4->5*/
public class Solution {
	//这里的head有疑问:head 是传入链表的头节点，即我们需要操作的链表的头节点，val是我们要在head这个头节点的链表上要删除的元素
    public ListNode removeElements(ListNode head, int val) {//传入的节点以及节点中的元素，使得和这个节点相匹配的链表节点中所有的节点都删去
        while(head!=null && head.val==val) {//如果链表的头部以及后面的部分（删去头部后下一个就为新的头部，加while就会使得和头部链接的匹配全部删除）
        	ListNode delNode=head;
        	head=head.next;
        	delNode.next=null;
        	//head=head.next;//在leetcode中上面3句话就可以这样写，因为所有的内存都会被删去，只要向后挪一下
        }
        if(head==null) {//如果这个链表中所有的元素都是要删除的元素
        	return head;//return null
        	
        }
        //删除链表中间的
        ListNode pre=head;
        while(pre.next!=null) {
        	if(pre.next.val==val) {
        		ListNode delNode=pre.next;
        		pre.next=delNode.next;
        		delNode.next=null;
        		//pre.next=pre.next.next;//在leetcode中上面3句话就可以这样写，因为所有的内存都会被删去,只要将其向后挪一下
        	}else {
        		pre=pre.next; 
        	}
        }
        return head;
    }
    //这里的测试部分有疑问？？？
    public static void main(String[] args) {
    	int[] nums= {1,2,6,3,4,5,6};
    	ListNode head=new ListNode(nums);//调用tostring方法，则输入head可以打印整个链表
    	System.out.println(head);
    	
    	ListNode res=(new Solution()).removeElements(head, 6);
    	
    	System.out.println(res);
    	
    }
}
