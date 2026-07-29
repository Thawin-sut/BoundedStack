import java.lang.reflect.Array;
import java.util.*;

public class BoundedStackTest { 
    
    private static int passed = 0;
    private static int failed = 0;

    /** helper กลาง — พิมพ์ PASS/FAIL และนับผลให้เอง */
    private static void check(String name, boolean condition) {
        if (condition) {
            passed++;
            System.out.println("[PASS] " + name);
        } else {
            failed++;
            System.out.println("[FAIL] " + name);
        }
    }

    public static void main(String[] args) {
        boolean assertsOn = false;
        assert assertsOn = true;
        if (!assertsOn) {
            System.out.println("WARNING: assertions disabled"
                    + " - re-run with: java -ea PlaylistTest\n");
        }

        System.out.println("Program Test");

        testCreator();


        System.out.println("\n=== Summary ===");
        System.out.println("Passed: " + passed);
        System.out.println("Failed: " + failed);
        System.out.println("Total : " + (passed + failed));
        System.out.println(failed == 0 ? "ALL TESTS PASSED" : "SOME TESTS FAILED");

        if (failed > 0) {
            System.exit(1);
        }
        
        
    }

    private static void testCreator(){
        System.out.println("Creator");   

        BoundedStack empty = new BoundedStack(50);
        check("new() -> empty", empty.size() == 0);
        check("new() -> contains nothing", !empty.contain("anything"));

        check("new() -> push book", empty.push("A"));
        check("new() -> push book", empty.push("B"));
        check("new() -> push book", empty.push("C"));
        check("new() -> size 3 ", empty.size() == 3);
        check("new() -> contains A", empty.contain("A"));
        //check("new() -> pop book", empty.pop());

        BoundedStack b = new BoundedStack(Arrays.asList("A","B","C","D"),50);
        check("new() -> size 4", b.size()==4);
        check("new() -> contain D", b.contain("D"));
        check("new() -> preserves order", 
                b.book().equals(Arrays.asList("A","B","C","D")));

        //boundary : list ว่าง
        BoundedStack fromEmpty = new BoundedStack(Arrays.asList(), 50);
        check("new(empty list) -> empty", fromEmpty.size() == 0);
        
        


        // input ผิด
        /*boolean throwDup = false;
        try {
            new BoundedStack(50);

        } catch (Exception e) {
            // TODO: handle exception
        }*/
    }

}
