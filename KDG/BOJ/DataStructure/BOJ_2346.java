import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static Queue<qLocation> q = new LinkedList<qLocation>();//풍선담을 큐
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());//풍선개수
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		for(int i=0; i<N; i++) {//풍선 담기
			q.add(new qLocation(Integer.parseInt(st.nextToken()),i+1));
		}
		
		//풍선 번호대로 터뜨리기
		while(true) {
			int preSize = q.size();//이전 풍선개수 변수
			int afterSize = 0;//이후 풍선개수 변수
			qLocation ql = q.poll();//풍선 하나 터뜨리기
			int nowV = ql.val;//터뜨린 풍선 값
			System.out.print(ql.loc+" ");//방금 터뜨린 풍선들 위치를 출력
			
			if(q.size()==0) break;//큐 비었으면 멈춤
			if(nowV>0) rightMove(preSize, afterSize, nowV);//오른쪽으로 이동
			else if(nowV<0) leftMove(preSize, afterSize, nowV);//왼쪽으로 이동
		}
	}
	
	//오른쪽 이동
	public static void rightMove(int preSize, int afterSize, int nowV) {
		int move = 0;//몇번 움직이는지에 대한 변수
		
		afterSize = preSize-1;//위에서 풍선하나 터뜨리고난 후의 개수
		move = nowV % afterSize;//몇번 움직일지 계산
		
		if(move==0) {//나눠 떨어지면 다음 풍선은 무조건 끝에 풍선을 터뜨리는 경우
			for(int i=0; i<afterSize-1; i++) {//-1을 해주는 이유는 다음 터뜨릴 풍선을 큐의 맨앞에 놓기위해서임
				q.add(q.poll());
			}
		}else {//그게 아니면 move-1만큼 이동
			for(int i=0; i<move-1; i++) {//-1을 해주는 이유는 다음 터뜨릴 풍선을 큐의 맨앞에 놓기위해서임
				q.add(q.poll());//다음 터뜨릴 풍선 앞에 올때까지 빼고 넣기 반복
			}
		}
	}
	
	//왼쪽 이동
	public static void leftMove(int preSize, int afterSize, int nowV) {
		int move = 0;
		
		afterSize = preSize-1;//위서 풍선하나 터뜨리고나 후의 개수
		//왼쪽으로 이동의경우 (전체개수 - 나머지값)으로 계산하는데 이는 왼쪽이동을
		//오른쪽이동처럼 해주기 위해서임.
		//이때 오른쪽이동에서 처럼 move==0일땐 절대 안나오게됨. 이유는 preSize를 빼주기 때문이고
		//만약 나머지값이 0이라면 결국 다음 풍선인 제일 앞에 풍선을 터뜨릴 수 있도록 이동수(move)가 계산됨.
		move = preSize - (Math.abs(nowV) % afterSize);//몇번 움직일지 계산
		
		for(int i=0; i<move-1; i++) {////-1을 해주는 이유는 다음 터뜨릴 풍선을 큐의 맨앞에 놓기위해서임
			q.add(q.poll());//다음 터뜨릴 풍선 앞에 올때까지 빼고 넣기 반복
		}
	}
}

//큐에 담을 풍선의 값과 위치를 나타내줄 클래스
class qLocation{
	int val;
	int loc;
	public qLocation(int val, int loc) {
		this.val = val;
		this.loc = loc;
	}
}
