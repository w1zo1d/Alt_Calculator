import java.util.*;

class Main {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        System.out.println("Input:");
        String input = s.nextLine();

        try {
            calc(input);
        } catch (Exception e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }

    static String calc(String input) throws Exception {
        String[] rangeWords = new String[input.length()];
        String[] parts;
        for (int i = 0; i < input.length(); i++) {
            char k = input.charAt(i);

            switch (k) {
                case '\"' :
                    rangeWords[i] = String.valueOf(i);
                    break;
                default:
                    rangeWords[i] = "-1";
                    break;
            }
        }
//        System.out.println(Arrays.toString(rangeWords));
//        System.out.println(Arrays.toString(charSymbol));

        List<String> stringList = new ArrayList<String>(Arrays.asList(rangeWords));
        stringList.removeAll(Arrays.asList("-1"));
        rangeWords = stringList.toArray(new String[0]);
//        System.out.println(Arrays.toString(rangeWords));

        int n = Integer.parseInt(rangeWords[1]);
        String[] charSymbol = new String[input.length()-n];
//        System.out.println(Arrays.toString(charSymbol));

        for (int k = 0; k < charSymbol.length; k++) {
            char b = input.charAt(k+n);
            switch (b) {
                case '+' :
                    charSymbol[k] = String.valueOf(k);
                    break;
                case '-' :
                    charSymbol[k] = String.valueOf(k);
                    break;
                case '*' :
                    charSymbol[k] = String.valueOf(k);
                    break;
                case '/' :
                    charSymbol[k] = String.valueOf(k);
                    break;
                default:
                    charSymbol[k] = "-1";
                    break;
            }
        }
//        System.out.println(Arrays.toString(charSymbol));
        List<String> stringList1 = new ArrayList<String>(Arrays.asList(charSymbol));
        stringList1.removeAll(Arrays.asList("-1"));
        charSymbol = stringList1.toArray(new String[0]);
//        System.out.println(Arrays.toString(charSymbol));

        String str1;
        String str2;
        String symbol;

        if (Arrays.stream(rangeWords).count() == 2) {
            String numStr = input.replaceAll("\\D", "");
            int numStrCheck = Integer.parseInt(numStr);

            if (numStrCheck > 1 && numStrCheck <= 10) {
                numStr = String.valueOf(numStrCheck);
            } else {
                throw new Exception("Формат операции не удовлетворяет заданию");
            }
            int num1 = Integer.parseInt(rangeWords[0]);
            int num2 = Integer.parseInt(rangeWords[1]);
            if (num1 != 0){
                throw new Exception("Формат операции не удовлетворяет заданию");
            }

            str1 = input.substring(num1 + 1, num2);

            int sym = Integer.parseInt(charSymbol[0]);
            symbol = input.substring(sym+n, sym+(n+1));
//            System.out.println(symbol);

            parts = new String[]{str1, symbol, numStr};

            String operand1 = parts[0];
            String operator = parts[1];
            String operand2 = parts[2];
            String result;

            switch (operator) {
                case "/":
                    result = division(operand1, operand2);
                    break;
                case "*":
                    result = multiplication(operand1, operand2);
                    break;
                default:
                    throw new Exception("Формат операции не удовлетворяет заданию");
            }
            System.out.println("Output:");
            if (result.length() > 40)
            {
                System.out.println("\"" + result.substring(0, 40) + "..." + "\"");
            } else {
                System.out.println("\"" + result + "\"");
            }
            return result;

        } else if(Arrays.stream(rangeWords).count() == 4){
            int num1 = Integer.parseInt(rangeWords[0]);
            int num2 = Integer.parseInt(rangeWords[1]);
            int num3 = Integer.parseInt(rangeWords[2]);
            int num4 = Integer.parseInt(rangeWords[3]);

            str1 = input.substring(num1+1,num2);
            str2 = input.substring(num3+1,num4);

            int sym = Integer.parseInt(charSymbol[0]);
            symbol = input.substring(sym+n, sym+(n+1));
            parts = new String[]{str1, symbol, str2};
        }
        else{
            throw new Exception("Формат операции не удовлетворяет заданию");
        }
        String operand1 = parts[0];
        String operator = parts[1];
        String operand2 = parts[2];
        String result;

        switch (operator) {
            case "+":
                result = concatenation(operand1, operand2);
                break;
            case "-":
                result = substraction(operand1, operand2);
                break;
            default:
                throw new Exception("Формат операции не удовлетворяет заданию");
        }
        System.out.println("Output:");
        if (result.length() > 40)
        {
            System.out.println("\"" + result.substring(0, 40) + "..." + "\"");
        } else {
            System.out.println("\"" + result + "\"");
        }
        return result;
    }

    private static String concatenation(String operand1, String operand2) {
        return operand1 + operand2;
    }
    private static String substraction(String operand1, String operand2) {
        return operand1.replace((operand2), "");
    }
    private static String multiplication(String operand1, String operand2) {
        return operand1.repeat(Integer.parseInt(operand2));
    }
    private static String division(String operand1, String operand2) {
        int length = operand1.length();
        int res = length / Integer.parseInt(operand2);
        return operand1.substring(0,res);
    }
}