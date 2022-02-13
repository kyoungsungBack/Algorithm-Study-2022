import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 파티 - 골드3
 * 다익스트라
 * 풀이시간: 30m
 * @author USER
 *
 */
public class Main {
	static int n, m, x; //정점 개수, 간선 개수, 시작(끝)정점
	static ArrayList<ArrayList<Node1238>> list = new ArrayList<ArrayList<Node1238>>();//정점과 간선가중치 리스트
	static class Node1238{//정점 하나와 간선의 가중치
		int v;
		int c;
		public Node1238(int v, int c) {
			this.v = v;
			this.c = c;
		}
	}
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int answer = Integer.MIN_VALUE;//정답 변수
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		x = Integer.parseInt(st.nextToken());
		
		for(int i=0; i<=n; i++)//1~n정점 추가
			list.add(new ArrayList<Node1238>());
		
		for(int i=0; i<m; i++) {
			st = new StringTokenizer(br.readLine());
			int v1 = Integer.parseInt(st.nextToken());
			int v2 = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			
			//v1 정점과 연결된 v2정점과 간선 가중치 추가(단방향)
			list.get(v1).add(new Node1238(v2, cost));
		}
		
		//1~n번 정점으로부터 x정점까지 왔다갔다 거리재기
		for(int i=1; i<=n; i++) {
			if(i==x) continue;//목표 정점일때는 어차피 0임(제외)
			answer = Math.max(answer, goParty(i)+goHome(i));//최댓값
		}
		
		System.out.println(answer);//출력
	}
	
	//집에서 파티장으로가는 최단경로, 매개변수: 시작정점
	public static int goParty(int start) {
		int[] dp = new int[n+1];//1~n번정점의 최단경로 저장해줄 배열 
		Arrays.fill(dp, Integer.MAX_VALUE);
		//가중치를 기준으로 오름차순 정렬
		PriorityQueue<Node1238> pq = new PriorityQueue<Node1238>(new Comparator<Node1238>() {
			
			@Override
			public int compare(Node1238 o1, Node1238 o2) {
				int c1 = o1.c;
				int c2 = o2.c;
				
				if(c1<c2) return -1;
				else if(c1>c2) return 1;
				else return 0;
			}
		});
		
		dp[start] = 0;//시작은 0으로 초기화
		pq.add(new Node1238(start, 0));//시작정점 추가

		while(!pq.isEmpty()) {
			Node1238 cur = pq.poll();//현재 정점 뽑기
			
			//현재정점의 최단경로 값보다 이전정점에서 현재정점으로 오는 최단경로 값이 더 크면 갱신 안함
			if(dp[cur.v] < cur.c) continue;
			if(cur.v==x) break; //현재 정점이 목표정점일때 중단
			
			//현재 정점으로 부터 인접노드를 탐색
			for(int i=0; i<list.get(cur.v).size(); i++) {
				Node1238 next = list.get(cur.v).get(i);
				
				//"인접노드의 최단경로 값 > 현재정점까지 값 + 인접노드 정점까지 가중치 값" 이면
				// 갱신할 필요가 최단경로 값을 더 작은값으로 갱신할 필요가 있음.
				if(dp[next.v] > cur.c + next.c) {
					dp[next.v] = cur.c + next.c;//인접노드의 최단경로 갱신
					pq.add(new Node1238(next.v, dp[next.v]));//갱신한 노드 추가
				}
			}
		}
		
		return dp[x];//목표정점일때 최단경로 값 반환
	}
	
	//goParty 메서드와 원리 같음.(파티장에서 집으로 돌아오는 최단경로)
	//매개변수 : 끝나는 정점
	public static int goHome(int endV) {
		int[] dp = new int[n+1];
		Arrays.fill(dp, Integer.MAX_VALUE);
		PriorityQueue<Node1238> pq = new PriorityQueue<Node1238>(new Comparator<Node1238>() {
			
			@Override
			public int compare(Node1238 o1, Node1238 o2) {
				int c1 = o1.c;
				int c2 = o2.c;
				
				if(c1<c2) return -1;
				else if(c1>c2) return 1;
				else return 0;
			}
		});
		
		dp[x] = 0;
		pq.add(new Node1238(x, 0));
		
		while(!pq.isEmpty()) {
			Node1238 cur = pq.poll();
			
			if(dp[cur.v] < cur.c) continue;
			if(cur.v==endV) break;
			
			for(int i=0; i<list.get(cur.v).size(); i++) {
				Node1238 next = list.get(cur.v).get(i);
				
				if(dp[next.v] > cur.c + next.c) {
					dp[next.v] = cur.c + next.c;
					pq.add(new Node1238(next.v, dp[next.v]));
				}
			}
		}
		return dp[endV];
	}
}
