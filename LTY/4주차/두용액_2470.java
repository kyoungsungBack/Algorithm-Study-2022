package week4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 두용액_2470 {

    static int ans1 = 0;
    static int ans2 = 0;
    public static void main(String[] args) throws IOException {




        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] list = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++){
            list[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(list); //오름차순 정렬


        int start =0;
        int end = list.length-1;
        int gap = list[end]; //배열에서 가장 큰 값을 임시적으로 gap으로 둔다.

        while(start<end){
            int sum = list[start] + list[end];
            int temp = Math.abs(sum);
            if(temp<gap){
                gap = temp;
                ans1 = list[start];
                ans2 = list[end];
            }

            if(sum>0)
                end--;
            else
                start++;
        }

        System.out.println(ans1+ " "+ans2);




    }
}
// 음수, 양수가 섞인 배열을 우선 오름차순 정렬.
//맨 왼쪽은 가장 낮은 음수, 맨 오른쪽은 가장 큰 양수
// 가운데로 갈 수록 점점 절댓값은 작아진다.

/*
양끝부터 시작해서 탐색을 진행
왼쪽 인덱스를 i 오른쪽 인덱스를 j라고할 때
i -> <-j
 */