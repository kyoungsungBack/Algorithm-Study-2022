import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

/**
 * 극장좌석 - 실1
 * TopDown
 * @author USER
 *
 */
public class Main {
	static Integer[] dp; //2번 -> dp배열 , dp[입장권 개수] = 앉을수 있는 경우의 수
	static HashMap<Integer, Integer> vip = new HashMap<Integer, Integer>();//vip 좌석
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());//총 좌석수
		int M = Integer.parseInt(br.readLine());//vip 좌석수 
		dp = new Integer[N+1];//0번~N번 까지 크기로
		
		for(int i=0; i<M; i++) {//vip좌석 넣기
			vip.put(Integer.parseInt(br.readLine()), 0);
		}
			
		//3번 -> 초기화
		dp[0]=0;
		dp[1]=1;
		//입장권이 2번까지 왔을때 초기화
		//vip좌석은 옮길수 없음. 따라서 1번 좌석이 vip라면
		//입장권이 2번까지 왔을때 경우의수는 1개임
		if(vip.containsKey(1)) dp[2]=1;
		else dp[2]=2;
		
		System.out.println(seat(N));//정답 출력
		for(int i:dp) {
			System.out.print(i+" ");
		}
	}
	
	//4번 -> N까지 앉을수 있는 좌석의 경우의수 계산
	public static int seat(int N) {
		if(dp[N]==null) {//null일때 계산
			if(vip.containsKey(N)) {//마지막 자리가 vip일때
				dp[N] = seat(N-1);
			}else if(!vip.containsKey(N-1) && !vip.containsKey(N-2)) {//vip좌석이 아닐때
				dp[N] = seat(N-1)+seat(N-2);
			}else if(vip.containsKey(N-1)) {//첫번째 전이 vip좌석일때
				dp[N] = seat(N-2);
			}else if(vip.containsKey(N-2)) {//두번째 전이 vip좌석일때
				dp[N] = seat(N-1)*2;
			}
		}
		
		return dp[N];
	}
}
