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

    // --- Creator : ว่าง / มีหนังสือ / input ที่ผิดเงื่อนไข
    private static void testCreator(){
        System.out.println("Creator");   

        BoundedStack empty = new BoundedStack(50);
        check("new() -> empty", empty.size() == 0);
        check("new() -> contains nothing", !empty.contain("anything"));

        BoundedStack b = new BoundedStack(Arrays.asList("A","B","C","D"),50);
        check("new() -> size 4", b.size()==4);
        check("new() -> contain D", b.contain("D"));
        check("new() -> preserves order", 
                b.book().equals(Arrays.asList("A","B","C","D")));

        //boundary : list ว่าง
        BoundedStack fromEmpty = new BoundedStack(Arrays.asList(), 50);
        check("new(empty list) -> empty", fromEmpty.size() == 0);
        

        //input ที่ผิดเงื่อนไขต้องติด exception
        boolean threwDup = false;
        try {
            new BoundedStack(Arrays.asList("A","A"),50);

        } catch (IllegalArgumentException e) {
            threwDup = true;
        }
        check("new(duplicates) -> throws IllegalArgumentException", threwDup);

        boolean threwNull = false;
        try {
            new BoundedStack(Arrays.asList("A",null),50);
        } catch (IllegalArgumentException e) {
            threwNull = true;
        }
        check("new(listBooks with null) -> throws IllegalArgumentException", threwNull);

        boolean threwNullList = false;
        try {
            new BoundedStack(Arrays.asList(),50);
        } catch (IllegalArgumentException e) {
            threwNullList = true;
        }
        check("new(null) -> throws IllegalArgumentException", threwNullList);
    }

    // --- Mutator : push ต้องรักษาลำดับและห้ามหนังสือซ้ำ
    private static void testPush(){
        System.out.println("\n--- Push ---");

        BoundedStack b = new BoundedStack(50);
        check("add(A) -> return true", b.push("A"));
        check("add(B) -> return true", b.push("B"));
        check("add(A,B) -> size 2", b.size() == 2);
        check("add(B) -> found by contains", b.contain("B"));

        b.push("C");
        check("push preserves insertion order", b.book().equals(Arrays.asList("A","B","C")));

        //เพลงซ้ำ คืน false
        check("push duplicate -> returns false ", !b.push("A"));
        check("failed push leaves size unchanged", b.size() == 3);



    }

    // --- Mutator : pop ทั้งกรณีพบและไม่พบ
    private static void testPop(){
        System.out.println("\n--- Pop ---");

        
    }
}
