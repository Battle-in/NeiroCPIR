package Neiro;

public class TextModule {
    DB dataBase;
    DataSort dataSort;

    public TextModule(){
        dataBase = new DB();
    }

    public void addText(String filename, String theme){
        dataSort = new DataSort();
        String[] a = dataSort.readStringMass(filename);
        dataBase.setTheme(theme);
        for (int i = 0; i < a.length; i++) {
            dataBase.push(a[i]);
        }
    }
}
