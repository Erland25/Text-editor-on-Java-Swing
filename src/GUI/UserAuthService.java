package GUI;

import javax.swing.*;

public class UserAuthService {
    
    private static final String MESSAGE = "Пожалуйста, введите ваш логин и пароль";
    private final Icon icon = new ImageIcon (getClass().getResource("Images/Enter.png"));
    private final JTextField userField = new JTextField();
    private final JPasswordField passField = new JPasswordField();

    protected void userAuthorization() {

        boolean isPasswordCorrect = false;
        do {
            int result = drawAuthorPane();
            char[] passEnter = passField.getPassword();

            if (result == JOptionPane.OK_OPTION) {
                if (checkPassword(passEnter)) {
                    drawInfoPane("Images/successEnter.png",
                                            "Добро пожаловать в TextEditorPRO!",
                                                     JOptionPane.INFORMATION_MESSAGE);
                    isPasswordCorrect = true;
                } else {
                    drawInfoPane("Images/wrongEnter.png",
                                            "Вы ввели некорректный пароль!",
                                                      JOptionPane.ERROR_MESSAGE);
                    passField.setText("");
                    passField.requestFocus();
                }
            }
            if (result == JOptionPane.CANCEL_OPTION || result == JOptionPane.CLOSED_OPTION) {
                System.exit(0);
            }
        } while (!isPasswordCorrect);
    }

    private void drawInfoPane(String iconName, String text, int errMess) {
        String login = userField.getText();
        if(login.isEmpty()) {
            login = "DefaultUser";
        }
        JOptionPane.showMessageDialog(null,
                new String[]{text + "\n" + "Логин: " + login},
                "TextEditorPRO", errMess,
                       new ImageIcon(getClass().getResource(iconName)));
    }

    private int drawAuthorPane() {
        int result = JOptionPane.showOptionDialog(null,
                new Object[]{MESSAGE, userField, passField},
                "TextEditorPRO", JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE, icon, null, null);
        return result;
    }

    private boolean checkPassword(char[] passEnter) {
        final char[] correctPassword = new char[]{'q', 'w', 'e'};

        if (correctPassword.length != passEnter.length) {
            return false;
        }

        for (int i = 0; i < correctPassword.length; i++) {
            if (correctPassword[i] != passEnter[i]) {
                return false;
            }
        }
        return true;
    }
}













