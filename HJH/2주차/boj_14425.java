/******************************************************************************

문제출처 : https://www.acmicpc.net/problem/14425
풀이시간 : 10m
시간복잡도 : O(n) (n: 입력 길이) 
공간복잡도 : O(n)

*******************************************************************************/

import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException{
	    var br = new BufferedReader(new InputStreamReader(System.in));
	    
	    var tmp = br.readLine().split(" ");
	    int N = Integer.parseInt(tmp[0]);
	    int M = Integer.parseInt(tmp[1]);
	    
	    var S = new HashSet<String>();
	    while(N-- > 0){
	        S.add(br.readLine());
	    }
	    
	    int answer = 0;
	    while(M-- > 0){
	        if(S.contains(br.readLine())) answer++;
	    }
	    
	    System.out.print(answer);
	}
	
}
