package com.wyh.stack;

public class TestS {

	public static void main(String[] args) {
		ArrayStack<Integer> stack=new ArrayStack<>();
		//入栈
		for(int i=0;i<5;i++) {
			stack.push(i);
			System.out.println(stack);
		}
		//出栈
		stack.pop();
		System.out.println(stack);

	}

}
