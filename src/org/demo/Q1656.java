package org.demo;

import java.util.ArrayList;
import java.util.List;

/**
 * 1656. 设计有序流
 * 
 * 有 n 个 (id, value) 对，其中 id 是 1 到 n 之间的一个整数，value 是一个字符串。不存在 id 相同的两个 (id, value) 对。
 * 设计一个流，以 任意 顺序获取 n 个 (id, value) 对，并在多次调用时 按 id 递增的顺序 返回一些值。
 * 实现 OrderedStream 类：
 * OrderedStream(int n) 构造一个能接收 n 个值的流，并将当前指针 ptr 设为 1 。
 * String[] insert(int id, String value) 向流中存储新的 (id, value) 对。存储后：
 * 如果流存储有 id = ptr 的 (id, value) 对，则找出从 id = ptr 开始的 最长 id 连续递增序列 ，并 按顺序 返回与这些 id 关联的值的列表。然后，将 ptr 更新为最后那个  id + 1 。
 * 否则，返回一个空列表。
 */
public class Q1656 {
    
	public static void main(String[] args) {
//    	 ["OrderedStream","insert","insert","insert","insert","insert"]
//    	 [[5],[3,"ccccc"],[1,"aaaaa"],[2,"bbbbb"],[5,"eeeee"],[4,"ddddd"]]
    	OrderedStream stream = new OrderedStream(5);
    	System.out.println(" 1:"+ stream.insert(3, "ccccc"));
    	System.out.println(" 2:"+ stream.insert(1, "aaaaa"));
    	System.out.println(" 3:"+ stream.insert(2, "bbbbb"));
    	System.out.println(" 4:"+ stream.insert(5, "eeeee"));
    	System.out.println(" 5:"+ stream.insert(4, "ddddd"));   	
		
	}
}
	
class OrderedStream {
	class Item {
		int id;
		String value;

		Item(int id, String value) {
			this.id = id;
			this.value = value;
		}
	}

	private final Item[] mItems;
	private final int length;
	private int ptr;

	public OrderedStream(int n) {
		mItems = new Item[n];
		length = n;
		ptr = 0;

	}
	
	public List<String> insert(int idKey, String value) {
		Item item = new Item(idKey, value);
		mItems[idKey - 1] = item;
		List<String> result = new ArrayList<>();
		for (; ptr < length;) {
			Item temp = mItems[ptr];
			if (null == temp) {
				break;
			} else {
				result.add(temp.value);
				ptr++;
			}
		}
		return result;
	}
}
