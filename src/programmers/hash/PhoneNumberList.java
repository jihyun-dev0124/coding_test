package programmers.hash;

import java.util.Arrays;

public class PhoneNumberList {
    public static void main(String[] args) {
        boolean result = solution(new String[]{"12","111","88","123","1235"});
        System.out.println("result = " + result);
    }

    public static boolean solution(String[] phone_book) {
        Arrays.sort(phone_book);

        for (int i = 0; i < phone_book.length - 1; i++) {
            if (phone_book[i + 1].startsWith(phone_book[i])) {
                return false;
            }
        }

        return true;
    }
}
