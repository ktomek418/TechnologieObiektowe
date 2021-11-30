import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyFrame extends JFrame {

    JButton button = new JButton();

    public MyFrame(){
        this.setSize(500, 500);
        this.setTitle("My first frame");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setLayout(null);
        button.setBounds(100, 50, 100, 50 );
        button.setText("Klikaj");
        button.setFocusable(false);
        button.setBackground(Color.CYAN);
        button.setForeground(Color.GREEN);
        button.addActionListener( e -> System.out.println("elo"));
        button.setBorder(BorderFactory.createEtchedBorder());
        this.add(button);

    }

}
