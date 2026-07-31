//นายธาวิน สุดวิเศษ 682151370 หมู่ 801
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
    private void checkRep(){
        assert listBooks != null : "listBooks is not null";
        //assert listBooks.size() >= 0 : "listBooks มากกว่าเท่ากับ 0";
        assert listBooks.size() <= this.capacity : "มีหนังสือไม่เกิน capacity";
        Set<String> seen = new HashSet<>();
        for (String b : listBooks){ //วนลูปใน Listbooks จนหมด
            assert b != null: "ชื่อหนังสือไม่เป็น null";
            assert !b.isEmpty(): "หนังสือว่าง";
            assert b.length() <= 100: "ชื่อหนังสือความยาวเกิน 100 ตัวอักษร";
            assert seen.add(b) : "ชื่อหนังสือซ้ำ : " + b; //วนเก็บหนังสือเรื่อยๆ ถ้าเจอซ้ำคือ false
        }
        assert capacity >= 0 : "capacity มากกว่าเท่ากับ 0";
    }

    // ----- Creator -----

    /**
     * สร้าง BoundedStack ว่าง
     * 
     * @param capacity พื้นที่เก็บสูงสุดของ listBooks
     * @throws IllegalArgumentException ถ้า capacity < 0
     */
    public BoundedStack(int capacity){
        if (capacity < 0) throw new IllegalArgumentException();
        this.listBooks = new ArrayList<>();
        this.capacity = capacity;
        checkRep();
    }
 

    /**
     * Creator ตัวที่สอง
     * 
     * สร้าง listBooks จากรายชื่อหนังสือที่ให้มา
     * 
     * ระวัง: ห้ามเก็บ reference ของ list ตรง ๆ (rep exposure!)
     * 
     * @param list รายชื่อหนังสือ ต้องไม่เท่ากับ null , ไม่เป็นสตริงว่าง ,หนังสือไม่ซ้ำ และไม่เกิน 100 ตัวอักษร
     * @param capacity พื้นที่เก็บสูงสุดของ listBooks ไม่น้อยกว่า 0
     * @throws IllegalArgumentException ถ้า list ผิดเงื่อนไข
     */
    public BoundedStack(List<String> list, int capacity) {
        if (list == null) throw new IllegalArgumentException();
        if (capacity < 0) throw new IllegalArgumentException();
        if (list.size() > capacity) throw new IllegalArgumentException();
        Set<String> seen = new HashSet<>();
        for (String s : list){
            if(s == null) throw new IllegalArgumentException();
            if(s.isEmpty()) throw new IllegalArgumentException();
            if (s.length() > 100) throw new IllegalArgumentException();
            if(!seen.add(s)) throw new IllegalArgumentException();
        }
        this.listBooks = new ArrayList<>(list); 
        this.capacity = capacity;
        checkRep(); // ตรวจสอบความถูกต้องของข้อมูลตาม RI
    }

    // ----- Mutators -----
    /**
     * 
     * @param b  หนังสือ ต้องไม่เป็น null, ไม่เป็นสตริงว่าง และความยาวไม่เกิน 100 ตัวอักษร
     * @return true ถ้าเพิ่มสำเร็จ, false ถ้าชั้นหนังสือเต็มหรือมีหนังสือนี้อยู่แล้ว
     * @throws IllegalArgumentException ถ้าหนังสือเป็น null , สตริงว่าง และความยาวเกิน100ตัวอักษร
     */
    public boolean push(String b){
        if(b == null || b.isEmpty() || b.length() > 100) throw new IllegalArgumentException();
        if(listBooks.contains(b) || listBooks.size() == this.capacity) return false;
        listBooks.add(b);
        checkRep();
        return true;
    }

    /**
     * นำหนังสือที่เอาเข้าล่าสุดออก
     * 
     * @return true ถ้าหนังสือถูกเอาออก , false ถ้า listBooks ว่าง
     * 
     */
    public boolean pop(){
        if(listBooks.isEmpty()) return false;   
        listBooks.remove(listBooks.size()-1);
        checkRep();
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
     * คืนค่าพื้นที่เก็บหนังสือทั้งหมด
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
