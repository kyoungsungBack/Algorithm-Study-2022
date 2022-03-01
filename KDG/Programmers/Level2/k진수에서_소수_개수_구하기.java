public class Solution {
	static int answer = 0;
	
	public static int solution(int n, int k) {
		String num = "";//진법 바꾸고나서 숫자
		
		if(k!=10) num = transNum(n,k);//10진법이 아닐때 k진수로 바꿔주고
		else num = String.valueOf(n);//10진접일땐 그대로 num에 넣어주기
		
		//숫자 잘라서 소수 판단하기
		isPrime(num);

		return answer;
	}
	
	//숫자 변환
	public static String transNum(int n, int k) {
		StringBuilder sb = new StringBuilder();//바뀔수
		int mock = n; //몫
		int mod = 0; //나머지
		
		//몫이 0이 될때까지 반복
		while(mock!=0) {
			mod = mock % k;
			mock = mock / k;
			
			//나머지를 가장앞에 넣기
			sb.insert(0, mod);
		}
		
		return sb.toString();
	}
	
	public static void isPrime(String num) {
		StringBuilder sb = new StringBuilder();
		boolean first = false; //첫번째 숫자 확인용도
		
		//zero가 true가 될때 이전에 현재 나온 0과 이전에 나왔던 0 사이의 숫자를
		//소수판단하기 위한 변수
		boolean zero = false; 
		
		for(int i=0; i<num.length(); i++) {
			if(num.charAt(i)!='0') sb.append(num.charAt(i)); //0이 아닐때 추가 
			
			//첫번째 0일경우 즉, 처음으로 만들어지는 숫자를 의미(오른쪽에 0하나)
			if(num.charAt(i)=='0' && !first) {
				answer += prime(sb.toString());//소수 판단
				first = true;
				sb.setLength(0);//초기화
			}
			//첫번째와 마지막으로 만들어지는 숫자를 제외하고 중간에 나오는 숫자(양옆에 0하나씩)
			else if(num.charAt(i)=='0' && first) {
				zero = true;
				if(zero && sb.length()>0) { //숫자가 만들어졌을때
					answer += prime(sb.toString());//소수 판단
					sb.setLength(0);//초기화
					zero = false;	
				}else if(zero && sb.length()==0) { //숫자가 안만들어졌을때
					zero = false;
				}
			}
			
		}
		
		//마지막 숫자 만들어졌을때(왼쪽에 0하나)
		if(sb.length()>0) answer += prime(sb.toString());
	}
	
	public static int prime(String n) {
		double num = Double.parseDouble(n);
		
		if(num <= 1) return 0;//1은 소수가 아님
		if(num <= 3) return 1;//2,3은 소수임
		if(num % 2 == 0) return 0;//2로 나눠지는 짝수는 소수가 아님
		
		//홀수만 선택하여 루트num까지 탐색
		for(int i=3; i<=Math.sqrt(num); i+=2) {
			if(num%i == 0) return 0;//나눠떨어지면 소수가 아님(1과 본인제외 다른 약수가 있다는 뜻)
		}
		
		//그외에는 소수임
		return 1;
	}
}
