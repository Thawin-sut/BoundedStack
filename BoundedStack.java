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

    //  representation

    //Abstraction Function:
    //  AF(books,bookshelf) = หนังสือที่เก็บอยู่ในชั้นหนังสือตามลำดับตัวอักษร 

    //Representation Invariant :
    //  ชั้นหนังสือต้องไม่ว่าง != null
    //  ไม่มีชั้นหนังสือใดเป็น null
    //  ไม่มีหนังสือเป็นสตริงว่าง
    //  หนังสือเรียงตามตัวอักษรตัวแรก
    //  หนังสือต้องไม่เป็นตัวอักษรพิเศษ
    //  มีหนังสือในชั้นได้ไม่เกิน 50 เล่ม

    // ----- Creator -----
    /**
     * 
     * @param capacity รายชื่อหนังสือเริ่มต้น ต้องไม่ซ้ำกันและไม่เกิน capacity
     * @thrown IllegalArgumentException 
     */
    public BoundedStack(int capacity){
        this.books = new ArrayList<>();
        this.bookshelf = capacity;
    }

    // ----- Mutators -----
    /**
     * 
     * @param s
     */
    public void push(String s){

    }
}
