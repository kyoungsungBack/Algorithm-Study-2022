/******************************************************************************

 문제출처 : https://www.acmicpc.net/problem/4358
 풀이시간 : 10m
 시간복잡도 : O(n) (n: 입력 길이)
 공간복잡도 : O(n)

 *******************************************************************************/

import java.io.*;
import java.util.*;

public class Main {
    private static final String EMPTY = " ";

    public static void main(String[] args) throws IOException{
        var br = new BufferedReader(new InputStreamReader(System.in));

        var cntMap = new HashMap<String, Double>();

        long count = 0;
        String input = br.readLine();
        while(true){
            cntMap.put(input, cntMap.getOrDefault(input, 0.0) + 1.0);
            count++;

            input = br.readLine();
            if(input == null || input.length() == 0) {
                break;
            }
        }

        for(var key : cntMap.keySet()){
            cntMap.put(key, cntMap.get(key) / count);
        }

        List<String> keyList = new ArrayList<>(cntMap.size());
        keyList.addAll(cntMap.keySet());
        Collections.sort(keyList);

        for(var key : keyList){
            System.out.print(key);
            System.out.print(EMPTY);
            System.out.printf("%.4f\n", cntMap.get(key) * 100.0);
        }
    }

}
