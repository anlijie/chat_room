package chat_room.traditional_chat_rooms;

import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 * @ProjectName: BS
 * @Package: chat_room.traditional_chat_rooms
 * @ClassName: Server
 * @Description: java类作用描述
 * @Author: alj
 * @CreateDate: 2018/10/5 21:21
 * @UpdateUser: 更新者
 * @UpdateDate: 2018/10/5 21:21
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class Server {
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
        double data = convert(packet.getData());
        System.out.println(data);
        // 6.
        server.close();
    }
    public static double convert(byte[] data) throws IOException{
        DataInputStream dos = new DataInputStream(new ByteArrayInputStream(data));
        double num = dos.readDouble();
        dos.close();
        return num;
    }
}
