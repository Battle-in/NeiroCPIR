import Neiro.Neiro;
import Neiro.TextModule;

public class Writer {
    public static void main(String[] args) {
        Neiro neiro = new Neiro();
        TextModule textModule = new TextModule();
        neiro.add(100);
        neiro.add(50);
        neiro.add(3);
        neiro.initalize_new();
        boolean[] a = {false,true,false,false,true};
        neiro.use(a);
        neiro.print_res();
        neiro.print_all_dep();

        textModule.writeNet(neiro, "neiro");
    }
}
