package acsl.chmod;

public class CHMOD {

    public enum Type {
        Oct,
        Bin,
        Perm
    }

    String input;
    Type inputType;

    private String bin;
    private String oct;
    private String perm;

    public CHMOD (String arg, Type t) {
        input = arg;
        inputType = t;

        setBin();
        setOct();
        setPerm();
    }

    /**
     * precondition: input is a valid bin, oct, or perm
     * post-condition: the bin variable is set with equivalent value
     */
    private void setBin () {
        String result = "";

        if (inputType == Type.Oct) {
            // Be able to loop through each digit easily and as a number
            int[] digits = {
                    Character.getNumericValue(input.charAt(0)),
                    Character.getNumericValue(input.charAt(1)),
                    Character.getNumericValue(input.charAt(2)),
            };

            // Mathematical converter
            // Replaceable with Integer.toBinaryString()
            for (int i : digits) {
                String binString = "";
                while (i > 0) {
                    binString = (i % 2) + binString;
                    i /= 2;
                }
//                result.append(Integer.toBinaryString(i));
                if (binString.length() < 3) {
                    while (binString.length() < 3) {
                        binString = "0" + binString;
                    }
                }
                result += binString + " ";
            }

            // Removing the hanging space at the end
            result = result.substring(0, result.length() - 1);
        } else if (inputType == Type.Perm) {
            for (int i = 0; i < input.length(); i++) {
                /* Compound additional assignment
                If whitespace add a space
                Otherwise if a dash add a 0
                Otherwise add a 1
                 */
                result += (input.charAt(i) == ' ') ? " " :
                        ((input.charAt(i) != '-') ? "1" : "0");
            }
        } else result = input;
        bin = result;
    }

    /**
     * precondition: the bin variable has been set
     * post-condition: the oct variable is set with equivalent value to bin
     */
    private void setOct () {
        String result = "";
        String[] bins = bin.split(" ");
        for (String i : bins) {

            // Reversing for easier iteration
            i = new StringBuilder(i).reverse().toString();

            // Mathematical converter
            int d = 0;
            for (int j = 0; j < i.length(); j++) {
                if (i.charAt(j) == '1') {
                    d += (Math.pow(2, j));
                }
            }
            result += d;
        }
        oct = result.toString();
    }

    /**
     * precondition: the bin variable has been set
     * post-condition: the perm variable is set with equivalent value to bin
     */
    private void setPerm () {
        String result = "";
        String[] bins = bin.split(" ");
        for (String i : bins) {
            for (int j = 0; j < i.length(); j++) {
                if (i.charAt(j) == '1') {
                    result += "rwx".charAt(j);
                } else {
                    result += "-";
                }
            }
            result += " ";
        }
        // Removing the hanging space
        result =result.substring(0, result.length() - 1);
        perm = result;
    }

    public void printAll () {
        System.out.println(bin + ", " + oct + ", " + perm);
    }

    public void printOutput () {
        if (inputType == Type.Oct)
            System.out.println(bin + " and " + perm);
        if (inputType == Type.Bin)
            System.out.println(oct + " and " + perm);
        if (inputType == Type.Perm)
            System.out.println(oct + " and " + bin);
    }
}
