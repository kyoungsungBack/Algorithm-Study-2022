import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
/**
 * 절댓값 힙 - 자료구조
 * 실1
 * @author USER
 *
 */
public class Main {
	static int N;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		//가장 작은값 부터 출력 -> 오름차순 출력
		PriorityQueue<Integer> pq = new PriorityQueue<>(new Comparator<Integer>() {//익명함수
			
			@Override
			public int compare(Integer o1, Integer o2) {
				Integer a = Math.abs(o1);
				Integer b = Math.abs(o2);
				
				//음수 : 자리교환 x , 양수 : 자리교환 o, 자바는 디폴트가 오름차순
				//절댓값 비교
				if(a > b) {
					return 1;//자리 교환 o
				}else if(a < b) {
					return -1;//자리 교환 x
				}else {// 원래 원소를 비교
					if(o1 > o2) { 
						return 1; // 자리 교환 o
					}else if(o1 < o2) {
						return -1; // 자리 교환 x
					}else {
						return 0;
					}
				}
			}
		});
		
		N = Integer.parseInt(br.readLine());
		
		for(int i=0; i<N; i++) {
			int x = Integer.parseInt(br.readLine());
			if(x==0) {
				if(pq.isEmpty()) {
					sb.append(0).append("\n");
				}else {
					sb.append(pq.poll()).append("\n");
				}
			}else {
				pq.add(x);
			}
		}
		System.out.println(sb);
	}	
}
