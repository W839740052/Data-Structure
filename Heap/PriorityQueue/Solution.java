package com.wyhleetcode347;

import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.TreeMap;



public class Solution {
	private class Freq implements Comparable<Freq>{
		public int e,freq;
		public Freq(int e,int freq) {
			this.e=e;
			this.freq=freq;
		}
		@Override
		public int compareTo(Freq another) {
			if(this.freq<another.freq)
				return -1;//定义优先级高的为小的数
			else if(this.freq>another.freq)
				return 1;
			else
				return 0;//频率相等
		}
	}
	public List<Integer> topKFrequent(int[] nums,int k){
		
		TreeMap<Integer,Integer> map=new TreeMap<>();
		for(int num:nums) {
			if(map.containsKey(num)) 
				map.put(num, map.get(num)+1);
			else
				map.put(num, 1);
		}
		
		PriorityQueue<Freq> pq=new PriorityQueue<Freq>();//java中的优先队列是最小堆
		for(int key:map.keySet()) {//对所有的键进行遍历
			if(pq.size()<k)
				pq.add(new Freq(key,map.get(key)));//如果队列中没有k个元素，将map中遍历的key,e放入
			else if(map.get(key)>pq.peek().freq){//如果满足k个,但是map中新遍历的key的频率>队列中的
				pq.remove();//优先队列中队首元素出队
				pq.add(new Freq(key,map.get(key)));//将频率高的进入队
			}
		}
		LinkedList<Integer> res=new LinkedList<>();
		while(!pq.isEmpty())
			res.add(pq.remove().e);
		return res;
		
	}
}
