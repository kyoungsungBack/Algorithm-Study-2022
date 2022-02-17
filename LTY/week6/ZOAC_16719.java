package week6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ZOAC_16719 {

    static class Pair implements Comparable<Pair>{
        String s;
        int idx;
        public Pair(String s, int idx){
            this.s = s;
            this.idx = idx;
        }

        //문자열 사전순 정렬
        public int compareTo(Pair o){
            if(s.compareTo(o.s) > 0){ //대상 문자열이 매개변수로 받은 문자열보다 사전순으로 뒤질 경우 양수 반환.
                return 1;
            }else if(s.compareTo(o.s)<0){ // 대상 문자열이 매개변수로 받은 문자열보다 사전 순으로 앞선 경우 음수 반환.
                return -1;
            }else //
                return 0; // 두 문자열이 사전 순으로 같다면 0을 반환.
        }
    }



    public static void main(String[] args) throws IOException {

        InputStream in = System.in;
        InputStreamReader reader = new InputStreamReader(in);
        BufferedReader br = new BufferedReader(reader);
        String temp = br.readLine();
        char[] temp2 = temp.toCharArray();
        boolean[] visited = new boolean[temp2.length];

        StringBuilder sb = new StringBuilder();
        for(int i =0; i<temp2.length; i++){
            sb.setLength(0); //sb 초기화
            List<Pair> list = new ArrayList<>();
            for(int j=0; j<temp2.length;j++){
                if(!visited[j]){
                    String temp3 = "";
                    for(int k=0;k<temp2.length; k++){
                        if(j==k || visited[k]){
                            temp3 +=temp2[k]+"";
                        }
                    }
                    list.add(new Pair(temp3, j));
                }
            }
            Collections.sort(list);
            sb.append(list.get(0).s);
            visited[list.get(0).idx] = true;
            System.out.println(sb.toString());
        }
    }


}