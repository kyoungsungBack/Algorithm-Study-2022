import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * A->B - 실1
 * @author USER
 *
 */
public class BOJ_16953 {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		long ori = Long.parseLong(st.nextToken());//초기값
		long target = Long.parseLong(st.nextToken());//최종값
			
		System.out.println(changeNum(ori, target));
	}
	
	public static int changeNum(long ori, long target) {
		int cnt=1;//반환값, 반환값은 최소횟수+1 이므로 초기값 1로 설정. 
		
		while(true) {
			if(ori==target) break;//최종값이 초기값과 같아지면 중단.
			else if(ori>target) {//최종값이 초기값으로 될수 없다면 cnt 갱신 후 중단.
				cnt = -1;
				break;
			}
			
			if(target%2==0) {//2로 나눠진다면 2로 나누기
				target = target/2;
				cnt++;
			}else if(target%10==1) {//끝자리가 1이라면 1제외 시키기
				target = target/10;
				cnt++;
			}else {//끝자리가 홀수인데 1이 아니라면 cnt 갱신 후 중단.
				cnt=-1;
				break;
			}
		}
		
		return cnt;
	}
}
