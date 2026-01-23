package hackerRank.simpleTextEditor.commandProcessor;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

interface Processor{
    void add(String key, int value);
    void remove(String key, int value);
    int get(String key);
    int total();
    void undo();
}

public class CommandProcessor implements Processor{
    private int total;
    private Map<String, Integer> products;
    private Deque<Undo> undoStack;

    public CommandProcessor() {
        this.total = 0;
        this.products = new HashMap<>();
        this.undoStack = new ArrayDeque<>();
    }

    private static class Undo{
        String key;
        int stock;
        boolean existed;

        public Undo(String key, int stock, boolean existed) {
            this.key = key;
            this.stock = stock;
            this.existed = existed;
        }
    }

    @Override
    public void add(String key, int value) {
        Integer curObj = products.get(key);
        int curStock = curObj == null ? 0 : curObj;
        boolean existed = curObj != null;

        products.put(key, curStock + value);
        undoStack.push(new Undo(key, curStock, existed));
        this.total += value;
    }

    @Override
    public void remove(String key, int value) {
        Integer curObj = products.get(key);
        if(curObj == null) return;

        if (curObj <= value) {
            products.remove(key);
            this.total -= curObj;
        }else{
            products.put(key, curObj - value);
            this.total -= value;
        }
        undoStack.push(new Undo(key, curObj, true));
    }

    @Override
    public int get(String key) {
        Integer curObj = products.get(key);
        return curObj == null ? 0 : curObj;
    }

    @Override
    public int total() {
        return this.total;
    }

    @Override
    public void undo() {
        if(undoStack.isEmpty()) return;

        Undo undo = undoStack.pop();

        String key = undo.key;
        Integer curObj = products.get(key);
        if (curObj == null) {
            products.put(key, undo.stock);
            this.total += undo.stock;
        }else{
            if (undo.existed) {
                products.put(key, undo.stock);
                this.total += (undo.stock - curObj);
            }else{
                products.remove(key);
                this.total -= curObj;
            }
        }
    }
}
