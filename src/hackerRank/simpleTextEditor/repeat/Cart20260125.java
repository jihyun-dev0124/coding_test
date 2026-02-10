package hackerRank.simpleTextEditor.repeat;

import java.util.*;

public class Cart20260125 {
    static final Map<String, Integer> cart = new HashMap<>();
    static final Deque<Undo> undoStack = new ArrayDeque<>();
    static int total=0;

    static class Undo{
        String name;
        int stock;
        boolean existed;
         public Undo(String name, int stock, boolean existed) {
             this.name = name;
             this.stock = stock;
             this.existed = existed;
         }
    }

    interface Processor {
        void add(String key, int value);
        void remove(String key, int value);
        int get(String key);
        int total();
        void undo();
    }

    static class CommandProcessor implements Processor {
        @Override
        public void add(String key, int value) {
            Integer cur = cart.get(key);
            boolean existed = cur != null;
            int curStock = cur == null ? 0 : cur;
            int add = value + curStock;

            cart.put(key, add);
            undoStack.push(new Undo(key, curStock, existed));

            total += value;
        }

        @Override
        public void remove(String key, int value) {
            Integer cur = cart.get(key);
            if(cur == null) return;

            int del = cur - value;
            if(del < 0){
                cart.remove(key);
                total -= cur;
            }else{
                cart.put(key, del);
                total -= value;
            }

            undoStack.push(new Undo(key, cur, true));
        }

        @Override
        public int get(String key) {
            Integer cur = cart.get(key);
            return cur == null ? 0 : cur;
        }

        @Override
        public int total() {
            return total;
        }

        @Override
        public void undo() {
            if(undoStack.isEmpty()) return;
            Undo undo = undoStack.pop();
            Integer cur = cart.get(undo.name);
            if (cur != null) {
                if (!undo.existed) {
                    cart.remove(undo.name);
                    total -= cur;
                }else{
                    cart.put(undo.name, undo.stock);
                    total += (undo.stock - cur);
                }
            }else {
                cart.put(undo.name, undo.stock);
                total += undo.stock;
            }
        }
    }


    public static void main(String[] args) {
        Processor processor = new CommandProcessor();
        processor.add("A", 128);
        processor.add("B", 200);
        System.out.println(processor.total()); // 328

        processor.remove("A", 130);
        System.out.println(processor.get("A")); // 0
        System.out.println(processor.total());  // 200

        processor.undo();
        System.out.println(processor.get("A")); //128
        System.out.println(processor.total());  //328


    }
}
