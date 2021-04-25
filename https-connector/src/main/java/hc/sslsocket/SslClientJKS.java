package hc.sslsocket;

import javax.net.ssl.*;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.security.KeyStore;

/**
 * @author 01395265
 * @description TODO
 * @date 2021/4/23
 */
public class SslClientJKS {

    public static void main(String[] args) throws Exception {

        String path = SslServerJKS.class.getClassLoader().getResource("").getPath();
        System.out.println(path);

        // 密钥管理器
        KeyStore clientKeyStore = KeyStore.getInstance("JKS");
        clientKeyStore.load(new FileInputStream(path + "/client.private"), "clientstorepw".toCharArray());

        KeyManagerFactory kmf = KeyManagerFactory.getInstance("SunX509");
        kmf.init(clientKeyStore, "clientkeypw".toCharArray());

        // 信任管理器
        KeyStore serverKeyStore = KeyStore.getInstance("JKS");
        serverKeyStore.load(new FileInputStream(path + "/server.public"), "serverpublicpw".toCharArray());

        TrustManagerFactory tmf = TrustManagerFactory.getInstance("SunX509");
        tmf.init(serverKeyStore);

        // SSL上下文
        SSLContext sslContext = SSLContext.getInstance("SSL");
        sslContext.init(kmf.getKeyManagers(), tmf.getTrustManagers(), null);

        SSLSocketFactory sslcntFactory = (SSLSocketFactory) sslContext.getSocketFactory();
        SSLSocket sslSocket = (SSLSocket) sslcntFactory.createSocket("127.0.0.1", 34567);

        String[] supported = sslSocket.getSupportedCipherSuites();
        sslSocket.setEnabledCipherSuites(supported);

        // 发送
        OutputStream out = sslSocket.getOutputStream();
        out.write("hello".getBytes());

    }

}
