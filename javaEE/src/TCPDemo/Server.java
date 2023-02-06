package TCPDemo;


import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server {
    private ServerSocket server;
    private List<PrintWriter> allOut;
    public Server() throws IOException {
        server=new ServerSocket(8888);

        allOut=new ArrayList<PrintWriter> ();
        /*
        System.out.println("正在尝试连接");
        Socket socket=server.accept();
        System.out.println("已经建立连接");
         */
    }
    private void addOut(PrintWriter add){
        allOut.add(add);
    }
    private void removeOut(PrintWriter out){
        allOut.remove(out);
    }
    private void sendMessage(String message){
        for(PrintWriter out:allOut){
            out.println(message);

        }
    }
    public void start(){
        try{
            while(true) {
                System.out.println("正在尝试连接");
                Socket socket = server.accept();
        /*
        启动一个线程来完成与该客户端的交互
         */
                ClientHender hander = new ClientHender(socket);
                Thread t = new Thread(hander);
                t.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try {
            Server server = new Server();
            server.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    class ClientHender implements Runnable {
        // 该线程处理方法的客户端
        private Socket socket;
        //  获取客户端的地址信息
        private String host;
        //  客户的昵称
        private String nickName;
        ClientHender(Socket socket) {
            this.socket = socket;
            InetAddress address=socket.getInetAddress();//  获取远程计算机的Ip地址
            host=address.getHostAddress(); // 获取String对象与该地址的文本地址
        }


        public void run() {
            PrintWriter pw=null;
            try{
                InputStream in=socket.getInputStream();
                InputStreamReader isr=new InputStreamReader(in,"UTF-8");
                BufferedReader br=new BufferedReader(isr);
           //    首先读取一段字符串为昵称
                nickName=br.readLine();
                sendMessage(nickName+"上线了");

                OutputStream out=socket.getOutputStream();
                OutputStreamWriter osw=new OutputStreamWriter(out,"UTF-8");
                pw=new PrintWriter(osw,true);
           //      将该客户端输入流存入到共享集合中
                addOut(pw);

                String message=null;
                while((message=br.readLine())!=null) {
                    /*
                    pw.println(host+"说 :"+message);

                     */
//                    广播消息
                    sendMessage(nickName+"说"+message);
                   /*
                   System.out.println("客户端:" + message);
                    */
                }
            }catch(Exception e){

            }finally{
                /*
                处理当前客户端断线的情况
                 */
//                将该客户的输出流从共享集合中删除
                removeOut(pw);
                System.out.println("该用户下线了");
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
