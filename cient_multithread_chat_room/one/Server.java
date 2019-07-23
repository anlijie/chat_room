package chat_room.cient_multithread_chat_room.one;

import chat_room.cient_multithread_chat_room.two.CloseUtil;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * @ProjectName: BS
 * @Package: chat_room.responsive_chat_room
 * @ClassName: Server
 * @Description: java类作用描述:服务器端
 * @Author: alj
 * @CreateDate: 2018/10/6 21:26
 * @UpdateUser: 更新者
 * @UpdateDate: 2018/10/6 21:26
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 *过程:
 * 建立连接
 *写入数据
 * 读取数据
 */
public class Server {
    private List<MyChannel> all = new ArrayList<MyChannel>(100);


    public static void main(String[] args) throws IOException {
        new Server().start();
    }

    public void start() throws IOException {
        ServerSocket server = new ServerSocket(9999);
        while (true){
            Socket client = server.accept();
            MyChannel myChannel = new MyChannel(client);
            all.add(myChannel);// 加入到容器中统一管理
            new Thread(myChannel).start();// 一条道路
        }
    }
    /*
    *一个客户端一条道路
    * 输入流
    * 输出流
    * 发送数据
    * 接收数据
    *
     */
private  class MyChannel implements Runnable{
        private DataInputStream dis;
        private DataOutputStream dos;
        private boolean isRunning = true;


        public MyChannel(Socket client){
            try {
                dis = new DataInputStream(client.getInputStream());
                dos = new DataOutputStream(client.getOutputStream());
            } catch (IOException e) {
                CloseUtil.closeAll(dis,dos);
                isRunning = false;
            }
        }
        /*
        *读取数据
         */
        private String receive(){
            String msg = "";
            try {
                msg = dis.readUTF();
            } catch (IOException e) {
                CloseUtil.closeAll(dis,dos);
                isRunning = false;
                all.remove(this);
            }
            return msg;
        }

    /**
     * @param msg 发送数据
     */
    private void send(String msg) {
            if (msg == null || msg.equals("")) {
                return;
            }
            try {
                dos.writeUTF(msg);
            } catch (IOException e) {
                CloseUtil.closeAll(dis,dos);
                isRunning = false;
                all.remove(this);
            }
        }

        /*发送给其他人*/
        private void sendOther(){
            String msg = receive();
            // 遍历容器
            for (MyChannel other:all) {
                if (other == this){
                    continue;
                }
                other.send(msg);
            }
        }


        @Override
        public void run() {
            while (isRunning){
                sendOther();
            }
        }
    }

}


