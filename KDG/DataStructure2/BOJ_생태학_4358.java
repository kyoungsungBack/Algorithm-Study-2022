import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;

/**
 * 생태학 - 자료구조
 * 실1
 * @author USER
 *
 */
public class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		HashMap<String, Double> map = new HashMap<String, Double>();
		double total = 0;
		
		while(true) {
			String tree = br.readLine();
			if(tree == null || tree.equals("")) break;
			
			total++;
			if(map.containsKey(tree)) {
				double value = map.get(tree);
				map.put(tree, value+1.0);
			}else {
				map.put(tree, 1.0);
			}
		}
		
		Object[] arr = map.keySet().toArray();
		Arrays.sort(arr);
		
		String answer = calTree(total, map, arr);
		System.out.println(answer);
	}
	
	
	public static String calTree(double total, HashMap<String, Double> map, Object[] arr) {
		StringBuilder sb = new StringBuilder();
		
		for(int i=0; i<arr.length; i++) {
			String key = (String) arr[i];
			double per = (map.get(key)/total)*100;
			String value = String.format("%.4f", per);
			
			sb.append(key).append(" ").append(value).append("\n");
		}
		
		return sb.toString();
	}
}
