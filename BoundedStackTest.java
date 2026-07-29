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
        testPush();
        testPop();
        testObserver();
        testProducer();

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
        check("new() -> contains nothing", !empty.contains("anything"));

        BoundedStack b = new BoundedStack(Arrays.asList("A","B","C","D"),50);
        check("new() -> size 4", b.size()==4);
        check("new() -> contain D", b.contains("D"));
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
        check("push(A) -> return true", b.push("A"));
        check("push(B) -> return true", b.push("B"));
        check("push(A,B) -> size 2", b.size() == 2);
        check("push(B) -> found by contains", b.contains("B"));

        b.push("C");
        check("push preserves insertion order", b.book().equals(Arrays.asList("A","B","C")));

        //เพลงซ้ำ คืน false
        check("push duplicate -> returns false ", !b.push("A"));
        check("failed push leaves size unchanged", b.size() == 3);

        //input ผิดเงื่อนไข ติด Exception
        boolean threwEmpty = true;
        try {
            b.push("");
        } catch (IllegalArgumentException e) {
            threwEmpty = true;
        }
        check("push(empty string) -> throws IllegalArgumentException", threwEmpty);

        boolean threwNull = false;
        try {
            b.push(null);
        } catch (IllegalArgumentException e) {
            threwNull = true;
        }
        check("push(null) -> throws IllegalArgumentException", threwNull);

        check("failed adds leave playlist unchanged", b.size() == 3);

        //boundary : เติมจนเต็มแล้วเติมเพิ่ม
        BoundedStack full = new BoundedStack(50);
        for (int i = 0 ; i < full.capacity();i++){
            full.push("book"+i);
        }
        check("can fill up to capacity", full.size() == full.capacity());
        check("push when full -> returns false", !full.push("one more"));
        check("full books stays at capacity",
                full.size() == full.capacity());
    }

    // --- Mutator : pop ทั้งกรณีพบและไม่พบ
    private static void testPop(){
        System.out.println("\n--- Pop ---");

        BoundedStack b = new BoundedStack(Arrays.asList("A", "B", "C"),50);
        check("pop() -> returns true", b.pop());
        check("pop -> size decreases", b.size() == 2);
        check("pop -> book is gone", !b.contains("C"));
        check("pop keeps the others in order",
                b.book().equals(Arrays.asList("A", "B")));
        
        // ลบหนังสือที่ไม่มี — คืน false เฉย ๆ
        check("pop on empty BoundedStack -> returns false", !b.pop());
        check("failed pop leaves size unchanged", b.size() == 2);

        // boundary : ลบจนหมด
        b.pop();
        b.pop();
        check("pop all -> empty", b.size() == 0);
        check("pop on empty BoundedStack -> returns false", !b.pop());
    }

    // --- Observer ต้องไม่มี side effect ---
    private static void testObserver() {
        System.out.println("\n--- Observer ---");

        BoundedStack b = new BoundedStack(Arrays.asList("A", "B"), 50);
        check("size reports 2", b.size() == 2);
        check("contains finds an existing book", b.contains("A"));
        check("contains rejects a missing book", !b.contains("Z"));
        check("songs returns the full list in order",
                b.book().equals(Arrays.asList("A", "B")));

        int before = b.size();
        b.size();
        b.contains("A");
        b.book();
        check("observers have no side effects", b.size() == before);
    }

    // --- Producer ต้องคืนตัวใหม่ ไม่แก้ตัวเดิม ---
    private static void testProducer() {
        System.out.println("\n--- Producer (shuffled) ---");

        BoundedStack original = new BoundedStack(Arrays.asList("A", "B", "C", "D"),50);
        BoundedStack reverse = original.reverse();

        check("reverse has the same size", reverse.size() == original.size());

        List<String> a = new ArrayList<String>(original.book());
        List<String> b = new ArrayList<String>(reverse.book());
        Collections.sort(a);
        Collections.sort(b);
        check("reverse contains exactly the same book", a.equals(b));

        check("reverse does not mutate the original",
                original.book().equals(Arrays.asList("A", "B", "C", "D")));

        // mutate ตัวใหม่ต้องไม่กระทบตัวเดิม
        reverse.push("E");
        check("mutating the result does not affect the original",
                original.size() == 4);

        // boundary: shuffle เพลย์ลิสต์ว่างต้องไม่พัง
        BoundedStack emptyReverse = new BoundedStack(50).reverse();
        check("reverse an empty listBooks is safe", emptyReverse.size() == 0);
    }

    // --- ทดสอบว่าไม่เกิด representation exposure ---
    private static void testExposure() {
        
    }
}
