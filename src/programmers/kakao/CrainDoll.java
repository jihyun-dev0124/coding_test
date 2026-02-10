package programmers.kakao;
import java.util.*;

public class CrainDoll {
    public static void main(String[] args) {

    }

    public static int solution(int[][] board, int[] moves) {
        Game game = new Game(board, moves);
        game.play();
        return game.getRemoveCnt();
    }

    static class Game{
        private Map<Integer, Deque<Integer>> grid;
        private int[] moves;
        private Deque<Integer> bucket;
        private int removeCnt;

        public Game(int[][] board, int[] moves){
            this.grid = new HashMap<>();
            makeGrid(board);
            this.moves = moves;
            this.removeCnt = 0;
            this.bucket = new ArrayDeque<>();
        }

        public void play(){
            for(int m : moves){
                Deque<Integer> dolls = grid.get(m);
                if(dolls.isEmpty()) continue;
                int doll = dolls.poll();
                System.out.println("m = " + m + ", doll = " + doll);
                if(!bucket.isEmpty() && bucket.peek() == doll){
                    bucket.pop();
                    removeCnt+=2;
                    continue;
                }

                bucket.push(doll);
            }
        }

        public int getRemoveCnt(){
            return this.removeCnt;
        }

        private void makeGrid(int[][] board){
            for(int i=0; i<board.length; i++){
                for(int j=0; j<board.length; j++){
                    int doll = board[i][j];
                    if(doll == 0) continue;
                    grid.computeIfAbsent(j+1, k -> new ArrayDeque<>()).offer(doll);
                    System.out.println(j+1 + " : " + doll);
                }
            }
        }




    }
}
