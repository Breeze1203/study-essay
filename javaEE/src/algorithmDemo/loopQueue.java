package algorithmDemo;

public class loopQueue<E> implements Queue<E>{
    private E[] data;
    private int front,tail;
    private int size;

    public loopQueue(int capacity){
        data=(E[])new Object[capacity-1];
        front=0;
        tail=0;
        size=0;
    }

    // 此构造方法调用了上面的构造方法

    public loopQueue(){
        this(10);
    }

    public int getCapacity(){
        return data.length-1;
    }

    @Override
    public boolean isEmpty(){
        return front==tail;
    }

    @Override
    public int GetSize(){
        return size;
    }

    // 扩容
    public void resize(int newCapacity){
        E[] newData=(E[])new Object[newCapacity+1];
        for(int i=0;i<size;i++){
            newData[i]=data[front+1];
        }
        data=newData;
        front=0;
        tail=size;
    }


    @Override
    public void dequeue() {
        if(isEmpty()){
            try {
                throw new IllegalAccessException("元素个数为0");
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }else{
            E ret=data[front];
            data[front]=null;
            front=(front+1)%data.length;
            size--;

        }
    }

    @Override
    public void enqueue(E e) {
        if((tail+1)%data.length==front){
            resize(getCapacity()*2);
        }
        data[tail]=e;
        tail=(tail+1)%data.length;
        size++;
    }
    public String toString(){
        StringBuilder of=new StringBuilder();
        of.append("loopQueue [");
        for(int i=front;i!=tail;i=(i+1)% data.length){
            of.append(data[i]);
            if((i+1)%data.length!=tail){
                of.append(",");
            }
        }
        of.append("]");
        return of.toString();
    }

}
