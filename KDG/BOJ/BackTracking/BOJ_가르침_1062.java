package BackTracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 가르침 - 골드4
 * 백트래킹
 * 풀이시간 : 1h~
 * 시간복잡도 : 
 * @author USER
 *
 */
public class BOJ_1062_2 {
	static int n, k, k1;
	static int answer = Integer.MIN_VALUE; //정답(최댓값)
	static boolean[] check; //방문배열
	static String[] arr;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken()); //단어 개수
		k = Integer.parseInt(st.nextToken()); //가르침 가능한 글자 수
		k1 = k-5; //a n t i c 를 제외하고 가르칠수 있는 글자 수
		arr = new String[n]; //"anta"와 "tica"를 제외하고 입력받은 단어들.
		
		//arr배열에 입력단어 넣기
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			String str = st.nextToken();
			str = str.substring(4, str.length()-4);
			arr[i] = str;
		}
		
		if(k1<0) {
			System.out.println(0);
			System.exit(0);
		}else if(k1>=21) {
			System.out.println(n);
			System.exit(0);
		}
		
		initCheck(); //방문배열 초기값 설정, a n t i c 방문처리.
		backTracking(1,0); //배울수 있는 알파벳들 조합하기
		System.out.println(answer);
		
	}
	
	//매개변수-> idx:탐색을 시작할 알파벳, depth:배운 알파벳 개수
	public static void backTracking(int idx, int depth) {
		//배울수 있을만큼 알파벳 배웠으면 읽을수 있는단어 몇개인지 세기
		//종료조건
		if(depth == k1) {
			countWords(); 
			return;
		}
		
		//'a'부터 시작해서 'z'까지 백트래킹으로 배울수 있는 알파벳 조합을 만듦.
		for(int i=idx; i<check.length; i++) {
			if(check[i]) continue;
			check[i] = true;
			backTracking(i, depth+1);
			check[i] = false;
		}
	}
	
	//읽을수 있는 단어 세기
	public static void countWords() {
		int cnt = 0;//읽을수 있는 단어 개수
		
		//단어 하나마다 읽을수 있는지 없는지 체크
		for(int i=0; i<arr.length; i++) {
			int w = 0;
			char[] word = arr[i].toCharArray();
			for(int j=0; j<word.length; j++) {
				if(!check[word[j]-96]) {//알파벳 안배워서 못읽음
					w = -1;
					break;
				}
			}
			
			if(w!=-1) cnt++;
		}
		
		//최댓값 갱신
		answer = Math.max(answer, cnt);
	}
	
	//a n t i c 방문 표시
	public static void initCheck() {
		//'a'= 1 , 'b'=2 ... 'z'=26
		check = new boolean[27];
		check['a'-96] = true;
		check['n'-96] = true;
		check['t'-96] = true;
		check['i'-96] = true;
		check['c'-96] = true;
	}
}
