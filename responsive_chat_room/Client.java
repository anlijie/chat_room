package chat_room.responsive_chat_room;

import java.io.*;
import java.net.Socket;

/**
 * @ProjectName: BS
 * @Package: chat_room.responsive_chat_room
 * @ClassName: Client
 * @Description: java类作用描述
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
        // 控制台的输入流
        BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
        DataOutputStream dos = new DataOutputStream(client.getOutputStream());
        DataInputStream dis = new DataInputStream(client.getInputStream());
        while (true) {
            String info = console.readLine();
            // 输出流
            dos.writeUTF(info);
            dos.flush();
            // 输入流
            String msg = dis.readUTF();
            System.out.println(msg);
        }
    }
}
