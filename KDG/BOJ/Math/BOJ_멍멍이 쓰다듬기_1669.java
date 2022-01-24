import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 멍멍이 쓰다듬기 - 실1
 * @author USER
 *
 */
public class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int monkey = Integer.parseInt(st.nextToken());
		int dog = Integer.parseInt(st.nextToken());
		int goal = dog - monkey;//원숭이가 늘릴키
		
		//늘릴키가 0,1,2일때는 바로 출력후 main함수 종료
		if(goal==0) {
			System.out.println(0);
			System.exit(0);
		}else if(goal==1) {
			System.out.println(1);
			System.exit(0);
		}else if(goal==2) {
			System.out.println(2);
			System.exit(0);
		}
		
		int num = (int) findNum(goal);//N 구하기 (N=num)
		int answer = (num*2)-1;//키를 같게하기 위한 가장 최소 일수
		if((num*num)==goal) {//만약 goal이 제곱수라면 answer 출력
			System.out.println(answer);
			System.exit(0);
		}
		
		//goal이 제곱수가 아니면 "최소일수 + 남은 키차이 줄이는 날짜"를 구해야한다. 
		System.out.println(dayCnt(num, goal, answer));
	}
	
	//N구하기
	public static double findNum(int goal) {
		double num = 2;//키차이가 3일때까지는 main에서 처리했기때문에
		//키차이가 4일때부터 생각하고 N을 구함.
		
		while(true) {
			//goal이 제곱수보다 크면 num 계속 증가
			if(Math.pow(num, 2) < goal) {
				num++;
			}
			//제곱수가 goal보다 커지는 순간 num하나 감소시키고 반복문 중단.
			else if(Math.pow(num, 2) > goal) {
				num--;
				break;
			}
			//제곱수와 goal이 같아지는 경우 중단.
			else break;
		}
		return num;
	}
	
	//남은 날짜 계산
	public static int dayCnt(int num, int goal, int answer) {
		int diff = goal - (num*num);//남은 키차이
		while(diff!=0) {
			if(num < diff) diff -= num;//남은 키차이가 num보다 크면 num을 빼줌
			else diff -= diff;//남은 키차이가 num보다 작으면 diff빼서 0으로 만들어줌
			answer++;//한번 실행할때 마다 answer 증가
		}
		return answer;
	}
}
