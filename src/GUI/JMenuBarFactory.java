package GUI;

import javax.swing.*;

public class JMenuBarFactory{

    private JMenu file, edit, view, text, fontStyle;
    private JMenuItem rewrite, saveAs, open, print, exit, cut, copy, paste;
    private JMenuBar menuBar;
    private TabManagerService tabManagerService;
    private OpenRewriteAndSaveService openRewriteAndSaveService;
    private EditorPaneService editorPaneService;

    public JMenuBarFactory(TabManagerService tabManagerService, OpenRewriteAndSaveService openRewriteAndSaveService, EditorPaneService editorPaneService) {
        this.tabManagerService = tabManagerService;
        this.openRewriteAndSaveService = openRewriteAndSaveService;
        this.editorPaneService = editorPaneService;
    }

    protected JMenuBar createJMenuBar() {
        menuBar = new JMenuBar();
        file =  createFileJMenu();
        edit = createEditJMenu();
        view = createViewJMenu();
        text = createTextJMenu();
        menuBar.add(file);
        menuBar.add(edit);
        menuBar.add(view);
        menuBar.add(text);
        return menuBar;
    }

   private JMenu createFileJMenu() {
        JMenu file = new JMenu("Файл");
        open = createOpenJMenuItem();
        rewrite = createRewriteJMenuItem();
        saveAs = createSaveAsJMenuItem();
        print = createPrintJMenuItem();
        exit = createExitJMenuItem();
        file.add(open);
        file.add(rewrite);
        file.add(saveAs);
        file.add(new JSeparator());
        file.add(print);
        file.add(new JSeparator());
        file.add(exit);
        return file;
    }

    private JMenuItem createOpenJMenuItem() {
        JMenuItem open = new JMenuItem("Открыть...");
        open.setIcon(new ImageIcon(getClass().getResource("Images/open.jpg")));
        open.addActionListener(event -> openRewriteAndSaveService.openFile(rewrite));
        return open;
    }

    private JMenuItem createRewriteJMenuItem() {
        JMenuItem rewrite = new JMenuItem("Перезаписать");
        rewrite.setIcon(new ImageIcon(getClass().getResource("Images/saveIcon.png")));
        rewrite.setEnabled(false);
        rewrite.addActionListener(event -> openRewriteAndSaveService.rewriteFile());
        return rewrite;
    }

    private JMenuItem createSaveAsJMenuItem() {
        JMenuItem saveAs = new JMenuItem("Сохранить как...");
        saveAs.setEnabled(false);
        saveAs.setIcon(new ImageIcon(getClass().getResource("Images/saveAs.png")));
        tabManagerService.setSaveAsJMenuItem(saveAs,rewrite);
        saveAs.addActionListener(event -> openRewriteAndSaveService.saveAsFile());
        return saveAs;
    }

    private JMenuItem createPrintJMenuItem() {
        JMenuItem print = new JMenuItem("Печать");
        print.setIcon(new ImageIcon(getClass().getResource("Images/print.jpg")));
        return print;
    }

    private JMenuItem createExitJMenuItem() {
        JMenuItem exit = new JMenuItem("Выход");
        exit.setIcon(new ImageIcon(getClass().getResource("Images/exit.png")));
        return exit;
    }

    private JMenu createViewJMenu() {
        JMenu view = new JMenu("Вид");
        JCheckBoxMenuItem line = new JCheckBoxMenuItem("Линейка");
        JCheckBoxMenuItem grid = new JCheckBoxMenuItem("Сетка");
        JCheckBoxMenuItem navigation = new JCheckBoxMenuItem("Навигация");
        JRadioButton onePage = new JRadioButton("Одна страница");
        JRadioButton twoPage = new JRadioButton("Две страницы");
        view.add(line);
        view.add(grid);
        view.add(navigation);
        view.add(new JSeparator());
        view.add(onePage);
        view.add(twoPage);
        return view;
    }

    private JMenu createEditJMenu() {
        JMenu edit = new JMenu("Редактирование");
        cut = new JMenuItem("Вырезать");
        copy = new JMenuItem("Копировать");
        paste = new JMenuItem("Вставить");
        setEditActions();
        setIconsCutCopyPaste();
        edit.add(cut);
        edit.add(copy);
        edit.add(paste);
        return edit;
    }

    private void setEditActions() {
        cut.addActionListener(event -> editorPaneService.execute("cut"));
        copy.addActionListener(event -> editorPaneService.execute("copy"));
        paste.addActionListener(event -> editorPaneService.execute("paste"));
    }

    private void setIconsCutCopyPaste() {
        cut.setIcon(new ImageIcon(getClass().getResource("Images/scissors.png")));
        copy.setIcon(new ImageIcon(getClass().getResource("Images/copy.png")));
        paste.setIcon(new ImageIcon(getClass().getResource("Images/dobavit.png")));
    }

    private JMenu createTextJMenu() {
        JMenu text = new JMenu("Текст");
        JMenuItem fontSize = new JMenuItem("Размер");
        fontStyle = createFontstyleJMenu();
        text.add(fontSize);
        text.add(fontStyle);
        return text;
    }

    private JMenu createFontstyleJMenu() {
        JMenu fontStyle = new JMenu("Стиль");
        JMenuItem arial = new JMenuItem("Arial");
        JMenuItem times = new JMenuItem("Times");
        JMenuItem calibri = new JMenuItem("Calibri");
        JMenuItem bold = new JMenuItem("Жирный");
        JMenuItem italic = new JMenuItem("Курсив");
        fontStyle.add(bold);
        fontStyle.add(italic);
        JMenu font = new JMenu("Шрифт");
        view.add(new JSeparator());
        font.add(arial);
        font.add(times);
        font.add(calibri);
        fontStyle.add(font);
        return fontStyle;
    }

    private void addElementsToParent(Object parent, Object[] elements) {

    }

}
