package hc.sslsocket;

import java.io.FileInputStream;
import java.io.OutputStream;
import java.security.KeyStore;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManagerFactory;

/**
 * @author 01395265
 * @description TODO
 * @date 2021/4/23
 */
public class SslClient {

    public static void main(String[] args) throws Exception {

        // 密钥管理器
        KeyStore clientKeyStore = KeyStore.getInstance("JKS");
        clientKeyStore.load(new FileInputStream("e:\\myclient.jks"), "123456".toCharArray());

        KeyManagerFactory kmf = KeyManagerFactory.getInstance("SunX509");
        kmf.init(clientKeyStore, "123456".toCharArray());

        //信任管理器
        KeyStore serverKeyStore = KeyStore.getInstance("JKS");
        serverKeyStore.load(new FileInputStream("e:\\myserver.jks"), "123456".toCharArray());

        TrustManagerFactory tmf = TrustManagerFactory.getInstance("SunX509");
        tmf.init(serverKeyStore);

        //SSL上下文
        SSLContext sslContext = SSLContext.getInstance("SSL");
        sslContext.init(kmf.getKeyManagers(), tmf.getTrustManagers(), null);

        SSLSocketFactory sslcntFactory = (SSLSocketFactory) sslContext.getSocketFactory();
        SSLSocket sslSocket = (SSLSocket) sslcntFactory.createSocket("127.0.0.1", 34567);

        String[] supported = sslSocket.getSupportedCipherSuites();
        sslSocket.setEnabledCipherSuites(supported);

        //发送
        OutputStream out = sslSocket.getOutputStream();
        out.write("hello".getBytes());

    }

}
