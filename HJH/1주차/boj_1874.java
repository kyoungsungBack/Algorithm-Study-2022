/******************************************************************************

문제출처 : https://www.acmicpc.net/problem/1874
풀이시간 : 20분
시간복잡도 : O(n) (n: 입력 길이) 입력길이 만큼 읽고 stack에 연산하기 때문
공간복잡도 : O(n)

*******************************************************************************/

import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException{
        var br = new BufferedReader(new InputStreamReader(System.in));
	    
	    int n = Integer.parseInt(br.readLine());
	    
	    Stack<Integer> stack = new Stack<>();
	    var sb = new StringBuilder();
	    
	    stack.push(1);
	    sb.append("+").append('\n');
	    int cur = 2;
	    
	    while(n-- > 0){
	        int readNum = Integer.parseInt(br.readLine());
	        
	        if(!stack.isEmpty() && readNum < stack.peek()){
	            System.out.print("NO");
	            br.close();
	            return;
	        }
	        
	        while(cur <= readNum){
	            stack.push(cur++);
	            sb.append("+").append('\n');
	        }
	        
	        if(!stack.isEmpty() && stack.peek() == readNum){
	            stack.pop();
	            sb.append("-").append('\n');
	        }
	    }
	   
	    br.close();
	    
	    System.out.print(sb.toString());
	}
	

}
