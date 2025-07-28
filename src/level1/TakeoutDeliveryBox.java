package level1;

public class TakeoutDeliveryBox {
    public static void main(String[] args) {
        int result = solution(22, 6, 8);
        System.out.println("result = " + result);
    }

    static int solution(int n, int w, int num) {
        int cnt = 0;
    // test
        while (num <= n) {
            int col = (num - 1) % w;
            int jump = (w - col - 1) * 2 + 1;
            num += jump;
            cnt++;
        }

        return cnt;
    }

}
