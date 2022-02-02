import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

/**
 * 네트워크 연결 - 골드4
 * 크루스칼 알고리즘 활용
 * 풀이시간 : 30m
 * @author USER
 *
 */
public class Main {
	static int answer = 0; //최소비용
	static int[] parents; //parents[자식노드] = 부모노드
	static ArrayList<Node1922> list = new ArrayList<Node1922>(); //간선과 비용 표시리스트
	static class Node1922{ //연결된 정점2개와 간선의 가중치 나타내는 클래스
		int node1;
		int node2;
		int nodeCost;
		
		public Node1922(int node1, int node2, int nodeCost) {
			this.nodeCost = nodeCost;
			this.node1 = node1;
			this.node2 = node2;
		}
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		int n = Integer.parseInt(br.readLine()); //정점개수
		int m = Integer.parseInt(br.readLine()); //간선개수
		parents = new int[n+1];
		
		//parents배열 초기화
		for(int i=1; i<=n; i++)
			parents[i] = i;
		
		//간선에 연결된 정점 2개랑 가중치 넣기
		for(int i=0; i<m; i++) {
			st = new StringTokenizer(br.readLine());
			int v1 = Integer.parseInt(st.nextToken());
			int v2 = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			list.add(new Node1922(v1, v2, c));
		}
		
		kruskal(n);//크루스칼 알고리즘
		System.out.println(answer);//출력
	}
	
	//크루스칼 알고리즘
	public static void kruskal(int n) {
		listSort(); //list 가중치 기준으로 오름차순 정렬
		int cnt = 0; //선택된 간선 개수
		
		//간선 순회
		for(int i=0; i<list.size(); i++) {
			int v1 = list.get(i).node1;
			int v2 = list.get(i).node2;
			int c = list.get(i).nodeCost;
			
			//간선의 개수가 정점개수-1 이면 종료.
			if(n-1==cnt) return;
			
			//사이클이되는지 안되는지 판단
			if(!sameP(v1,v2)) {
				union(v1,v2);//다른루트 노드 = 사이클 x = 트리 합치기
				answer += c;//선택한 간선의 가중치 더하기
				cnt++;//간선 개수 증가
			}
		}
		
	}
	
	public static void listSort() {
		Collections.sort(list, new Comparator<Node1922>() {

			@Override
			public int compare(Node1922 o1, Node1922 o2) {
				int c1 = o1.nodeCost;
				int c2 = o2.nodeCost;
				
				if(c1<c2) return -1;
				else if(c1>c2) return 1;
				else return 0;
			}
		});
	}
	
	public static void union(int v1, int v2) {
		v1 = find(v1);
		v2 = find(v2);
		
		if(v1>v2) parents[v1] = v2;
		else if(v1<v2) parents[v2] = v1;
	}
	
	public static boolean sameP(int v1, int v2) {
		v1 = find(v1);
		v2 = find(v2);
		
		if(v1!=v2) return false;
		return true;
	}
	
	public static int find(int v) {
		if(parents[v]==v) return v;
		
		return parents[v] = find(parents[v]);
	}
}
