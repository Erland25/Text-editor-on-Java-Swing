package GUI;

import javax.swing.*;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;
import java.io.IOException;
import java.net.URL;

public class InternetService {

    private TabManagerService tabManagerService;
    private JTextField urlField;

    public InternetService(TabManagerService tabManagerService) {
        this.tabManagerService = tabManagerService;
    }

    protected JTextField getURLField(){
        urlField = new JTextField();
        urlField.addActionListener(event -> openURL(event.getActionCommand()));
        return urlField;
    }

    private void openURL(String urlString) {
        try {
            URL url = new URL(urlString);
            JEditorPane currentEditorPane = tabManagerService.getCurrentEditor();
            currentEditorPane.addHyperlinkListener(new LinkActivator());
            currentEditorPane.setEditable(false);
            currentEditorPane.setPage(url);
            urlField.setText(url.toExternalForm());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    class LinkActivator implements HyperlinkListener {
        @Override
        public void hyperlinkUpdate(HyperlinkEvent e) {
            HyperlinkEvent.EventType type = e.getEventType();
            if (type == HyperlinkEvent.EventType.ACTIVATED) {
                openURL(e.getURL().toExternalForm());
            }
        }
    }
}
