package programmers.stackAndQueue;

import java.util.ArrayDeque;
import java.util.Deque;

public class TruckCrossingBridge {
    public static void main(String[] args) {
        int solution = solution(100, 100, new int[]{10});
        System.out.println("solution = " + solution);
    }

    public static int solution(int bridge_length, int weight, int[] truck_weights) {
        int time = 0;
        int bridgeTruckWeight = 0;

        Deque<Truck> bridgeTrucks = new ArrayDeque<>();
        Deque<Truck> waitTrucks = new ArrayDeque<>();
        for (int truckWeight : truck_weights) {
            waitTrucks.offer(new Truck(truckWeight));
        }

        while (!waitTrucks.isEmpty() || !bridgeTrucks.isEmpty()) {
            time++;
            if (bridgeTrucks.isEmpty()) {
                Truck truck = waitTrucks.poll();
                truck.moving();
                bridgeTrucks.offer(truck);
                bridgeTruckWeight += truck.getWeight();
                continue;
            }

            Truck waitTruck = waitTrucks.peek();
            if (waitTruck != null &&
                    weight >= bridgeTruckWeight + waitTruck.getWeight() && bridge_length > bridgeTrucks.size()) {
                bridgeTruckWeight += waitTruck.getWeight();
                bridgeTrucks.offer(waitTrucks.poll());
            }

            for (Truck bridgeTruck : bridgeTrucks) {
                bridgeTruck.moving();
            }

            Truck bridgeTruck = bridgeTrucks.peek();
            if (bridgeTruck.getMove() >= bridge_length) {
                bridgeTruckWeight -= bridgeTruck.getWeight();
                bridgeTrucks.poll();
            }
        }
        return time + 1;
    }

}

class Truck{
    private int weight;
    private int move;

    public Truck(int weight) {
        this.weight = weight;
    }
    public void moving() {
        move++;
    }
    public int getWeight(){
        return weight;
    }
    public int getMove() {
        return move;
    }
}
