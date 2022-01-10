import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int[] answer;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int T = Integer.parseInt(br.readLine());
		answer = new int[T];
		
		for(int i=0; i<T; i++) {//테스트 케이스만큼 반복
			st = new StringTokenizer(br.readLine());
			Queue<printQ> Q = new LinkedList<printQ>();//테케마다 큐 새로 생성
			int N = Integer.parseInt(st.nextToken());//문서 개수
			int M = Integer.parseInt(st.nextToken());//원하는 문서의 순서 변수
			int impM = 0;//원하는 문서의 중요도 변수
			st = new StringTokenizer(br.readLine());
			
			//1번
			//큐에 문서 입력
			for(int j=0; j<N; j++) {
				int value = Integer.parseInt(st.nextToken());//중요도
				if(j==M) impM = value;//원하는 문서의 순서일때
				
				Q.add(new printQ(j,value));
			}
			
			//큐사이즈 1이면 문서한개임. 따라서 1번째로 뽑힘
			if(Q.size()==1) answer[i]=1;
			//printQueue(원하는문서 중요도, 원하는문서 순서, 문서저장된 큐)
			else answer[i]=printQueue(impM,M,Q); 
		}
		
		for(int a:answer) {
			System.out.println(a);
		}
	}
	
	//원하는 문서 몇번째로 뽑히는지 알아내는 메서드
	public static int printQueue(int impM, int M, Queue<printQ> Q) {
		int maxImp=Q.peek().imp;//중요도 최댓값을 큐의 첫번째값으로 초기화
		int maxSeq=0;//첫번째 문서이므로 0으로 순서 초기화
		int count=0;//몇번째로 뽑히는지 알려줄 변수
		int search=0;//큐 탐색횟수
		
		//2번
		//첫번째 while문 -> 큐전체 탐색하며 최댓값 찾기
		while(!Q.isEmpty()) {
			//반복문 돌다 큐사이즈 1이면 마지막이 원하는 문서라는 의미가됨.
			//따라서 지금까지 카운트한 count변수에 +1을 해주고 반환.
			if(Q.size()==1) return count+1;
			
			printQ pq = Q.poll();
			int crrSeq = pq.seq;//현재 순서
			int crrImp = pq.imp;//현재 중요도
			
			//현재 중요도가 지금까지 중요도가 최댓값인것 보다 크면
			//문서의 중요도 최댓값과 순서를 현재꺼로 갱신시켜줌.
			if(maxImp < crrImp) {
				maxImp = crrImp;
				maxSeq = crrSeq;
			}
			
			//큐 하나 요소 탐색했으면 다시 원래대로 삽입
			Q.add(new printQ(crrSeq, crrImp));
			//요소 하나 탐색할때 마다 search 증가.
			search++;

			//요소를 전부 탐색했을때
			if(search==Q.size()) {
				//maxImp가 0이라면 문서하나 뽑은뒤 나머지 문서가
				//모두 중요도가 같다는 의미가됨.
				//따라서 큐에 있는 첫번재 요소가 먼저 출력돼야 하기 때문에
				//문서의 최댓값 중요도와 순서를 큐의 가장 앞에 있는 원소로 할당
				if(maxImp==0) {
					maxImp=Q.peek().imp;
					maxSeq=Q.peek().seq;
				}
				
				//3번
				//두번째 while -> 위에서 찾은 최댓값에 해당하는 문서 출력
				//최댓값에 해당하는 문서가 출력될때까지 처음부터 끝까지 큐탐색하며
				//최댓값을 찾음.
				while(true) {
					printQ temp = Q.poll();
					int tempSeq = temp.seq;
					int tempImp = temp.imp;
					
					//지금 뽑은 요소가 최댓값과 순서가 같을때 최댓값 출력.
					//출력한다는 의미 -> 큐에서 요소를 없앤다.(바로 위에서 poll함)
					if(tempSeq==maxSeq && tempImp==maxImp) {
						count++;//출력할때 카운트 증가.
						//만약 내가원하는 문서이면 count 반환하고 종료.
						if(tempSeq==M && tempImp==impM) return count;
						break;//그게아니면 반복문 종료.
					}
					//만약 현재 뽑은 요소가 최댓값 문서가 아니면
					//다시 큐에 넣어줌.
					else {
						Q.add(new printQ(tempSeq, tempImp));
					}
				}
				
				//반복문 후에 변수 다시초기화
				maxImp=0;
				maxSeq=0;
				search=0;
			}
			
		}
		return count;
	}
}

//문서의 중요도와 순서를 저장해주는 클래스
class printQ{
	int seq;//순서
	int imp;//중요도
	
	//생성자
	public printQ(int seq, int imp) {
		this.seq = seq;
		this.imp = imp;
	}
}
