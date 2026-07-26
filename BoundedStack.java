import java.util.*;

/**
 * BoundedStack - ADT ชั้นหนังสือที่เก็บรายชื่อหนังสือ
 * 
 * ค่านามธรรม (A) : ลำดับหนังสือบนชั้นหนังสือ เช่น [หนังสือA, หนังสือB, หนังสือC]
 * 
 */
public class BoundedStack {

    private final List<String> elements;
    private final int capacity;

    //  representation

    //Abstraction Function:
    //  AF(elements,capacity) = 
    //RI


    /**
     * 
     * @param capacity
     */
    public BoundedStack(int capacity){ //creator
        this.elements = new ArrayList<>();
        this.capacity = capacity;
    }

    /**
     * 
     * @param s
     */
    public void push(String s){

    }
}
