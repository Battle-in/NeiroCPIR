import Neiro.*;

public class Main {
    public static void main(String[] args) {
        Neiro neiro = new Neiro();
        neiro.add(3);
        neiro.add(2);
        neiro.add(1);
        boolean[] rrs = {true, false, true};
        neiro.start(rrs);
        neiro.print_res();
    }
//
//    static boolean[] comparison(String[] inp, String[] base){
//        boolean[] res = new boolean[base.length];
//
//
//        System.out.println(base.length);
//        System.out.println(inp.length);
//
//        for (int i = 0; i < base.length; i++) {
//            for (int j = 0; j < inp.length; j++) {
//                if (base[i].equals(inp[j]))
//                    res[i] = true;
//                else {
//                    if (res[i])
//                        continue;
//                    res[i] = false;
//                }
//            }
//        }
//        return res;
//    }
}