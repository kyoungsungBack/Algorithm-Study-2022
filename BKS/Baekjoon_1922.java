package Baekjoon_5주차;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
// 네트워크 연결_성공
// 무향 연결 그래프에서 간선의 가중치의 합이 최소인 신장트리
// 즉, 최소신장트리(Minimum Spanning Tree, MST)를 이용

// 다익스트라를 쓸 수 없는 이유는 모든 점을 지나야 하는데 
//  모든 점을 지나지도 않고 지나는 점들을 확인하지 못한다.
public class Baekjoon_1922 {
	static class info implements Comparable<info> {
		int start;
		int end;
		int distance;

		public info(int start, int end, int distance) {
			super();
			this.start = start;
			this.end = end;
			this.distance = distance;
		}

		@Override
		public int compareTo(info o) {
			return Integer.compare(distance, o.distance);
		}
	}

	static int N, M;
	static info[] List; // 간선리스트
	static int[] Parent;
	static int Result;
	static int EdgeCount;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());

		List = new info[M + 1];
		Parent = new int[N + 1];

		int a, b, c;
		for (int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			List[i] = new info(a, b, c);
		}

		Arrays.sort(List, 1, M + 1);

		for (int i = 1; i <= N; i++) {
			Parent[i] = i;
		}

		Result = 0;
		EdgeCount = 0;
		// 간선의 개수만큼 반복문 실행
		for (int i = 1; i <= M; i++) {
			//
			if (find(List[i].start) != find(List[i].end)) {
				union(List[i].start, List[i].end);
				Result += List[i].distance;
				EdgeCount++;
			} else {
				continue;
			}

			if (EdgeCount == N - 1) {
				break;
			}
		}
		System.out.println(Result);

	}

	static void union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		Parent[aRoot] = bRoot;
	}

	static int find(int i) {
		if (Parent[i] == i) { // Self loop 종료
			return i;
		} else {
			return Parent[i] = find(Parent[i]);
		}
	}
}
