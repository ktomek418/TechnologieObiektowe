import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class Test {

    public static void main(String[] args) throws Exception{
        //Test 1
//        JFrame myFrame = new JFrame();
//        myFrame.setVisible(true);
//        myFrame.setSize(500, 500);
//        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        myFrame.setLayout(new BorderLayout(10, 10));
//
//        JPanel panel1 = new JPanel();
//        JPanel panel2 = new JPanel();
//        JPanel panel3 = new JPanel();
//        JPanel panel4 = new JPanel();
//        JPanel panel5 = new JPanel();
//
//        panel1.setPreferredSize(new Dimension(100, 100));
//        panel2.setPreferredSize(new Dimension(100, 100));
//        panel3.setPreferredSize(new Dimension(100, 100));
//        panel4.setPreferredSize(new Dimension(100, 100));
//        panel5.setPreferredSize(new Dimension(100, 100));
//
//        panel1.setBackground(Color.CYAN);
//        panel2.setBackground(Color.GREEN);
//        panel3.setBackground(Color.BLUE);
//        panel4.setBackground(Color.GRAY);
//        panel5.setBackground(Color.MAGENTA);
//
//        myFrame.add(panel1, BorderLayout.NORTH);
//        myFrame.add(panel2, BorderLayout.WEST);
//        myFrame.add(panel3, BorderLayout.EAST);
//        myFrame.add(panel4, BorderLayout.SOUTH);
//        myFrame.add(panel5, BorderLayout.CENTER);
//
//        panel5.setLayout(new BorderLayout(5,5));
//
//        JPanel panel6 = new JPanel();
//        JPanel panel7 = new JPanel();
//        JPanel panel8 = new JPanel();
//        JPanel panel9 = new JPanel();
//        JPanel panel10 = new JPanel();
//
//        panel6.setPreferredSize(new Dimension(100, 100));
//        panel7.setPreferredSize(new Dimension(100, 100));
//        panel8.setPreferredSize(new Dimension(100, 100));
//        panel9.setPreferredSize(new Dimension(100, 100));
//        panel10.setPreferredSize(new Dimension(100, 100));
//
//        panel6.setBackground(Color.BLUE);
//        panel7.setBackground(Color.DARK_GRAY);
//        panel8.setBackground(Color.LIGHT_GRAY);
//        panel9.setBackground(Color.YELLOW);
//        panel10.setBackground(Color.pink);
//
//        panel5.add(panel6, BorderLayout.NORTH);
//        panel5.add(panel7, BorderLayout.WEST);
//        panel5.add(panel8, BorderLayout.EAST);
//        panel5.add(panel9, BorderLayout.SOUTH);
//        panel5.add(panel10, BorderLayout.CENTER);

        //Test 2
        JFrame second = new JFrame();
        second.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        second.setSize(500, 500);
        second.setLayout(new FlowLayout( FlowLayout.CENTER, 5 , 20));

        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(250, 250));
        panel.setBackground(Color.MAGENTA);
        second.add(panel);
        panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 10));

        JButton button1 = new JButton();
        JButton button2 = new JButton();
        JButton button3 = new JButton();
        JButton button4 = new JButton();
        JButton button5 = new JButton();
        JButton button6 = new JButton();
        JButton button7 = new JButton();
        JButton button8 = new JButton();

        panel.add(button1);
        panel.add(button2);
        panel.add(button3);
        panel.add(button4);
        panel.add(button5);
        panel.add(button6);
        panel.add(button7);
        panel.add(button8);


        second.setVisible(true);

        Thread.sleep(2000);
        panel.remove(button8);
        second.repaint();

    }
}
