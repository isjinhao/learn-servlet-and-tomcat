package wit;

import javax.websocket.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * @author 01395265
 * @date 2021/4/20
 */
@ClientEndpoint
public class WebSocketClient {

    protected WebSocketContainer container;
    protected Session userSession = null;

    public WebSocketClient() {
        container = ContainerProvider.getWebSocketContainer();
    }

    public void Connect(String sServer) {
        try {
            userSession = container.connectToServer(this, new URI(sServer));
        } catch (DeploymentException | URISyntaxException | IOException e) {
            e.printStackTrace();
        }
    }

    public void SendMessage(String sMsg) throws IOException {
        userSession.getBasicRemote().sendText(sMsg);
    }

    @OnOpen
    public void onOpen(Session session) {
        System.out.println("Connected");
    }

    @OnClose
    public void onClose(Session session, CloseReason closeReason) {
    }

    @OnMessage
    public void onMessage(Session session, String msg) {
        System.out.println(msg);
    }

    public void Disconnect() throws IOException {
        userSession.close();
    }

    public static void main(String[] args) throws Exception {
        WebSocketClient wsc = new WebSocketClient();
        wsc.Connect("ws://localhost:8080/websocket");
        Thread.sleep(1000);
        wsc.Disconnect();
    }

}
