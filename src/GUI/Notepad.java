package GUI;

import javax.swing.*;
import java.awt.*;

import static java.awt.BorderLayout.*;
import static javax.swing.BoxLayout.Y_AXIS;

public class Notepad extends JPanel {

    private static JFrame frame;
    private static JTabbedPane tabbedPane;
    private static SerializationService serializationService;
    private static JMenuBarFactory menuBarFactory;
    private static ToolBarFactory toolBarFactory;
    private static TabManagerService tabManagerService;
    private static OpenRewriteAndSaveService openRewriteAndSaveService;
    private static InternetService internetService;
    private static EditorPaneService editorPaneService;


    public Notepad() {
        tabbedPane = new JTabbedPane();
        serializationService = new SerializationService(this);
        tabManagerService = new TabManagerService(tabbedPane);
        openRewriteAndSaveService = new OpenRewriteAndSaveService(tabManagerService);
        internetService = new InternetService(tabManagerService);
        editorPaneService = new EditorPaneService();
        menuBarFactory = new JMenuBarFactory(tabManagerService,openRewriteAndSaveService,editorPaneService);
        toolBarFactory = new ToolBarFactory(tabManagerService,internetService,editorPaneService);
    }

    protected void startGui() {
        JPanel mainPanel = createMainPanel();
        JMenuBar menuBar = menuBarFactory.createJMenuBar();
        frame = createFrame();
        frame.setJMenuBar(menuBar);
        frame.getContentPane().add(mainPanel);
    }

    private JFrame createFrame() {
        JFrame frame = new JFrame("TextEditor_PRO V1.0");
        frame.setSize(1000, 800);
        frame.setLocation(400, 150);
        frame.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("Images/mainImage.jpg")));
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        return frame;
    }

    private JPanel createMainPanel() {
        JPanel mainPanel = new JPanel();
        JPanel toolbarsPanel = createToolbarsPanel(toolBarFactory);
        JPanel tabbedEditorPanel = createTabbedEditorPanel();
        SerializationPanel serializationPanel = new SerializationPanel(serializationService);
        JPanel bottomPanel = serializationPanel.getSerializationPanel();
        JPanel rightPanel = new JPanel();
        rightPanel.setBackground(Color.lightGray);
        JPanel leftPanel = new JPanel();
        leftPanel.setBackground(Color.lightGray);
        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(tabbedEditorPanel);
        mainPanel.add(toolbarsPanel, NORTH);
        mainPanel.add(bottomPanel, SOUTH);
        mainPanel.add(rightPanel, EAST);
        mainPanel.add(leftPanel, WEST);
        return mainPanel;
    }

    private JPanel createTabbedEditorPanel() {
        JPanel tabbedEditorPanel = new JPanel();
        tabbedEditorPanel.setLayout(new BoxLayout(tabbedEditorPanel, Y_AXIS));
        tabbedEditorPanel.setBackground(Color.lightGray);
        tabbedEditorPanel.add(tabbedPane);
        return tabbedEditorPanel;
    }

    private JPanel createToolbarsPanel(ToolBarFactory toolBarFactory) {
        JPanel toolbarsPanel = new JPanel();
        toolbarsPanel.setLayout(new BoxLayout(toolbarsPanel, Y_AXIS));
        toolbarsPanel.add(toolBarFactory.createGetAndOpenToolbar());
        toolbarsPanel.add(toolBarFactory.createFontAndTabsToolbar());
        toolbarsPanel.add(toolBarFactory.createAdditionalOptionsToolbar());
        return toolbarsPanel;
    }
}
