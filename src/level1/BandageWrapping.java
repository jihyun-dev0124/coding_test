package level1;

import java.util.Arrays;

public class BandageWrapping {
    public static void main(String[] args) {
        int result1 = solution(new int[]{5, 1, 5}, 30, new int[][]{{2, 10}, {9, 15}, {10, 5}, {11, 5}});
        int result2 = solution(new int[]{3, 2, 7}, 20, new int[][]{{1,15}, {5,16}, {8,6}});
        int result3 = solution(new int[]{4, 2, 7}, 20, new int[][]{{1,15}, {5,16}, {8,6}});
        int result4 = solution(new int[]{1, 1, 1}, 5, new int[][]{{1,2}, {3,2}});

        System.out.println("result1 = " + result1);
        System.out.println("result2 = " + result2);
        System.out.println("result3 = " + result3);
        System.out.println("result4 = " + result4);
    }

    public static int solution(int[] bandage, int health, int[][] attacks) {
        int nowHealth = health;
        int continuousTime = 0;
        int endTime = attacks[attacks.length - 1][0];
        for (int t = 1; t <= endTime; t++) {
            boolean isAttack = false;

            for (int[] attack : attacks) {
                if (attack[0] == t) {//공격
                    nowHealth -= attack[1];
                    isAttack = true;
                    break;
                }
            }

            if(isAttack){
                continuousTime = 0;
                continue;
            }

            continuousTime++;

            if (continuousTime == bandage[0]) {
                nowHealth = recovery(health, nowHealth, (bandage[1] + bandage[2]));
                continuousTime = 0;
            }else{
                nowHealth = recovery(health, nowHealth, bandage[1]);
            }

            if (nowHealth <= 0) break;
        }

        return nowHealth <= 0 ? -1 : nowHealth;
    }

    public static int recovery(int maxHealth, int nowHealth, int addHealth) {
        int totalHealth = nowHealth + addHealth;
        return Math.min(maxHealth, totalHealth);
    }

    public static int solution2(int[] bandage, int health, int[][] attacks) {
        int nowHealth = health; //현재 체력
        int continuousTime; //지속시간
        int lastAttackTime = 0; //마지막 공격 시간
        for (int[] attack : attacks) {
            continuousTime = attack[0] - lastAttackTime - 1;

            nowHealth += (continuousTime * bandage[1]);
            nowHealth += (continuousTime / bandage[0]) * bandage[2];

            nowHealth = Math.min(nowHealth, health) - attack[1];

            if (nowHealth <= 0) break;

            lastAttackTime = attack[0];
        }
        return nowHealth <= 0 ? -1 : nowHealth;
    }
}
