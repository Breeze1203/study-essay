package TCPDemo;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private Socket socket;
    public Client() throws IOException {
        socket =new Socket("localhost",8888);
        System.out.println("已于服务端连接");
    }
    public void start(){
        try {
//            请先输入一个昵称
            Scanner scanner=new Scanner(System.in);
            String nickName=null;
            while(true) {
                System.out.println("请输入一个昵称");
                nickName=scanner.nextLine();
                if(nickName.length()>0){
                    break;
                }
                System.out.println("输入有误");
            }
            System.out.println("欢迎你"+nickName+"开始聊天吧");
            OutputStream out=socket.getOutputStream();
            OutputStreamWriter osw=new OutputStreamWriter(out,"UTF-8");
            PrintWriter pw=new PrintWriter(osw,true);
            /*
            启动读取服务端发过来消息的线程
             */
//            先将昵称发送给服务器
            pw.println(pw);
            ServerHender t1=new ServerHender();
            Thread t2=new Thread(t1);
            t2.start();

            while(true){
                pw.println(scanner.nextLine());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
//    该线程用来读取服务端发过来的消息
//    并输出到客户端显示台显示
    class ServerHender implements Runnable{

        @Override
        public void run() {
            try {
                InputStream in=socket.getInputStream();
                InputStreamReader isr=new InputStreamReader(in,"UTF-8");
                BufferedReader pw=new BufferedReader(isr);
                String message=null;
                while((message=pw.readLine())!=null){
                    System.out.println(message);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }


        }
    }

    public static void main(String[] args) {
        try {
            Client client = new Client();
            client.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
