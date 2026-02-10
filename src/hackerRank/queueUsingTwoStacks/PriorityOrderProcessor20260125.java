package hackerRank.queueUsingTwoStacks;

import org.junit.jupiter.api.Assertions;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class PriorityOrderProcessor20260125 {
    public enum Status{READY, CANCELLED};

    static class Order{
        public final String id;
        public final int priority; //1이 가장 높음.
        public final Status status;
        public Order(String id, int priority, Status status) {
            this.id = id;
            this.priority = priority;
            this.status = status;
        }
    }

    interface OrderProcessor{
        void enqueue(Order order);
        Order process(); //반환할 주문 없으면 null
    }

    static class PriorityOrderProcessor implements OrderProcessor{
        private class Node{
            final Order order;
            final long seq;
            public Node(Order order, long seq){
                this.order = order;
                this.seq = seq;
            }
        }

        private long seqGen = 0;

        private final PriorityQueue<Node> orders = new PriorityQueue<>(
                Comparator.<Node>comparingInt(n -> n.order.priority)
                        .thenComparingLong(n -> n.seq)
        );
        private final Map<String, Integer> retryOrder = new HashMap<>();

        @Override
        public void enqueue(Order order) {
            orders.offer(new Node(order, seqGen++));
        }

        @Override
        public Order process() {
            while(!orders.isEmpty()){
                Node node = orders.poll();
                Order order = node.order;

                if(order.status == Status.CANCELLED) continue;

                if (shouldFail(order)) {
                    int cnt = retryOrder.getOrDefault(order.id, 0);
                    if (cnt >= 1) {
                        retryOrder.remove(order.id);
                        continue;
                    }

                    retryOrder.put(order.id, cnt+1);
                    orders.offer(new Node(order, seqGen++));
                    continue;
                }

                retryOrder.remove(order.id);
                return order;
            }
            return null;
        }

        private boolean shouldFail(Order order){
            return false;
        }
    }

    public static void main(String[] args) {
        //주문 추가 후 정상 반환 확인
        OrderProcessor op = new PriorityOrderProcessor();
        op.enqueue(new Order("O-001", 1, Status.READY));
        Order process = op.process();
        System.out.println(process != null); //true
        System.out.println(process.id.equals("O-001")); //true
        Assertions.assertEquals(process.id, "O-001");

        System.out.println("=========================");
        //실패 주문은 반환되지 않음
        op.enqueue(new Order("O-002", 0, Status.CANCELLED));
        process = op.process();
        System.out.println(process == null); //true;
        Assertions.assertTrue(process == null);

        System.out.println("=========================");
        //순서 priority 오름차순, 같으면 FIFO
        op.enqueue(new Order("O-001", 1, Status.READY));
        op.enqueue(new Order("O-002", 2, Status.READY));
        op.enqueue(new Order("O-003", 3, Status.READY));
        op.enqueue(new Order("O-004", 1, Status.READY));
        //001 -> 004 -> 002 -> 003
        process = op.process();
        System.out.println(process.id.equals("O-001"));
        Assertions.assertEquals(process.id, "O-001");

        process = op.process();
        System.out.println(process.id.equals("O-004"));
        Assertions.assertEquals(process.id, "O-004");

        process = op.process();
        System.out.println(process.id.equals("O-002"));

        process = op.process();
        System.out.println(process.id.equals("O-003"));







    }
}
