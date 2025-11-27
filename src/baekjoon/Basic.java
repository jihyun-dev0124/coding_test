package baekjoon;

import java.io.*;
import java.util.StringTokenizer;

public class Basic {
    public static void main(String[] args) throws IOException {
        String result = "";

        int[] monthDays = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int month = Integer.parseInt(st.nextToken());
        int day = Integer.parseInt(st.nextToken());

        int numberOfday = 0;
        for(int i=0; i<monthDays.length; i++){
            int realMonth = i + 1;
            if(month > realMonth){
                numberOfday += monthDays[i];
                continue;
            }
            numberOfday += day;
            break;
        }

        switch(numberOfday % 7){
            case 0 : result = "SUN"; break;
            case 1 : result = "MON"; break;
            case 2 : result = "TUE"; break;
            case 3 : result = "WED"; break;
            case 4 : result = "THU"; break;
            case 5 : result = "FRI"; break;
            case 6 : result = "SAT"; break;
        }

        System.out.println(result);
    }
}
