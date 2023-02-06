package algorithmDemo;

public class ArrayQueue<E> implements Queue<E>{

    Array<E> array;

    public ArrayQueue(int capacity){
        array= new Array<E>(capacity);
    }

    public ArrayQueue(){
        array=new Array<E>();
    }
    @Override
    public int GetSize(){
        return array.getSize();
    }
    @Override
    public boolean isEmpty(){
        return array.isEmpty();
    }
    @Override
    public void enqueue(E e){
        array.addLast(e);
    }
    @Override
    public void dequeue(){
        array.removeFirst();
    }
    @Override
    public String toString(){
        StringBuilder str=new StringBuilder();
        str.append("Queue: Front");
        str.append('[');
        for(int i=0;i<array.getSize();i++){
            str.append(array.get(i));
            if(i!=array.getSize()-1){
                str.append(",");
            }
        }
        str.append("] tail");
        return str.toString();
    }
}
