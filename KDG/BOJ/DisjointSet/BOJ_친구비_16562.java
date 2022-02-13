import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 친구비 - 골드3
 * 풀이시간: 40m
 * @author USER
 *
 */
public class Main {
	static int[] parents; //parents[자식노드] = 부모노드
	static int[] money;
	static int answer = 0;
	static int k;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		
		int n = Integer.parseInt(st.nextToken());//학생 수
		int m = Integer.parseInt(st.nextToken());//친구 관계 수
		k = Integer.parseInt(st.nextToken());//갖고있는 돈
	
		parents = new int[n+1];
		for(int i=1; i<=n; i++) //배열 초기화
			parents[i] = i;
		
		st = new StringTokenizer(br.readLine());
		money = new int[n+1];
		for(int i=1; i<=n; i++) //각 학생의 친구비용
			money[i] = Integer.parseInt(st.nextToken());
		
		for(int i=0; i<m; i++) { //친구 관계에 따른 트리만들기(union)
			st = new StringTokenizer(br.readLine());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			union(v,w);
		}
		
		//cost함수를 통해 최소비용 계산
		if(cost(n)) System.out.println(answer);
		else System.out.println("Oh no");
		
	}
	
	//합치기 - 트리 만들기 - 같은 루트노드 같도록 만들기
	public static void union(int v1, int v2) {
		v1 = find(v1);//v1의 루트 노드 찾기
		v2 = find(v2);//v2의 루트 노드 찾기
		
		if(v1 != v2) {
			 //루트 노드가 다를때 합치기전에 최소비용이
			 //들어가는 노드를 루트노드로 해서 합치기
			if(money[v1] > money[v2]) parents[v1] = v2; //v2를 루트노드로
			else parents[v2] = v1; //v1을 루트노드로
		}
	}
	
	//루트노드 찾기
	public static int find(int v) {
		//자식과 부모노드가 같다면 v가 루트노드임.
		if(parents[v]==v) return v; 
		
		//다를땐 재귀를 통해서 v노드의 루트노드 찾기
		return parents[v] = find(parents[v]);
	}
	
	//비용 얼마나 드는지 계산(계산되면 true, 안되면 false반환)
	public static boolean cost(int n) {
		//1~n까지 루트노드를 탐색
		for(int i=1; i<=n; i++) {
			if(parents[i]==i) {//루트노드 일때
				k -= money[i];//갖고있는 돈에서 친구비용 빼기
				if(k>=0) {//갖고있는돈이 남아있으면 친구만들수 있음.
					union(0,i);//합쳐주기.
					answer += money[i];//answer에 합친 친구 비용 추가.
				}else return false;//돈없으면 false 반환
			}
		}
		return true;
	}
}
