package algorithmDemo;

public interface Stack<E>{
    // 查看栈元素个数
    int getSize();
    // 查看栈是否为空；
    boolean isEmpty();
    // 在栈里放入元素
    void push(E e);
    // 取出栈顶端元素
    void peek();
    // 查看栈顶端元素
    void pok();
}
