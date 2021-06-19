package GUI;

import javax.swing.*;
import java.awt.*;

public class SerializationPanel {

    private SerializationService serializationService;

    public SerializationPanel(SerializationService serializationService) {
        this.serializationService= serializationService;
    }

    protected JPanel getSerializationPanel(){
        JPanel serializationPanel = new JPanel();
        serializationPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        serializationPanel.setBackground(Color.lightGray);
        JButton rememberPreferencesButton = this.getRememberPreferencesButton();
        JButton restorePreferencesButton = this.getRestorePreferencesButton();
        serializationPanel.add(rememberPreferencesButton);
        serializationPanel.add(restorePreferencesButton);
        return serializationPanel;
    }

    private JButton getRememberPreferencesButton(){
        JButton rememberPreferencesButton = new JButton("Сохранить настройки редактора");
        rememberPreferencesButton.addActionListener(event -> serializationService.writeNotepadPreferences());
        return rememberPreferencesButton;
    }

    private JButton getRestorePreferencesButton(){
        JButton restorePreferencesButton = new JButton("Восстановить настройки редактора");
        restorePreferencesButton.addActionListener(event -> serializationService.readNotepadPreferences());
        return restorePreferencesButton;
    }
}
