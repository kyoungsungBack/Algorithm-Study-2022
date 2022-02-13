package week5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 빗물_14719 {

    static int result = 0;
    public static void main(String[] args) throws IOException {

        InputStream in = System.in;
        InputStreamReader reader = new InputStreamReader(in);
        BufferedReader br = new BufferedReader(reader);

        StringTokenizer st = new StringTokenizer(br.readLine());
        int H = Integer.parseInt(st.nextToken());
        int W = Integer.parseInt(st.nextToken());



        StringTokenizer st2 = new StringTokenizer(br.readLine());

        int list[] = new int[W]; //가로 길이 만큼 1차원 배열 만들어주기
        for(int i=0; i<W;i++){
            list[i] = Integer.parseInt(st2.nextToken());
        }


        //맨 왼쪽과 오른쪽에는 물을 채울수 없다.

        //로직 - 왼쪽에서 가장 큰 벽과 오른쪽에서 가장 큰 벽을 구한후,
        // 두개중 작은 벽을 기준으로 한다.


        for(int i=1;i<W-1;i++){
            int base = list[i];//기준치
            int leftwall =base;
            int rightwall = base;

            //왼쪽 최대벽
            for(int j=i-1; j>=0; j--) {
                if (list[j] > base) {
                    leftwall = Math.max(leftwall, list[j]);
                }
            }

            //오른쪽 최대
            for(int j=i+1; j<W; j++){
                if(list[j]>base)
                    rightwall = Math.max(rightwall, list[j]);
            }

            if(Math.min(leftwall, rightwall)>base){ //오른쪽제일높은벽 왼쪽 제일높은벽보다 현재벽이 작다
                result+= Math.min(leftwall, rightwall) - base;
            }




        }
        System.out.println(result);
    }
}
