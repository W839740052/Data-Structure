package com.wyh.segmenttree;
//融合
public class Main {
	public static void main(String[] args) {
		Integer[] nums= {-2,0,3,-5,2,-1};
		SegmentTree<Integer> segtree=new SegmentTree<>(nums,new Merger<Integer>() {//（a,b）->a+b也可以用蓝布塔表达式
			@Override
			public Integer merge(Integer a,Integer b) {
				return a+b;
			}
		});
		System.out.println(segtree);
		
	}
}
