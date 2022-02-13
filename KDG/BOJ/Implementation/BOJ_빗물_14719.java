import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 빗물 - 골드5
 * 풀이시간 : 50m
 * @author USER
 *
 */
public class Main {
	static int[][] arr; //블록배열
	static int answer = 0; //빗물총량
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int h = Integer.parseInt(st.nextToken());//블록 최고 높이
		int w = Integer.parseInt(st.nextToken());//블록 최고 넓이
		arr = new int[h][w];
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<w; i++) {
			//해당하는 열(i)에 블록을 얼마나 쌓을지 알려주는 변수
			int tempH = Integer.parseInt(st.nextToken());
			createArr(tempH,i); //createArr로 블록 배열 만들기
		}
		
		//행을 하나씩 살피면서 가로로 1과 1사이에 있는 빗물 카운트
		for(int i=0; i<h; i++) 
			answer += rain(i,w);
		System.out.println(answer);//출력
	}
	
	//w번째 열에서 h만큼 arr배열에 밑에서 부터 1로 표기해주기.
	public static void createArr(int h, int w) {
		for(int i=arr.length-1; i>=arr.length-h; i--) 
			arr[i][w] = 1;
	}
	
	//가로 한줄에 빗물이 얼마나 있는지 카운트
	public static int rain(int h, int w) {
		int start = -1;//시작 블록
		int end = -1;//끝 블록
		int cnt = 0;
		
		//h(높이)번째 가로줄에 해당하는 0번열 ~ 마지막열까지 빗물 총량 구하기.
		for(int i=0; i<w; i++) {
			int checkWall = arr[h][i];
			
			//start 블록이 초기화 안됐으면 초기화
			if(checkWall==1 && start==-1) {
				start = i;
				continue;
			}
			
			//end 블록이 초기화 안됐으면 초기화
			if(checkWall==1 && start!=-1 && end==-1) {
				end = i;
				cnt += end-start-1;//첫번째 빗물 총량 카운트
				continue;
			}
			
			//start와 end블록 모두 값이 있는상태에서
			//또 다시 블록이 나온다면 start와 end를 갱신시켜줌.
			if(checkWall==1) {
				start = end;
				end = i;
				cnt += end-start-1;//첫번째 이후 빗물 총량 계속 카운트
			}
		}
		return cnt;
	}
}
