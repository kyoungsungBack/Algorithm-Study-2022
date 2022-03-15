public class Solution {
	static int answer = 0;//정답
	
	public static int solution(int[] numbers, int target) {
		dfs(numbers, 0, target, numbers[0]);//첫숫자가 양수일때
		dfs(numbers, 0, target, -1*numbers[0]);//첫숫자가 음수일때
		System.out.println(answer);
		return answer;
	}
	
	//numbers -> 입력받은 배열
	//idx -> 입력받은 배열에서 몇번째 숫자 사용할껀지 알려주는 인덱스
	//target -> 입력받은 숫자
	//sum -> dfs함수 실행할때 마다 더하거나 뺐을때의 합
	public static void dfs(int[] numbers, int idx, int target, int sum) {
		if(idx == numbers.length-1) {//종료조건, 모든숫자를 다썼을경우
			if(sum == target) {//합이 target과 같을때 answer 증가
				answer++;
			}
			return;
		}
		
		dfs(numbers, idx+1, target, sum+numbers[idx+1]);//sum에다 다음 숫자를 더했을때
		dfs(numbers, idx+1, target, sum-numbers[idx+1]);//sum에다 다음 숫자를 뺐을때
	}
}
