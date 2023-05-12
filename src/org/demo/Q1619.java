package org.demo;

import java.util.Arrays;

public class Q1619 {
	class SubArray{
        int[] subArray;
        int size;
        int initValue;        

        SubArray(int size){
            this.size = size;
            subArray = new int[size];
        }

        SubArray(int size, int initValue){
            this(size);
            for(int i=0;i<size;i++){
                subArray[i] = initValue;
            }
            this.initValue = initValue;
        }


        boolean insertAndSort(int number, boolean asc){
//            System.out.println("number:"+number + " asc:"+asc);
//            System.out.print("[");
//            for(int i=0;i<size;i++){
//                System.out.print(subArray[i]+",");
//            }
//            System.out.println("]");

            if(asc){
            	int temp = subArray[size-1];
            	if(temp!=initValue && number>=temp) {
            		return true;
            	}            	
            	
                for(int i=0;i<size;i++){
                    int a = subArray[i];
                    if(initValue==a){
                        subArray[i] = number;
                        return false;
                    }
                    if(number<=a){
                        for(int j = size-2; j>=i;j--){
                            subArray[j+1] = subArray[j];                            
                        }
                        subArray[i] = number;
                        return true;
                    }
                }
                return false;
            } else{
            	int temp = subArray[size-1];
            	if(temp!=0 && number<=temp) {
            		return true;
            	} 
            	
                for(int i=0;i<size;i++){
                    int a = subArray[i];
                    if(0==a){
                        subArray[i] = number;
                        return false;
                    }
                    if(number>=a){
                        for(int j = size-2; j>=i;j--){
                            subArray[j+1] = subArray[j];                            
                        }
                        subArray[i] = number;
                        return true;
                    }
                }
                return false;
            }
        }

        int total(){
            int total = 0;
            for(int i=0;i<size;i++){
                total += subArray[i];
            }            
            System.out.println("Sub total:"+total);
            return total;
        }
    }
	
	class Node {
		int value;
		Node previous;
		Node next;

		Node(int value) {
			this.value = value;
		}

		public String toString() {
			return value + "," + next;
		}
	}
	
	public void insert(Node start, Node target) {
		if (null == start) {
			return;
		}
		if (start.value <= target.value && target.value <= start.next.value) {
			target.previous = start;
			target.next = start.next;
			start.next.previous = target;
			start.next = target;
		} else {
			insert(start.next, target);
		}
	}

	
    public double trimMean(int[] arr) {
        int length;
        if(null==arr || 0 == (length=arr.length)){
            return 0.0d;
        }
        System.out.println("length:"+length);


        int del = (int)(length * 0.05f);
        if(0==del){
            del = 1;
        }
        System.out.println("del:"+del);

//        SubArray min = new SubArray(del, -1);
//        SubArray max = new SubArray(del);
//
//        int total = 0;
//        for(int i=0, a=0;i<length;i++){
//            a = arr[i];
//            min.insertAndSort(a, true);
//            max.insertAndSort(a, false);
//            total += a;
//        }
//        System.out.println("total:"+total);
//        return ((double)(total - min.total() - max.total())) / (length - 2 * del);
        
        int value = arr[0];
        Node start = new Node(value);
        Node end = start;
        
        int total = value;        
        for (int i = 1; i < length; i++) {
        	value = arr[i];
        	total += value;
        	Node temp = new Node(value);
        	if(value<= start.value) {	
        		start.previous = temp;
        		temp.next = start;
        		start = temp;
        	}else if(value>=end.value) {
        		end.next = temp;
        		temp.previous = end;
        		end = temp;        		
        	}else {
        		insert(start, temp);        		
        	}
//        	System.out.println("Node list:"+start);
        }
        System.out.println("total:" + total);
        
        int subTotal1 = 0;
        Node temp = start;
		for (int i = 0; i < del; i++) {
			subTotal1+=temp.value;
			temp = temp.next;
		}
		System.out.println("subTotal1:" + subTotal1);

		int subTotal2 = 0;
		temp = end;
		for (int i = 0; i < del; i++) {
			subTotal2+=temp.value;
			temp = temp.previous;
		}
		System.out.println("subTotal2:" + subTotal2);
		return ((double) (total - subTotal1 - subTotal2)) / (length - 2 * del);
        
        
//		int total = arr[0];
//		for (int i = 0; i < length - 1; i++) {
//			int a = arr[i];
//			int b = arr[i + 1];
//			total += b;
//			if (b < a) {
//				arr[i] = b;
//				arr[i + 1] = a;
//				for (int j = i; j > 0; j--) {
//					int c = arr[j];
//					int d = arr[j-1];
//					if (d <= c) {
//						break;
//					}
//					if (d > c) {
//						arr[j] = d;
//						arr[j-1] = c;
//					}
//				}
//			}
//		}
		
//		Arrays.sort(arr);
//		int total = 0;
//		for (int i = 0; i < length; i++) {
//			total+=arr[i];
//		}
		
//		System.out.println("total:" + total);
//		System.out.print("[");
//		for (int i = 0; i < length; i++) {
//			System.out.print(arr[i] + ",");
//		}
//		System.out.println("]");         
//         
//		int subTotal1 = 0;
//		for (int i = 0; i < del; i++) {
//			subTotal1 += arr[i];
//		}
//		System.out.println("subTotal1:" + subTotal1);
//
//		int subTotal2 = 0;
//		for (int i = length - del; i < length; i++) {
//			subTotal2 += arr[i];
//		}
//		System.out.println("subTotal2:" + subTotal2);
//		return ((double) (total - subTotal1 - subTotal2)) / (length - 2 * del);
    }
    
