package week8;

public class 타겟넘버 {


    static int result;

    public static void main(String[] args) {
        int[] numbers = {1, 1, 1, 1, 1};
        int target = 3;


        dfs(0, 0,numbers,target);

        System.out.println(result);

    }




    public static void dfs(int idx, int sum, int[] numbers, int target) {

        //마지막까지 탐색했을때 target과 같다면 방법수 1+
        if (idx == numbers.length) {
            if (sum == target)
                result++;
            return;
        }

        dfs(idx+1, sum + numbers[idx], numbers,target);
        dfs(idx+1, sum - numbers[idx],numbers,target);
    }

}