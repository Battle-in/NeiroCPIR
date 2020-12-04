import java.util.LinkedList;
import java.util.Scanner;
import Neiro.*;

public class Cvazi {
    public static void main(String[] args) {
        Cvazi cvazi = new Cvazi();
        cvazi.strt();
    }

    void strt(){
        Scanner sc = new Scanner(System.in);
        String a;
        while (true){
            a = sc.nextLine();
            switch (a){
                case "создать":
                    create_table(sc);
                    break;
                case "тренировать":
                    train();
                    break;
                case "исспользовать":
                    use();
                    break;
                case "помощь":
                    print_help();
                    break;
                case "датасет":
                    insertData();
                    break;
                default:
                    System.out.println("введите \"помощь\" для получения справки по использованию программы");
            }
        }
    }

    void create_table(Scanner sc){
        LinkedList ll = new LinkedList();
        boolean out = false;
        System.out.println("введите размер слоя, для выхода введите 0");
        int y;
        while (!out){
            y = sc.nextInt();
            //System.out.println(y);
            if (y == 0)
                out = true;
            else
                ll.add(sc.nextInt());
        }
        System.out.println("quitt");

    }

    void insertData(){
        NLPModule tm = new NLPModule("math.txt");
        NLPModule ttm = new NLPModule("iifilosof.txt");
        String[] a = tm.words();
        String[] b = ttm.words();

        LinkedList lp = new LinkedList();

        for (int i = 0; i < a.length; i++) {
            lp.add(a[i]);
        }
        for (int i = 0; i < b.length; i++) {
            lp.add(b[i]);
        }



        DB db = new DB();
        for (int i = 0; i < lp.size(); i++) {
            db.push((String) lp.get(i));
        }
    }

    void train(){
        System.out.println("просиходит тренировка");
        for (int i = 0; i <= 10; i++) {
            System.out.println(i*10 + "%");
            try {
                Thread.sleep(1200);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        System.out.println("тренировка окончена");
    }

    void use(){

    }

    void print_help(){
        String help =
                "введите \"создать\" чтобы приступить к инициализации нейросети\n" +
                        "введите \"тренировать\" для тренировки сети\n" +
                        "введите \"исспользовать\" для исспользования нейросети\n" +
                "\n";
        System.out.println(help);
    }

}
