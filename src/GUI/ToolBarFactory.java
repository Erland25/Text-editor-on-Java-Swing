package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ToolBarFactory {

    private TabManagerService tabManagerService;
    private InternetService internetService;
    private EditorPaneService editorPaneService;
    private JTextField urlField;

    public ToolBarFactory (TabManagerService tabManagerService,InternetService internetService,EditorPaneService editorPaneService) {
        this.tabManagerService = tabManagerService;
        this.internetService = internetService;
        this.editorPaneService =  editorPaneService;
        editorPaneService.setTabManagerService(tabManagerService);
    }

    protected JToolBar createGetAndOpenToolbar() {
        JToolBar getAndOpenToolbar = createNotFloatableToolbar();
        urlField = internetService.getURLField();
        JButton getCode = new JButton("HTML");
        JButton clearURLbutton = getClearURLButton();
        getAndOpenToolbar.add(getCode);
        getAndOpenToolbar.add(clearURLbutton);
        getAndOpenToolbar.add(new JLabel("             Адрес(URL):     "));
        getAndOpenToolbar.add(urlField);
        return getAndOpenToolbar;
    }

    protected JToolBar createFontAndTabsToolbar() {
        JToolBar fontAndTabsToolbar = createNotFloatableToolbar();
        fontAndTabsToolbar.setLayout(new FlowLayout(FlowLayout.LEFT));
        fontAndTabsToolbar.add(tabManagerService.getAddTabButton()); //кнопка добавления Таба
        fontAndTabsToolbar.add(tabManagerService.getRemoveTabButton()); //кнопка удаления Таба

        JButton simpleFont = editorPaneService.getSimpleFontButton();
        JButton kursivFont = editorPaneService.getKursivFontButton();
        JButton boldFont = editorPaneService.getBoldFont();
        JComboBox<String> fontBox = editorPaneService.getFontComboBox();
        JComboBox<String> fontSizeBox = editorPaneService.getSizeComboBox();
        fontSizeBox.addActionListener(event-> editorPaneService.changeEditorFontSize());
        JButton cleanEditorTextButton = getCleanEditorTextButton();
        JButton editorPaneColorButton = getEditorPaneColorButton();

        fontAndTabsToolbar.add(simpleFont);
        fontAndTabsToolbar.add(kursivFont);
        fontAndTabsToolbar.add(boldFont);
        fontAndTabsToolbar.add(fontBox);
        fontAndTabsToolbar.add(fontSizeBox);
        fontAndTabsToolbar.add(cleanEditorTextButton);
        fontAndTabsToolbar.add(editorPaneColorButton);
        return fontAndTabsToolbar;
    }

    protected JToolBar createAdditionalOptionsToolbar() {
        JToolBar additionalOptionsToolbar = createNotFloatableToolbar();
        additionalOptionsToolbar.setLayout(new FlowLayout(FlowLayout.LEFT));
        return additionalOptionsToolbar;
    }

    private JToolBar createNotFloatableToolbar() {
        JToolBar toolBar = new JToolBar();
        toolBar.setFloatable(false);
        toolBar.addSeparator();
        return toolBar;
    }

    private JButton getClearURLButton(){
        JButton clearURLbutton = new JButton("Очистить URL");
        clearURLbutton.setToolTipText("Нажмите, чтобы очистить адресную строку");
        clearURLbutton.addActionListener(event-> urlField.setText(""));
        return clearURLbutton;
    }

    private JButton getCleanEditorTextButton(){
        Icon icon = new ImageIcon(getClass().getResource("Images/cleanEditorText.png"));
        JButton cleanEditorTextButton = new JButton(icon);
        cleanEditorTextButton.setToolTipText("Нажмите, чтобы очистить текущую страницу редактора");
        cleanEditorTextButton.addActionListener(event-> editorPaneService.cleanEditorText());
        return cleanEditorTextButton;
    }

    private JButton getEditorPaneColorButton(){
        Icon icon = new ImageIcon(getClass().getResource("Images/editorPaneColor.png"));
        JButton editorPaneColorButton = new JButton(icon);
        editorPaneColorButton.setToolTipText("Изменить фон редактора");
        editorPaneColorButton.addActionListener(event-> editorPaneService.changeEditorPaneColor());
        return editorPaneColorButton;
    }
}


