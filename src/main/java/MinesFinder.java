import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MinesFinder extends JFrame {

    private JPanel painelPrincipal;
    private JButton jogoFácilButton;
    private JButton jogoMédioButton;
    private JButton jogoDifícilButton;
    private JButton sairButton;

    public MinesFinder(String title) {
        super(title);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(painelPrincipal);
        pack();

        sairButton.addActionListener(this::sairButtonActionPerformed);
//        sairButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                System.exit(0);
//            }
//        });

    }

    private void sairButtonActionPerformed(ActionEvent e){
        System.exit(0); //é o que vai acontecer quando carregar no botão
    }

    public static void main(String[] args) { new MinesFinder("Mines Finder").setVisible(true); }
}