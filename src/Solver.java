import java.io.File;
import java.lang.reflect.Method;
import java.util.Scanner;

public class Solver {
    public static void main(String[] args) throws Exception {
        int[] days = new int[] {1};
        boolean[] parts = new boolean[] { true, false };

        for (int day : days) {
            for (boolean part : parts) {
                File input = new File("src/day" + day + "/input");
                Scanner in = new Scanner(input);
                Class<?> cls = Class.forName("day" + day + ".Day" + day);
                Method m = cls.getDeclaredMethod("solve", boolean.class, Scanner.class);
                String answer = (String) m.invoke(cls.getDeclaredConstructor().newInstance(), part, in);
                System.out.println(
                        "Day " + day + " part " + (part ? 1 : 2) + " solution: " + answer);
                in.close();
            }
        }
    }
}
