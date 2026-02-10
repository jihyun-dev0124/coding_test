package programmers.fullSearch;

import java.util.HashSet;
import java.util.Set;

public class PrimeNumber {
    public static void main(String[] args) {
        int solution = solution("011");
        System.out.println("solution = " + solution);
    }

    public static int solution(String numbers) {
        int answer = 0;
        boolean[] visited = new boolean[numbers.length()];
        Set<Integer> results = new HashSet<>();
        dfs(results, "", numbers, visited);
        for (int number : results) {
            if(isPrime(number)) answer++;
        }
        return answer;
    }

    public static void dfs(Set<Integer> results, String current, String numbers, boolean[] visited) {
        if (!current.isEmpty()) {
            results.add(Integer.parseInt(current));
        }

        for (int i = 0; i < numbers.length(); i++) {
            if (!visited[i]) {
                visited[i] = true;
                dfs(results, current + numbers.charAt(i), numbers, visited);
                visited[i] = false;
            }
        }
    }

    public static boolean isPrime(int num) {
        if(num == 2) return true;
        if(num <= 1 || num % 2 == 0) return false;
        for (int i = 3; i <= Math.sqrt(num); i+=2) {
            if(num % i == 0) return false;
        }
        return true;
    }
}
