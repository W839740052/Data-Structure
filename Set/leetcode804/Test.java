package com.wyh.leetcode804;

public class Test {

	public static void main(String[] args) {
		String[] codes={".-","-...","-.-.","-..",".","..-.","--.","....","..",".---","-.-",".-..","--","-.","---",".--.","--.-",".-.","...","-","..-","...-",".--","-..-","-.--","--.."};
		
		String [] words= {"good","name","father"};
		for(String word:words) {
			StringBuilder res=new StringBuilder();
			for(int i=0;i<word.length();i++)
			res.append(codes[word.charAt(i)-'a']);
			System.out.print(res.toString());
		}
		

	}

}
