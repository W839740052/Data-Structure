package com.wyh.leetcode20;

import java.util.Stack;

import java.util.Stack;

class Solution {
	public boolean isVlaid(String s) {
		Stack<Character> stack=new Stack<>();
		for(int i=0;i<s.length();i++) {
			char c=s.charAt(i);//每次查看第i个字符是什么
			if(c=='(' || c=='[' || c=='{' ) {//如果是左边，则压入栈
				stack.push(c);
			}else {
				if(stack.isEmpty()) {//如果是空，则返回false
					return false;
				}
				char topChar =stack.pop();
				if(c==')' && topChar!='(') 
					return false;
				if(c==']' && topChar!='[')
					return false;
				if(c=='}' && topChar!='{')
					return false;
			}
		}
		return stack.isEmpty();//最后栈里为空才匹配成功
	}
	//测试代码
	public static void main(String[] args) {
		Solution sl=new Solution();
		System.out.println(sl.isVlaid("()[]"));
		System.out.println(sl.isVlaid("()[["));
		System.out.println(sl.isVlaid("([{}])"));
	}
}
