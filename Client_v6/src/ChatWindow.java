import javax.swing.*;
import java.awt.*;

public class ChatWindow {
    private JTextArea textAreaMain;
    private JTextField textFieldMsg;

    protected final String FRM_TITLE = ConfigData.getNick();// TODO сделать проверку на пустое поле
    private final int FRM_WIDTH = ConfigData.getWidth();
    private final int FRM_HEIGHT = ConfigData.getHeight();

    protected void frameDraw(JFrame frame) {
        textFieldMsg = new JTextField();
        textAreaMain = new JTextArea( FRM_HEIGHT / 19, 50);
        textAreaMain.setLineWrap(true);
        textAreaMain.setEditable(false);

        JScrollPane scrollPaneMane = new JScrollPane(textAreaMain);
        scrollPaneMane.setLocation(0, 0);

        JButton btnSend = new JButton();
        btnSend.setText("Send");
        btnSend.setToolTipText("Broadcast a message");
        btnSend.addActionListener(e -> {
            try {
                ChatClient6.btnSend_Handler();// TODO!!!!!
//                System.out.println("Кнопка нажата!");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setTitle(FRM_TITLE);
        frame.setSize(FRM_WIDTH, FRM_HEIGHT);
        frame.setResizable(false);
        frame.add(BorderLayout.NORTH, scrollPaneMane);// todo попробовать без getContentPane
        frame.add(BorderLayout.CENTER, textFieldMsg);
        frame.add(BorderLayout.EAST, btnSend);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }// frameDraw

    public JTextArea getTextAreaMain() {
        return textAreaMain;
    }

    public JTextField getTextFieldMsg() {
        return textFieldMsg;
    }
}// class
