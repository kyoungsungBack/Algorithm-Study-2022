package Baekjoon_8주차;

import java.util.ArrayList;

public class Pro_빛의경로사이클 {
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };
	static int R, C;
	static boolean[][][] check;

	public static void main(String[] args) {
		ArrayList<Integer> answer = new ArrayList<>();
		String[] grid = { "SL", "LR" };
		R = grid.length; // 격자에 대한 행의 길이
		C = grid[0].length(); // 격자에 대한 열의 길이

		check = new boolean[R][C][4]; // 각 정점에 대한 4가지 방향의 방문배열

		// 모든 격자에 대한 4가지 방향으로 빛을 받는 경우의 수만큼 확인
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				for (int d = 0; d < 4; d++) {
					if (!check[i][j][d]) {
						answer.add(light(grid, i, j, d));
						System.out.println(i + " " + j + " " + d);
					}
				}
			}
		}
		answer.stream().sorted().mapToInt(i -> i).toArray();
	}

	static int light(String[] grid, int r, int c, int d) {
		int cnt = 0;

		// 한 싸이클을 돌때까지 반복
		while (true) {
			// 들어온 점으로 다시 돌아왔을 때 즉, 한 싸이클을 돌았을 때
			if (check[r][c][d]) {
				break;
			}

			// 격자에 의해 빛이 통과하면 cnt 1증가 하고 방문 표시
			cnt++;
			check[r][c][d] = true;
			// 상하좌우로 들어온 빛에 대해 격자의 이동방향을 갱신해준다
			if (grid[r].charAt(c) == 'L') {
				// 현재 격자가 L이면 들어온 빛의 방향을 기준으로 왼쪽으로 이동
				// 빛이 들어오는 방향에 대해 d를 갱신
				d = d == 0 ? 3 : d - 1;
			} else if (grid[r].charAt(c) == 'R') {
				// 현재 격자가 R이면 들어온 빛의 방향을 기준으로 오른쪽으로 이동
				// 빛이 들어오는 방향에 대해 d를 갱신
				d = d == 3 ? 0 : d + 1;
			}
			// 다음 격자로 이동
			r = (r + dr[d] + R) % R; // 빛을 쏘고나서 도착한 행
			c = (c + dc[d] + C) % C; // 빛을 쏘고나서 도착한 열
		}
		return cnt;
	}
}
