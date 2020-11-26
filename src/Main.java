import Neiro.*;
import com.google.gson.Gson;

public class Main {
    public static void main(String[] args) {

        int inp_size = 10;
        TextModule tm = new TextModule();
        Neiro neiro = tm.readNet("main");
//        neiro.add(10);
//        neiro.add(6);
//        neiro.add(2);
//        neiro.initalize_new();
        boolean[] a  = {false,false};
        neiro.train();
        neiro.print_res();
        tm.writeNet(neiro,"main");
    }


    static boolean[] randInputGener(int i){
        boolean[] res = new boolean[i];

        for (int j = 0; j < i; j++) {
            if (Math.random() < 0.5){
                res[j] = true;
            } else {
                res[j] = false;
            }
        }

        return res;
    }
}
