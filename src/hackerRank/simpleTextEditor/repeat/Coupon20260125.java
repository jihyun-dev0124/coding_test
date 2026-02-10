package hackerRank.simpleTextEditor.repeat;

import java.util.HashMap;
import java.util.Map;

public class Coupon20260125 {
    static final Map<String, Coupon> coupons = new HashMap<>();
    static final Map<String, Order> orders = new HashMap<>();

    interface CouponAllocater{
        void stock(String sku, int qty); //쿠폰 재고 초기화
        String apply(String orderId, String sku, int qty); //return "OK","REJECR", orderId에 sku 쿠폰 qty개 추가, 쿠폰 재고 부족하면 reject
        String cancel(String orderId); //return "OK", "NOOP"  주문 취소, 해당 주문에 적용된 쿠폰 재고 원복, 존재하지 않는 주문이면 NOOP
        int query(String sku); //쿠폰 재고 출력
    }

    static class CouponAllocaterImpl implements CouponAllocater{
        @Override
        public void stock(String sku, int qty) {
            coupons.computeIfAbsent(sku, Coupon::new).qty = qty;
        }

        @Override
        public String apply(String orderId, String sku, int qty) {
            /*Order order = orders.get(orderId);
            if(order==null){
                order = orders.computeIfAbsent(orderId,o -> new Order(orderId));
            }*/
            Order order = orders.computeIfAbsent(orderId, Order::new);

            Coupon coupon = coupons.get(sku);
            if(coupon==null || coupon.qty < qty) return "REJECT";

            coupon.qty -= qty;
            order.coupons.computeIfAbsent(sku, Coupon::new).qty += qty;

            return "OK";
        }

        @Override
        public String cancel(String orderId) {
            Order order = orders.get(orderId);
            if(order == null) return "NOOP";
            if(order.coupons.isEmpty()) return "OK";

            for (Coupon coupon : order.coupons.values()) {
                coupons.computeIfAbsent(coupon.sku, Coupon::new).qty += coupon.qty;
            }

            orders.remove(orderId);

            return "OK";
        }

        @Override
        public int query(String sku) {
            Coupon coupon = coupons.get(sku);
            return coupon == null ? 0 : coupon.qty;
        }
    }

    static class Coupon{
        private String sku;
        private int qty;

        public Coupon(String sku) {
            this.sku = sku;
            this.qty = 0;
        }
    }

    static class Order{
        private String orderId;
        private Map<String, Coupon> coupons;


        public Order(String orderId) {
            this.orderId = orderId;
            this.coupons = new HashMap<>();
        }
    }

    public static void main(String[] args) {
        CouponAllocater ca = new CouponAllocaterImpl();
        ca.stock("C001", 10);
        ca.apply("O001", "C001", 1);
        System.out.println("query = " + ca.query("C001")); // 9
        ca.apply("O001", "C001", 3);
        System.out.println("query = " + ca.query("C001")); // 6
        System.out.println("====================");

        String result = ca.apply("O001", "C001", 10); //쿠폰 재고 초과
        System.out.println("result = " + result); // REJECT
        System.out.println("query = " + ca.query("C001")); // 9
        System.out.println("====================");

        String result2 = ca.apply("O001", "C003", 10); //없는 쿠폰
        System.out.println("result2 = " + result2); // REJECT
        System.out.println("query = " + ca.query("C001")); // 9
        System.out.println("====================");

        String cancel = ca.cancel("O001");
        System.out.println("cancel = " + cancel); //OK
        System.out.println("query = " + ca.query("C001")); //쿠폰 10
        System.out.println("====================");

        String cancel2 = ca.cancel("O001"); //없는 주문 삭제
        System.out.println("cancel2 = " + cancel2); //REJECT

        System.out.println("====================");
        ca.stock("C001", 3); //쿠폰 재고 초기화
        System.out.println("query = " + ca.query("C001")); //3
    }
}
