package week8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class k진수에서소수개수구하기 {
    static int n;
    static int k;
    public static void main(String[] args) throws IOException {

        InputStream in = System.in;
        InputStreamReader reader = new InputStreamReader(in);
        BufferedReader br = new BufferedReader(reader);
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        n = Integer.parseInt(st.nextToken()); //양의 정수 n
        k = Integer.parseInt(st.nextToken()); //변환할 진수 k

        System.out.println((solution(n, k)));

    }

    public static int solution(int n, int k){
        int result = 0;
        StringBuilder sb = new StringBuilder();
        while(n>0){
            sb.append(n % k);
            n/=k;
        }
        sb.reverse();
        String[] nums = sb.toString().split("0");


        for(String num : nums){
            if(num.equals(""))
                continue;
            if(isPrime(Long.valueOf(num)))
                result++;
        }


        return result;
    }

    public static boolean isPrime(long num){
        boolean temp = true;
        if(num==1)
            temp = false;
        for(int i=2; i<num; i++){
            if(num%i==0){
                temp = false;
                break;
            }else{
                temp = true;
            }
        }
        return temp;
    }


}
