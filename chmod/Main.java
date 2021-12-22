package acsl.chmod;

import java.util.Scanner;
import acsl.chmod.CHMOD.Type;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Type[] types = {
                Type.Oct,
                Type.Oct,
                Type.Bin,
                Type.Bin,
                Type.Perm
        };

        System.out.println("Inputs: ");
        CHMOD[] chmods = new CHMOD[5];


        for (int i = 0; i < 5; i++) {
            String arg = sc.nextLine();
            chmods[i] = new CHMOD(arg, types[i]);
        }

        System.out.println("Outputs:");
        for (CHMOD i : chmods)
            i.printOutput();

//        CHMOD test = new CHMOD("--x r-- r-x", Type.Perm);
//        test.printOutput();

    }
}
