package Baekjoon_5주차;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
// 최단경로(다익스트라)_성공
public class Baekjoon_1753 {
	static class info implements Comparable<info> {
		int node;
		int distance;

		public info(int node, int distance) {
			super();
			this.node = node;
			this.distance = distance;
		}

		@Override
		public int compareTo(info o) {
			return Integer.compare(distance, o.distance);
		}

	}

	static int V, E;
	static int[] Distance;
	static ArrayList<info>[] Map;
	static int Start;
	static final int INF = Integer.MAX_VALUE;

	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		Map = new ArrayList[V + 1];
		Distance = new int[V + 1];

		for (int i = 1; i <= V; i++) {
			Map[i] = new ArrayList<>();
			Distance[i] = INF;
		}

		Start = Integer.parseInt(br.readLine());

		int u, v, w;
		for (int i = 1; i <= E; i++) {
			st = new StringTokenizer(br.readLine());
			u = Integer.parseInt(st.nextToken());
			v = Integer.parseInt(st.nextToken());
			w = Integer.parseInt(st.nextToken());
			Map[u].add(new info(v, w));
		}

		findShortestPath(Start);

		for (int i = 1; i <= V; i++) {
			if (Distance[i] != INF) {
				bw.append(String.valueOf(Distance[i])).append("\n");
			} else {
				bw.append("INF").append("\n");
			}
		}
		
		bw.flush();
		bw.close();
	}

	private static void findShortestPath(int start) {
		// 우선순위 큐에 정점으로부터 아직 방문하지 않은 정점이 있으면 끝까지 가면서 거리 갱신
		PriorityQueue<info> pq = new PriorityQueue<>();
		Distance[start] = 0;
		pq.add(new info(start, 0)); // 시작점에 대한 정보 저장

		while (pq.isEmpty() != true) {
			info current = pq.poll(); // 이 시점부터 최단 경로를 알 수 있는 정점

			// 현재 정점의 가중치가 크다면 그때의 거리배열을 갱신할 필요가 없다.
			if (current.distance > Distance[current.node]) { 
				continue;
			}
			
			// 현재 시작점부터 연결된 정점들을 모두 탐색하여 최단거리를 갱신
			for (info next : Map[current.node]) {
				if (Distance[next.node] > Distance[current.node] + next.distance) {
					Distance[next.node] = Distance[current.node] + next.distance;
					// 현재 연결된 정점까지의 최단거리를 갱신하고 이후에 연결된 정점이 있을 
					//	경우 더 계산해줘야 하므로 pq에 다시 넣는다.
					pq.add(new info(next.node, Distance[next.node])); 
				}
			}

		}
	}
}
