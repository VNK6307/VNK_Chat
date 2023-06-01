import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class NetConnection {
    private final Socket socket;
    private final Thread thread;
    private final NetConnectionListener eventListener;
    private final BufferedReader in;
    private final BufferedWriter out;

    public NetConnection(NetConnectionListener eventListener, Socket socket) throws IOException {
        this.eventListener = eventListener;
        this.socket = socket;
        in = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
        out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8));
        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    eventListener.onConnectionReady(NetConnection.this);
                    while (!thread.isInterrupted()) {
                        String msg = in.readLine();
                        eventListener.onReceiveString(NetConnection.this, msg);
                    }
                } catch (IOException e) {
                    eventListener.onException(NetConnection.this, e);
                } finally {
                    eventListener.onDisconnect(NetConnection.this);
                }
            }
        });
        thread.start();
    }

    public synchronized void sendString(String value) {
        try {
            out.write(value + "\r\n");
            out.flush();
        } catch (IOException e) {
            eventListener.onException(NetConnection.this, e);
            disconnect();
        }
    }

    public synchronized void disconnect() {
        thread.interrupt();
        try {
            socket.close();
        } catch (IOException e) {
            eventListener.onException(NetConnection.this, e);
        }
    }

    @Override
    public String toString() {
        return socket.getInetAddress() + ": " + socket.getPort();
    }
}// class
