package Baekjoon_7주차;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
// 연구소_성공
// 문제가 비선형 자료구조이고 완전탐색을 해야하므로 DFS와 BFS를 이용하여 각 원소들을 탐색
// 1. 벽을 먼저 세운다. -> 벽에 무작위로 세운다.
// 2. 바이러스가 퍼진다. -> 상하좌우의 벽으로 퍼짐
// 3. 안전영역의 개수를 센다.
public class Baekjoon_14502 {
	// 좌, 우, 위, 아래
	static int[] MX = { -1, 1, 0, 0 };
	static int[] MY = { 0, 0, -1, 1 };
	static int[][] map; // 전체 맵
	static int[][] mapSpread; // 바이러스 맵
	static int N, M;
	static int ans;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N + 1][M + 1];
		mapSpread = new int[N + 1][M + 1];

		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		ans = 0;
		makeWall(0);
		
		System.out.println(ans);
	}

	static void makeWall(int cnt) {
		// DFS 사용
		// 1. 체크인
		// 6. 체크아웃
		if (cnt == 3) {
			bfs();
			countSafe();
			return;
		}
		// 2. 목적지인가?
		// 3. 연결된 곳을 순회
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= M; j++) {
				// 4. 갈 수 있는가?
				if (map[i][j] == 0) {
					map[i][j] = 1;
					// 5. 간다
					makeWall(cnt + 1); // 여기서 계속 걸쳐서 벽 3개가 됐을 때 바이러스 퍼지고 그때의 안전영역 개수 체크
					map[i][j] = 0;
				}
			}
		}
	}

	static void bfs() {
		Queue<Point> que = new LinkedList<>();

		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= M; j++) {
				mapSpread[i][j] = map[i][j];
			}
		}

		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= M; j++) {
				if (map[i][j] == 2) {
					que.add(new Point(i, j));
				}
			}
		}

		while (que.isEmpty() == false) {
			// 1. 큐에서 꺼내옴
			Point p = que.poll();
			// 2. 목적지인가
			// 3. 연결된 곳을 순회
			for (int k = 0; k < 4; k++) {
				int px = p.x + MX[k];
				int py = p.y + MY[k];
				if (py > 0 && py <= N && px > 0 && px <= M) {
					// 4. 갈 수 있는가?
					if (mapSpread[py][px] == 0) {
						// 5. 간다
						mapSpread[py][px] = 2;
						// 6. 큐에 넣음
						que.add(new Point(py, px));
					}
				}
			}
		}
	}

	static void countSafe() {
		int cnt = 0;
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= M; j++) {
				if (mapSpread[i][j] == 0) {
                    cnt++;
                }
			}
		}
		ans = Math.max(ans, cnt);
	}

}

class Point {
	int y;
	int x;

	public Point(int y, int x) {
		super();
		this.y = y;
		this.x = x;
	}

}
