import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MinesFinder extends JFrame {

    private JPanel painelPrincipal;
    private JButton btnFacil;
    private JButton btnMedio;
    private JButton btnDificil;
    private JButton sairButton;
    private JLabel lblNomeFacil;
    private JLabel lblTempoFacil;
    private JLabel lblNomeMedio;
    private JLabel lblTempoMedio;
    private JLabel lblNomeDificil;
    private JLabel lblTempoDificil;
    private TabelaRecordes recordesFacil;
    private TabelaRecordes recordesMedio;
    private TabelaRecordes recordesDificil;

    public MinesFinder(String title) {
        super(title);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(painelPrincipal);
        pack();

        recordesFacil = new TabelaRecordes();
        recordesMedio = new TabelaRecordes();
        recordesDificil = new TabelaRecordes();
        lerRecordesDoDisco();

        lblNomeFacil.setText(recordesFacil.getNome_jogador());
        lblTempoFacil.setText(Long.toString(recordesFacil.getDuracao_jogo()/1000));
        lblNomeMedio.setText(recordesMedio.getNome_jogador());
        lblTempoMedio.setText(Long.toString(recordesMedio.getDuracao_jogo()/1000));
        lblNomeDificil.setText(recordesDificil.getNome_jogador());
        lblTempoDificil.setText(Long.toString(recordesDificil.getDuracao_jogo()/1000));


        recordesFacil.addTabelaRecordesListener(new TabelaRecordesListener() {
            @Override
            public void recordesActualizados(TabelaRecordes recordes) {
                recordesFacilActualizado(recordes);
            }
        });
        recordesMedio.addTabelaRecordesListener(new TabelaRecordesListener() {
            @Override
            public void recordesActualizados(TabelaRecordes recordes) {
                recordesMedioActualizado(recordes);
            }
        });
        recordesDificil.addTabelaRecordesListener(new TabelaRecordesListener() {
            @Override
            public void recordesActualizados(TabelaRecordes recordes) {
                recordesDificilActualizado(recordes);
            }
        });




        sairButton.addActionListener(this::sairButtonActionPerformed);
//        sairButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                System.exit(0);
//            }
//        });

        btnFacil.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                var janela = new JanelaDeJogo("Jogo Fácil", new CampoMinado(9,9,10), recordesFacil);
                janela.setVisible(true);

            }
        });

        btnMedio.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                var janela = new JanelaDeJogo("Jogo Médio", new CampoMinado(16,16,40), recordesMedio);
                janela.setVisible(true);

            }
        });

        btnDificil.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                var janela = new JanelaDeJogo("Jogo Difícil", new CampoMinado(16,30,90), recordesDificil);
                janela.setVisible(true);

            }
        });

    }

    private void guardarRecordesDisco() {
        ObjectOutputStream oos = null;
        try {
            File f =new
                    File(System.getProperty("user.home")+File.separator+"minesfinder.recordes");
            oos = new ObjectOutputStream(new FileOutputStream(f));
            oos.writeObject(recordesFacil);
            oos.writeObject(recordesMedio);
            oos.writeObject(recordesDificil);
            oos.close();
        } catch (IOException ex) {
            Logger.getLogger(MinesFinder.class.getName()).log(Level.SEVERE, null,
                    ex);
        }
    }

    private void lerRecordesDoDisco() {
        ObjectInputStream ois = null;
        File f = new
                File(System.getProperty("user.home")+File.separator+"minesfinder.recordes");
        if (f.canRead()) {
            try {
                ois = new ObjectInputStream(new FileInputStream(f));
                recordesFacil=(TabelaRecordes) ois.readObject();
                recordesMedio=(TabelaRecordes) ois.readObject();
                recordesDificil=(TabelaRecordes) ois.readObject();
                ois.close();
            } catch (IOException ex) {
                Logger.getLogger(MinesFinder.class.getName()).log(Level.SEVERE,
                        null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(MinesFinder.class.getName()).log(Level.SEVERE,
                        null, ex);
            }
        }
    }



    private void recordesFacilActualizado(TabelaRecordes recordes) {
        lblNomeFacil.setText(recordes.getNome_jogador());
        lblTempoFacil.setText(Long.toString(recordes.getDuracao_jogo()/1000));
        guardarRecordesDisco();
    }
    private void recordesMedioActualizado(TabelaRecordes recordes) {
        lblNomeMedio.setText(recordes.getNome_jogador());
        lblTempoMedio.setText(Long.toString(recordes.getDuracao_jogo()/1000));
        guardarRecordesDisco();
    }
    private void recordesDificilActualizado(TabelaRecordes recordes) {
        lblNomeDificil.setText(recordes.getNome_jogador());
        lblTempoDificil.setText(Long.toString(recordes.getDuracao_jogo()/1000));
        guardarRecordesDisco();
    }

    private void sairButtonActionPerformed(ActionEvent e){
        System.exit(0); //é o que vai acontecer quando carregar no botão
    }

    private void btnFacilActionPerformed(ActionEvent e){

    }

    public static void main(String[] args) { new MinesFinder("Mines Finder").setVisible(true); } //é preciso fazer setVisible para tornar a janela visivel
}