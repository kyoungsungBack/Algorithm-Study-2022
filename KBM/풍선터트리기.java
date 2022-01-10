package test;

import java.util.Scanner;

public class Test1 {
    public static void main(String[] args) {
            int n;
            Scanner sc =new Scanner(System.in);
            n = sc.nextInt();
            int[] arr= new int[n];
            for(int i=0;i<n;i++){
                arr[i] =sc.nextInt();
            }

            run(arr,n);
    }

    static void run(int[] arr, int n) {
        int index = 0, jump = 1,length=0;
        boolean[] check = new boolean[n];

       for(int i=0;i<n;i++){
            for(int j= 0;j<Math.abs(length); j++){
                index = (index + jump + n) % n;
                if(check[index]) j--;
            }
            System.out.print(index +1+ " ");
            check[index] = true;
            length =arr[index];
            if(arr[index]<0) jump =-1;
            else jump= 1;
        }
    }
}