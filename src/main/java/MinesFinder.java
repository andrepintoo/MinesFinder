import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MinesFinder extends JFrame {

    private JPanel painelPrincipal;
    private JButton btnFacil;
    private JButton btnMedio;
    private JButton btnDificil;
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


//            @Override
//            public void actionPerformed(ActionEvent e) {
//                var janela = new JanelaDeJogo();
//
//
//            }

        btnFacil.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        btnMedio.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        btnDificil.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

    }

    private void sairButtonActionPerformed(ActionEvent e){
        System.exit(0); //é o que vai acontecer quando carregar no botão
    }

    private void btnFacilActionPerformed(ActionEvent e){

    }

    public static void main(String[] args) { new MinesFinder("Mines Finder").setVisible(true); }
}