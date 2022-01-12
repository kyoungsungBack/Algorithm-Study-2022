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
		HashMap<String, Double> map = new HashMap<String, Double>(); //나무 넣을 맵
		double total = 0; //전체 나무 개수
		
		while(true) {
			String tree = br.readLine();
			if(tree == null || tree.equals("")) break; //입력값이 이럴때 while문 탈출
			
			total++;//한번 입력할때마다 total 증가
			
			//입력한 나무이름이 map에 있다면 해당 나무의 value값을 증가시키고 갱신
			//없다면 걍 넣기.
			if(map.containsKey(tree)) {
				double value = map.get(tree)+1.0;
				map.put(tree, value);
			}else {
				map.put(tree, 1.0);
			}
		}
		
		//나무이름 오름차순 정렬(key값 기준 정렬) 
		Object[] arr = map.keySet().toArray();
		Arrays.sort(arr);
		
		//나무 분포도 계산-> calTree(전체 나무개수, 나무 저장된 map, 나무이름 정렬된 배열)
		String answer = calTree(total, map, arr);
		System.out.println(answer);
	}
	
	//나무 분포도 계산
	public static String calTree(double total, HashMap<String, Double> map, Object[] arr) {
		StringBuilder sb = new StringBuilder();
		
		//순서가 가장 빠른 나무 ~ 마지막 나무까지 분포도 구하고
		//소숫점 4째자리까지 표시한거 sb에 삽입.
		for(int i=0; i<arr.length; i++) {
			String key = (String) arr[i]; //오름차순된 나무
			double per = (map.get(key)/total)*100; //해당 나무 분포도 
			String value = String.format("%.4f", per); //소숫점 4째자까지 반올림후 표기 
			
			sb.append(key).append(" ").append(value).append("\n");
		}
		
		return sb.toString();
	}
}
