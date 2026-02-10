package hackerRank.simpleTextEditor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class CartCommandProcessorWithUndo {
    static class Undo{
        String sku;
        int prevQty;
        boolean existed; //이전에 존재했는지 여부

        Undo (String sku, int prevQty, boolean existed){
            this.sku = sku;
            this.prevQty = prevQty;
            this.existed = existed;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        StringBuilder out = new StringBuilder();
        Deque<Undo> stack = new ArrayDeque<>();
        Map<String, Integer> products = new HashMap<>();
        long totalQty = 0;

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String cmd = st.nextToken();
            if(cmd.equals("ADD")){
                String sku = st.nextToken();
                int qty = Integer.parseInt(st.nextToken());

                Integer curObj = products.get(sku);
                int curQty = curObj == null ? 0 : curObj;
                boolean existed = curObj != null;

                stack.push(new Undo(sku, curQty, existed));
                products.put(sku, curQty + qty);
                totalQty += qty;

            }else if(cmd.equals("REMOVE")){
                String sku = st.nextToken();
                int qty = Integer.parseInt(st.nextToken());

                Integer curObj = products.get(sku);
                if(curObj == null) continue;
                int curQty = curObj;

                stack.push(new Undo(sku, curQty, true));

                if (curObj <= qty){
                    products.remove(sku);
                    totalQty -= curQty;
                } else {
                    products.put(sku, curQty - qty);
                    totalQty -= qty;
                }
            } else if (cmd.equals("SET")) {
                String sku = st.nextToken();
                int qty = Integer.parseInt(st.nextToken());

                Integer curObj = products.get(sku);
                if(curObj == null) continue;

                int curQty = curObj;

                stack.push(new Undo(sku, curQty, true));

                if (qty <= 0) {
                    products.remove(sku);
                    totalQty -= curQty;
                }else{
                    products.put(sku, qty);
                    totalQty += (qty - curQty);
                }
            } else if (cmd.equals("COUNT")) {
                String sku = st.nextToken();
                out.append(products.getOrDefault(sku, 0)).append('\n');

            } else if (cmd.equals("TOTAL")) {
                out.append(totalQty).append('\n');

            } else if (cmd.equals("UNDO")) {
                if(stack.isEmpty()) continue;

                Undo undo = stack.pop();
                String sku = undo.sku;
                Integer curObj = products.get(sku);
                int curQty = curObj == null ? 0 : curObj;
                boolean existedNow = curObj != null;

                if(!undo.existed){
                    if (existedNow) {
                        products.remove(sku);
                        totalQty -= curQty;
                    }
                }else{
                    if (!existedNow) {
                        products.put(sku, undo.prevQty);
                        totalQty += undo.prevQty;
                    }else{
                        products.put(sku, undo.prevQty);
                        totalQty += (undo.prevQty - curQty);
                    }
                }
            }

        }

        System.out.println(out);


        List<String>[] bucket = new List[2];

    }
  /*
9
ADD A 3
ADD B 2
TOTAL
REMOVE A 1
COUNT A
UNDO
COUNT A
SET B 0
TOTAL
*/

}
