import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;

public class ChatServer5 implements NetConnectionListener {
    private final ArrayList<NetConnection> connections = new ArrayList<>();

    public static void main(String[] args) {
        new ChatServer5();

    }// main todo

    private ChatServer5() {
        int port = ConfigData.getPort();
        System.out.println("Сервер запущен на порту " + port);
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            while (true) {
                try {
                    new NetConnection(this, serverSocket.accept());
                } catch (IOException e) {
                    System.out.println("Порт " + port + "- ошибка подключения: " + e);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }// ChatServer

    @Override
    public synchronized void onConnectionReady(NetConnection netConnection) {
        connections.add(netConnection);
        sendToAllConnections("К чату присоединился: " + netConnection);
    }

    @Override
    public synchronized void onReceiveString(NetConnection netConnection, String value) {
        sendToAllConnections(value);
    }

    @Override
    public synchronized void onDisconnect(NetConnection netConnection) {
        connections.remove(netConnection);
        sendToAllConnections("Чат покинул: " + netConnection);
    }

    @Override
    public synchronized void onException(NetConnection netConnection, Exception e) {
        System.out.println("Произошел сбой подключения: " + netConnection);
    }

    private void sendToAllConnections(String value) {
        System.out.println(value);
        final int cnt = connections.size();
        for (int i = 0; i < cnt; i++) {
            connections.get(i).sendString(value);
        }
    }

}// class
