package Neiro;
import com.google.gson.Gson;

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

    public void writeNet(Neiro neiro, String name){
        Gson g = new Gson();
        String json = g.toJson(neiro);
        dataBase.pushNetWorck(name, json);
    }

    public Neiro readNet(String name){
        Gson g = new Gson();
        return g.fromJson(dataBase.readNW(name), Neiro.class);
    }
}
