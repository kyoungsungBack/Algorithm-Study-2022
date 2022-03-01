import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int r, c, ans;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int length = (int) Math.pow(2,N);//행렬의 처음 길이
		r = Integer.parseInt(st.nextToken());//행위치
		c = Integer.parseInt(st.nextToken());//열위치
		ans = 0;//답
		
		//ZDC(입력값, 행시작점, 행끝점, 열시작점, 열끝점);
		ZDC(N,0,length,0,length);
		System.out.println(ans);
	}
	
	public static void ZDC(int N, int rowS, int rowE, int colS, int colE) {
		if(N==0) return;//N==0이라는 말은 더이상 나눌수 없다는 말.
		
		int rowMid = (rowE-rowS)/2;//행 중간점
		int colMid = (colE-colS)/2;//열 중간점
		
		if(r<rowMid && c<colMid) { //r,c가 1사분면에 있을때
			ZDC(N-1, rowMid, rowMid*2, colMid, colMid*2);
		}else if(r<rowMid && c>=colMid) { //r,c가 2사분면에 있을때
			ans += colMid*rowMid; //1사분면 방개수 만큼 카운트
			c -= colMid; //2사분면이면 열만 갱신
			ZDC(N-1, rowMid, rowMid*2, colMid, colMid*2);
		}else if(r>=rowMid && c<colMid) { //r,c가 3사분면에 있을때
			ans += 2*(colMid*rowMid);//1,2사분면 방개수 만큼 카운트
			r -= rowMid; //3사분면이면 행만 갱신
			ZDC(N-1, rowMid, rowMid*2, colMid, colMid*2);
		}else if(r>=rowMid && c>=colMid) { //r,c,가 4사분면에 있을때
			ans += 3*(colMid*rowMid); //1,2,3사분면 방개수 만큼 카운트
			r -= rowMid; //4사분면이면 행도 갱신
			c -= colMid; //4사분면이면 열도 갱신
			ZDC(N-1, rowMid, rowMid*2, colMid, colMid*2);
		}
		
	}
}
