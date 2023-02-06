package algorithmDemo;

public class Array<E>{
    private E[] data;
    private int size=0;
    public Array(int capacity){
        data= (E[]) new Object[capacity];
        size=0;
    }
    public Array(){
        this(10);
    }
    // 判断数组是否为空
    public boolean isEmpty(){
        return size==0;
    }
    // 查看数组容量
    public int getCapacity(){
        return data.length;
    }
    // 查看数组里面元素个数
    public int getSize(){
        return size;
    }
    public int found(int e){
        for(int i=0;i<data.length;i++){
            if(data[i].equals(e)){
                return i;
            }
        }
        return -1;
    }
    // 在数组中添加元素
    public void add(int index,E e){
        if(size==data.length){
            if(index<0||index>size)
                try {
                    throw new IllegalAccessException("add filed,Required index>0||index<size");
                } catch (IllegalAccessException ex) {
                    ex.printStackTrace();
                }
            // 判断容器容量是否能够插入元素
            if(size==data.length){
                resize(2*data.length);
            }
        }
        for(int i=size-1;index<=i;i++){
            data[i+1]=data[i];
        }
        data[index]=e;
        size++;
    }
    // 在数组末端添加元素
    public void addFirst(E e){
        add(0,e);
    }
    public void addLast(E e){
        add(size,e);
    }
    public E getLast(){
        return get(size-1);
    }
    public E getFirst(){
        return get(0);
    }
    // 获取某位置索引元素
    public E get(int index){
        if(index<0||index>size-1){
            try {
                throw new IllegalAccessException("index is not required");
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return data[index];
    }

    // 修改某位置元素
    public void set(int index,E e){
        if(index<0||index>size){
            try {
                throw new IllegalAccessException("index 不符合要求");
            } catch (IllegalAccessException ex) {
                ex.printStackTrace();
            }
        }
        data[index]=e;
    }
    // 删除index位置的元素，返回删除位置的元素
    public E remove(int index){
        if(index<0||index>=size){
            try {
                throw new IllegalAccessException("index 不符合要求");
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        E ret=data[index];
        for(int i=index+1;i<=size;i++) {
            data[i - 1] = data[i];
        }
        size--;
        return ret;
    }
    // 删除末尾元素
    public void removeLast(){
        remove(size-1);
    }
    // 删除第一个元素
    public void removeFirst(){
        remove(0);
    }
    @Override
    public String toString(){
        StringBuilder str = new StringBuilder();
        str.append("Array:size="+size+"\n");
        str.append("capacity="+data.length+"\n");
        str.append("[");
        for (int i = 0; i < size; i++) {
            str.append(data[i]);
            if (i != size - 1) {
                str.append(",");
            }
        }
        str.append("]");
        return str.toString();
    }
    private void resize(int capacity){
        E[] Data= (E[]) new Object[capacity];
        for(int i=0;i<size;i++){
            data[i]=Data[i];
        }
        data=Data;
    }

}
