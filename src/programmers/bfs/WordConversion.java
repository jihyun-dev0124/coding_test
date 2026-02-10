package programmers.bfs;

import java.util.ArrayDeque;
import java.util.Queue;

public class WordConversion {

    public static void main(String[] args) {
        int solution = solution("hit", "cog", new String[]{"hot", "dot", "dog", "lot", "log", "cog"});
        //int solution = solution("hit", "cog", new String[]{"hot", "dot", "dog", "lot", "log"});
        System.out.println("solution = " + solution);
    }

    public static int solution(String begin, String target, String[] words){
        boolean[] visited = new boolean[words.length];

        Queue<Node> q = new ArrayDeque<>();
        q.offer(new Node(begin, 0));

        while (!q.isEmpty()) {
            Node cur = q.poll();
            if(cur.getNext().equals(target)) return cur.getCnt();

            for (int i = 0; i < words.length; i++) {
                String word = words[i];
                if(visited[i] || !isOneCharDiffrent(cur.getNext(), word)) continue;

                visited[i] = true;
                q.offer(new Node(word, cur.getCnt() + 1));
            }
        }

        return 0;
    }

    public static boolean isOneCharDiffrent(String cur, String word){
        int diffCount = 0;
        for (int i = 0; i < word.length(); i++) {
            if (cur.charAt(i) != word.charAt(i)) {
                diffCount++;
                if(diffCount > 1) return false;
            }
        }
        if(diffCount == 0) return false;
        return true;
    }
}

class Node{
    private String next;
    private int cnt;

    public Node(String next, int cnt) {
        this.next = next;
        this.cnt = cnt;
    }

    public String getNext() {
        return this.next;
    }

    public int getCnt() {
        return this.cnt;
    }
}