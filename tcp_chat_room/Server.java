package chat_room.tcp_chat_room;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @ProjectName: BS
 * @Package: chat_room.tcp_chat_room
 * @ClassName: Server
 * @Description: java类作用描述
 * @Author: alj
 * @CreateDate: 2018/10/5 22:16
 * @UpdateUser: 更新者
 * @UpdateDate: 2018/10/5 22:16
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class Server {
    public static void main(String[] args) throws IOException {
        // 1.创建服务器 指定端口 ServerSocket（int port）
        ServerSocket server = new ServerSocket(8888);
        // 2.接收客户端连接 阻塞式
        Socket socket = server.accept();
        System.out.println("一个客户端建立连接");
        // 发送数据
        String msg = "欢迎使用";
        // 输出流
        BufferedWriter bv = new BufferedWriter(
                new OutputStreamWriter(
                        socket.getOutputStream()
                )
        );
        bv.write(msg);
        bv.newLine();
        bv.flush();
    }
}
