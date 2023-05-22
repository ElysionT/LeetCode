package org.demo.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * 658. 找到 K 个最接近的元素
 * 
 * 给定一个 排序好 的数组 arr ，两个整数 k 和 x ，从数组中找到最靠近 x（两数之差最小）的 k 个数。返回的结果必须要是按升序排好的。
 * 
 * 整数 a 比整数 b 更接近 x 需要满足：
 * |a - x| < |b - x| 或者
 * |a - x| == |b - x| 且 a < b
 * 
 * 示例 1：
 * 输入：arr = [1,2,3,4,5], k = 4, x = 3
 * 输出：[1,2,3,4]
 * 
 * 示例 2：
 * 输入：arr = [1,2,3,4,5], k = 4, x = -1
 * 输出：[1,2,3,4]
 * 
 * 提示：
 * 1 <= k <= arr.length
 * 1 <= arr.length <= 104
 * arr 按 升序 排列
 * -104 <= arr[i], x <= 104
 * 
 * https://leetcode.cn/problems/find-k-closest-elements/
 */
public class Q658 {
	
	public List<Integer> findClosestElements(int[] arr, int k, int x) {
        int length;
        if(null==arr||0==(length=arr.length)||k>length){
            return null;
        }

        if(x<=arr[0]){
            return getSubList(arr, 0, k);    
        }
        if(x>=arr[length-1]){
            return getSubList(arr, length-k ,k);
        }

        int matchIndex = findMatchIndex(arr, 0, length-1, x);
        System.out.println("matchIndex:"+matchIndex);
        if(-1==matchIndex){
            return null;
        }
//        return getSubList2(arr, matchIndex, k);
        return getSubList3(arr, matchIndex, k, x);
    }


    List<Integer> getSubList(int[] arr, int start, int size){
        List<Integer> result = new ArrayList<>(size);
        for(int i=0;i<size;start++,i++){
            result.add(arr[start]);
        }
        return result;
    }

    List<Integer> getSubList2(int[] arr, int end, int size){
        int offset = size - end - 1;
        if(offset > 0){
            List<Integer> result = getSubList(arr, 0, end+1);
            for(int i=0;i<offset;i++){
                result.add(arr[++end]);
            }
            return result;
        }else{
            return getSubList(arr, end - size, size);
        }
    }
    
    List<Integer> getSubList3(int[] arr, int start, int size, int target){
    	int length = arr.length;
    	List<Integer> result = new ArrayList<>(size);
    	for(int i=start, j=start+1; size>0;) {
			if (i >= 0) {
				int a = arr[i];
				int al = target - a;
				if (0 == al) {
					result.add(0, a);
					size--;
					i--;
					continue;
				}else {
					if (j < length) {
						int b = arr[j];
						int br = b - target;
						if (al <= br) {
							result.add(0, a);
							size--;
							i--;
							continue;
						} else {
							result.add(b);
							size--;
							j++;
							continue;
						}
					}else {
						result.add(0, a);
						size--;
						i--;
						continue;						
					}					
				}
			}else {
				int b = arr[j];
				result.add(b);
				size--;
				j++;
				continue;				
			}
    		
    	}
    	
    	return result;
    }

    int findMatchIndex(int[] arr, int start, int end, int target){
    	if(start == end) {
    		return start;
    	}
        int index = start + (end - start + 1)/2;
        int a = arr[index];
        if(target == a){
            return index;
        } else if(target<a) {
        	if(arr[index-1]<=target) {
        		return index-1;
        	}else {
        		return findMatchIndex(arr, start, index-1, target);
        	}        	
        }else {
        	if(arr[index+1]>=target) {
        		return index;
        	}else {
        		return findMatchIndex(arr, index+1, end, target);
        	}
        	
        }        
    }
    
    public static void main(String[] args) {
    	Q658 solution = new Q658();
    	System.out.println("1.{{1,2,3,4,5}, 4, 3} [1, 2, 3, 4]:"+solution.findClosestElements(new int[] {1,2,3,4,5}, 4, 3));
    	System.out.println("2.{{1,2,3,4,5}, 4, -1} [1, 2, 3, 4]:"+solution.findClosestElements(new int[] {1,2,3,4,5}, 4, -1));
    	System.out.println("3.{{1,1,1,10,10,10}, 1, 9} [10]:"+solution.findClosestElements(new int[] {1,1,1,10,10,10}, 1, 9));
    	System.out.println("4.{{0,0,1,2,3,3,4,7,7,8}, 3, 5} [3, 3, 4]:"+solution.findClosestElements(new int[] {0,0,1,2,3,3,4,7,7,8}, 3, 5));
    	System.out.println("5.{{1,25,35,45,50,59}, 1, 30} [25]:"+solution.findClosestElements(new int[] {1,25,35,45,50,59}, 1, 30));

    
    	
    	
	}

}
