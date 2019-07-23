package chat_room.cient_multithread_chat_room.two;

import java.io.Closeable;
import java.io.IOException;

/**
 * @ProjectName: BS
 * @Package: chat_room.cient_multithread_chat_room.two
 * @ClassName: CloseUtil
 * @Description: java类作用描述
 * @Author: alj
 * @CreateDate: 2018/10/6 21:46
 * @UpdateUser: 更新者
 * @UpdateDate: 2018/10/6 21:46
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class CloseUtil {
    public static void closeAll(Closeable... io){
        for (Closeable temp:io) {
            try {
                temp.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
