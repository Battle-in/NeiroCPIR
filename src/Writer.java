import Neiro.Neiro;
import Neiro.TextModule;

public class Writer {
    public static void main(String[] args) {
        Neiro neiro = new Neiro();
        TextModule textModule = new TextModule();
        neiro.add(3);
        neiro.add(2);
        neiro.add(1);
        neiro.initalize_new();
        boolean[] a = {false,true,false};
        neiro.use(a);
        neiro.print_res();
        neiro.print_all_dep();

        textModule.writeNet(neiro, "neiro");
    }
}
