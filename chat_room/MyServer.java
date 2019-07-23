package chat_room.chat_room;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
/**
 * @ProjectName: BS
 * @Package: chat_room.chat_room
 * @ClassName: MyServer
 * @Description: java类作用描述
 * @Author: alj
 * @CreateDate: 2018/10/5 20:03
 * @UpdateUser: 更新者
 * @UpdateDate: 2018/10/5 20:03
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 * 服务器端
 * 步骤:
 * 1.创建服务器端+端口
 * 2.准备接受容器
 * 3.封装成包
 *4.接受数据(阻塞式)
 *5.分析数据
 *6.释放
 *
 */
public class MyServer {
    public static void main(String[] args) throws IOException {
        // 1.
        DatagramSocket server = new DatagramSocket(8888);
        // 2.
        byte[] container = new byte[1024];
        // 3.
        DatagramPacket packet = new DatagramPacket(container,container.length);
        // 4.
        server.receive(packet);
        // 5.
        byte[] data = packet.getData();
        int len = packet.getLength();
        System.out.println(new String(data,0,len));
        // 6.
        server.close();
    }
}
