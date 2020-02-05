package com.wyh.leetcode223;
/**
 * 当加入dummyHead之后的解法
 * */
public class Solution2 {
	//这里的head有疑问？？？？？？？
    public ListNode removeElements(ListNode head, int val) {//传入的节点以及节点中的元素，使得和这个节点相匹配的链表节点中所有的节点都删去
    	ListNode dummyHead =new ListNode(-1);//这里可以随便给定值，因为这个dunmmyHead是虚拟的
    	dummyHead.next=head;
    	
    	//如果有虚拟节点，那么要删除的地方都可以理解为：删除链表中间的
    	ListNode pre=dummyHead;
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
        return dummyHead.next;
    }
  //这里的测试部分有疑问？？？
    public static void main(String[] args) {
    	int[] nums= {1,2,6,3,4,5,6};
    	ListNode head=new ListNode(nums);//调用tostring方法，则输入head可以打印整个链表
    	System.out.println(head);
    	
    	ListNode res=(new Solution2()).removeElements(head, 6);
    	
    	System.out.println(res);
    	
    }
}
