package Baekjoon_6주차;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.StringTokenizer;
// ACM Craft(위상정렬)_성공
public class Baekjoon_1005 {
	static int T;
	static int N, K;
	static ArrayList<Integer>[] Map;
	static int[] InDegree;
	static int[] Count;
	static int[] BuildCount;

	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		T = Integer.parseInt(br.readLine());

		for (int i = 0; i < T; i++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());

			InDegree = new int[N + 1];
			Count = new int[N + 1];
			BuildCount = new int[N + 1];
			Map = new ArrayList[N + 1];

			st = new StringTokenizer(br.readLine());
			// 정점을 기준으로 하는 배열
			for (int j = 1; j <= N; j++) {
				Count[j] = Integer.parseInt(st.nextToken());
				BuildCount[j] = Count[j];
				Map[j] = new ArrayList<>();
			}

			int start, end;
			for (int j = 1; j <= K; j++) {
				st = new StringTokenizer(br.readLine());
				start = Integer.parseInt(st.nextToken());
				end = Integer.parseInt(st.nextToken());
				Map[start].add(end);
				InDegree[end]++;
			}

			int W = Integer.parseInt(br.readLine());

			// 진입차수가 0인 점을 que에 넣는다. 즉, 시작점
			ArrayDeque<Integer> que = new ArrayDeque<>();

			for (int j = 1; j <= N; j++) {
				if (InDegree[j] == 0) {
					que.add(j);
				}
			}

			// 같은 집입차수의 점들은 초가 동시에 줄어든다.
			while (que.isEmpty() == false) {
				int now = que.poll();
				
				if(now == W) break;
				
				// 후행자에 대해서 연결된 선행자들의 비교하여 최종 건설시간을 갱신
				for (int next : Map[now]) {
					if (BuildCount[next] < BuildCount[now] + Count[next]) {
						BuildCount[next] = BuildCount[now] + Count[next];
					}
					if (InDegree[next] > 0) {
						InDegree[next]--;
						if (InDegree[next] == 0) { // 건설이 완료되는 시점
							que.add(next);
						}
					}
				}
			}
			bw.append(String.valueOf(BuildCount[W])).append("\n");
		}

		bw.flush();
		bw.close();
	}
}
