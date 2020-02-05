package com.wyh.linkedlistStack;

import java.util.Random;

/**
 * 用于比较链表栈和数组栈的运行时间复杂度，即所用的时间快慢
 * 其实这个时间比较很复杂，因为linkedlist包含很多new操作
 * 可能当次数扩大时，linkedlist要慢很多，但是差异不大
 * 但是这两种栈的时间复杂度是一致的
 * */
public class Test {
	private static double testStack(Stack<Integer> stack,int opCount) {
		long startTime= System.nanoTime();
		Random random=new Random();
		for(int i=0;i<opCount;i++) {
			stack.push(random.nextInt(Integer.MAX_VALUE));
		}
		for(int i=0;i<opCount;i++) {
			stack.pop();
		}
		long endTime= System.nanoTime();
		
		return (endTime-startTime)/1000000000.0;
	}
	public static void main(String[] args) {
		int opCount=1000000;
		ArrayStack<Integer> arraystack=new ArrayStack<>();
		double time1=testStack(arraystack,opCount);
		System.out.println("arraystack :"+time1+"s");
		
		LinkedListStack<Integer> linkedliststack=new LinkedListStack<>();
		double time2=testStack(linkedliststack,opCount);
		System.out.println("linkedliststack :"+time2+"s");
	}
}
