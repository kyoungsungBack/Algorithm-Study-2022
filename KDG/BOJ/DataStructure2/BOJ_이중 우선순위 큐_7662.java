import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeMap;

/**
 * 이중 우선순위 큐 - 자료구조
 * 골5
 * TreeMap 사용 -> TreeMap만 봤을때 추가,삭제,정렬 다해서 O(logN)
 * @author USER
 *
 */
public class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		int N = Integer.parseInt(br.readLine());
		
		for(int i=0; i<N; i++) {
			int k = Integer.parseInt(br.readLine());
			String[] arr = new String[k];
			int[] value = new int[k];
			for(int j=0; j<k; j++) {
				st = new StringTokenizer(br.readLine());
				arr[j] = st.nextToken();//"I" , "D" 
				value[j] = Integer.parseInt(st.nextToken());//값
			}
			System.out.println(queue(arr, value));
		}
	}
	
	public static String queue(String[] arr, int[] value) {
		//key=값, value=값의 갯수, TreeMap은 자동 정렬됨.
		TreeMap<Integer, Integer> map = new TreeMap<Integer, Integer>();
		int min = Integer.MAX_VALUE;
		int max = Integer.MIN_VALUE;
		
		for(int i=0; i<arr.length; i++) {
			if(arr[i].equals("I")) {
				//삽입, 삽입 시 key값이 존재하면 value+1 하고 아니면 걍 1
				map.put(value[i], map.getOrDefault(value[i], 0)+1);
			}else {
				if(map.isEmpty()) continue;//map비었으면 무시
				if(value[i]==-1) {//최솟값
					//맨앞에 값(최소)
					int key = map.firstKey();
					//해당 key값의 value가 여러개면 하나 줄이기(삭제)
					if(map.get(key)>1) map.put(key, map.get(key)-1);
					//value가 1이면 걍 key값 삭제
					else map.remove(key);
				}else {
					//맨뒤에 값(최대)
					int key = map.lastKey();
					//해당 key값의 value가 여러개면 하나 줄이기(삭제)
					if(map.get(key)>1) map.put(key, map.get(key)-1);
					//value가 1이면 걍 key값 삭제
					else map.remove(key);
				}
			}
		}

		if(map.isEmpty()) {//map이 비었으면
			return "EMPTY";
		}else if(map.size()==1) {//map의 key개수가 1개이면
			min = map.firstKey();
			max = min;
		}else {//map의 key개수가 여러개 일때
			min = map.firstKey();
			max = map.lastKey();
		}
		
		return max+" "+min; 
	}
}
