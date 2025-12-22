package baekjoon.fullSearch;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Baekjoon1107 {
    static boolean[] broken = new boolean[10];

    // 채널 x를 숫자 버튼으로 만들 수 있으면 자릿수(누르는 횟수) 반환, 불가능하면 -1
    static int canPressLen(int x) {
        if (x == 0) {
            return broken[0] ? -1 : 1;
        }
        int len = 0;
        while (x > 0) {
            int d = x % 10;
            if (broken[d]) return -1;
            len++;
            x /= 10;
        }
        return len;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine().trim());
        int M = Integer.parseInt(br.readLine().trim());

        if (M > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < M; i++) {
                int b = Integer.parseInt(st.nextToken());
                broken[b] = true;
            }
        }

        // 1) +/-만 사용하는 경우
        int ans = Math.abs(N - 100);

        // 2) 숫자로 누를 수 있는 채널 x를 선택 후 +/-로 보정
        for (int x = 0; x <= 999999; x++) {
            int len = canPressLen(x);
            if (len == -1) continue; // 숫자 버튼으로 입력 불가
            int cost = len + Math.abs(N - x);
            if (cost < ans) ans = cost;
        }

        System.out.println(ans);
    }

}
