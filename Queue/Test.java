package com.wyh.queue;

import java.util.Random;

/**
 * 测试循环队列和数组队列的复杂度有什么不同
 * ArrayQueue, time: 34.819770123 s
	LoopQueue, time: 0.011986602 s
 * */
public class Test {
	//测试使用q运行opCount个enqueue和dequeue操作所需要的时间，单位:秒
	//Queue<Integer> q 来继承Queue接口，这使得ArrayQueue 和LoopQueue都可以传入
	private static double testQueue(Queue<Integer> q,int opCount) {//只有自己的main可以调用
		long startTime=System.nanoTime();
		Random random=new Random();//随机数
		for(int i=0;i<opCount;i++) 
			q.enqueue(random.nextInt(Integer.MAX_VALUE));//入队一个随机数,随机数为从0->int的最大值
		for(int i=0;i<opCount;i++)
			q.dequeue();//再一个个删去
		long endTime=System.nanoTime();
		return (endTime-startTime)/1000000000.0;//纳秒和秒之间转化,10的9次方
	}
	public static void main(String[] args) {
		int opCount=100000;//具体要操作的数据
		
		ArrayQueue<Integer> arrayQueue=new ArrayQueue<>();
		double time1=testQueue(arrayQueue,opCount);
		System.out.println("ArrayQueue, time: "+time1+" s");
		
		LoopQueue<Integer> loopQueue=new LoopQueue<>();
		double time2=testQueue(loopQueue,opCount);
		System.out.println("LoopQueue, time: "+time2+" s");
	}

}