    public static void main(String[] args) {
    	Q1619 solution = new Q1619();
//    	int[] arr = {73316,30651,98043,27429,52655,4373,44185,56358,43481,72613,27510,35996,85416,66386,64202,21273,59298,2639,56361,34981,23376,15317,36951,53079,12414,24445,28352,858,57180,87765,40112,8997,74101,79683,41058,56115,46348,6977,65666,32160,74170,7024,30224,43927,37757,31037,55693,50102,60485,33939,78024,48917,62298,56391,12939,18952,47790,8447,34988,4991,65786,98347,88987,36876,76676,55177,15060,64100,68853,59421,10556,95099,4285,531,40213,98850,21073,37624,1073,1202,50790,44139,56782,8076,53547,68027,37710,91732,92488,51351,85215,6490,5820,43026,53281,8516,51858,63549,48860,40447,42330,41138,56495,6301,65081,89961,73565,73586,20206,25699,23243,30805,54905,21839,19937,5700,23381,18417,62538,88940,52844,27447,48947,4978,10877,79405,63496,82149,22348,20561,25072,30188,28541,8584,95482,10800,59351,92268,45706,28924,90874,65383,17383,6655,50694,94829,36104,30458,75653,57297,52164,77003,73095,19079,21235,60198,23429,70471,16212,92614};
    	int[] arr = {44061,60673,74187,45011,99504,98535,13463,36963,14112,74623,94229,81623,14380,84327,89047,56551,67840,91531,16349,53588,2011,77621,21917,26809,92891,43168,32560,8628,96766,79711,3083,63880,95622,68358,67105,89015,69397,26670,30763,29987,45362,22736,14672,26686,8242,92910,93077,63008,89823,26923,32398,41460,59576,27444,81066,91477,48712,66841,6362,53792,14701,93197,34566,29493,3454,68647,54899,23274,10213,95444,14011,7591,29028,29519,92970,4800,88198,98735,93100,37002,13587,71520,29240,44240,1696,44141,7586,93121,68363,39315,11473,5686,93946,95346,1655,16043,91353,5258,85723,17680,92436,64360,43071,93978,46174,11913,16533,24633,6212,43502,85987,71237,89007,32822,71274,52707,40828,63382,49281,13202,68061,9005,73567,21875,24846,16320,52076,22422,12472,96192,61622,68058,45490,14452,7626,10237,76678,35318,75211,23871,75871,75949,70346,18402,47129,11448,42193,44003,97865,75453,72688,78662,99974,77001,15125,52646,43926,64140,51409,68165,84096,40966,28041,50798,59975,79323,78306,71236,90044,83455,28668,15157,26656,14875,12971,40739,74168,85039,61617,60992,73004,37611,34961,26145,50959,30909,74469,79319,49771,9557,5373,99300,69034,94747,88060,7684,2754,46649,68821,84629,34537,7596,28882,5666,85628,75406,32650,60401,10437,9151,93886,36033,29753,88843,8095,83784,76804,37044,18363,84687,4602,42921,16454,81446,43226,42050,3452,63941,9217,73890,12094,74920,63372,94930,56785,860,3798,57424,91817,47896,24435,58482,74638,39476,42112,99927,65162,48049,74802,53929,71137,58405,36171,81277,49034,71689,98590,2215,31539,52054,21146,43083,49911,7942,40052,63086,78759,46609,22959,82079,99999,18484,79658,72554,31534,52548,91008,42926,99556,16055,48312,39566,38320,99250,25803,85341,72768,40644,23267,82642,95215,67570,7368,12032,41084,73695,96287,18599,65175,19596,49955,34245,5288,88843,98426,12155,36187,87203,76424,9530,25949,57411,54773,86405,86402,99396,64645,52534,1176,76076,48522,27253,96999,10536,13496,98670,87181,51638,69741,75079,57932,62363,2011,4282,23330,81957,17126,28323,12158,64972,98413,8153,72424,61887,46164,97727,76572,6745,32949,87044,70766,89887,98232,27974,1696,78322,66590,77095,98496,4036,41444,22356,41561,41119,31719,56131,97087,90148,34600,30126,81928,13435,65409,77413,22766,15057,75917,32140,86424,72347,58757,94115,66849,77476,65127,65579,49131,20121,18690,55198,67790,72227,11050,18542,785,41421,68416,65074,27064,82296,31428,2807,71016,31270,22493,5716,7668,74975,47387,84141,68068,94145,47140,52868,26783,83322,15088,22211,20144,17213,21733,3423,14295,81299,55761,18999,16334,39106,89025,9864,33327,16723,58120,7151,72882,54884,95058,79194,86581,11379};
//    	int[] arr = {6,0,7,0,7,5,7,8,3,4,0,7,8,1,6,8,1,1,2,4,8,1,9,5,4,3,8,5,10,8,6,6,1,0,6,10,8,2,3,4};
    	
    	long start = System.currentTimeMillis();
//    	System.out.println("44270.01389:" + solution.trimMean(arr));
    	System.out.println("50275.86111 : " + solution.trimMean(arr));
//    	System.out.println("4.77778 : " + solution.trimMean(arr));
    	
    	System.out.println("Time:" + (System.currentTimeMillis() - start));
		
	}

}
