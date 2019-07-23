package chat_room.cient_multithread_chat_room.two;

import java.io.*;
import java.net.Socket;

/**
 * @ProjectName: BS
 * @Package: chat_room.responsive_chat_room
 * @ClassName: Client
 * @Description: java类作用描述：客户端
 * @Author: alj
 * @CreateDate: 2018/10/6 21:08
 * @UpdateUser: 更新者
 * @UpdateDate: 2018/10/6 21:08
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 * 过程：
 * 建立连接:
 * 输入数据
 * 输出数据
 *
 */
public class Client {
    public static void main(String[] args) throws IOException {
        Socket client = new Socket("localhost",9999);
        new Thread(new Send(client)).start();
        new Thread(new Receive(client)).start();;
    }
}
