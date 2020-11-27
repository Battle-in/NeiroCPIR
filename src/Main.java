import Neiro.*;
import com.google.gson.Gson;

public class Main {
    public static void main(String[] args) {

        int inp_size = 10;
        TextModule tm = new TextModule();
        Neiro neiro = tm.readNet("neiro");
        boolean[] a  = {false,true,false};
       // neiro.train();
        neiro.ll_sout();
        neiro.use(a);
        neiro.print_res();
        for (int i = 0; i < 10000; i++)
        neiro.train(0.0);
        neiro.use(a);
        neiro.print_all_dep();
        tm.writeNet(neiro,"neiro");
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
