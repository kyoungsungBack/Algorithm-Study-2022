import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * ACM Craft - 골드3
 * 위상정렬
 * 풀이시간 : 1h~
 * @author USER
 *
 */
public class Main {
	static int T, n, k, w; //테케, 정점, 간선, 타겟정점
	static int[] time; //각 정점의 건설시간
	static int[] indegree; //각 정점의 진입차수의 개수
	static int[] dp; //각 정점까지 걸리는 건설시간
	static ArrayList<ArrayList<Integer>> list; //간선표시
	static Queue<Node1005> q; //이전,현재 정점 넣어줄 큐
	static class Node1005{ //이전,현재 정점 나타내는 클래스
		int preV;//이전
		int curV;//현재
		public Node1005(int preV, int curV) {
			this.preV = preV;
			this.curV = curV;
		}
	}
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		/*--------------------------변수값 할당 및 초기화-------------------------------------*/
		T = Integer.parseInt(br.readLine());
		for(int i=0; i<T; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			k = Integer.parseInt(st.nextToken());
			dp = new int[n+1];
			
			st = new StringTokenizer(br.readLine());
			time = new int[n+1];
			for(int t=1; t<=n; t++) {
				time[t] = Integer.parseInt(st.nextToken());
				dp[t] = Integer.MIN_VALUE;
			}
			
			list = new ArrayList<ArrayList<Integer>>();
			for(int l=0; l<=n; l++)
				list.add(new ArrayList<Integer>());
			
			indegree = new int[n+1];
			for(int j=0; j<k; j++) {
				st = new StringTokenizer(br.readLine());
				int v1 = Integer.parseInt(st.nextToken());
				int v2 = Integer.parseInt(st.nextToken());
				
				list.get(v1).add(v2);
				indegree[v2]++;
			}
			w = Integer.parseInt(br.readLine()); //타겟 정점
			/*--------------------------------------------------------------------*/
			
			
			if(indegree[w]==0) {//타겟 정점이 시작 정점일때
				sb.append(time[w]).append("\n");
				continue;
			}
			
			topologicalSort();//위상정렬
			sb.append(dp[w]).append("\n");
		}
		
		System.out.println(sb.toString());//출력
		
	}
	
	//위상정렬
	public static void topologicalSort() {
		q = new LinkedList<Node1005>();
		/*
		 * 하나의 정점에대해 인접한 정점(진입차수가 0인것만)의 개수를 세고
		 * 그 개수만큼 끊어서 반복문을 돌리기 위해서(동시에 실행되는거 고려)
		 * addCnt변수 선언
		 */
		int addCnt = 0; 
		
		//시작정점들 탐색 -> 진입차수가 0인 것들
		for(int i=1; i<=n; i++) { 
			if(indegree[i]==0) {
				addCnt++;
				q.add(new Node1005(0, i));
			}
		}
		
		while(!q.isEmpty()) {
			//sectionSort를 통해 addCnt개수 만큼 끊어서 위상정렬 시작
			if(addCnt!=-1) addCnt = sectionSort(addCnt);
			else return;
		}
	}
	
	public static int sectionSort(int addCnt) {
		int cnt = 0; //큐에 드가는 정점 개수세는 변수(진입차수 0이되는 것들)
		
		//addCnt개수만큼 돌리기
		for(int i=0; i<addCnt; i++) {
			Node1005 v = q.poll();

			//현재 정점이 타겟과 같으면 dp배열 갱신 후 -1 반환.
			if(v.curV==w) {
				//현재 정점까지 걸리는 시간 = Math.max(이전 정점까지 걸리는시간+현재 정점의 건설시간 , 현재 정점까지 걸리는 시간)
				dp[v.curV] = Math.max(dp[v.preV]+time[v.curV], dp[v.curV]);
				return -1;
			}
			
			dp[v.curV] = Math.max(dp[v.preV]+time[v.curV], dp[v.curV]);
			
			//현재 정점에 인접한 노드를 탐색
			for(int j=0; j<list.get(v.curV).size(); j++) {
				int nextV = list.get(v.curV).get(j);
				indegree[nextV]--;//현재 정점을 탐색했기때문에 인접노드의 진입차수 하나 감소
				
				//인접노드가 진입차수가 0이될때 큐에 추가, cnt 증가
				if(indegree[nextV]==0) {
					q.add(new Node1005(v.curV, nextV));
					cnt++;
				}else {
					/*
					 * 인접한 정점의 진입차수가 0이 아니라는 말은 다른 정점들에서
					 * 인접한 정점으로 간선이 있다는 말이다.
					 * 따라서 인접한 정점으로 진입하는 모든 정점들의 건설시간을 고려해야한다(동시진행때문)
					 * 이를 고려하기 위해 진입차수가 0이 아닐땐 미리 인접한 정점이 건물짓는데 걸리는 시간을
					 * 넣어놔야한다.
					 * 
					 * 예를 들어 5,6번 정점이 7번 정점방향으로 간선이 있고,
					 * dp[5]=20 , dp[6]=10 , time[7]=5 일때
					 * dp[7]=25가 나와야한다.
					 * 이때, 만약 큐에 (5,6)의 순서대로 있다면
					 * 5번정점에서 7번정점으로 갔을때 걸리는 시간인 25와
					 * 6번정점에서 7번정점으로 갔을때 걸리는 시간인 15를 비교해야 하는데
					 * 아래 코드가 없다면 7번 정점은 6번정점이 탐색을 마친 후 큐에 추가가 된다.
					 * 그래서 dp[7]은 6번정점에서 7번정점으로 갔을때 걸리는 시간밖에 알수가 없다. 
					 * 즉, dp[7]이 15일때와 25일때를 비교해주기 위해 필요한 코드이다.
					 */
					dp[nextV] = Math.max(dp[v.curV]+time[nextV], dp[nextV]);
				}
			}
		}
		
		return cnt; //큐에추가된 노드 개수만큼 반환.
	}
}
