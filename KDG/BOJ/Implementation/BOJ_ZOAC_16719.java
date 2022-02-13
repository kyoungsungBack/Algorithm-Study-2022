import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * ZOAC - 골드5
 * 구현
 * 풀이시간 : 
 * @author USER
 *
 */
public class Main {
	static String str;
	static char[] orgArr;
	static boolean[] check;
	static StringBuilder sb = new StringBuilder();
	static class where16719{
		int idx;
		int dic;
		public where16719(int idx, int dic) {
			this.idx = idx;
			this.dic = dic;
		}
	}
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		str = br.readLine();
		orgArr = str.toCharArray();
		check = new boolean[str.length()];
		
		for(int i=0; i<str.length(); i++)
			sb.append("a");
		
		compStr();
		//System.out.println(sb.toString());
	}
	
	public static void compStr() {
		where16719 w = new where16719(0, 0);
		//!sb.toString().equals(str)
		
		while(!sb.toString().equals("aTARTLINK")) {
			if(w.dic>=0) w = rightComp(w.idx);
			else w = leftComp(w.idx);
			System.out.println(sb.toString());
			
		}
	}
	
	public static where16719 rightComp(int idx) {
		where16719 w = null;
		String str1 = "a";
		String str2 = null;
		int com = 0;
		int ridx = 0;
		
		for(int i=idx; i<str.length(); i++) {
			if(check[i]) continue;
			str2 = String.valueOf(orgArr[i]);
			com = str2.compareTo(str1);
			
			if(com<0) {
				str1 = str2;
				ridx = i;
			}
		}
		check[ridx] = true;
		sb.replace(ridx, ridx+1, str1);
		w = new where16719(ridx,checkRight(ridx));
		
		return w;
	}
	
	
	public static where16719 leftComp(int idx) {
		where16719 w = null;
		String str1 = "a";
		String str2 = null;
		int com = 0;
		int ridx = 0;
		int lidx = checkLeft(idx);
		for(int i=idx; i>=lidx; i--) {
			if(check[i]) continue;
			str2 = String.valueOf(orgArr[i]);
			com = str2.compareTo(str1);
			
			if(com<0) {
				str1 = str2;
				ridx = i;
			}
		}
		
		check[ridx] = true;
		sb.replace(ridx, ridx+1, str1);
		w = new where16719(ridx, checkRight(ridx));
		
		return w;
	}
	
	public static int checkRight(int idx) {
		for(int i=idx; i<check.length; i++) {
			if(!check[i]) return 1;
		}
		return -1;
	}
	
	public static int checkLeft(int idx) {
		for(int i=idx; i>=0; i--) {
			if(check[i]) return i;
		}
		return 0;
	}
}
