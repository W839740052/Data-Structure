package com.wyh.map;

import java.util.ArrayList;



public class Main {
	private static double testMap(Map<String,Integer> map, String filename) {
		long startTime=System.nanoTime();
		System.out.println(filename);
		ArrayList<String> words=new ArrayList<>();
		if(FileOperation.readFile("/Users/wangyinghao/Downloads/pride-and-prejudice.txt", words)) {
			System.out.println("Total words: "+words.size());
			
			for(String word:words) {
				if(map.contains(word))//寻找有无这个单词
					map.set(word, map.get(word)+1);//如果有，对应key下的value值+1
				else
					map.add(word, 1);//无，将单词放入key中，对应value变为1，相当于记一次数
			}
			System.out.println("Total different words: "+map.getSize());
			System.out.println("Frequency of words: "+map.get("is"));
			
		}
		long endTime=System.nanoTime();
		return (endTime-startTime)/1000000000.0;
	}	
	public static void main(String[] args) {
		String filename="pride-and-prejudice.txt";
		BSTMap<String,Integer> bstmap=new BSTMap<>();
		double time1=testMap(bstmap,filename);
		System.out.println("bstmap: "+time1+" s");
		System.out.println("#######################");
		LinkedListMap<String,Integer> linkedlistmap=new LinkedListMap<>();
		double time2=testMap(linkedlistmap,filename);
		System.out.println("linkedlistmap: "+time2+" s");
		System.out.println("#######################");
		AVLMap<String, Integer> avlMap = new AVLMap<>();
        double time3 = testMap(avlMap, filename);
        System.out.println("AVL Map: " + time3 + " s");
	}
}
