import java.util.*;

/**
 * BoundedStack - ADT แทนรายการหนังสือที่ผู้ใช้ต้องการอ่าน
 * 
 * ค่านามธรรม (A) : ลำดับของหนังสือที่ผู้ใช้ต้องการอ่าน เช่น [หนังสือA, หนังสือB, หนังสือC]
 * 
 */
public class BoundedStack {

    private final List<String> listBooks; //private final String[] elements;
    private final int capacity ;

    //  representation

    //Abstraction Function:
    //  AF(listBooks) = รายการหนังสือที่ผู้ใช้ต้องการอ่านตามลำดับใน listBooks

    //Representation Invariant :
    //  listBooks ต้องไม่เป็น null //
    //  จำนวนหนังสือใน listBooks ต้องมากกว่าหรือเท่ากับ 0 //
    //  ไม่มีหนังสือเป็น null //
    //  ไม่มีชื่อหนังสือเป็นสตริงว่าง //
    //  ชื่อหนังสือต้องไม่ยาวเกิน 100 ตัวอักษร //
    //  ชื่อหนังสือไม่ซ้ำกัน
    //  มีหนังสือใน listBooks ได้ไม่เกิน capacity //

    //Safety from rep exposure:
    //  listBooks เป็น private final
    //  คัดลอกข้อมูลทั้งขาเข้าขาออก

    //CheckRep
    private void checkrep(){
        assert listBooks != null : "Listbooks is not null";
        assert listBooks.size() >= 0 : "Listbooks มากกว่า 0";
        assert listBooks.size() <= this.capacity : "มีหนังสือไม่เกิน capacity";
        Set<String> seen = new HashSet<>();
        for (String b : listBooks){ //วนลูปใน Listbooks จนหมด
            assert b != null: "ชื่อหนังสือไม่เป็น null";
            assert !b.isEmpty(): "หนังสือว่าง";
            assert b.length() <= 100: "ชื่อหนังสือความยาวเกิน 100 ตัวอักษร";
            assert seen.add(b) : "ชื่อหนังสือซ้ำ : " + b; //วนเก็บหนังสือเรื่อยๆ ถ้าเจอซ้ำคือ false
        }
    }

    // ----- Creator -----

    /**
     * 
     * 
     * @throws IllegalArgumentException 
     */
    public BoundedStack(int capacity){
        this.listBooks = new ArrayList<>();
        this.capacity = capacity;
        checkrep();
    }
 

    //ตัวที่สอง
    public BoundedStack(List<String> list, int capacity) {
        this.listBooks = new ArrayList<>(list); 
        this.capacity = capacity;
        checkrep(); // ตรวจสอบความถูกต้องของข้อมูลตาม RI
    }

    // ----- Mutators -----
    /**
     * 
     * @param b  หนังสือไม่เป็น null, ไม่เป็นสตริงว่าง และความยาวไม่เกิน 100 ตัวอักษร
     * @return true ถ้าเพิ่มสำเร็จ, false ถ้าชั้นหนังสือเต็มหรือมีหนังสือนี้อยู่แล้ว
     * @throws IllegalArgumentException ถ้าหนังสือเป็น null , สตริงว่าง และความยาวเกิน100ตัวอักษร
     */
    public boolean push(String b){
        if(b == null || b.isEmpty() || b.length() > 100) throw new IllegalArgumentException();
        if(listBooks.contains(b) || listBooks.size() == this.capacity) return false;
        listBooks.add(b);
        checkrep();
        return true;
    }

    /**
     * นำหนังสือที่เอาเข้าล่าสุดออก
     * 
     * @return true ถ้าหนังสือถูกเอาออก 
     * 
     */
    public boolean pop(){
        if(listBooks.isEmpty()) return false;
        listBooks.remove(listBooks.size()-1);
        checkrep();
        return true;
    }

    // ----- Observers -----
    
    /**
     * คืนค่าจำนวนหนังสือใน listBooks
     * 
     */
    public int size(){
        return listBooks.size();
    }

    /**
     * ตรวจสอบว่ามีชื่อหนังสือมั้ย
     * 
     * 
     */
    public boolean contains(String book){
        return listBooks.contains(book);
    }

    /**
     * 
     * 
     */
    public int capacity() {
        return this.capacity;
    }  

    /**
     * 
     * คืนชื่อหนังสือทั้งหมดตามลำดับ
     * 
     * ระวัง ห้ามคืน reference ของ books ตรง ๆ
     */
    public List<String> book(){
        return new ArrayList<>(listBooks);
    }

    // ----- Producers -----

    /**
     * ระวัง ห้ามแก้ list เดิม
     * 
     * @return ลิสต์หนังสือที่กลับลำดับ
     */
    public BoundedStack reverse(){
        List<String> copy = new ArrayList<>(listBooks);
        Collections.reverse(copy);
        return new BoundedStack(copy,this.capacity);
    }

    /*@Override
    public String toString() {
        return listBooks.toString();
    }*/

}
