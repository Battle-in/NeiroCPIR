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

    private double[] considerNetworkR() {
        LinkedList<Double> ll = new LinkedList<>();
        for (int i = 1; i < resDep.length; i++) {
            for (int j = 0; j < resDep[i].size(); j++) {

                double mass = 0;

                for (int k = 0; k < resDep[i - 1].size(); k++) {
                    if ((boolean) resDep[i - 1].get(k)) {
                        mass += layers[i - 1].getDepNeiron(k, j);
                    }
                }

                //System.out.print(activate_sigma(mass));
                if (i == resDep.length - 1){
                    ll.add(mass);
                }

                if (activate_sigma(mass) >= layers[i].getValNeiron(j))
                    resDep[i].set(j, true);
                else
                    resDep[i].set(j, false);
            }
        }

        Double[] a = ll.toArray(new Double[ll.size()]);
        double[] b = new double[a.length];
        for (int i = 0; i < a.length; i++) {
            b[i] = a[i].doubleValue();
        }

        return b;
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

    public void train() {
        double[] out = considerNetworkR();

        for (int i = 0; i < out.length; i++) {
            for (int j = layers.length - 2; j >= 0; j--) {
                for (int k = 0; k < layers[j].getSize(); k++) {
                    layers[j].setDep(k,j-1,weight_error());
                }
            }
        }


    }

    double activate_sigma(double sum) {
        return (1 / (1 + (1 / Math.exp(sum))));
    }

    double weight_error(double weight, double inp_weight, double sum, double epected) {
        return weight - inp_weight * sigma_error(sum, epected) * 0.2;
    }

    double sigma_error(double sum, double expected) {
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
}

/*
SQLки
    select * from networcks
    insert (name, json) into networcks values ('hello', 'world');
    select json from networcks where (name = 'hack');
 */