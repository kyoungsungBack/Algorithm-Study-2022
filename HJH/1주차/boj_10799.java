/******************************************************************************

문제출처 : https://www.acmicpc.net/problem/10799
풀이시간 : 20분
시간복잡도 : O(n) (n: 입력 길이)
공간복잡도 : O(n)

*******************************************************************************/

import java.io.*;
import java.util.*;

public class Main {
    private static String input;

	public static void main(String[] args) throws IOException{
		init();
		
		Stack<Character> stack = new Stack<>();
		stack.push(input.charAt(0));
		
		int answer = 0;
		for(int i = 1; i < input.length(); i++){ // index 1부터
		    char prevCh = input.charAt(i - 1);
		    char curCh = input.charAt(i);
		    
		    if(curCh == '('){
		        stack.push(curCh);
		        continue;
		    }
		    
		    // 아래는 현재 캐릭터가 ')' 인 경우임
		    if(isLaser(prevCh, curCh)){
		        stack.pop();
		        answer += stack.size();
		        continue;
		    }
		    
		    // 아래는 prevCh = ')', curCh = ')' 인 경우임
		    stack.pop();
		    answer += 1;
		}
		
		System.out.println(answer);
	}
	
	private static boolean isLaser(char ch1, char ch2){
	    return ch1 == '(' && ch2 == ')';
	}
	
	private static void init() throws IOException{
	    var br = new BufferedReader(new InputStreamReader(System.in));
	    
	    input = br.readLine();
	   
	    br.close();
	}
}
