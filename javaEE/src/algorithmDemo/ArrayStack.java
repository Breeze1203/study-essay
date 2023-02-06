package algorithmDemo;

public class ArrayStack<E> implements Stack<E>{

    Array<E> array;

    // 该类的构造方法
    public ArrayStack(int capacity){
        array=new Array<E>(capacity);
    }
    // 该类无参构造方法
    public ArrayStack(){
        array=new Array<E>();
    }
    @Override
    public int getSize(){
        return array.getSize();
    }
    @Override
    public boolean isEmpty(){
        return array.isEmpty();
    }
    // 在栈中放入元素
    @Override
    public void push(E e){
        array.addLast(e);
    }
    // 取出栈顶端元素,相当于删除最后一个元素
    @Override
    public void peek(){
        array.removeLast();
    }
    @Override
    public void pok(){
        array.getLast();
    }
    @Override
    public String toString(){
        StringBuilder str=new StringBuilder();
        str.append("stack  [");
        for(int i=0;i< array.getSize();i++){
            str.append(i);
            if(i!= array.getSize()-1){
                str.append(",");
            }
        }
        str.append("] top");
        return str.toString();
    }
}
