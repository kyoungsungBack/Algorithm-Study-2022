import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 여행가자 - 골드4(유니온 파인드)
 * 풀이시간: 10m
 * @author USER
 *
 */
public class Main {
	static int[] parents; //parents[본인노드] = 부모노드
	static int check = 0; //루트노드 담아줄 변수
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		int n = Integer.parseInt(br.readLine()); //도시 개수
		int m = Integer.parseInt(br.readLine()); //여행 계획에 있는 도시 개수
		parents = new int[n+1];
		
		//배열 초기화
		for(int i=1; i<=n; i++) 
			parents[i] = i;
		
		//도시개수 만큼 입력받기
		for(int i=1; i<=n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=1; j<=n; j++) {
				int v = Integer.parseInt(st.nextToken());
				if(v==1) union(i,j);//도시가 연결돼있을때 배열에 도시두개 연결시키기(합치기)
			}
		}
		
		//여행계획에 포함된 도시들 입력받기
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<m; i++) {
			int target = Integer.parseInt(st.nextToken());
			//첫번째 도시는 무조건 방문, 그후의 도시는 첫번째 방문 도시의 루트노드와 같아야함.
			if(i==0) {
				check = find(target);
				continue;
			}
			
			//루트노드가 다르면 계획대로 도시못감(연결안돼있음)
			if(!sameP(target)) {
				System.out.println("NO");
				System.exit(0);
			}
		}
		
		System.out.println("YES");
	}
	
	//합치기
	public static void union(int v1, int v2) {
		v1 = find(v1);//v1도시의 루트노드 찾기
		v2 = find(v2);//v2도시의 루트노드 찾기
		
		//두개의 루트노드 중 더 작은 노드를 루트노드로 바꿔서 합쳐주기
		if(v1 < v2) parents[v2] = v1;
		else if(v1 > v2) parents[v1] = v2;
	}
	
	//같은 루트노드에 포함되는지 찾기
	public static boolean sameP(int target) {
		target = find(target);

		if(check==target) return true;//루트노드가 같으면 true 리턴
		
		return false;
	}
	
	//v도시의 루트노드가 뭔지 찾기
	public static int find(int v) {
		if(parents[v]==v) return v;
		
		return parents[v]=find(parents[v]);
	}
}
