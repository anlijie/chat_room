package chat_room.private_chat_room.one;

import chat_room.private_chat_room.two.CloseUtil;

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
        private String name;

        public MyChannel(Socket client){
            try {
                dis = new DataInputStream(client.getInputStream());
                dos = new DataOutputStream(client.getOutputStream());
                this.name = dis.readUTF();
                System.out.println(this.name);
                send("欢迎进入聊天室！");
                sendOther(this.name + "进入聊天室！",true);
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
                sendOther(this.name+"离开聊天室！",true);
            }
        }

        /*
        * 发送给其他人
        * */
        private void sendOther(String msg, boolean sys){
            // 是否为私聊 // 私聊
            if (msg.startsWith("@") && msg.indexOf(":")>-1){
                // 获取name
                String name = msg.substring(1,msg.indexOf(":"));
                String content = msg.substring(msg.indexOf(":")+1);
                for (MyChannel other:all) {
                    if (other.name.equals(name)){
                        other.send(this.name+"对您悄悄的说："+content);
                    }
                }
            }else {
                // 遍历容器
                for (MyChannel other : all) {
                    if (other == this) {
                        continue;
                    }
                    if (sys) {
                        other.send("系统消息：" + msg);
                    } else {
                        other.send(this.name + "对所有人说:" + msg);
                    }
                }
            }
        }


        @Override
        public void run() {
            while (isRunning){
                sendOther(receive(),false);
            }
        }
    }

}


