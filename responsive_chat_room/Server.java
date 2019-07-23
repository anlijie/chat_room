package chat_room.responsive_chat_room;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @ProjectName: BS
 * @Package: chat_room.responsive_chat_room
 * @ClassName: Server
 * @Description: java类作用描述
 * @Author: alj
 * @CreateDate: 2018/10/6 21:26
 * @UpdateUser: 更新者
 * @UpdateDate: 2018/10/6 21:26
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 *过程:
 * 建立连接
 *接收数据
 *响应数据
 */
public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(9999);
        Socket client = server.accept();
        // 写出数据
        // 输入流
        DataInputStream dis = new DataInputStream(client.getInputStream());
        String msg = dis.readUTF();

        // 输出流
        DataOutputStream dos = new DataOutputStream(client.getOutputStream());
        dos.writeUTF("服务器返回去的----》"+msg);
        dos.flush();
    }
}
