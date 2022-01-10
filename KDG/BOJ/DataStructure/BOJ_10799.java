import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Stack;
/**
 * 쇠막대기 - 10799
 * 실3
 * @author USER
 *
 */
public class Main {
	static ArrayList<Integer> razerIdx = new ArrayList<Integer>();//레이저 위치를 저장하는 리스트
	static Stack<StickPoint> stickIdx = new Stack<StickPoint>();//쇠막대기 위치를 저장하는 스택
	
	public static void main(String[] args) throws IOException{
		int answer = 0;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine();
		
		location(s);//쇠막대기와 레이저 위치
		
		//쇠막대기 하나씩 잘라서 answer에 추가
		while(!stickIdx.isEmpty()) {
			StickPoint sp = stickIdx.pop();
			answer += stickDivide(sp,1);
		}
		
		System.out.println(answer);
	}
	
	//위치
	public static void location(String s) {
		Stack<StickPoint> st = new Stack<StickPoint>();
		
		for(int i=0; i<s.length(); i++) {
			char temp = s.charAt(i);
			
			//스택에 '('일때 추가하고, ')'일때 pop. 
			if(st.isEmpty() || temp==st.peek().c) {
				st.add(new StickPoint(i, 0, temp)); 
			} else {
				StickPoint sp = st.pop();
				//바로 전요소 체크후 레이저면 레이저 위치 추가.
				if(s.charAt(i-1)=='(') { razerIdx.add(i); }
				//레이저가 아니면 쇠막대기 위치 추가.
				else if(s.charAt(i-1)!='(') { stickIdx.add(new StickPoint(sp.x, i, temp));}
			}
		}
	}
	
	//쇠막대기 조각내서 개수 세기
	//cnt(개수)는 레이저 n개를 쐈을때 조각난 쇠막대기가 n+1개가 되야하므로
	//초기값을 1로하고 시작함.
	public static int stickDivide(StickPoint sp, int cnt) {
		int stickX = sp.x;//쇠막대기 시작 위치
		int stickY = sp.y;//쇠막대기 끝나는 위치
		
		for(int i=0; i<razerIdx.size(); i++) {
			int razer = razerIdx.get(i);
			//레이저가 쇠막대기 시작과 끝위치 안에 포함되면
			//레이저가 닿는다는 의미로 쇠막대기가 조각나게됨.
			//따라서 레이저 닿을때 마다 cnt 증가.
			if(stickX<razer && stickY>razer) { cnt++; }
		}
		return cnt;
	}
}

//스택에 저장해줄 클래객체
class StickPoint{
	int x;//start 위치
	int y;//end 위치
	char c;//괄호 모양
	public StickPoint(int x, int y, char c) {
		this.x = x;
		this.y = y;
		this.c = c;
	}
}
