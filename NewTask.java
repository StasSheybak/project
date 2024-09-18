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

    public static ArrayList<String> getData(String input) throws Exception {
        ArrayList<Integer> qMarks = new ArrayList<Integer>();
        ArrayList<String> data = new ArrayList<String>();
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == '"') {
                qMarks.add(i);
            }
        }
        for (int i = 0; i < qMarks.size() - 1; i += 2) {
            data.add(input.substring(qMarks.get(i) + 1, qMarks.get(i + 1)));
        }
        if (data.size() < 2){
            throw new Exception("Некорректный ввод");
        }
        for (int i = 0; i < data.size(); i++) {
            if (data.get(i).length() > 10) {
                throw new Exception("Строка не может содержать более 10 символов");
            }
        }
        return data;
    }

        public static String doMathAction(String input) throws Exception {
            ArrayList<String> data = getData(input);
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
                        result = multiply(data);
                        break;
                    case '/':
                        result = divide(data);
                }
            }

            if (result.length() > 40) {
                String cutResult = result.substring(0, 41) + "...";
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

    public static String multiply(ArrayList<String> data){
        String result = "";
        for (int i = 0; i < data.size() - 1; i++) {
            int multiplier = Integer.parseInt(data.get(i + 1));
            for (int j = 0; j < multiplier; j++) {
                result += data.get(i);
            }
        }
        return result;
    }

    public static String divide(ArrayList<String> data){
        String strResult = "";
        for (int i = 0; i < data.size() - 1; i++) {
            int divisible = data.get(i).length();
            int divider = Integer.parseInt(data.get(i + 1));
            int endIndex = divisible / divider;
            strResult = data.get(i).substring(0, endIndex);
        }
        return strResult;
    }
}
