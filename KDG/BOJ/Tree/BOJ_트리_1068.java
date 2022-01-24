import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

/**
 * 트리 - 골5
 * @author USER
 *
 */
public class Main {
	static ArrayList<ArrayList<Integer>> edge = new ArrayList<ArrayList<Integer>>();//인접리스트(간선표시)
	static HashMap<Integer, Integer> mapLevel = new HashMap<Integer, Integer>();//노드 레벨, key=인덱스(노드) , value=레벨
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];//인덱스 - 값 연결돼있음.
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			if(arr[i]==-1) mapLevel.put(i, 0);//부모노드 추가
		}
		
		int key = 0; //부모노드(인덱스,key)
		for(int k:mapLevel.keySet()) key = k;
		Integer target = Integer.parseInt(br.readLine());
		
		createEdge(arr);//1. 간선 만들기
		level(key);//2. 각 노드 레벨 표시
		deleteNode(target);//3. target과 연결된 노드의 간선 모두 제거
		System.out.println(findLeaf(arr));//4. 단말노드 개수 출력
		
		for(ArrayList<Integer> l:edge) {
			System.out.println(l);
		}
	}
	
	//1. 간선 만들기
	public static void createEdge(int[] arr) {
		for(int i=0; i<arr.length; i++) {//인접리스트 생성
			edge.add(new ArrayList<Integer>());
		}
		
		//간선 연결해주기
		for(int i=0; i<arr.length; i++) {
			if(arr[i]==-1) {continue;}//부모노드는 건너뜀
			int aNode = arr[i];
			int bNode = i;
			edge.get(aNode).add(bNode);
			edge.get(bNode).add(aNode);
		}
	}
	
	//2. 각 노드의 레벨 표시(DFS)
	public static void level(int v) {
		//간선 존재 여부로 노드 v에 연결된 노드를 모두 탐색하면서
		//탐색한 노드(i)가 아직 레벨 표시가 안돼있으면 표시해주기
		//이때 v는 부모 노드부터 시작하기 때문에
		//i의 value를 추가할땐 v노드의 value값+1로 해준다. 
		for(Integer i:edge.get(v)) {
			if(!mapLevel.containsKey(i)) {
				mapLevel.put(i, mapLevel.get(v)+1);
				level(i);
			}
		}
	}
	
	//3. target에 연결된 노드의 간선 모두 제거(DFS)
	public static void deleteNode(Integer target) {
		//간선을 가지고 target에 연결된 노드를 temp에 모두 추가.
		ArrayList<Integer> temp = new ArrayList<Integer>();
		for(Integer i:edge.get(target)) {
			temp.add(i);
		}
		
		//temp에 추가된 노드들을 전부 탐색하면서 간선에 연결된 노드 제거.
		for(int i=0; i<temp.size(); i++) {
			//target노드를 기준으로 자식노드들을 모두 없애준다.(자식 노드 있을때)
			if(mapLevel.get(temp.get(i))>mapLevel.get(target)) {
				edge.get(target).remove(temp.get(i));
				edge.get(temp.get(i)).remove(target);
				//자식노드를 기준으로 간선이 연결된 노드 계속 제거
				deleteNode(temp.get(i));
			}
			
			//edge.size()는 노드의 개수임. 
			//target노드에 연결된 간선이 1개면 마지막 노드라는 의미 (자식 노드 없을때)
			if(edge.size()>2 && edge.get(target).size()==1) {
				//target본인 노드를 지우고 간선에 의해 부모에 있는 target 노드를 지움.
				edge.get(target).remove(0);
				edge.get(temp.get(0)).remove(target);
			}else if(edge.size()<=2 && edge.get(target).size()==1) {
				//전체 노드가 2개이하 일땐 target본인 노드만을 지운다.
				//이유는 부모노드는 남겨놔야 하기때문에.
				edge.get(target).remove(0);
			}
		}
		
	}
	
	//4. 단말노드 개수 구하기
	public static int findLeaf(int[] arr) {
		int cnt = 0;
		
		//노드가 2개 보다 많을땐 모든 간선을 돌면서 부모노드를 제외하고
		//간선에 연결된 노드가 한개인 것들만 cnt++해줌( 그게 단말 노드임 )
		if(edge.size()>2) {
			for(int i=0; i<edge.size(); i++) {
				if(arr[i]!=-1 && edge.get(i).size()==1) cnt++;
			}
		}
		//그게 아닐땐
		//노드 사이즈가 한개인것만 cnt++해줌.
		//이때는 부모노드하나만 있어서 1로 나오거나,
		//부모노드를 target으로 했다면 0으로 나옴.
		else if(edge.size()<=2) {
			for(int i=0; i<edge.size(); i++) {
				if(edge.get(i).size()==1) cnt++;
			}
		}
		return cnt;
	}
}
