package GUI;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class TabManagerService {

    private final JTabbedPane tabbedPane;
    public static int tabNumber = 0;
    private static int editorIndex = 0;
    private static ArrayList <JEditorPane> allEditors = new ArrayList();
    private JMenuItem saveAs,rewrite;

    public TabManagerService(JTabbedPane tabbedPane) {
        this.tabbedPane = tabbedPane;
    }

    protected void addTab() {
        JEditorPane editor = new JEditorPane();
        allEditors.add(editor);
        JScrollPane pane = new JScrollPane(allEditors.get(editorIndex));
        pane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        pane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        tabNumber = tabbedPane.getTabCount() + 1;
        String currentTabIndex = "Вкладка " + tabNumber;
        tabbedPane.addTab(currentTabIndex, pane);
        rewrite.setEnabled(false);
        saveAs.setEnabled(true);
        editorIndex++;
    }

    protected int getTabbedPaneStatus(){
        int status =  tabbedPane.getSelectedIndex();
        return status;
    }

    protected JEditorPane getCurrentEditor (){
        JEditorPane currentEditor = allEditors.get(tabbedPane.getSelectedIndex());
        return currentEditor;
    }

    protected JButton getAddTabButton() {
        Icon icon = new ImageIcon(getClass().getResource("Images/addTabb.png"));
        JButton buttonAddTabb = new JButton(icon);
        buttonAddTabb.setToolTipText("Добавить новую вкладку");
        buttonAddTabb.addActionListener(event->this.addTab());
        return buttonAddTabb;
    }

    protected JButton getRemoveTabButton() {
        Icon icon = new ImageIcon(getClass().getResource("Images/deleteTabb.png"));
        JButton buttonRemoveTab = new JButton(icon);
        buttonRemoveTab.setToolTipText("Удалить текущую вкладку");
        buttonRemoveTab.addActionListener(event->this.deleteTabb());
        return buttonRemoveTab;
    }

    protected void deleteTabb() {
        Component removingComponent = tabbedPane.getSelectedComponent();
        tabbedPane.remove(removingComponent);
        if(this.getTabbedPaneStatus()==-1){
            saveAs.setEnabled(false);
        }
    }

    protected void setSaveAsJMenuItem(JMenuItem saveAs, JMenuItem rewrite){
        this.saveAs = saveAs;
        this.rewrite = rewrite;
    }
}