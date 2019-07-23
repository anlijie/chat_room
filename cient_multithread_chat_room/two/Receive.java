package chat_room.cient_multithread_chat_room.two;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * @ProjectName: BS
 * @Package: chat_room.cient_multithread_chat_room.two
 * @ClassName: Receive
 * @Description: java类作用描述：接收线程
 * @Author: alj
 * @CreateDate: 2018/10/6 22:03
 * @UpdateUser: 更新者
 * @UpdateDate: 2018/10/6 22:03
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class Receive implements Runnable{
    // 输入流
    private DataInputStream dis ;
    // 线程标识
    private boolean isRunning = true;
    public Receive(){

    }

    public Receive(Socket client){
        try {
            dis = new DataInputStream(client.getInputStream());
        } catch (IOException e) {
            isRunning =false;
            CloseUtil.closeAll(dis);
        }
    }

    public String receive() {
        String msg = "";
        try {
            msg = dis.readUTF();
        } catch (IOException e) {
            isRunning =false;
            CloseUtil.closeAll(dis);
        }
        return msg;
    }

    @Override
    public void run() {
        // 线程体
        while (isRunning){
            System.out.println(receive());
        }
    }
}
