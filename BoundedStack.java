import java.util.*;

/**
 * BoundedStack - ADT ชั้นหนังสือที่เก็บรายชื่อหนังสือ
 * 
 * ค่านามธรรม (A) : ลำดับหนังสือบนชั้นหนังสือ เช่น [หนังสือA, หนังสือB, หนังสือC]
 * 
 */
public class BoundedStack {

    private final List<String> books; //private final String[] elements;
    private final int bookshelf ;
    private final List<String> ISBN;

    //  representation

    //Abstraction Function:
    //  AF(books,bookshelf) = หนังสือที่เก็บอยู่ในชั้นหนังสือตามลำดับตัวอักษร 

    //Representation Invariant :
    //  ISBN ต้องไม่ซ้ำกัน
    //  ชั้นหนังสือต้องไม่ว่าง != null
    //  ไม่มีชั้นหนังสือใดเป็น null
    //  ไม่มีหนังสือเป็นสตริงว่าง
    //  หนังสือเรียงตามตัวอักษรตัวแรก
    //  หนังสือต้องไม่เป็นตัวอักษรพิเศษ
    //  หนังสือมีเลข ISBN ไม่ซ้ำกัน
    //  มีหนังสือในชั้นได้ไม่เกิน 50 เล่ม

    //Safety from rep exposure:
    //  books เป็น private final
    //  ISBN เป็น private final
    //  คัดลอกข้อมูลทั้งขาาเข้าขาออก

    //CheckReq
    
    private void checkrep(){
        
    }

    // ----- Creator -----

    /**
     * 
     * @param capacity รายชื่อหนังสือ ต้องไม่ซ้ำกันและไม่เกิน capacity
     * @throws IllegalArgumentException 
     */
    public BoundedStack(int capacity){
        this.books = new ArrayList<>();
        this.bookshelf = capacity;
        this.ISBN = new ArrayList<>();

    }

    // ----- Mutators -----
    /**
     * 
     * @param s หนังสือต้อง ไม่เป็น null, ไม่เป็นสตริงว่าง และ ไม่เป็นตัวอักษรพิเศษ
     * @return true ถ้าเพิ่มสำเร็จ, false ถ้าชั้นหนังสือเต็มหรือมีหนังสือนี้อยู่แล้ว
     * @throws IIllegalArgumentException ถ้า 
     */
    public void add(String s){

    }

    /**
     * 
     * @param s
     */
    public void remove(String s){

    }
}
