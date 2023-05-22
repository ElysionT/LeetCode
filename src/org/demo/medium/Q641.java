package org.demo.medium;

/**
 * 641. 设计循环双端队列
 * 设计实现双端队列。
 * 
 * 实现 MyCircularDeque 类:
 * MyCircularDeque(int k) ：构造函数,双端队列最大为 k 。
 * boolean insertFront()：将一个元素添加到双端队列头部。 如果操作成功返回 true ，否则返回 false 。
 * boolean insertLast() ：将一个元素添加到双端队列尾部。如果操作成功返回 true ，否则返回 false 。
 * boolean deleteFront() ：从双端队列头部删除一个元素。 如果操作成功返回 true ，否则返回 false 。
 * boolean deleteLast() ：从双端队列尾部删除一个元素。如果操作成功返回 true ，否则返回 false 。
 * int getFront() )：从双端队列头部获得一个元素。如果双端队列为空，返回 -1 。
 * int getRear() ：获得双端队列的最后一个元素。 如果双端队列为空，返回 -1 。
 * boolean isEmpty() ：若双端队列为空，则返回 true ，否则返回 false  。
 * boolean isFull() ：若双端队列满了，则返回 true ，否则返回 false 。
 * 
 * 示例 1：
 * 输入
 * ["MyCircularDeque", "insertLast", "insertLast", "insertFront", "insertFront", "getRear", "isFull", "deleteLast", "insertFront", "getFront"]
 * [[3], [1], [2], [3], [4], [], [], [], [4], []]
 * 输出
 * [null, true, true, true, false, 2, true, true, true, 4]
 * 解释
 * MyCircularDeque circularDeque = new MycircularDeque(3); // 设置容量大小为3
 * circularDeque.insertLast(1);			        // 返回 true
 * circularDeque.insertLast(2);			        // 返回 true
 * circularDeque.insertFront(3);			    // 返回 true
 * circularDeque.insertFront(4);			    // 已经满了，返回 false
 * circularDeque.getRear();  					// 返回 2
 * circularDeque.isFull();				        // 返回 true
 * circularDeque.deleteLast();			        // 返回 true
 * circularDeque.insertFront(4);			    // 返回 true
 * circularDeque.getFront();					// 返回 4
 * 
 * 提示：
 * 1 <= k <= 1000
 * 0 <= value <= 1000
 * insertFront, insertLast, deleteFront, deleteLast, getFront, getRear, isEmpty, isFull  调用次数不大于 2000 次
 * 
 * https://leetcode.cn/problems/design-circular-deque/
 */
public class Q641 {
	
	static class MyCircularDeque {
	    class Node{
	        int value;
	        Node start;
	        Node end;
	        Node(int value){
	            this.value = value;
	        }
	        Node(int value, Node end){
	            this.value = value;
	            this.end = end;
	            end.start = this;
	        }
	        Node(Node start, int value){
	            this.start = start;
	            this.value = value;
	            start.end = this;
	        }
	    }
	    
	    final int maxSize;
	    int size;

	    Node firstNode; //deque;
	    Node lastNode;

//	    private Node findFront(Node node){
//	        if(null == node.start){
//	            return node;
//	        }else{
//	            return findFront(node.start);
//	        }
//	    }

//	    private Node findLast(Node node){
//	        if(null==node.end){
//	            return node;
//	        }else{
//	            return findLast(node.end);
//	        }
//	    }

	    public MyCircularDeque(int k) {
	        maxSize = k;
	        size = 0;
	    }
	    
	    public boolean insertFront(int value) {
	        if(size == maxSize){
	            return false;
	        }

//	        if(null == deque){
	        if(0 == size){
	        	lastNode = firstNode = new Node(value);
//	            deque = new Node(value);
	        } else {
//	            final Node temp = findFront(deque);
//	            deque =  new Node(value, temp);
	        	firstNode = new Node(value, firstNode);
//	            temp.start = deque;
	        }        
	        size++;
	        return true;
	    }
	    
