package hackerRank.simpleTextEditor.commandProcessor;


public class Cart {

    public static void main(String[] args) {
        CommandProcessor cp = new CommandProcessor();
        cp.add("A", 100);
        cp.undo();
        cp.undo();


        int a = cp.get("A");
        System.out.println("A = " + a);

        int b = cp.get("B");
        System.out.println("B = " + b);

        int c = cp.get("C");
        System.out.println("C = " + c);

        int total = cp.total();
        System.out.println("total = " + total);
    }
}
