package Neiro;

import java.util.Arrays;

public class Layer {
    Neiron[] neirons;

    public Layer(int size, int depSize){
        neirons = new Neiron[size];

        for (int i = 0; i < neirons.length; i++) {
            neirons[i] = new Neiron(depSize);
        }
    }

    public double getDepNeiron(int neiron, int dep){
        return neirons[neiron].getDep(dep);
    }

    void setDep(int neiron, int dep ,double value){
        neirons[neiron].setDep(dep, value);
    }

    int getDepSize(int neiron){
        return  neirons[neiron].getSizeDep();
    }

    int getSize(){
        return neirons.length;
    }

    double getValNeiron(int numNeiron){
        return neirons[numNeiron].getVal();
    }

    @Override
    public String toString() {
        return "Layer{" +
                "neirons=" + Arrays.toString(neirons) +
                '}';
    }

    public double getSamNeiron(int numNeiron) {
        double res = 0;

        for (int i = 0; i < neirons.length; i++) {
            res += neirons[i].getDep(numNeiron);
        }

        return res;
    }
}
