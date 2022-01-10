package test;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;


public class Test1 {
    public static void main(String[] args) {
        Queue<Character> que = new LinkedList<>();
        Scanner sc = new Scanner(System.in);
        int n;
        n= sc.nextInt();
        int[] arr =new int[n];
        for(int i=0;i<n;i++){
            arr[i] = sc.nextInt();
        }
        if(run(que,n,arr)){
            while(!que.isEmpty()){
                System.out.println(que.poll());
            }
        }else{
            System.out.println("NO");
        }
    }

    static boolean run(Queue<Character> que, int n, int[] arr) {
        int stnum=1;
        int i=0;
        Stack<Integer> st =new Stack();
        while(i<n){
            if(!st.isEmpty() &&st.peek() == arr[i]){
                st.pop();
                que.add('-');
                i++;
            }else if(stnum <=n){
                st.push(stnum++);
                que.add('+');
            }else{
                return false;
            }
        }
        return true;
    }
}
