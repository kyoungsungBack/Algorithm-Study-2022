package DFS.BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 연구소 - 골드5
 * BFS
 * 풀이시간 :1h
 * @author USER
 *
 */
public class BOJ_14502 {
	static int answer = Integer.MIN_VALUE;//정답
	static int n, m;//세로,가로
	static int[] x = {-1, 1, 0, 0};//상하
	static int[] y = {0, 0, -1, 1};//좌우
	static class Node14502 {//인덱스 클래스
		int x;//세로 idx
		int y;//가로 idx
		public Node14502(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		int[][] map = new int[n][m]; //입력받을 배열
		
		//초기상태 배열 입력
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		//백트래킹으로 벽을 세울수 있는 모든 경우의수를 탐색.
		backTracking(0,map);
		
		System.out.println(answer);
	}
	
	//depth:벽의 개수,    map:벽을 세웠을때의 맵 
	public static void backTracking(int depth, int[][] map) {
		//종료조건, 벽3개 다세웠을때 바이러스 퍼뜨려 보기
		if(depth==3) {
			//bfs에서 사용할 map을 복사
			int[][] subMap = new int[n][m];
			for(int i=0; i<n; i++) {
				for(int j=0; j<m; j++) subMap[i][j] = map[i][j];
			}
		
			BFS(subMap);//바이러스 퍼뜨려 보기
			return;
		}
		
		//각 요소값을 모두 탐색하면서 0인자리를 1로 갱신(벽을 세움)
		for(int i=0; i<n; i++) {
			for(int j=0; j<m; j++) {
				//빈공간 아니면 건너뜀
				if(map[i][j]!=0) continue;
				
				map[i][j] = 1;//벽세우기
				backTracking(depth+1, map);//다음 벽세우러 ㄱㄱ
				map[i][j] = 0;//되돌리기

			}
		}
	}
	
	//subMap:벽을 3개 세우고난뒤 map
	public static void BFS(int[][] subMap) {
		Queue<Node14502> q  = new LinkedList<Node14502>();
		int safeCnt = 0; //안전공간 개수
		int virusCnt = 0; //바이러스있는 공간 개수
		
		//바이러스의 초기시작 인덱스 삽입
		for(int i=0; i<n; i++) {
			for(int j=0; j<m; j++) {
				if(subMap[i][j]==2) q.add(new Node14502(i, j));
				else if(subMap[i][j]==0) safeCnt++;
			}
		}
		
		//q빌때까지 바이러스 퍼뜨리기
		while(!q.isEmpty()) {
			Node14502 curN = q.poll();
			int curX = curN.x;//현재인덱스(세로)
			int curY = curN.y;//현제인덱스(가로)
			
			//현재 인덱스로 부터 상하좌우 탐색
			for(int i=0; i<4; i++) {
				int nextX = curX + x[i];//다음인덱스(세로)
				int nextY = curY + y[i];//다음인덱스(가로)
				
				if(nextX>=0 && nextY>=0 && nextX<n && nextY<m) {//배열 내의 범위면서
					if(subMap[nextX][nextY]==0) {//빈공간일때 바이러스 퍼뜨리기
						subMap[nextX][nextY] = 2;
						virusCnt++;//퍼진 바이러스 개수 증가
						q.add(new Node14502(nextX, nextY));
					}
				}
			}
		}
		
		//벽 3개 세운 경우마다 "초기안전공간 - 바이러스퍼진공간" 으로
		//안전공간이 최대는 경우를 출력하도록 answer 갱신
		answer = Math.max(answer, safeCnt-virusCnt);
	}
}
