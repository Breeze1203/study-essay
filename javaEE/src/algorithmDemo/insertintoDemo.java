package algorithmDemo;

public class insertintoDemo {
    public static void main(String[] args) {
        int[] arr={6,2,3,4,1};
        insertintoDemo.insertSort(arr);
        for(int t:arr){
            System.out.println(t);
        }
    }
    public static void insertSort(int arr[]){
        for(int i=0;i<arr.length;i++)
            for(int j=i;j-1>=0;j--){
                if(arr[j]-arr[j-1]>0){
                    int t=arr[j];
                    arr[j]=arr[j-1];
                    arr[j-1]=t;
                }else{
                    break;
                }
            }
    }
}
