package GUI;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.*;

public class OpenRewriteAndSaveService {

    private TabManagerService tabManagerService;
    private File file;

    public OpenRewriteAndSaveService(TabManagerService tabManagerService) {
        this.tabManagerService = tabManagerService;
    }

    protected void openFile(JMenuItem save) {
        if(tabManagerService.getTabbedPaneStatus() == -1) {
            tabManagerService.addTab();
        }
        JEditorPane currentEditor = tabManagerService.getCurrentEditor();
        JFileChooser fileChooser = new JFileChooser();
        int currentVal = fileChooser.showOpenDialog(null);
        if (currentVal == JFileChooser.APPROVE_OPTION) {
            file = fileChooser.getSelectedFile();
                FileNameExtensionFilter filter = new FileNameExtensionFilter("Текстовые файлы", "txt");
                fileChooser.setFileFilter(filter);
                try {
                    BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
                    currentEditor.read(bufferedReader, null);
                    bufferedReader.close();
                    save.setEnabled(true);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
        }
    }

    protected void rewriteFile() {
        JEditorPane currentEditor = tabManagerService.getCurrentEditor();
        Icon icon = new ImageIcon(getClass().getResource("Images/save.png"));
        String message1 = "Вы уверены, что хотите перезаписать файл?" + "\n" + "Имя файла: "
                + file.getName();
        String message2 = "Ваш файл успешно перезаписан!";
        int result = JOptionPane.showOptionDialog(null,
                new Object[]{message1}, "TextEditorPRO", JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE, icon, null, null);
        if (result == JOptionPane.OK_OPTION) {
            try {
                if (file != null && result == JFileChooser.APPROVE_OPTION) {
                    FileWriter fileWriter = new FileWriter(file);
                    String editorText = currentEditor.getText();
                    fileWriter.write(editorText);
                    fileWriter.close();
                    JOptionPane.showMessageDialog(null,
                                new String[]{message2, "\n", "Имя файла: "
                                        + file.getName()},
                                "TextEditorPRO", JOptionPane.OK_OPTION, icon);
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    protected void saveAsFile() {
        JEditorPane currentEditor = tabManagerService.getCurrentEditor();
        String editorText;
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Текстовые файлы", "txt");
        fileChooser.setFileFilter(filter);
        int result = fileChooser.showSaveDialog(null);
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        file = fileChooser.getSelectedFile();
        try {
            if (file != null && result == JFileChooser.APPROVE_OPTION) {
                FileWriter fileWriter = new FileWriter(file);
                editorText = currentEditor.getText();
                fileWriter.write(editorText);
                fileWriter.close();
                JOptionPane.showMessageDialog(fileChooser.getParent(),
                        "Ваш файл успешно сохранен!" + "\n" + "\n"
                                + "Путь: " + fileChooser.getSelectedFile() + "\n" + "Имя файла: "
                                + file.getName());
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
