package level1;

import java.time.format.DateTimeFormatter;

public class Flextime {
    public static void main(String[] args) {
        int [] schedules = {750, 855, 700, 720};
        int[][] timelogs = {{710, 700, 650, 735, 700, 931, 912}, {908, 901, 805, 815, 800, 831, 835}, {705, 701, 702, 705, 710, 710, 711}, {707, 731, 859, 913, 934, 931, 905}};
        int result = solution(schedules, timelogs, 1);
    }

    public static int solution(int[] schedules, int[][] timelogs, int startday) {
        int answer = 0;

        for (int i = 0; i < timelogs.length; i++) {
            boolean beLate = false;
            int schedule = getScheduleTime(schedules[i]);

            for (int j = 0; j < timelogs[i].length; j++) {
                int date = (startday + j) % 7;
                if(date == 0 || date == 6) continue;
                int time = timelogs[i][j];

                if (time > schedule) {
                    beLate = true;
                    break;
                }
            }

            if(beLate) continue;

            answer++;
        }

        return answer;
    }

    private static int getScheduleTime(int schedules) {
        int hour = schedules / 100;
        int minute = (schedules % 100) + 10;
        hour += minute / 60;
        minute = minute % 60;
        return hour * 100 + minute;
    }
}
