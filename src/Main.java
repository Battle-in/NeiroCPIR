import Neiro.*;
import com.google.gson.Gson;

public class Main {
    public static void main(String[] args) {

        int inp_size = 6;
        Neiro neiro = new Neiro();
        neiro.add(1);
        neiro.add(2);
        neiro.add(3);
        boolean[] inp = {true};
        neiro.initalize_new();
        neiro.use(inp);
        neiro.print_res();
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
