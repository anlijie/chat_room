package chat_room.tcp_chat_room;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * @ProjectName: BS
 * @Package: chat_room.tcp_chat_room
 * @ClassName: Client
 * @Description: java类作用描述
 * @Author: alj
 * @CreateDate: 2018/10/5 22:15
 * @UpdateUser: 更新者
 * @UpdateDate: 2018/10/5 22:15
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class Client {
    public static void main(String[] args) throws IOException {
        // 创建客户端 必须指定服务器+端口， 此时就在连接
        Socket client = new Socket("localhost",8888);
        // 接收数据
        BufferedReader br = new BufferedReader(new InputStreamReader(client.getInputStream()));
        String echo = br.readLine();
        System.out.println(echo);
    }
}
