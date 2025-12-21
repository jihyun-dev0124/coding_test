package baekjoon.fullSearch;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baekjoon1476 {
    public static void main(String[] args) throws Exception {
        int e = 15, s = 28, m = 19;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int ye = Integer.parseInt(st.nextToken()) % e;
        int ys = Integer.parseInt(st.nextToken()) % s;
        int ym = Integer.parseInt(st.nextToken()) % m;

        int year = ye == 0 ? e : ye;
        while(true){
            if((ye == year % e) && (ys == year % s) && (ym == year % m)){
                break;
            }
            year+=e;
        }

        System.out.println(year);
    }
}
