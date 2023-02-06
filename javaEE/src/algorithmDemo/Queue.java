package algorithmDemo;

public interface Queue<E> {
    // 队列中添加元素
    void enqueue(E e);
    // 取出元素
    void dequeue();

    int GetSize();

    boolean isEmpty();
}
