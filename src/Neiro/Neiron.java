package Neiro;

public class Neiron {
    double[] dep;
    double val;

    public void setDep(int dep, double val) {
        this.dep[dep] = val;
    }

    public Neiron(int depSize) {
        dep = new double[depSize];

        for (int i = 0; i < dep.length; i++) {
            dep[i] = Math.random();
        }

        //System.out.println(depSize);

        val = 0.60;
    }

    public double getDep(int num){
        return dep[num];
    }

    public int getSizeDep(){
        return dep.length;
    }

    public double getVal() {
        return val;
    }
}
