package ShortestPath;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 플로이드 - 골드4
 * 플로이드
 * 풀이시간 : 30m
 * @author USER
 *
 */
public class BOJ_11404 {
	static int[][] dist; //dist[i][j] -> i번노드에서 j번노드까지 거리최소비용 배열
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine()); //노드 개수
		int m = Integer.parseInt(br.readLine()); //간선 개수
		dist = new int[n+1][n+1];
		
		//자기 자신 까지 거리는 0으로
		//그외는 ㅈㄴ 큰값으로 초기화 --> 최소비용으로 나중에 갱신해주기 위해.
		for(int i=1; i<=n; i++) {
			for(int j=1; j<=n; j++) {
				if(i==j) dist[i][j]=0;
				else dist[i][j]=Integer.MAX_VALUE;
			}
		}
		
		//간선 개수 만큼돌면서 v1노드에서 v2노드까지 비용 초기화
		//방향성 있음.
		for(int i=0; i<m; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int v1 = Integer.parseInt(st.nextToken());
			int v2 = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			
			//현재 바로 갈수 있는 노드들은 비용 최솟값으로 초기화.
			dist[v1][v2] = Math.min(dist[v1][v2], cost);
		}
		
		floyd(n,m); //플로이드ㄱㄱ 
		printAll(n);//정답 출력
	}
	
	/* 전체 노드개수 n개일때
	 * 1번노드에서 출발해 1~n번 까지 노드까지 최소비용 구하기 
	 * 2번노드에서 출발해 1~n번 까지 노드까지 최소비용 구하기 
	 * 3번노드에서 출발해 1~n번 까지 노드까지 최소비용 구하기 
	 * 					....
	 * n번노드에서 출발해 1~n번 까지 노드까지 최소비용 구하기 
	 */
	public static void floyd(int n, int m) {
		for(int k=1; k<=n; k++) {//k = 거처가는 노드
			for(int i=1; i<=n; i++) {//i = 출발노드
				for(int j=1; j<=n; j++) {//j = 도착노드
					int firstV = dist[i][k];//i에서 출발해서 k까지 최소비용
					int secondV = dist[k][j];//k에서 시작해서 j까지 최소비용
					if(firstV == Integer.MAX_VALUE && secondV > 0) continue;//오버플로 예방
					else if(secondV == Integer.MAX_VALUE && firstV > 0) continue;//오버플로 예방
					else dist[i][j] = Math.min(dist[i][j], firstV+secondV);//오버플로 안나면 최솟값 갱신
					
					//참고-> dist[i][j]:i노드에서 j노드로 바로 감.
					//   -> dist[i][k] + dist[k][j]: i노드에서 k노드 거친다음 j노드로 감.
				}
			}
		}
	}
	
	//출력
	public static void printAll(int n) {
		StringBuilder sb = new StringBuilder();
		for(int i=1; i<=n; i++) {
			for(int j=1; j<=n; j++) {
				if(dist[i][j]==Integer.MAX_VALUE) sb.append(0).append(" ");
				else sb.append(dist[i][j]).append(" ");
			}
			sb.append("\n");
		}
		
		System.out.println(sb.toString());
	}
}
