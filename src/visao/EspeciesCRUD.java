package visao;

import controle.ControleEspecie;
import modelo.Especie;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EspeciesCRUD extends JFrame {

    private JFrame frame;
    private JButton removerEspécieButton;
    private JButton alterarEspécieButton;
    private JButton adicionarEspécieButton;
    private JPanel mainPanel;
    private JButton voltarButton;
    private JTextField tfespecie;
    private JTextField tfalt_cod;
    private JTextField tfrem_cod;
    private JButton remove_irButton;
    private JButton alt_irButton;
    private JPanel altPanel;
    private JPanel removePanel;
    private JTextField tfalt_especie;
    private JTextField tfrem_especie;
    private JPanel remove_error;
    private JPanel alt_error;
    private JTextField tfcod;
    private JPanel alt_error_2;
    private JPanel remove_error_2;
    private JPanel add_error;

    public EspeciesCRUD(String caller) {

        ControleEspecie ce = new ControleEspecie();

        frame = new JFrame("Especies");
        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setSize(400, 350);


        //Definições
        altPanel.setVisible(false);
        removePanel.setVisible(false);
        add_error.setVisible(false);
        alt_error.setVisible(false);
        alt_error_2.setVisible(false);
        remove_error.setVisible(false);
        remove_error_2.setVisible(false);
        tfrem_especie.setEditable(false);


        //BOTÕES

        //busca especie para alteração
        alt_irButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if(ce.buscaEspecie(Integer.parseInt(tfalt_cod.getText())) == null){
                    alt_error.setVisible(true);
                }else{
                    Especie e = ce.buscaEspecie(Integer.parseInt(tfalt_cod.getText()));
                    altPanel.setVisible(true);
                    tfalt_cod.setText(Integer.toString(e.getCodigo()));
                    tfalt_especie.setText(e.getDescricao());

                    if (!altPanel.isVisible())
                        frame.setSize(frame.getWidth(), frame.getHeight() + 25);
                    altPanel.setVisible(true);
                }

            }
        });
        //busca especie para remoção
        remove_irButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if(ce.buscaEspecie(Integer.parseInt(tfrem_cod.getText())) == null){
                    remove_error.setVisible(true);
                }else {
                    Especie e = ce.buscaEspecie(Integer.parseInt(tfrem_cod.getText()));
                    removePanel.setVisible(true);
                    tfrem_cod.setText(Integer.toString(e.getCodigo()));
                    tfrem_especie.setText(e.getDescricao());

                    if (!removePanel.isVisible())
                        frame.setSize(frame.getWidth(), frame.getHeight() + 25);
                    removePanel.setVisible(true);
                }
            }
        });
        //retorna a tela Gerenciar
        voltarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                GerenciarAnimais g = new GerenciarAnimais(caller);
                frame.dispose();
            }
        });

        adicionarEspécieButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if(!ce.adicionaEspecie(tfespecie.getText()))
                    add_error.setVisible(true);
            }
        });

        alterarEspécieButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if(!ce.alteraEspecie(Integer.parseInt(tfalt_cod.getText()), tfalt_especie.getText())) {
                    alt_error_2.setVisible(true);
                }
            }
        });

        removerEspécieButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if(!ce.removeEspecie(Integer.parseInt(tfrem_cod.getText()))) {
                    remove_error_2.setVisible(true);
                }
            }
        });
    }

}
