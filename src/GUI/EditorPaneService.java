package GUI;

import javax.swing.*;
import java.awt.*;

public class EditorPaneService {

    private TabManagerService tabManagerService;

    protected void setTabManagerService(TabManagerService tabManagerService) {
        this.tabManagerService = tabManagerService;
    }

    protected JButton getSimpleFontButton() {
        JButton simpleFont = new JButton("Обычный");
        simpleFont.addActionListener(event ->
                this.setSimpleFont());
        return simpleFont;
    }

    private void setSimpleFont() {
        if (tabManagerService.getTabbedPaneStatus() != -1) {
            JEditorPane currentEditor = tabManagerService.getCurrentEditor();
            currentEditor.setFont(new Font("TimesRoman", Font.PLAIN, currentEditor.getFont().getSize()));
        }
    }

    protected JButton getKursivFontButton() {
        JButton kursivFont = new JButton("Курсив");
        kursivFont.addActionListener(event ->
                this.setKursivFont());
        return kursivFont;
    }

    private void setKursivFont() {
        if (tabManagerService.getTabbedPaneStatus() != -1) {
            JEditorPane currentEditor = tabManagerService.getCurrentEditor();
            currentEditor.setFont(new Font("TimesRoman", Font.ROMAN_BASELINE, currentEditor.getFont().getSize()));
        }
    }

    protected JButton getBoldFont() {
        JButton boldFontButton = new JButton("Жирный");
        boldFontButton.setFont(new Font("TimesRoman", Font.BOLD, 14));
        return boldFontButton;
    }

    protected JComboBox<String> getFontComboBox() {
        String[] fontList = {"Шрифт 1", "Шрифт 2", "Шрифт 3", "Шрифт 4"};
        JComboBox<String> fontComboBox = new JComboBox(fontList);
        return fontComboBox;
    }

    protected JComboBox<String> getSizeComboBox() {
        String[] fontSize = {"8", "10", "12", "14", "16", "18", "20", "22", "24", "26", "28", "30"};
        JComboBox<String> fontSizeComboBox = new JComboBox(fontSize);
        return fontSizeComboBox;
    }

    protected void changeEditorFontSize() {
        if (tabManagerService.getTabbedPaneStatus() != -1) {
            JEditorPane currentEditor = tabManagerService.getCurrentEditor();
            JComboBox<String> fontSizeComboBox = getSizeComboBox();
            String selectedFontSize = String.valueOf(fontSizeComboBox.getSelectedItem());
            switch (selectedFontSize) {
                case "8":
                    Font currentFont = currentEditor.getFont();
            }
        }
    }

    protected void cleanEditorText() {
        if (tabManagerService.getTabbedPaneStatus() != -1) {
            JEditorPane currentEditor = tabManagerService.getCurrentEditor();
            currentEditor.setText("");
        }
    }

    protected void changeEditorPaneColor() {
        if (tabManagerService.getTabbedPaneStatus() != -1) {
            Icon icon = new ImageIcon(getClass().getResource("Images/editorPaneColorOption.png"));
            JEditorPane currentEditor = tabManagerService.getCurrentEditor();
            String[] colorList = {"Белый (по умолчанию)", "Серый (light)", "Cерый (dark)", "Черный"};
            JDialog colorDialog = new JDialog();
            JComboBox<String> colorBox = new JComboBox(colorList);
            colorDialog.add(colorBox);
            int result = JOptionPane.showOptionDialog(null,
                    new Object[]{"Цвет", colorBox},
                    "Выберите цвет фона редактора", JOptionPane.OK_CANCEL_OPTION,
                    JOptionPane.QUESTION_MESSAGE, icon, null, null);
            if (result == JOptionPane.OK_OPTION) {
                String selectedColor = String.valueOf(colorBox.getSelectedItem());
                switch (selectedColor) {
                    case "Белый (по умолчанию)":
                        currentEditor.setBackground(Color.WHITE);
                        break;
                    case "Серый (light)":
                        currentEditor.setBackground(Color.lightGray);
                        break;
                    case "Cерый (dark)":
                        currentEditor.setBackground(Color.darkGray);
                        break;
                    case "Черный":
                        currentEditor.setBackground(Color.BLACK);
                        break;
                }
            }
        }
    }

    protected void execute(String command) {
        if (tabManagerService.getTabbedPaneStatus() != -1) {
            JEditorPane currentEditor = tabManagerService.getCurrentEditor();

            switch (command) {
                case "cut":
                    currentEditor.cut();
                    break;
                case "copy":
                    currentEditor.copy();
                    break;
                case "paste":
                    currentEditor.paste();
                    break;
            }
        }
    }
}


