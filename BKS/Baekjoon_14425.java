package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

//문자열집합
public class Baekjoon_14425 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		HashSet<String> set = new HashSet<String>();
		int N = Integer.parseInt(st.nextToken()); // 집합의 문자열 개수
		int M = Integer.parseInt(st.nextToken()); // 확인할 문자열 개수
		int count = 0;

		for (int i = 0; i < N; i++) {
			set.add(br.readLine());
		}

		for (int i = 0; i < M; i++) {
			String str = br.readLine();
			if(set.contains(str)) {
				count++;
			}
		}
		br.close();
		System.out.println(count);

	}
}
