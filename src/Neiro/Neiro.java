package Neiro;

import java.util.LinkedList;

public class Neiro {
    Layer[] layers;
    LinkedList linkedList;
    LinkedList[] resDep;

    public Neiro() {
        linkedList = new <Integer>LinkedList();
    }

    public void add(int size) {
        linkedList.add(size);
    }

    public void start(boolean... inp) {


        layers = new Layer[linkedList.size()];

        for (int i = 0; i < linkedList.size(); i++) {
            if (i < linkedList.size() - 1)
                layers[i] = new Layer((int) linkedList.get(i), (int) linkedList.get(i + 1));
            else
                layers[i] = new Layer((int) linkedList.get(i), 0);
        }
        //забиваем слои

        resDep = new LinkedList[layers.length];

        for (int i = 0; i < resDep.length; i++) {
            resDep[i] = new <Boolean>LinkedList();
        }

        for (int i = 0; i < layers.length; i++) {
            for (int j = 0; j < layers[i].getSize(); j++) {
                resDep[i].add(false);
            }
        }

        resDep[0].clear();

        for (int i = 0; i < inp.length; i++) {
            resDep[0].add(inp[i]);
        }

        if (inp.length == layers[0].getSize()) {
            start_use();
        } else {
            System.out.println("incorrect write input layer");
        }
    }

    private void start_use() {
        for (int i = 1; i < resDep.length; i++) {

            for (int j = 0; j < resDep[i].size(); j++) {

                double mass = 0;
                int c = 0;

                for (int k = 0; k < resDep[i - 1].size(); k++) {
                    if ((boolean) resDep[i - 1].get(k)) {
                        mass += layers[i - 1].getDepNeiron(k, j);
                    }
                    c = k;
                }


                System.out.print("<" + mass + ">");

//                System.out.println(resDep[i].size() - 1);
//                System.out.println(c);

                System.out.print(activate_sigma(mass));

                if (activate_sigma(mass) <= layers[i].getValNeiron(j)) {
                    System.out.println("a");
                    resDep[i].set(j, true);
                } else {
                    System.out.println("b");
                    resDep[i].set(j, false);
                }
            }
        }
    }


//    boolean activate(int numLayer, int numNeiron) {
//        double x = layers[numLayer - 1].getSamNeiron(numNeiron);
//        return ((1 / (1 + Math.exp(x))) >= layers[numLayer].getValNeiron(numNeiron));
//    }

    double activate_sigma(double sum) {
        return  (1 / (1 + Math.exp(sum)));
    }


    public void print() {
        for (int i = 0; i < layers.length; i++) {
            for (int j = 0; j < layers[i].getSize(); j++) {
                System.out.print("[" + layers[i].getDepSize(j) + "]");
            }
            System.out.println();
        }
    }

    public void print_res() {
        for (int i = 0; i < resDep.length; i++) {
            for (int j = 0; j < resDep[i].size(); j++) {
                System.out.print(resDep[i].get(j) + ", ");
            }
            System.out.println("результат -" + resDep[i].size());

        }
    }
}
