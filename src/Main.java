import Neiro.*;
import com.google.gson.Gson;

public class Main {
    public static void main(String[] args) {

        int inp_size = 10;
        TextModule tm = new TextModule();
        Neiro neiro = new Neiro();
        neiro.add(inp_size);
        neiro.add(6);
        neiro.add(2);
        boolean[] inp = {true};
        neiro.initalize_new();
        neiro.use(randInputGener(inp_size));
        neiro.print_res();
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
