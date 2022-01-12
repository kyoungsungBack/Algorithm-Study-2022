import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

/**
 * 스택 수열
 * 실3
 * @author USER
 *
 */
public class Main {
	static StringBuilder answer = new StringBuilder();
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[] arr = new int[n];
		
		for(int i=0; i<n; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		
		stackOper(arr,n);//스택 연산을 통해 +, - 를 answer에 삽입해줌.
		
		//answer의 마지막 2글자가 NO일때 NO출력후 함수 끝.
		if(answer.substring(answer.length()-2, answer.length()).equals("NO")) {
			System.out.println("NO");
			System.exit(0);
		}
		
		//answer출력
		System.out.println(answer.toString());
	}
	
	public static void stackOper(int[] arr, int n) {
		Stack<Integer> st = new Stack<Integer>();
		int stValue = 0; //스택에 넣을 값(오름차순)
		int idx = 0; //arr의 인덱스
		
		while(true) {
			//n까지 모든값이 돌았을때 종료조건
			if(stValue==n && st.isEmpty()) {
				break;
			}else if(st.isEmpty()) {//스택 비었을땐 바로 추가
				stValue++;
				st.add(stValue);
				answer.append("+").append("\n");
				continue;
			}
			
			int value = st.peek();//top값
			if(value!=arr[idx]) {//top과 현재 탐색중인 값이 다를때 값추가
				//이때 만약 스택에 넣을 값이 최대치라면
				//더이상 수열을 만들수 없다고 판단하고 함수 종료.
				if(stValue == n) {
					answer.append("NO");
					return;
				}
				stValue++;
				st.add(stValue);
				answer.append("+").append("\n");
			}else {//top과 현재 탐색중인 값이 같을때 값반환
				//탐색하는 값이 마지막값이 아니면 인덱스는 증가시켜줌
				if(idx != arr.length-1) idx++;
				st.pop();
				answer.append("-").append("\n");
			}
		}
	}
}
