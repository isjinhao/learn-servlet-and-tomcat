package hc.sslsocket;

import java.io.FileInputStream;
import java.io.InputStream;
import java.security.KeyStore;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.TrustManagerFactory;

/**
 * @author 01395265
 * @date 2021/4/23
 */
public class SslServer {

    public static void main(String[] args) throws Exception {

        String path = SslServer.class.getClassLoader().getResource("").getPath();
        System.out.println(path);

        // 密钥管理器 证书库格式
        KeyStore serverKeyStore = KeyStore.getInstance("PKCS12");

        // 加载密钥库
        serverKeyStore.load(new FileInputStream(path + "/server.private"), "serverstorepw".toCharArray());
        // 证书格式
        KeyManagerFactory kmf = KeyManagerFactory.getInstance("SunX509");
        // 加载密钥储存器
        kmf.init(serverKeyStore, "serverstorepw".toCharArray());

        // 信任管理器
        KeyStore clientKeyStore = KeyStore.getInstance("PKCS12");
        System.out.println(clientKeyStore == serverKeyStore);
        clientKeyStore.load(new FileInputStream(path + "/server.public"), "clientpublic".toCharArray());

        TrustManagerFactory tmf = TrustManagerFactory.getInstance("SunX509");
        tmf.init(clientKeyStore);

        // SSL上下文设置
        SSLContext sslContext = SSLContext.getInstance("SSL");
        sslContext.init(kmf.getKeyManagers(), tmf.getTrustManagers(), null);

        // SSLServerSocket
        SSLServerSocketFactory serverFactory = sslContext.getServerSocketFactory();
        SSLServerSocket svrSocket = (SSLServerSocket) serverFactory.createServerSocket(34567);

        // 客户端模式，服务端需要验证客户端身份
        svrSocket.setNeedClientAuth(true);

        // 加密套件
        String[] supported = svrSocket.getEnabledCipherSuites();
        svrSocket.setEnabledCipherSuites(supported);

        // 接收消息
        System.out.println("端口已打开，准备接受信息");

        // 开始接收
        SSLSocket cntSocket = (SSLSocket) svrSocket.accept();
        // 输入流
        InputStream in = cntSocket.getInputStream();
        int a = in.read(new byte[1024]);
        // 循环检查是否有消息到达
        System.out.println("来自于客户端:" + a);

    }
}
