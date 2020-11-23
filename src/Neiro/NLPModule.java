package Neiro;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class NLPModule {
    String fileName;

    public NLPModule(String fileName){
        this.fileName = fileName;
    }

    public String[] words(){
        String[] res;
        char[] read = read().toCharArray();
        int a = 0;
        boolean out = false;

        for (int i = 0; i < read.length; i++) {
            if (read[i] == ' ' || read[i] == '\n' || read[i] == ','){
                if (out) {
                    a++;
                    out = false;
                }
            } else {
                out = true;
            }
        }

        res = new String[a + 1];
        a=0;

        for (int i = 0; i < res.length; i++) {
            res[i] = "";
        }

//        for (int i = 0; i < read.length; i++) {
//            System.out.print(read[i]);
//        }

        out = false;

        for (int i = 0; i < read.length; i++) {
            if (read[i] == ' ' || read[i] == '\n' || read[i] == ','){
                if (out) {
                    a++;
                    out = false;
                }
            } else {
                out = true;
                res[a] += read[i];
            }
        }

        return res;
    }

    public String[] words(String text){
        String[] res;
        char[] read = read(text).toCharArray();
        int a = 0;
        boolean out = false;

        for (int i = 0; i < read.length; i++) {
            if (read[i] == ' ' || read[i] == '\n' || read[i] == ','){
                if (out) {
                    a++;
                    out = false;
                }
            } else {
                out = true;
            }
        }

        res = new String[a + 1];
        a=0;

        for (int i = 0; i < res.length; i++) {
            res[i] = "";
        }

//        for (int i = 0; i < read.length; i++) {
//            System.out.print(read[i]);
//        }

        out = false;

        for (int i = 0; i < read.length; i++) {
            if (read[i] == ' ' || read[i] == '\n' || read[i] == ','){
                if (out) {
                    a++;
                    out = false;
                }
            } else {
                out = true;
                res[a] += read[i];
            }
        }

        return res;
    }

    public String read(){
        String res = "";

        try {
            FileReader fr = new FileReader(fileName);

            while (fr.ready()){
                res += (char) fr.read();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return res;
    }

    public String read(String text){
        String res = "";

        try {
            FileReader fr = new FileReader(text);

            while (fr.ready()){
                res += (char) fr.read();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return res;
    }
}
