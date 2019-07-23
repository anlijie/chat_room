package chat_room.traditional_chat_rooms;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
/**
 * @ProjectName: BS
 * @Package: chat_room.traditional_chat_rooms
 * @ClassName: Client
 * @Description: java类作用描述
 * @Author: alj
 * @CreateDate: 2018/10/5 21:20
 * @UpdateUser: 更新者
 * @UpdateDate: 2018/10/5 21:20
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class Client {
    public static void main(String[] args) throws IOException {
        // 1.
        DatagramSocket client = new DatagramSocket(6666);
        // 2.准备数据
        //String msg = "udp编程";
        double num = 89.12;
        byte[] data = convert(num);
        //byte[] data = msg.getBytes();
        // 3.
        DatagramPacket packet = new DatagramPacket(data,data.length,new InetSocketAddress("localhost",8888));
        // 4.
        client.send(packet);
        // 5.
        client.close();
    }

    public static byte[] convert(double num) throws IOException{
        byte[] data = null;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(bos);
        dos.writeDouble(num);
        dos.flush();
        // 获取数据
        data = bos.toByteArray();
        dos.close();
        return data;
    }
}
