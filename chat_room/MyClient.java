package chat_room.chat_room;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;

/**
 * @ProjectName: BS
 * @Package: chat_room.chat_room
 * @ClassName: MyClient
 * @Description: java类作用描述
 * @Author: alj
 * @CreateDate: 2018/10/5 20:03
 * @UpdateUser: 更新者
 * @UpdateDate: 2018/10/5 20:03
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 * 客户端:
 * 1.创建客户端+端口
 * 2.发送数据
 * 3.打包（指定发送的地点和端口）
 * 4.发送
 *5.释放资源
 *
 */
public class MyClient {
    public static void main(String[] args) throws IOException {
        // 1.
        DatagramSocket client = new DatagramSocket(6666);
        // 2.准备数据
        String msg = "udp编程";
        byte[] data = msg.getBytes();
        // 3.
        DatagramPacket packet = new DatagramPacket(data,data.length,new InetSocketAddress("localhost",8888));
        // 4.
        client.send(packet);
        // 5.
        client.close();
    }
}
