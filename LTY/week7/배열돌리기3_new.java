package week7;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 배열돌리기3_new {

    static int N,M,R;
    static int arr[][]; //2차원 배열 생성
    static int operation[]; //연산 종류를 받아주는 1차원 배열 생성
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer  st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); //가로
        M =Integer.parseInt(st.nextToken()); //세로
        R = Integer.parseInt(st.nextToken());  //수행해야 할 연산의 수

        arr = new int[N][M];
        operation = new int[R];
        for(int i=0;i<N;i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<M;j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        } //배열 입력

        st = new StringTokenizer(br.readLine()); //연산 종류 입력받기
        for(int i=0;i<R;i++) {
            operation[i] = Integer.parseInt(st.nextToken());
        }


        for(int i=0;i<R;i++) {
            if(operation[i] == 1)
                func1();
            else if(operation[i] == 2)
                func2();
            else if(operation[i] == 3)
                func3();
            else if(operation[i] == 4)
                func4();
            else if(operation[i] == 5)
                func5();
            else if(operation[i] == 6)
                func6();


        }


        //결과 출력
       for(int i=0;i<arr.length;i++) {
            for(int j=0;j<arr[0].length;j++)
                System.out.print(arr[i][j]+" ");
            System.out.println();
        }

    }

    static void func1() {
        //상하 반전
        N = arr.length;
        M =arr[0].length;
        int temp[] = new int[M];
        for(int i=0;i<N/2;i++) { //반으로 쪼개서 진행
            temp = arr[i]; //따로 저장해서 넣어주는 방식
            arr[i] = arr[(N-1)-i];
            arr[(N-1)-i] = temp;
        }
    }

    static void func2() {
        //좌우 반전
        N = arr.length;
        M =arr[0].length;
        int temp[] = new int[N];
        for(int i=0;i<M/2;i++) {
            for(int j=0;j<N;j++) {
                temp[j] = arr[j][i]; //따로 저장해서 넣어주는 방식
                arr[j][i] = arr[j][(M-1)-i];
                arr[j][(M-1)-i] = temp[j];
            }
        }
    }

    static void func3() {
        //오른쪽 90도 회전
        func4(); //왼쪽 90도 회전
        func1(); // 상하반전
        func2(); //좌우반전
    }
    static void func4() {
        N = arr.length;
        M = arr[0].length;
        int arr2[][] = new int[M][N]; //가로 세로 반전
        int temp[]; //가로 배열 저장할 temp

        for(int i=0;i<N;i++) {
            int cnt = 0;
            temp = arr[i]; //본래 배열의 첫번째 가로줄
            for(int j=M-1;j>=0;j--) {
                //첫번째 가로줄은 N에 들어간다.
                //마지막 가로줄은 0에 들어간다.
                arr2[j][i] = temp[cnt++];
            }
        }//배열 생성 끝

        arr = new int[M][N]; //새로 생성 해주기
        for(int i=0;i<M;i++)
            arr[i] = arr2[i];
    }
    static void func5() {

        //배열을 4구획으로 나눈 후 옮기기ㅣ
        //1->2, 2->3, 3->4, 4->1이동
        N = arr.length;
        M = arr[0].length;
        int arr2[][] =new int[N][M];

        for(int i=0;i<N;i++) {
            for(int j=0;j<M;j++) {
                if(i<=(N/2-1) && j<=(M/2-1))
                    arr2[i][j] = arr[i+(N/2)][j];//1번 구역에는 4번 넣어주기
                else if(i<=(N/2-1) && j>=(M/2))
                    arr2[i][j] = arr[i][j-(M/2)];//2번 구역에는 1번 넣어주기
                else if(i>=(N/2) && j<=(M/2-1))
                    arr2[i][j] = arr[i][j+(M/2)];//4번 구역에는 3번 넣어주기
                else if(i>=(N/2) && j>=(M/2))
                    arr2[i][j] = arr[i-(N/2)][j];//3번 구역에는 2번 넣어주기

            }
        }

        for(int i=0;i<N;i++)
            arr[i] = arr2[i];

    }

    static void func6() {
        //1->4
        //4->3 3->2 2->1
        N = arr.length;
        M = arr[0].length;
        int arr2[][] =new int[N][M];
        for(int i=0;i<N;i++) {
            for(int j=0;j<M;j++) {
                if(i<=(N/2-1) && j<=(M/2-1))
                    arr2[i][j] = arr[i][j+(M/2)];//1번 구역에는 2번 넣어주기
                else if(i<=(N/2-1) && j>=(M/2))
                    arr2[i][j] = arr[i+(N/2)][j];//2번 구역에는 3번 넣어주기
                else if(i>=(N/2) && j<=(M/2-1))
                    arr2[i][j] = arr[i-(N/2)][j];//4번 구역에는 1번 넣어주기
                else if(i>=(N/2) && j>=(M/2))
                    arr2[i][j] = arr[i][j-(M/2)];//3번 구역에는 4번 넣어주기

            }
        }
        for(int i=0;i<N;i++)
            arr[i] = arr2[i];
    }
}