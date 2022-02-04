package Baekjoon_5주차;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;
// 찬구비(union-find)_성공
public class Baekjoon_16562 {
	static int N, M, K;
	static int[] Friends;
	static int[] Cost;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		Cost = new int[N + 1];
		Friends = new int[N + 1];

		for (int i = 1; i <= N; i++) {
			Cost[i] = Integer.parseInt(st.nextToken());
			Friends[i] = i;
		}

		int a, b;
		for (int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			union(a, b);
		}

		int minCost = 0;
		
		for (int i = 1; i <= N; i++) {
			//Self loop 인 점
			if(Friends[i] == i) {
				minCost += Cost[i];
			}
		}
		
		if(minCost <= K) {
			bw.append(String.valueOf(minCost));
		}else {
			bw.append("Oh no");
		}
		
		bw.flush();
		bw.close();
	}

	static void union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		
		// 비용을 기준으로 적은 비용을 부모노드로 설정
		if(Cost[aRoot] < Cost[bRoot]) {
			Cost[bRoot] = Cost[aRoot];
			Friends[bRoot] = aRoot;
		}else {
			Cost[aRoot] = Cost[bRoot];
			Friends[aRoot] = bRoot;
		}
		
	}

	static int find(int i) {
		if (Friends[i] == i) {
			return i;
		} else {
			return Friends[i] = find(Friends[i]);
		}
	}
}
