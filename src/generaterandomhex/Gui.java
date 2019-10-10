package generaterandomhex;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import static javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED;

/**
 *
 * @author henrique
 */
public class Gui extends JFrame {

    JTextArea textarea;
    JPanel panel;
    JScrollPane scroll;
    JLabel label;

    public Gui() {
        setTitle("Gerador de Numeros Hexadecimais");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(600, 650);
        setLocationRelativeTo(null);
        setLayout(null);
        setResizable(false);
        setVisible(true);
        add(createGenerateNumberButton());
        add(addLabel());
        add(addTextFIeld());
        add(addScroll());
        add(createExportAsTxtButton());
        add(createExportAsCsvButton());

    }

    private JButton createGenerateNumberButton() {
        JButton button = new JButton("Gerar Numeros");
        button.setBounds(220, 50, 160, 40);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                shownum();

            }
        });

        return button;
    }

    private JTextArea addTextFIeld() {
        this.textarea = new JTextArea();
        textarea.setBounds(55, 100, 500, 400);
        textarea.setLineWrap(true);
        textarea.setEditable(false);
        textarea.setCaretPosition(0);
        return textarea;

    }

    private JScrollPane addScroll() {
        JScrollPane scroll = new JScrollPane(textarea);
        scroll.setBounds(55, 100, 500, 400);
        scroll.setVerticalScrollBarPolicy(VERTICAL_SCROLLBAR_AS_NEEDED);
        add(scroll);
        return scroll;
    }

    private JButton createExportAsTxtButton() {
        JButton button = new JButton("Salvar como .txt");
        button.setBounds(55, 520, 160, 40);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    exportAsTxt();

                } catch (FileNotFoundException ex) {
                    Logger.getLogger(Gui.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        return button;
    }

    private JButton createExportAsCsvButton() {
        JButton button = new JButton("Salvar como .csv");
        button.setBounds(255, 520, 160, 40);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    exportAsCsv();

                } catch (FileNotFoundException ex) {
                    Logger.getLogger(Gui.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        return button;
    }

    private JLabel addLabel() {
        this.label = new JLabel("V2.0 HenricoLabs.");
        label.setFont(new Font("Courier New", Font.ITALIC, 12));
        label.setBounds(450, 520, 300, 100);
        label.setVisible(true);

        return label;
    }

    public String shownum() {

        Random rand = new Random();
        char[] charArr = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

        String result = "";
        String result2 = "";
        for (int a = 0; a < 50; a++) {

            for (int x = 0; x < 20; x++) {
                int resInt = rand.nextInt(charArr.length);
                if (x % 4 == 0) {
                    result = result + " ";
                }
                result += charArr[resInt];

            }

            result2 = result2 + result.trim() + "\n";
            this.textarea.setText(result2);
            result = "";

        }

        return result2;
    }

    public void exportAsTxt() throws FileNotFoundException {

        JFileChooser fc = new JFileChooser();
        fc.showSaveDialog(this);
        File f = fc.getSelectedFile();

        try {
            FileWriter fw = new FileWriter(f);
            String txt = this.textarea.getText();
            fw.write(txt);

            fw.close();

        } catch (IOException e) {
            System.out.println(e);
        }

    }

    public void exportAsCsv() throws FileNotFoundException {

        JFileChooser jfc = new JFileChooser();
        jfc.showSaveDialog(this);
        File f2 = jfc.getSelectedFile();

        try {
            FileWriter fwr = new FileWriter(f2 + ".csv", true);
            BufferedWriter bfr = new BufferedWriter(fwr);
            PrintWriter pwr = new PrintWriter(bfr);

            pwr.println(this.textarea.getText());
            pwr.flush();
            pwr.close();

        } catch (Exception e) {
            System.out.println(e);
        }

    }

}
