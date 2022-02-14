package Baekjoon_6주차;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
// 파티(다익스트라)_성공
public class Baekjoon_1238 {
	static class info implements Comparable<info> {
		int node;
		int time;

		public info(int node, int time) {
			super();
			this.node = node;
			this.time = time;
		}

		@Override
		public int compareTo(info o) {
			return Integer.compare(time, o.time);
		}

	}

	static int N, M, X;
	static ArrayList<info>[] Map;
	static int[] GoDistance;
	static int[] BackDistance;
	static int[] Result;
	static final int INF = Integer.MAX_VALUE;

	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());

		Map = new ArrayList[N + 1];
		GoDistance = new int[N + 1];
		BackDistance = new int[N + 1];
		Result = new int[N + 1];

		for (int i = 1; i <= N; i++) {
			Map[i] = new ArrayList<>();
		}

		int from, to, time;
		for (int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine());
			from = Integer.parseInt(st.nextToken());
			to = Integer.parseInt(st.nextToken());
			time = Integer.parseInt(st.nextToken());
			Map[from].add(new info(to, time));
		}

		for (int i = 1; i <= N; i++) {
			Result[i] = GofindShortestPath(i, X); // i에서 X로 갈 때의 최단거리
		}

		BackfindShortestPath(X); // 파티정점에서 본인 정점으로 돌아갈 때의 최단시간

		int result = 0;
		for (int i = 1; i <= N; i++) {
			result = Integer.max(result, Result[i] + BackDistance[i]);
		}

		System.out.println(result);
	}

	static int GofindShortestPath(int start, int end) {
		Arrays.fill(GoDistance, INF);
		PriorityQueue<info> pq = new PriorityQueue<>();
		GoDistance[start] = 0;
		pq.add(new info(start, 0));

		while (pq.isEmpty() == false) {
			info now = pq.poll();

			if (now.time > GoDistance[now.node]) {
				continue;
			}

			for (info next : Map[now.node]) {
				if (GoDistance[next.node] > GoDistance[now.node] + next.time) {
					GoDistance[next.node] = GoDistance[now.node] + next.time;
					pq.add(new info(next.node, GoDistance[next.node]));
				}
			}
		}
		return GoDistance[end];
	}

	static void BackfindShortestPath(int start) {
		Arrays.fill(BackDistance, INF);
		PriorityQueue<info> pq = new PriorityQueue<>();
		BackDistance[start] = 0;
		pq.add(new info(start, 0));

		while (pq.isEmpty() == false) {
			info now = pq.poll();

			if (now.time > BackDistance[now.node]) {
				continue;
			}

			for (info next : Map[now.node]) {
				if (BackDistance[next.node] > BackDistance[now.node] + next.time) {
					BackDistance[next.node] = BackDistance[now.node] + next.time;
					pq.add(new info(next.node, BackDistance[next.node]));
				}
			}

		}
	}
}
