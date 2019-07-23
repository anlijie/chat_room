package chat_room.cient_multithread_chat_room.two;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * @ProjectName: BS
 * @Package: chat_room.cient_multithread_chat_room.two
 * @ClassName: Send
 * @Description: java类作用描述:发送线程
 * @Author: alj
 * @CreateDate: 2018/10/6 21:39
 * @UpdateUser: 更新者
 * @UpdateDate: 2018/10/6 21:39
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class Send implements Runnable {
    // 控制台的输入流
    private BufferedReader console;
    // 管道的输出流
    private DataOutputStream dos;
    // 看看线程的状态
    private boolean isRunning = true;

    public Send(){
        console = new BufferedReader(new InputStreamReader(System.in));
    }
    public Send(Socket client) {
        this();
        try{
            dos = new DataOutputStream(client.getOutputStream());
        }catch (IOException e){
            isRunning = false;
            CloseUtil.closeAll(dos,console);
        }
    }

    // 1.从控制台读取数据
    private String getMsgFromConsole(){
        try {
            return console.readLine();
        } catch (IOException e) {
            isRunning = false;
            CloseUtil.closeAll(dos,console);
        }
        return "";
    }

    public void send(){
        String msg = getMsgFromConsole();
            if (null!=msg && !msg.equals("")) {
                try {
                    dos.writeUTF(msg);
                    dos.flush();// 强制刷新
                } catch (IOException e) {
                    isRunning = false;
                    CloseUtil.closeAll(dos,console);
                }
        }
    }

    @Override
    public void run() {
        while (isRunning){
            send();
        }
    }
}
