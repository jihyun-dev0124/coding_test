package baekjoon.etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Baekjoon10825 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        Member[] members = new Member[N];

        for(int i=0; i<N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            String name = st.nextToken();
            int koreanScore = Integer.parseInt(st.nextToken());
            int englishScore = Integer.parseInt(st.nextToken());
            int mathScore = Integer.parseInt(st.nextToken());

            members[i] = new Member(name, koreanScore, englishScore, mathScore);
        }

        Arrays.sort(members,
                Comparator.comparingInt(Member::getKoreanScore).reversed()
                        .thenComparingInt(Member::getEnglishScore)
                        .thenComparing(Comparator.comparingInt(Member::getMathScore).reversed())
                        .thenComparing(Member::getName)
        );

        for(int i=0; i<N; i++){
            System.out.println(members[i].getName());
        }
    }

    static class Member{
        private String name;
        private int koreanScore;
        private int englishScore;
        private int mathScore;

        public Member(String name, int koreanScore, int englishScore, int mathScore){
            this.name = name;
            this.koreanScore = koreanScore;
            this.englishScore = englishScore;
            this.mathScore = mathScore;
        }

        public String getName(){
            return this.name;
        }

        public int getKoreanScore(){
            return this.koreanScore;
        }

        public int getEnglishScore(){
            return this.englishScore;
        }

        public int getMathScore(){
            return this.mathScore;
        }


    }
}

