/******************************************************************************

문제출처 : https://www.acmicpc.net/problem/7662
풀이시간 : 10m
시간복잡도 : O(n) (n: 입력 길이) 
공간복잡도 : O(n)

*******************************************************************************/

import java.io.*;
import java.util.*;

public class Main {
    private static final String EMPTY = "EMPTY";
    private static final String I = "I";
    private static final String D = "D";

	public static void main(String[] args) throws Exception{
	    var br = new BufferedReader(new InputStreamReader(System.in));
	    
	    int tcNum = Integer.parseInt(br.readLine());
	    
	    while(tcNum-- > 0){
	        int commandNum = Integer.parseInt(br.readLine());
	        
	        solve(br, commandNum);
	    }

	}
	
	private static void solve(BufferedReader br, int commandNum) throws Exception{
	    var minHeap = new PriorityQueue<Integer>();
	    var maxHeap = new PriorityQueue<Integer>(Collections.reverseOrder());
	    
	    Set<Integer> activeNums = new HashSet<>();
	    
	    while(commandNum-- > 0){
	        String command = br.readLine();
	        String[] splitedCommand = command.split(" ");
	        
	        int num = Integer.parseInt(splitedCommand[1]);
	        if(splitedCommand[0].equals(I)){
	            minHeap.add(num);
	            maxHeap.add(num);
	            activeNums.add(num);
	            
	            continue;
	        }
	        
	        if(minHeap.isEmpty()) continue;
	            
	       if(num == 1){
	           int max = maxHeap.poll();
	           activeNums.remove(max);
	           while(!maxHeap.isEmpty() && activeNums.contains(max)){
	               activeNums.remove(max);
	               max = maxHeap.poll();
	           }
	       }else{
	           int min = minHeap.poll();
	           activeNums.remove(min);
	           while(!minHeap.isEmpty() && activeNums.contains(min)){
	               activeNums.remove(min);
	               min = minHeap.poll();
	           }
	       }
	    }

	    
	    
	    if(minHeap.isEmpty()) {
	        System.out.println(EMPTY);
	        return;
	    }
	    
	    System.out.println(maxHeap.poll() + " " + minHeap.poll());
	}
	
}
