package GUI;

import java.io.*;

public class SerializationService {
    Notepad notepad;

    public SerializationService(Notepad notepad){
        this.notepad = notepad;
    }

    protected void writeNotepadPreferences () {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream("Notepad.ser");
            ObjectOutputStream ous = new ObjectOutputStream(fileOutputStream);
            ous.writeObject(notepad);
            ous.close();
            System.out.println("Сериализация выполнена");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    protected void readNotepadPreferences(){
        try{
            FileInputStream fileInputStream = new FileInputStream("Notepad.ser");
            ObjectInputStream ois = new ObjectInputStream(fileInputStream);
            Notepad restoreNotepad = (Notepad)ois.readObject();
            ois.close();
            System.out.println("Чтение объекта выполнено");
        }catch (IOException ex){
            ex.printStackTrace();
        }catch (ClassNotFoundException ex){
            ex.printStackTrace();
        }
    }
}
