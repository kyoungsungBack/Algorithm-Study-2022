import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

/**
 * 수들의 합 4 - 골드5
 * 누적합
 * 풀이시간 : 1h
 * @author USER
 *
 */
public class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		long answer = 0;//답
		int n = Integer.parseInt(st.nextToken());//개수
		int k = Integer.parseInt(st.nextToken());//타겟
		int[] arr = new int[n+1];//각각의 숫자
		int[] sum = new int[n+1];//누적합
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();//key:구간합, value:개수
		
		st = new StringTokenizer(br.readLine());
		for(int i=1; i<=n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			sum[i] = arr[i]+sum[i-1];
		}
		
		map.put(0, 1);
		for(int i=1; i<=n; i++) {
			answer += map.getOrDefault(sum[i] - k, 0);
			map.put(sum[i], map.getOrDefault(sum[i], 0)+1);
		}
		
		System.out.println(answer);
		
	}
}
