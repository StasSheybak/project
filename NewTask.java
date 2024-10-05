package Experiments;

import java.util.ArrayList;
import java.util.Scanner;

public class NewTask {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        try {
            System.out.println(doMathAction(input));
        } catch (Exception e){
            System.out.println(e);
        }

    }

    public static ArrayList<String> getString(String input) throws Exception {
        ArrayList<Integer> qMarks = new ArrayList<Integer>();
        ArrayList<String> data = new ArrayList<String>();
        int digit;
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == '"') {
                qMarks.add(i);
            }
        }
        for (int i = 0; i < qMarks.size() - 1; i += 2) {
            data.add(input.substring(qMarks.get(i) + 1, qMarks.get(i + 1)));
        }
        for (int i = 0; i < data.size(); i++) {
            if (data.get(i).length() > 10) {
                throw new Exception("Строка не может содержать более 10 символов");
            }
        }
        return data;
    }

    public static int getDigit(String input) throws Exception {
        int beginIndex = 0;
        int digit = 0;
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == '*' && input.charAt(i) == '/'){
                beginIndex = i;
            }
        }
        for (int i = beginIndex + 1; i < input.length(); i++) {
            if (Character.isDigit(input.charAt(i))){
                if (input.charAt(i) == '1' && i < input.length() - 1 && input.charAt(i + 1) == '0'){
                    digit = 10;
                    break;
                }
                digit = Character.getNumericValue(input.charAt(i));
            }
        }
        return digit;
    }

        public static String doMathAction(String input) throws Exception {
            ArrayList<String> data = getString(input);
            int digit = getDigit(input);
            String result = "";
            for (int i = 0; i < input.length(); i++) {
                switch (input.charAt(i)) {
                    case '+':
                        result = sum(data);
                        break;
                    case '-':
                        result = substract(data);
                        break;
                    case '*':
                        result = multiply(data, digit);
                        break;
                    case '/':
                        result = divide(data, digit);
                }
            }

            if (result.length() >= 40) {
                String cutResult = result.substring(0, 40) + "...";
                result = cutResult;
            }
            return result;
        }


    public static String sum(ArrayList<String> data){
        String result = "";
        for (int i = 0; i < data.size(); i++) {
            result += data.get(i);
        }
        return result;
    }

    public static String substract(ArrayList<String> data){
        String result = "";
        for (int i = 0; i < data.size() - 1; i++) {
            String[] resultArr = data.get(i).split(data.get(i + 1));
            for (int j = 0; j < resultArr.length; j++) {
                result += resultArr[j];
            }
        }
        return result;
    }

    public static String multiply(ArrayList<String> data, int digit){
        String result = "";
        for (int j = 0; j < digit; j++) {
            result += data.get(0);
        }
        return result;
    }

    public static String divide(ArrayList<String> data, int digit) throws Exception {
        String strResult = "";
        int divisible = data.get(0).length();
        int endIndex = divisible / digit;
        strResult = data.get(0).substring(0, endIndex);
        return strResult;
    }
}
