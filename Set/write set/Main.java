package com.wyh.set;

import java.util.ArrayList;

/**
 * 测试用链表实现的集合以及二叉搜索树实现的集合的时间复杂度
 * */
public class Main {
	private static double testSet(Set<String> set,String filename) {
		long startTime=System.nanoTime();
		System.out.println(filename);
		ArrayList<String> words=new ArrayList<>();
		if(FileOperation.readFile(filename, words)) {
			System.out.println("Total words:"+words.size());
			//使用set来计算一本书的所有不同的单词总数，因为set集合规定它的元素不可以重复
			for(String word:words) {
				set.add(word);
			}
			System.out.println("Total different words: "+set.getSize());
		}
		long endTime=System.nanoTime();
		
		return (endTime-startTime)/1000000000.0;
	}
	public static void main(String[] args) {
		String filename="/Users/wangyinghao/Downloads/pride-and-prejudice.txt";
		
		BSTSet<String> bstset=new BSTSet<>();
		double time1= testSet(bstset,filename);
		System.out.println("BSTset: "+time1+"s");
		System.out.println();
		
		LinkedListSet<String> linkedlistset=new LinkedListSet<>();
		double time2= testSet(linkedlistset,filename);
		System.out.println("LinkedListset: "+time2+"s");
		System.out.println();

        AVLSet<String> avlSet = new AVLSet<>();
        double time3 = testSet(avlSet, filename);
        System.out.println("AVL Set: " + time3 + " s");
	}
}
