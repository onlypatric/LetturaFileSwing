
import javax.swing.*;

import java.awt.BorderLayout;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;

/*
 * @author Patric Pintescul
 * @version 1.2 (10 maggio 2024)
 * semplicissimo java txt file manager
 */
public class LetturaFileSwing extends JFrame {
  private JComboBox<String> fileComboBox;
  private JTextArea textArea;
  private File path;

  // inizializzazione GUI e componenti base
  public LetturaFileSwing() {
    setSize(400, 300);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLayout(new BorderLayout());

    // ComboBox per selezionare il file
    fileComboBox = new JComboBox<>();
    fileComboBox.addActionListener(e -> {
      // per la lettura del file
      String selectedFile = (String) fileComboBox.getSelectedItem();
      if (selectedFile != null) {
        try {
          readFile(selectedFile);
        } catch (IOException ex) {
          ex.printStackTrace();
          JOptionPane.showMessageDialog(
              LetturaFileSwing.this, "Errore durante la lettura del file.", "Errore",
              JOptionPane.ERROR_MESSAGE);
        }
      }
    });
    // apri file e seleziona cartella in un elemento orizzontale
    add(new Horizontal(fileComboBox, new Button("Open").action(e -> {
      LetturaFileSwing.this.loadDir();
    })), BorderLayout.NORTH);

    // TextArea per visualizzare il contenuto del file
    textArea = new JTextArea();
    JScrollPane scrollPane = new JScrollPane(textArea);
    add(new Vertical(scrollPane,new Button("Salva").action(e->{
      LetturaFileSwing.this.save();})), BorderLayout.CENTER);

    // Caricamento dei file nella ComboBox
    loadFiles(new File("./"));

    JMenuBar mb = new JMenuBar();
    JMenu menu = new JMenu("File");
    JMenuItem newItem = new JMenuItem("Nuovo");
    newItem.addActionListener(e->{LetturaFileSwing.this.addNewItem();});
    mb.add(menu);
    menu.add(newItem);
    setJMenuBar(mb);

    setLocationRelativeTo(null);
  }

  // Metodo per aggiungere un nuovo file
  private void addNewItem(){
    // apri una schermata di dialogo e richiedi nome del file senza estensione
    String fileName = JOptionPane.showInputDialog(this, "Inserisci il nome del file:");
    if (fileName != null && !fileName.isEmpty()) {
      // crea un nuovo file con il nome inserito
      File newFile = this.path.toPath().resolve(fileName + ".txt").toFile();
      try {
        // crea il file e salva il contenuto nella JTextArea
        newFile.createNewFile();
        textArea.setText("");
        loadFiles(this.path);
      } catch (IOException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(
            LetturaFileSwing.this, "Errore durante la creazione del file.", "Errore",
            JOptionPane.ERROR_MESSAGE);
      }
    }
  }

  // Metodo per caricare un'altra directory
  private void loadDir() {
    JFileChooser fileChooser = new JFileChooser();
    fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
    int result = fileChooser.showOpenDialog(this);
    if (result == JFileChooser.APPROVE_OPTION) {
      File selectedDir = fileChooser.getSelectedFile();
      loadFiles(selectedDir);
    }
  }

  // Metodo per salvare il contenuto della JTextArea nel file selezionato
  private void save(){
    if(this.path!=null){
      Path selectedFile = new File(fileComboBox.getSelectedItem().toString()).toPath();
      Path currentPath = this.path.toPath();
      File temp = currentPath.resolve(selectedFile).toFile();
      try {
        FileWriter fw = new FileWriter(temp);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(textArea.getText());
        bw.close();
        fw.close();
      } catch (IOException e) {}
    }
  }

  // Metodo per caricare i file nella ComboBox
  private void loadFiles(File path) {
    this.path = path;
    this.setTitle(path.getAbsolutePath());
    this.fileComboBox.removeAllItems();
    File[] files = path.listFiles();
    if (files != null) {
      for (File file : files) {
        if (file.isFile() && file.getName().toLowerCase().endsWith(".txt")) {
          fileComboBox.addItem(file.getName());
        }
      }
    }
    this.fileComboBox.repaint();
    this.fileComboBox.revalidate();
  }

  // Metodo per leggere il contenuto del file e visualizzarlo nella JTextArea
  private void readFile(String fileName) throws IOException {
    File file = this.path.toPath().resolve(fileName).toFile();
    BufferedReader reader = new BufferedReader(new FileReader(file));
    StringBuilder content = new StringBuilder();
    String line;
    while ((line = reader.readLine()) != null) {
      content.append(line).append("\n");
    }
    reader.close();
    textArea.setText(content.toString());
  }

  public static void main(String[] args) {
    SwingUtilities.invokeLater(()->{new LetturaFileSwing().setVisible(true);});
  }
}