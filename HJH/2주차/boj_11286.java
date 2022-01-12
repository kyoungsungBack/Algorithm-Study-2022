/******************************************************************************

문제출처 : https://www.acmicpc.net/problem/11286
풀이시간 : 10m
시간복잡도 : O(n) (n: 입력 길이) 
공간복잡도 : O(n)

*******************************************************************************/

import java.io.*;
import java.util.*;

public class Main {
    private static class Element implements Comparable<Element>{
        int origin;
        int abs;
        
        Element(int origin, int abs){
            this.origin = origin;
            this.abs = abs;
        }
        
        public int compareTo(Element e){
            if(this.abs == e.abs){
                return this.origin - e.origin;
            }
            return this.abs - e.abs;
        }
    }

	public static void main(String[] args) throws IOException{
	    var br = new BufferedReader(new InputStreamReader(System.in));
	    
	    int size = Integer.parseInt(br.readLine());
	    
	    var pq = new PriorityQueue<Element>();
	    
	    var sb = new StringBuilder();
	    while(size-- > 0){
	        int num = Integer.parseInt(br.readLine());
	        
	        if(num == 0){
	            sb.append(pq.isEmpty() ? 0 : pq.poll().origin).append('\n');
	            continue;
	        }
	        
	        pq.add(new Element(num, Math.abs(num)));
	    }
	    
	    System.out.print(sb.toString());
	}
	
}
