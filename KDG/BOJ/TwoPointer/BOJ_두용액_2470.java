import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

/**
 * 두 용액 - 골드5
 * 풀이시간: 1h
 * @author USER
 *
 */
public class Main {
	static ArrayList<Integer> list = new ArrayList<Integer>();//용액들
	static int min = Integer.MAX_VALUE;//0에 가까운 최소값
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());//용액 개수
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			list.add(Integer.parseInt(st.nextToken()));
		}
		
		//오름차순 정렬
		Collections.sort(list, new Comparator<Integer>() {

			@Override
			public int compare(Integer o1, Integer o2) {
				if(o1<o2) return -1;
				else if(o1>o2) return 1;
				else return 0;
			}
		});
		
		System.out.println(findLiquid());//출력
		
	}
	
	//0에 가까워지는 용액 2개 찾기
	public static String findLiquid() {
		int start = 0;//첫번째 인덱스
		int end = list.size()-1;//두번째 인덱스
		int minStart = 0;//정답 인덱스(첫번째)
		int minEnd = 0;//정답 인덱스(두번째)
		int sum = 0;//두용액의 합
		
		while(start<end) {//두 용액의 인덱스가 만날때까지 돌리기
			sum = list.get(start) + list.get(end);
			
			//최소값이 sum보다 크면 min의 정보를 sum의 정보로 갱신 시켜주기
			//절대값 쓰는 이유는 0에 가까운 최소값을 구하기 위해선
			//계속 양수랑 비교해줘야함.
			if(Math.abs(min)>Math.abs(sum)) {
				min = sum;
				if(min==0) return list.get(start)+" "+list.get(end);//최소값0이면 바로 끝내기
				minStart = start;//최소값 인덱스 갱신
				minEnd = end;//최소값 인덱스 갱신
			}
			
			//특정값 0기준으로 인덱스를 늘리고 줄이기. 
			if(sum>0) end--;
			else if(sum<0) start++;
		}
		
		return list.get(minStart)+" "+list.get(minEnd);
	}
}
