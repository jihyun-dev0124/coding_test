package hackerRank.simpleTextEditor;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 * Coupon Stock Allocator (Apply / Cancel)
 *
 * Commands:
 *  STOCK sku qty (쿠폰 재고 초기화, 갱신)
 *  APPLY orderId sku qty    -> print "OK" or "REJECT" (해당 주문에 쿠폰 qty개 적용, 재고 차감 -> 재고 부족이면 reject, 성공이면 ok)
 *  CANCEL orderId           -> print "OK" or "NOOP".  (해당 주문에 저장된 모든 쿠폰 재고 원복, ok 출력 / 존재하지 않는 order면 noop)
 *  QUERY sku                -> print remaining stock (int) (sku 재고 출력)
 *
 * Notes / Constraints:
 *  - 1 <= Q <= 200_000
 *  - sku, orderId are non-empty strings without spaces
 *  - qty: 0..1_000_000
 *  - Atomicity: APPLY must be all-or-nothing (if insufficient stock -> no state change)
 */
public class CouponStockAllocator {
    static Map<String, Coupon> coupons = new HashMap<>();
    static Map<String, Order> orders = new HashMap<>();

    interface CouponAllocator {
        void stock(String sku, int qty);
        String apply(String orderId, String sku, int qty);
        String cancel(String orderId);
        int query(String sku);
    }

    static class CouponAllocatorImpl implements CouponAllocator {
        @Override
        public void stock(String sku, int qty) {
            Coupon coupon = coupons.get(sku);
            if (coupon == null) {
                coupons.put(sku, new Coupon(sku, qty));
            }else{
                coupon.qty = qty;
            }
        }

        @Override
        public String apply(String orderId, String sku, int qty) {
            Order order = orders.computeIfAbsent(orderId, Order::new);

            Coupon coupon = coupons.get(sku);
            if(coupon==null || coupon.qty < qty) return "REJECT";

            coupon.qty -= qty;

            Coupon cur = order.coupons.get(sku);
            if(cur==null) order.coupons.put(sku, new Coupon(sku, qty));
            else cur.qty += qty;

            return "OK";
        }

        @Override
        public String cancel(String orderId) {
            Order order = orders.get(orderId);
            if(order==null || order.coupons.isEmpty()) return "NOOP";

            for (Coupon c : order.coupons.values()) {
                coupons.computeIfAbsent(c.sku, k -> new Coupon(c.sku, 0)).qty += c.qty;
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

        public Coupon(String sku, int qty) {
            this.sku = sku;
            this.qty = qty;
        }
    }

    static class Order{
        private String orderId;
        private Map<String, Coupon> coupons;

        public Order(String orderId){
            this.orderId = orderId;
            this.coupons = new HashMap<>();
        }

        public Order(String orderId, Map<String, Coupon> coupons) {
            this.orderId = orderId;
            this.coupons = coupons;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        CouponAllocator couponAllocator = new CouponAllocatorImpl();
        StringBuilder out = new StringBuilder();

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String value = st.nextToken();
            if (value.equals("STOCK")) {
                couponAllocator.stock(st.nextToken(), Integer.parseInt(st.nextToken()));
            } else if (value.equals("APPLY")) {
                String result = couponAllocator.apply(st.nextToken(), st.nextToken(), Integer.parseInt(st.nextToken()));
                out.append(result).append("\n");
            } else if(value.equals("CANCEL")) {
                String result = couponAllocator.cancel(st.nextToken());
                out.append(result).append("\n");
            } else if(value.equals("QUERY")) {
                int result = couponAllocator.query(st.nextToken());
                out.append(result).append("\n");
            }
        }

        System.out.println(out);
    }
}

/**
10
STOCK A 5
APPLY O1 A 3
QUERY A
APPLY O2 A 3
CANCEL O1
QUERY A
APPLY O2 A 3
QUERY A
CANCEL O2
QUERY A

 */