	    public boolean insertLast(int value) {
	        if(size == maxSize){
	            return false;
	        }

//	        if(null == deque){
	        if(0 == size){
	        	firstNode = lastNode = new Node(value);
//	            deque = new Node(value);
	        } else {
//	            final Node temp = findLast(deque);
	        	lastNode = new Node(lastNode, value);
//	            temp.end = deque;
	        }
	        size++;
	        return true;
	    }
	    
	    public boolean deleteFront() {
	        if(0==size){
	            return false;
	        }
	        if(1==size) {
//	        	deque = null;
	        	lastNode = firstNode = null;
//	        	size--;
//	        	return true;
	        }else {
				firstNode = firstNode.end;
				firstNode.start = null;
	        }
//	        final Node temp;
//	        if(null == deque.start) {
//	        	temp = deque;
//	        }else {
//	        	temp = findFront(deque);
//	        }
//	        deque = temp.end;
//	        deque.start = null;
//	        temp.end = null;
	        size--;
	        return true;
	    }
	    
	    public boolean deleteLast() {
	        if(0==size){
	            return false;
	        }
	        if(1==size) {
//	        	deque = null;
	        	lastNode = firstNode = null;
//	        	size--;
//	        	return true;
	        }else {
				lastNode = lastNode.start;
				lastNode.end = null;
	        }
//	        final Node temp;
//	        if(null == deque.end) {
//	        	temp = deque;
//	        }else {
//	        	temp = findLast(deque);
//	        }
//	        
////	        final Node temp = findLast(deque);
//	        deque = temp.start;
//	        deque.end = null;
//	        temp.start = null;
	        size--;
	        return true;
	    }
	    
	    public int getFront() {
	        if(0==size){
	            return -1;
	        }
//	        final Node temp = findFront(deque);
	        return firstNode.value;

	    }
	    
	    public int getRear() {
	        if(0==size){
	            return -1;
	        }
//	        final Node temp = findLast(deque);
	        return lastNode.value;

	    }
	    
	    public boolean isEmpty() {
	        return 0==size;

	    }
	    
	    public boolean isFull() {
	        return size==maxSize;

	    }
	}
	
	public static void main(String[] args) {
		// ["MyCircularDeque","insertFront","getFront","isEmpty","deleteFront","insertLast",
		//  "getRear","insertLast","insertFront","deleteLast","insertLast","isEmpty"]
		// [[8],[5],[],[],[],[3],[],[7],[7],[],[4],[]]
//		MyCircularDeque deque = new MyCircularDeque(8);
//		System.out.println("1:"+ deque.insertFront(5));
//		System.out.println("2:"+ deque.getFront());
//		System.out.println("3:"+ deque.isEmpty());
//		System.out.println("4:"+ deque.deleteFront());
//		System.out.println("5:"+ deque.insertLast(3));
//		System.out.println("6:"+ deque.getRear());
//		System.out.println("7:"+ deque.insertLast(7));
//		System.out.println("8:"+ deque.insertFront(7));
//		System.out.println("9:"+ deque.deleteLast());
//		System.out.println("10:"+ deque.insertLast(4));
//		System.out.println("11:"+ deque.isEmpty());
		
		// ["MyCircularDeque","insertFront","deleteLast","getRear","getFront","getFront",
		// "deleteFront","insertFront","insertLast","insertFront","getFront","insertFront"]
		// [[4],[9],[],[],[],[],[],[6],[5],[9],[],[6]]
		MyCircularDeque deque = new MyCircularDeque(4); 
		System.out.println("1 true:"+ deque.insertFront(9));	// true	
		System.out.println("2 true:"+ deque.deleteLast());		// true
		System.out.println("3 -1:"+ deque.getRear());			// -1
		System.out.println("4 -1:"+ deque.getFront());     		// -1
		System.out.println("5 -1:"+ deque.getFront());			// -1
		System.out.println("6 false:"+ deque.deleteFront());  	// false
		System.out.println("7 true:"+ deque.insertFront(6));	// true	
		System.out.println("8 true:"+ deque.insertLast(5));		// true	
		System.out.println("9 true:"+ deque.insertFront(9));	// true	
		System.out.println("10 9:"+ deque.getFront());			// 9	
		System.out.println("11 true:"+ deque.insertFront(6));	// true
	}
}
