import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 평범한 배낭 - 골드5
 * 풀이시간:1h
 * TopDown
 * @author USER
 *
 */
public class Main {
	static int [][] dp;// dp[물건번호][가방이 감당가능한 최대 무게]
	static int [][] arr;// arr[물건번호][무게or가치]
	public static void main(String[] args) throws IOException{
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int maxW = Integer.parseInt(st.nextToken());
		
		arr = new int[N+1][2];
		dp = new int[N+1][maxW+1];
		
		for(int i=1; i<=N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<2; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		/* 
		 *  if( 현재 물건 무게(arr[i][1]) <= 감당 가능 무게(W) )
		 *  dp[i][w] = Math.max( dp[i-1][W] , arr[i][0] + dp[i-1][W-arr[i][1] )
		 *
		 *	else if( 현재 물건 무게(arr[i][1]) > 감당 가능 무게(W) )
		 *	dp[i][w] = dp[i-1][W]
		 * 
		 */
		
		System.out.println(knapsack(N,maxW));
	}
	
	//TopDown
	public static int knapsack(int i, int w) {
		if(dp[i][w]==0 && w!=0 && i!=0) {//0행0열이 아닐때 dp값 계산 안됐으면 계산하기
			if(arr[i][0] <= w) {//현재물건무게 <= 감당 가능 무게
				dp[i][w] = Math.max( knapsack(i-1, w), arr[i][1] + knapsack(i-1, w-arr[i][0]) );
			}else {//현재물건무게 > 감당 가능 무게
				dp[i][w] = knapsack(i-1, w);
			}
		}
		return dp[i][w];
	}
}
