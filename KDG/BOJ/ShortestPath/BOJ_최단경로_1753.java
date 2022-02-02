import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 최단경로 - 골드5
 * 다익스트라 활용
 * 풀이시간 : 1h~
 * @author USER
 *
 */
public class Main {
	static int V, E, start;//정점, 간선, 시작정점
	static int[] dp;//dp[n번 노드] = 최소비용 -> 시작정점~n번노드까지 최소비용
	static ArrayList<ArrayList<Node1753>> list = new ArrayList<ArrayList<Node1753>>();//간선 list
	static class Node1753{//정점 번호와 비용을 알려주는 클래스
		int nodeIdx;
		int nodeCost;
		public Node1753(int nodeIdx, int nodeCost) {
			this.nodeIdx = nodeIdx;
			this.nodeCost = nodeCost;
		}
	}

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		V = Integer.parseInt(st.nextToken()); //정점 개수
		E = Integer.parseInt(st.nextToken()); //간선 개수
		start = Integer.parseInt(br.readLine()); //시작 정점
		dp = new int[V+1];
		
		//정점개수 만큼 list 방 만들기.(0은 안씀)
		for(int i=0; i<=V; i++) 
			list.add(new ArrayList<Node1753>());
		
		//dp배열 초기화
		for(int i=1; i<dp.length; i++) 
			dp[i] = Integer.MAX_VALUE; 
		
		//입력받는 간선과 비용 표시해주기
		for(int i=0; i<E; i++) {
			st = new StringTokenizer(br.readLine());
			int v1 = Integer.parseInt(st.nextToken());
			int v2 = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			//방향성이 있기때문에 반대는 안함
			list.get(v1).add(new Node1753(v2, c));
		}
		
		dijkStra();//다익스트라 실행
		System.out.println(printArr());//출력
		
	}
	
	//다익스트라 알고리즘으로 각 정점의 최소비용을 dp배열에 저장
	public static void dijkStra() {
		//cost기준으로 오름차순 정렬
		PriorityQueue<Node1753> pq = new PriorityQueue<Node1753>(new Comparator<Node1753>() {
			
			@Override
			public int compare(Node1753 o1, Node1753 o2) {
				int c1 = o1.nodeCost;
				int c2 = o2.nodeCost;
				
				if(c1<c2) return -1;
				else if(c1>c2) return 1;
				else return 0;
			}
		});
		
		dp[start] = 0; //시작정점에서 최소비용
		pq.add(new Node1753(start, 0)); //시작정점 정보 삽입(정점,비용)
		
		//시작정점 ~ 간선방향에 따라 연결된 모든 정점 순회
		while(!pq.isEmpty()) {
			Node1753 curN = pq.poll(); //현재 정점
			
			//현재정점의 최소비용보다 이전 정점들에서 현재정점으로 오는 비용이 더크다면 갱신할필요 없음
			//즉, 갱신할지 말지 for문을 통해 검사할 필요가 없음.
			//최소비용을 뽑는 문제임.
			if(dp[curN.nodeIdx] < curN.nodeCost) continue;  
			
			
			//현재 뽑은 노드와 연결된 인접노드를 탐색
			for(int i=0; i<list.get(curN.nodeIdx).size(); i++) {
				//인접노드
				Node1753 nextN = list.get(curN.nodeIdx).get(i);
				
				//인접노드까지의 거리가 dp에 저장된 최소비용보다 작으면 인접노드에 대한 최소비용을 갱신
				if(dp[nextN.nodeIdx] > curN.nodeCost + nextN.nodeCost) {
					//최소비용 갱신
					dp[nextN.nodeIdx] = curN.nodeCost + nextN.nodeCost;
					
					//인접노드의 번호와 그 노드까지의 최소비용 넣기
					pq.add(new Node1753(nextN.nodeIdx, dp[nextN.nodeIdx]));
				}
			}
		}
	}
	
	public static String printArr() {
		StringBuilder sb = new StringBuilder();
		for(int i=1; i<dp.length; i++) {
			if(dp[i]==Integer.MAX_VALUE) sb.append("INF").append("\n");
			else sb.append(dp[i]).append("\n");
		}
		return sb.toString();
	}
}

