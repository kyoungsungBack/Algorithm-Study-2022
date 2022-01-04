package test;

import java.util.*;

class files {
    int pr;
    int id;
    files(int pr,int id){
        this.id = id;
        this.pr = pr;
    }

}
public class Test1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n =sc.nextInt();
        for(int i=0;i<n;i++){
            System.out.println(run(sc));
        }

    }
    static int run(Scanner sc){
        int count = 0;
        Deque<files> dq = new ArrayDeque<>();
        PriorityQueue<Integer> pq =new PriorityQueue<>(Collections.reverseOrder());
        int size = sc.nextInt();
        int target = sc.nextInt();
        int p;
        for (int i = 0; i < size; i++) {
            p =sc.nextInt();
            pq.add(p);
            dq.addLast(new files(p, i));
        }
        files tmp ;
        while (!dq.isEmpty()) {
            tmp =dq.pollFirst();
             if (tmp.pr == pq.peek()) {
                pq.poll();
                count++;
                if(tmp.id ==target){
                    break;
                }
            }else{
                dq.addLast(tmp);
            }
        }
        return count;

    }

}
