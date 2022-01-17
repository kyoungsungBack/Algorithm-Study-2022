/******************************************************************************

문제출처 : https://www.acmicpc.net/problem/11279
풀이시간 : 10m
시간복잡도 : O(n^n) (n: 입력 길이) 
공간복잡도 : O(n)

*******************************************************************************/

import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException{
	    var br = new BufferedReader(new InputStreamReader(System.in));
	    
	    int size = Integer.parseInt(br.readLine());
	    
	    var pq = new PriorityQueue<Integer>(Collections.reverseOrder());
	    
	    var sb = new StringBuilder();
	    while(size-- > 0){
	        int num = Integer.parseInt(br.readLine());
	        
	        if(num == 0){
	            sb.append(pq.isEmpty() ? 0 : pq.poll()).append('\n');
	            continue;
	        }
	        
	        pq.add(num);
	    }
	    
	    System.out.print(sb.toString());
	}
	
}
