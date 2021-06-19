package GUI;

public class Main {

    public static void main(String[] args) {

        Notepad notepad = new Notepad();
        UserAuthService userAuthService = new UserAuthService();
        userAuthService.userAuthorization();
        notepad.startGui();
    }
}
