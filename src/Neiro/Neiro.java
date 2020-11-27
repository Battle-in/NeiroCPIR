package Neiro;

import java.util.LinkedList;

public class Neiro {
    Layer[] layers;
    LinkedList linkedList;
    LinkedList[] resDep;
    LinkedList result;
    boolean initalize;

    public Neiro() {
        linkedList = new <Integer>LinkedList();
        result = new LinkedList();
    }

    public void add(int size) {
        linkedList.add(size);
    }


    private void considerNetwork() {
        LinkedList<Double>[] ll = new LinkedList[layers.length];
        for (int i = 0; i < layers.length; i++) {
            ll[i] = new LinkedList();
        }
        for (int i = 1; i < resDep.length; i++) {
            for (int j = 0; j < resDep[i].size(); j++) {

                double mass = 0;

                for (int k = 0; k < resDep[i - 1].size(); k++) {
                    if ((boolean) resDep[i - 1].get(k)) {
                        mass += layers[i - 1].getDepNeiron(k, j);
                    }
                }

                //System.out.print(activate_sigma(mass));

                if (activate_sigma(mass) >= layers[i].getValNeiron(j))
                    resDep[i].set(j, true);
                else
                    resDep[i].set(j, false);
            }
        }
    }

    private LinkedList[] considerNetworkR() {
        LinkedList<Double>[] ll = new LinkedList[layers.length];
        for (int i = 0; i < layers.length; i++) {
            ll[i] = new LinkedList();
        }
        for (int i = 1; i < resDep.length; i++) {
            for (int j = 0; j < resDep[i].size(); j++) {

                double mass = 0;

                for (int k = 0; k < resDep[i - 1].size(); k++) {
                    if ((boolean) resDep[i - 1].get(k)) {
                        mass += layers[i - 1].getDepNeiron(k, j); //* ll[i].get(k);
                    }
                }

                //System.out.print(activate_sigma(mass));
                ll[i].add(mass);

                if (activate_sigma(mass) >= layers[i].getValNeiron(j))
                    resDep[i].set(j, true);
                else
                    resDep[i].set(j, false);
            }
        }


        return ll;
    }


    public void initalize_new() {
        layers = new Layer[linkedList.size()];
        resDep = new LinkedList[linkedList.size()];

        for (int i = 0; i < linkedList.size(); i++) {
            resDep[i] = new LinkedList();
            if (i < linkedList.size() - 1) {
                layers[i] = new Layer((int) linkedList.get(i), (int) linkedList.get(i + 1));
            } else {
                layers[i] = new Layer((int) linkedList.get(i), 0);
            }
        }
        for (int i = 1; i < layers.length; i++) {
            for (int j = 0; j < layers[i].getSize(); j++) {
                resDep[i].add((boolean) false);
            }
        }

        initalize = true;
    }

    public void use(boolean... inp) {
        if (!initalize) {
            System.out.println("Проинициализируйте нейро через метод initalize_new");
            return;
        }

        resDep[0].clear();

        for (int i = 0; i < inp.length; i++)
            resDep[0].add(inp[i]);
        considerNetwork();
    }

    public void training() {

    }

    public void train(double expected) {
        LinkedList[] out = considerNetworkR();

        //System.out.println(out.length);
//        for (int i = out.length - 1; i >= 0 ; i--) { //0 (1)
//            //System.out.println(out[i].size() + " ");
//            for (int j = 0; j < out[i].size(); j++) {  //
//                if (expected != (double)out[i].get(j)){
//                    for (int k = 0; k < layers[i].getSize(); k++) {
//                        layers[i].setDep(j,k,weight_error(layers[i].getDepNeiron(k,j), (double)out[i].get(j), (double)out[i].get(j),expected));
//                        System.out.println(weight_error(layers[i].getDepNeiron(j,k), (double)out[i].get(j), (double)out[i].get(j),expected));
//                    }
//                    System.out.println("fucl");
//                }
//            }
//        }
        for (int i = out.length - 1; i >= 0 ; i--) {
            for (int j = 0; j < out[i].size(); j++) {
                for (int k = 0; k < layers[i].getDepSize(j); k++) {
                    if (expected != (double)out[i].get(j)){
                        layers[i - 1].setDep(k,j,weight_error(layers[i-1].getDepNeiron(j,k),activate_sigma((double)out[i].get(j)),activate_sigma((double)out[i].get(k)),expected));
                    }
                }
            }
            //System.out.println();
        }
    }

    double activate_sigma(double sum) {
        return (1 / (1 + (1 / Math.exp(sum))));
    }

    double weight_error(double weight, double inp_weight, double sum, double expected) {
        return weight - inp_weight * sigma_error(sum - expected) * 0.1;
    }

    double sigma_error(double sum) {
        double x = activate_sigma(sum);
        return x * (x * (1 - x));
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
        int max = 0;
        for (int i = 0; i < resDep.length; i++) {
            if (max < resDep[i].size()) {
                max = resDep[i].size();
            }
        }
        for (int i = 0; i < resDep.length; i++) {
            for (int k = max / 2; k > resDep[i].size() / 2; k--) {
                System.out.print("   ");
            }
            for (int j = 0; j < resDep[i].size(); j++) {
                if ((boolean) resDep[i].get(j))
                    System.out.print("[+]");
                else
                    System.out.print("[-]");
            }
            System.out.println(" -" + resDep[i].size());
        }
    }

    public void print_val() {
        for (int i = 0; i < layers.length; i++) {
            for (int j = 0; j < layers[i].getSize(); j++) {
                System.out.print("<" + layers[i].getValNeiron(j) + ">");
            }
            System.out.println();
        }
    }

    public void ll_sout(){
        LinkedList[] a = considerNetworkR();
        for (int l = 0; l < a.length; l++) {
            for (int i = 0; i < a[l].size(); i++) {
                System.out.print(a[l].get(i) + "//");
            }
            System.out.println();
        }
    }

    public void print_all_dep(){
        for (int i = 0; i < layers.length; i++) {
            for (int j = 0; j < layers[i].getSize(); j++) {
                for (int k = 0; k < layers[i].getDepSize(j); k++) {
                    System.out.print(layers[i].getDepNeiron(j,k) + " ");
                }
                System.out.print("///");
            }
            System.out.println();
        }
    }
}


/*
SQLки
    select * from networcks
    insert (name, json) into networcks values ('hello', 'world');
    select json from networcks where (name = 'hack');
 */