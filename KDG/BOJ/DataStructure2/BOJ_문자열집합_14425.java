import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

/**
 * 문자열 집합 - 자료구조
 * 실3
 * @author USER
 *
 */
public class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		HashMap<String, Integer> map = new HashMap<String, Integer>();//N에 해당하는 문자열 넣을 map
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		String[] arr = new String[M];//M에 해당하는 문자열 넣을 배열
		
		for(int i=0; i<N; i++) {//입력
			map.put(br.readLine(), 0);
		}
		for(int i=0; i<M; i++) {//입력
			arr[i]=br.readLine();
		}
		
		int answer = checkStr(map,arr);//집합 S에 해당하는 문자 count
		System.out.println(answer);
	}
	
	//집합 S에 해당하는 문자 count
	public static int checkStr(HashMap<String, Integer> map, String[] arr) {
		int cnt = 0;
		
		//arr배열 즉, M에 해당하는 문자가 부분집합 S(map)에 있다면 cnt 증가.
		for(int i=0; i<arr.length; i++) {
			if(map.containsKey(arr[i])) cnt++;
		}
		
		return cnt;
	}
}
