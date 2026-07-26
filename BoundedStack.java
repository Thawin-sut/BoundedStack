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
    //  AF(Listbooks) = รายการหนังสือที่ผู้ใช้ต้องการอ่านตามลำดับใน Listbooks

    //Representation Invariant :
    //  Listbooks ต้องไม่เป็น null //
    //  จำนวนหนังสือใน Listbooks ต้องมากกว่าหรือเท่ากับ 0 //
    //  ไม่มีหนังสือเป็น null //
    //  ไม่มีชื่อหนังสือเป็นสตริงว่าง //
    //  ชื่อหนังสือต้องไม่ยาวเกิน 100 ตัวอักษร //
    //  ชื่อหนังสือไม่ซ้ำกัน
    //  มีหนังสือใน Listbooks ได้ไม่เกิน capacity //

    //Safety from rep exposure:
    //  Listbooks เป็น private final
    //  คัดลอกข้อมูลทั้งขาเข้าขาออก

    //CheckReq
    private void checkrep(){
        assert Listbooks != null : "Listbooks is not null";
        assert Listbooks.size() >= 0 : "Listbooks มากกว่า 0";
        assert Listbooks.size() <= this.capacity : "มีหนังสือไม่เกิน capacity";
        Set<String> seen = new HashSet<>();
        for (String b : Listbooks){ //วนลูปใน Listbooks จนหมด
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
