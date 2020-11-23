package Neiro;

import java.io.FileReader;
import java.io.IOException;

public class DataSort {




    public String[] readStringMass(String filename) {
        String[] b = stringToMass(stringReader(filename));
        int quantityWords = 0;

        while (!b[quantityWords].equals("")) {
            quantityWords++;
        }
        String[] res = new String[quantityWords];
        for (int i = 0; i < quantityWords; i++) {
            if (!b[i].equals(null) || !b[i].equals('\n')){
                res[i] = b[i].toLowerCase();
            }
        }

        return res;
    }

    public String[] stringToMass(String inputSting) { //принимает стринг,возвращает чистый массив на сотку
        char[] sim = inputSting.toCharArray();
        String[] strMass = new String[200000];
        boolean repeat = false;
        int b = 0;
        int c = 0;

        for (int n = 0; n < strMass.length; n++)
            strMass[n] = "";

        for (int a = 0; a < sim.length; a++) {
            if (sim[a] == ' ' || sim[a] == ':' ||
                    sim[a] == '@' || sim[a] == ';' ||
                    sim[a] == '%' || sim[a] == '.' ||
                    sim[a] == '?' || sim[a] == '!' ||
                    sim[a] == ',' || sim[a] == '\n') {
                b++;
                if (a < (sim.length - 1)) {
                    for (int i = 1; sim[(a + i)] == ' ' || sim[(a + i)] == ':' ||
                            sim[(a + i)] == '@' || sim[(a + i)] == ';' ||
                            sim[(a + i)] == '%' || sim[(a + i)] == '.' ||
                            sim[(a + i)] == '?' || sim[(a + i)] == '!' ; i++)
                        c = i;
                }
                a += c;
                c = 0;
            } else {
                 strMass[b] += sim[a];
                repeat = true;
            }
        }
        return strMass;
    }


    public String stringReader(String filename) {

        String a = "";

        try (FileReader reader = new FileReader(filename)) {
            // читаем посимвольно
            int c;
            while ((c = reader.read()) != -1) {
                a += ((char) c);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return a;
    }
}
