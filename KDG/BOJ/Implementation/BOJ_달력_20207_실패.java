import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 달력 - 실버1
 * 풀이시간:
 * @author USER
 *
 */
public class Main {
	static int[][] arr = new int[367][367];
	static Queue<Integer> wall = new LinkedList<Integer>();
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int s = Integer.MAX_VALUE;
		int e = Integer.MIN_VALUE;
		int answer = 0;
		
		
		for(int i=0; i<n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int v1 = Integer.parseInt(st.nextToken());
			int v2 = Integer.parseInt(st.nextToken());
			s = Math.min(s, v1);
			e = Math.max(e, v2);
			location(v1,v2);
		}
		
		for(int i=s-1; i<=e+1; i++)  
			if(checkWall(i)) wall.add(i);
		
		while(wall.size()!=1) 
			answer += size();
		
		System.out.println(answer);
	}
	
	public static void location(int v1, int v2) {
		for(int i=1; i<arr.length; i++) {
			if(!check(i,v1,v2)) continue;
			for(int j=v1; j<=v2; j++) 
				arr[i][j]=1;
			return;
		}
	}
	
	public static boolean check(int i, int v1, int v2) {
		for(int j=v1; j<=v2; j++) 
			if(arr[i][j]!=0) return false;
		return true;
	}
	
	public static boolean checkWall(int i) {
		for(int j=1; j<arr.length; j++) 
			if(arr[j][i]!=0) return false;
		return true;
	}
	
	public static int size() {
		int left = wall.poll()+1;
		int right = wall.peek()-1;
		int height = 0;
		int result = 0;
		
		for(int i=1; i<arr.length; i++) {
			if(!check(i,left,right)) height++;  
			else break;
		}
		
		result = height*(right-left+1);
		
		return result;
	}
}
