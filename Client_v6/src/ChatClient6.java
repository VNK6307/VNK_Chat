import javax.swing.*;
import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class ChatClient6 extends JFrame {

    protected static ChatWindow frame = new ChatWindow();
    private static final String IP_ADDRESS = ConfigData.getIPAddress();
    private static final int PORT = ConfigData.getPort();
    private static Socket socket;

    static {
        try {
            socket = new Socket(IP_ADDRESS, PORT);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // TODO Добавить чтение из файла
        frame.frameDraw(new JFrame());
        try {
            receiveMessage();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }// main

    static void receiveMessage() throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
        while (true) {
            String inputMsg = in.readLine();
            frame.getTextAreaMain().append(inputMsg);
            frame.getTextAreaMain().append("\r\n");
            frame.getTextAreaMain().setCaretPosition(frame.getTextAreaMain().getText().length());// todo перевод курсора в конец текста
        }
    }// receiveMessage

    protected static void btnSend_Handler() throws Exception {
        String message = frame.getTextFieldMsg().getText();
        System.out.println(frame.FRM_TITLE + ": " + message);// todo Удалить по завершении
        if (message.equals("")) return;
        sendMessage(frame.FRM_TITLE + ": " + message);
        frame.getTextFieldMsg().setText(null);
    }// btnSend_Handler

    public static void sendMessage(String message) throws IOException {
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8));
        try {
            out.write(message + "\r\n");
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();// todo Добавить более понятный вывод??
            socket.close();
        }
    }
}// class
