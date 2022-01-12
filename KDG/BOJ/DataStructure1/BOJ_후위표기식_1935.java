import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

/**
 * 후위 표기식2
 * 실3
 * @author USER
 *
 */
public class Main {
	static Stack<Double> st = new Stack<Double>();//연산값 넣을 스택
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		Double[] arr = new Double[N];
		String str = br.readLine();
		
		for(int i=0; i<N; i++) {
			arr[i] = Double.parseDouble(br.readLine());
		}
		
		postfix(arr,str);//후위표기 연산
		System.out.println(String.format("%.2f", st.pop()));//출력
	}
	
	//후위 표기 연산
	public static void postfix(Double[] arr, String str) {
		int idx = 0;//arr 인덱스
		
		for(int i=0; i<str.length(); i++) {
			char temp = str.charAt(i);
			//피연산자일때와 연산자일때로 나눔
			if('A'<=temp && temp<='Z') {
				idx = temp - 'A';
				st.add(arr[idx]);
			}else {
				//연산자나오면 스택에 피연산자 2개 뽑고 계산 후 다시 스택에 삽입
				double valueB = st.pop();
				double valueA = st.pop();
				double result = cal(valueA, valueB, temp); 
				st.add(result);
			}
		}
	}
	
	//연산자 나왔을때 스택에 피연산자값 2개 뺀거 계산
	public static double cal(double A, double B, char temp) {
		double value = 0;
		switch (temp) {
		case '+':
			value = A+B;
			break;
		case '-':
			value = A-B;
			break;
		case '/':
			value = A/B;
			break;
		case '*':
			value = A*B;
			break;
		}
		return value;
	}
}
