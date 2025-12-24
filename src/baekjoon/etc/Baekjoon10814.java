package baekjoon.etc;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Baekjoon10814 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        Member[] members = new Member[n];
        for(int i=0; i<n; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            members[i] = new Member(i, Integer.parseInt(st.nextToken()), st.nextToken());
        }

        Arrays.sort(members, Comparator.comparing((Member m) -> m.getAge())
                .thenComparing(m -> m.getRegit()));


        for(int i=0; i<n; i++){
            System.out.println(members[i].getAge() + " " + members[i].getName());
        }
    }
}


class Member{
    private int regit;
    private int age;
    private String name;

    public Member(int regit, int age, String name){
        this.regit = regit;
        this.age = age;
        this.name = name;
    }

    public int getAge(){
        return this.age;
    }

    public int getRegit(){
        return this.regit;
    }

    public String getName() {
        return name;
    }
}