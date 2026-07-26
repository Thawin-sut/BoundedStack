import java.util.*;

/**
 * BoundedStack - ADT แทนรายการหนังสือที่ผู้ใช้ต้องการอ่าน
 * 
 * ค่านามธรรม (A) : ลำดับของหนังสือที่ผู้ใช้ต้องการอ่าน เช่น [หนังสือA, หนังสือB, หนังสือC]
 * 
 */
public class BoundedStack {

    private final List<String> Listbooks; //private final String[] elements;
    private final int capacity ;

    //  representation

    //Abstraction Function:
    //  AF(books) = รายการหนังสือที่ผู้ใช้ต้องการอ่านตามลำดับใน books

    //Representation Invariant :
    //  books ต้องไม่เป็น null
    //  books ต้องมากกว่าหรือเท่ากับ 0
    //  ไม่มีหนังสือเป็น null
    //  ไม่มีชื่อหนังสือเป็นสตริงว่าง
    //  ชื่อหนังสือต้องไม่ยาวเกิน 100 ตัวอักษร
    //  ชื่อหนังสือไม่ซ้ำกัน
    //  มีหนังสือใน books ได้ไม่เกิน capacity

    //Safety from rep exposure:
    //  books เป็น private final
    //  คัดลอกข้อมูลทั้งขาเข้าขาออก

    //CheckReq
    private void checkrep(){
        assert Listbooks != null : "books is not null";
        assert Listbooks.size() <= this.capacity : "มีหนังสือได้ไม่เกิน capacity";

    }

    // ----- Creator -----

    /**
     * 
     * 
     * @throws IllegalArgumentException 
     */
public BoundedStack(int capacity){
        this.Listbooks = new ArrayList<>();
        this.capacity = capacity;

    }

    // ----- Mutators -----
    /**
     * 
     * @param s books ไม่เป็น null, ไม่เป็นสตริงว่าง 
     * @return true ถ้าเพิ่มสำเร็จ, false ถ้าชั้นหนังสือเต็มหรือมีหนังสือนี้อยู่แล้ว
     * @throws IIllegalArgumentException ถ้า 
     */
    public void push(String s){

    }

    /**
     * 
     * @param s
     */
    public void pop(){

    }

    // ----- Observers -----






    // ----- Producers -----



}
