import java.io.CharConversionException;
import java.io.IOException;
import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;
import static java.lang.Integer.parseInt;


public class Main {
    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(System.in);
        String output;
        output = calc(in.nextLine());
        in.close();

        System.out.print(output);

    }


        public static String calc(String input) throws IOException {

        String[] words = input.split(" ");
        if (words.length < 3) throw new IOException("throws Exception //т.к. строка не является математической операцией");
        if (words.length > 3) throw new IOException("throws Exception //т.к. формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");


        Map<String,String> romArab = new HashMap<>();
        romArab.put("I", "1");
        romArab.put("II", "2");
        romArab.put("III", "3");
        romArab.put("IV", "4");
        romArab.put("V", "5");
        romArab.put("VI", "6");
        romArab.put("VII", "7");
        romArab.put("VIII", "8");
        romArab.put("IX", "9");
        romArab.put("X", "10");

        if (romArab.containsKey(words[0]) ^ romArab.containsKey(words[2])) throw new IOException("throws Exception //т.к. используются одновременно разные системы счисления");

        boolean isArabDig = true;

        if (romArab.containsKey(words[0])) {
            isArabDig = false;
            words[0] = romArab.get(words[0]);
            words[2] = romArab.get(words[2]);
        }

        byte val = opValues(words);

        if (isArabDig) {
            return Integer.toString(val);
        } else {
            if (val < 1) throw new CharConversionException("throws Exception //т.к. в римской системе нет отрицательных чисел");
            return convertArabToRom(val);
        }


    }




    static String convertArabToRom(byte val) {

        Map<String,String> arabRom = new HashMap<>();
        arabRom.put("0", "");
        arabRom.put("1", "I");
        arabRom.put("2", "II");
        arabRom.put("3", "III");
        arabRom.put("4", "IV");
        arabRom.put("5", "V");
        arabRom.put("6", "VI");
        arabRom.put("7", "VII");
        arabRom.put("8", "VIII");
        arabRom.put("9", "IX");
        arabRom.put("10", "X");
        arabRom.put("20", "XX");
        arabRom.put("30", "XXX");
        arabRom.put("40", "XL");
        arabRom.put("50", "L");
        arabRom.put("60", "LX");
        arabRom.put("70", "LXX");
        arabRom.put("80", "LXXX");
        arabRom.put("90", "XC");
        arabRom.put("100", "C");


        byte seconddig = (byte)(val % 10);
        byte firstdig = (byte)(val - seconddig);

        if (firstdig == 0) {
            return arabRom.get(Byte.toString(seconddig));
        } else {
            return (arabRom.get(Byte.toString(firstdig)) + arabRom.get(Byte.toString(seconddig)));

        }
    }



    static byte opValues(String[] words) {
        switch (words[1]) {
            case "+" ->
            {
                return  (byte)(parseInt(words[0]) + parseInt(words[2]));
            }
            case "-" ->
            {
                return (byte) (parseInt(words[0]) - parseInt(words[2]));
            }
            case "*" ->
            {
                return  (byte)(parseInt(words[0]) * parseInt(words[2]));
            }
            case "/" ->
            {
                return  (byte)(parseInt(words[0]) / parseInt(words[2]));
            }
        }
        return 101;
    }
}