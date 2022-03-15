package Baekjoon_8주차;
/**
 * 
 * @author mycom
 	시간복잡도
	두가지 연산을 이용한 이진트리 형태의 재귀호출이므로 O(2^n)이고 n은 주어진 배열의 길이로 볼 수 있다

	알고리즘설명
	dfs를 사용해서 sum에 대한 모든 경우의 수를 구한다.
	종료조건은	sum과 target이 같을 때 이고 이때 1을 return 한다.
	종료조건에서 return된 수를 count에 계속 더해주고 count를 반환한다.
	하나의 수에 대해 할 수 있는 연산이 +/- 두가지 이므로 target이 아닐때 
	항상 두번 재귀호출을 하면 모든 경우의 수를 구하게 된다

	얻어갈점
	필요한 연산에 대해 생각해보고 연산에 맞는 호출방식을 선택한다. 이 문제의 경우 +/- 두가지 연산만 사용되므로 이진트리를 이용한 재귀호출을 떠올렸으면 좋았을 것이다

	실폐사례
	없음
 */
public class Pro_타겟넘버 {
	public static void main(String[] args) {
		int[] nums = {1,1,1};
		int target = 1;
		System.out.println(solution(nums, target));

	}

	public static int solution(int[] numbers, int target) {
		int answer = 0;
		answer = dfs(numbers, target, 0, 0);
		return answer;
	}

	static int dfs(int[] numbers, int target, int depth, int sum) {
		int count = 0;
		// 1. 체크인
		if (depth == numbers.length) {
			// 2. 목적지인가
			if (sum == target) {
				return 1;
			}
		} else {
			// 3. 연결된 곳을 순회
			count += dfs(numbers, target, depth + 1, sum + numbers[depth]);
			count += dfs(numbers, target, depth + 1, sum - numbers[depth]);

		}
		return count;
	}
}
