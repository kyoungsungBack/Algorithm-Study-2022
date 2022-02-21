package Implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/**
 * 배열 돌리기3 - 실버1
 * 구현
 * 풀이시간:1h~
 * @author USER
 *
 */
public class BOJ_16935 {
	static int n, m, r, Rcnt;//배열 크기:n,m   연산수:r    회전수:Rcnt 
	static int[][] arr;//초기 배열
	static int[][] Rarr;//회전된 배열
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		r = Integer.parseInt(st.nextToken());
		Rcnt = 2;
		arr = new int[n][m];
		Rarr = new int[m][n];
		
		int[] oper = new int[r];
		
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<m; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<r; i++) 
			oper[i] = Integer.parseInt(st.nextToken());
		
		for(int i=0; i<oper.length; i++) {
			int operKind = oper[i];
			if(operKind == 1) reverseUD(); //상하반전
			else if(operKind == 2) reverseLR(); //좌우반전
			else if(operKind == 3) angleR(); //90도회전 오른쪽
			else if(operKind == 4) angleL(); //90회전 왼쪽
			else if(operKind == 5) groupR(); //그룹이동 오른쪽
			else groupL(); //그룹이동 왼쪽
		}
		
		printAll();//연산끝난 배열 출력
	}
	
	public static void reverseUD() {
		if(Rcnt%2==0) {
			for(int i=0; i<m; i++) {
				for(int j=0; j<n/2; j++) {
					int temp = arr[j][i];
					arr[j][i] = arr[n-j-1][i];
					arr[n-j-1][i] = temp;
				}
			}
		}else {
			for(int i=0; i<n; i++) {
				for(int j=0; j<m/2; j++) {
					int temp = Rarr[j][i];
					Rarr[j][i] = Rarr[m-j-1][i];
					Rarr[m-j-1][i] = temp;
				}
			}
		}
		
	}
	
	public static void reverseLR() {
		if(Rcnt%2==0) {
			for(int i=0; i<n; i++) {
				for(int j=0; j<m/2; j++) {
					int temp = arr[i][j];
					arr[i][j] = arr[i][m-j-1];
					arr[i][m-j-1] = temp;
				}
			}
		}else {
			for(int i=0; i<m; i++) {
				for(int j=0; j<n/2; j++) {
					int temp = Rarr[i][j];
					Rarr[i][j] = Rarr[i][n-j-1];
					Rarr[i][n-j-1] = temp;
				}
			}
		}
		
	}
	
	//회전은 한줄씩 떼어다가 회전될 배열에 차례대로 갖다붙이는거임.
	public static void angleR() {
		if(Rcnt%2==0) {
			for(int i=0; i<Rarr.length; i++) {
				for(int j=0; j<Rarr[i].length; j++) {
					Rarr[i][j] = arr[n-j-1][i];
				}
			}
		}else {
			for(int i=0; i<arr.length; i++) {
				for(int j=0; j<arr[i].length; j++) {
					arr[i][j] = Rarr[m-j-1][i];
				}
			}
		}
		Rcnt++;
	}
	
	public static void angleL() {
		if(Rcnt%2==0) {
			for(int i=0; i<Rarr.length; i++) {
				for(int j=0; j<Rarr[i].length; j++) {
					Rarr[i][j] = arr[j][m-i-1];
				}
			}
		}else {
			for(int i=0; i<arr.length; i++) {
				for(int j=0; j<arr[i].length; j++) {
					arr[i][j] = Rarr[j][n-i-1];
				}
			}
		}
		Rcnt++;
	}
	
	//그룹이동은 각그룹에 있는 요소들을 이동될 위치로 하나씩 옮겨주는거임.
	public static void groupR() {
		if(Rcnt%2==0) {
			for(int i=0; i<n/2; i++) {
				for(int j=0; j<m/2; j++) {
					int temp = arr[i][j];
					arr[i][j] = arr[(n/2)+i][j];
					arr[(n/2)+i][j] = arr[(n/2)+i][(m/2)+j];
					arr[(n/2)+i][(m/2)+j] = arr[i][(m/2)+j];
					arr[i][(m/2)+j] = temp;
				}
			}
		}else {
			for(int i=0; i<m/2; i++) {
				for(int j=0; j<n/2; j++) {
					int temp = Rarr[i][j];
					Rarr[i][j] = Rarr[(m/2)+i][j];
					Rarr[(m/2)+i][j] = Rarr[(m/2)+i][(n/2)+j];
					Rarr[(m/2)+i][(n/2)+j] = Rarr[i][(n/2)+j];
					Rarr[i][(n/2)+j] = temp;
				}
			}
		}
		
	}
	public static void groupL() {
		if(Rcnt%2==0) {
			for(int i=0; i<n/2; i++) {
				for(int j=0; j<m/2; j++) {
					int temp = arr[i][j];
					arr[i][j] = arr[i][(m/2)+j];
					arr[i][(m/2)+j] = arr[(n/2)+i][(m/2)+j];
					arr[(n/2)+i][(m/2)+j] = arr[(n/2)+i][j];
					arr[(n/2)+i][j] = temp;
				}
			}
		}else {
			for(int i=0; i<m/2; i++) {
				for(int j=0; j<n/2; j++) {
					int temp = Rarr[i][j];
					Rarr[i][j] = Rarr[i][(n/2)+j];
					Rarr[i][(n/2)+j] = Rarr[(m/2)+i][(n/2)+j];
					Rarr[(m/2)+i][(n/2)+j] = Rarr[(m/2)+i][j];
					Rarr[(m/2)+i][j] = temp;
				}
			}
		
		}
	}
	
	public static void printAll() {
		if(Rcnt%2==0) {
			for(int i=0; i<n; i++) {
				for(int j=0; j<m; j++) {
					System.out.print(arr[i][j]+" ");
				}
				System.out.println();
			}
		}else {
			for(int i=0; i<m; i++) {
				for(int j=0; j<n; j++) {
					System.out.print(Rarr[i][j]+" ");
				}
				System.out.println();
			}
		}
		
	}
}
