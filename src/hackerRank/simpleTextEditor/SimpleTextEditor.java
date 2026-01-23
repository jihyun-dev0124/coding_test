package hackerRank.simpleTextEditor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class SimpleTextEditor {
    static class UndoOp {
        int type;
        int len;
        String deleted;

        static UndoOp undoAppend(int len){
            UndoOp op = new UndoOp();
            op.type = 1;
            op.len = len;
            return op;
        }

        static UndoOp undoDelete(String deleted) {
            UndoOp op = new UndoOp();
            op.type = 2;
            op.deleted = deleted;
            return op;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder text = new StringBuilder();
        StringBuilder out = new StringBuilder();
        Deque<UndoOp> undoStack = new ArrayDeque<>();

        int count = Integer.parseInt(br.readLine());
        for (int i = 0; i < count; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int key = Integer.parseInt(st.nextToken());

            if (key == 1) {
                String value = st.nextToken();
                text.append(value);
                undoStack.push(UndoOp.undoAppend(value.length()));
            } else if (key == 2) {
                int value = Integer.parseInt(st.nextToken());
                int start = text.length() - value;
                String deleted = text.substring(start);
                text.delete(start, text.length());
                undoStack.push(UndoOp.undoDelete(deleted));
            } else if (key == 3) {
                int value = Integer.parseInt(st.nextToken());
                out.append(text.charAt(value - 1)).append("\n");
            } else if (key == 4) {
                UndoOp pop = undoStack.pop();
                if (pop.type == 1) {
                    int len = pop.len;
                    text.delete(text.length() - len, text.length());
                }else{
                    String append = pop.deleted;
                    text.append(append);
                }
            }
        }

        System.out.println(out);
    }
}

/*
8
1 abc
3 3
2 3
1 xy
3 2
4
4
3 1
3 1
*/
