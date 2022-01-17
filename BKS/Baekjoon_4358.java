package Baekjoon_2주차;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.TreeMap;
//생태학_성공
public class Baekjoon_4358 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		TreeMap<String, Integer> map = new TreeMap<String, Integer>();

		double mapCount = 0;
		
		while (true) {
			String str = br.readLine();
            if (str == null || str.length() == 0)
				break;
			mapCount++;

			if (map.keySet().contains(str)) {
				// 그때의 key값에 대한 value값 replace
				map.replace(str, map.get(str) + 1);
			} else {
				map.put(str, 1);
			}
		}
		br.close();
        Iterator<String> iter = map.keySet().iterator();
        
		while (iter.hasNext()) {
            String key = iter.next();
			int value = map.get(key);
			double per = (double) (value * 100) / mapCount;
			System.out.print(key + " ");
			System.out.println(String.format("%.4f", per));
		}
	}
}