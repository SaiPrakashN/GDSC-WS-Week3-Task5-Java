package Google_DSC_WS;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;


class romanNumber {
    HashMap<Integer, String> romanHashMap;
    romanNumber(){
        romanHashMap = new HashMap<>();
        romanHashMap.put(1,"I");
        romanHashMap.put(4,"IV");
        romanHashMap.put(5,"V");
        romanHashMap.put(9,"IX");
        romanHashMap.put(10,"X");
        romanHashMap.put(40,"XL");
        romanHashMap.put(50,"L");
        romanHashMap.put(90,"XC");
        romanHashMap.put(100,"C");
        romanHashMap.put(400,"CD");
        romanHashMap.put(500,"D");
        romanHashMap.put(900,"CM");
        romanHashMap.put(1000,"M");
    }

    String lessthan10(int num){
        String val = "";
        if (romanHashMap.get(num) != null){
            return romanHashMap.get(num);
        }
        else if (num < 4){
            for (int i = 0; i< num; i++){
                val += romanHashMap.get(1);
            }
        }
        else if (num > 5 && num < 9){
            val =  romanHashMap.get(5);
            for (int i = 0; i< num%5; i++){
                val += romanHashMap.get(1);
            }
        }
        return val;
    }

    String lessthan100(int num){
        String val = "";
        if (romanHashMap.get(num) != null){
            return romanHashMap.get(num);
        }
        else if (num < 40){
            for (int i = 0; i<num/10; i++){
                val += romanHashMap.get(10);
            }
            val += lessthan10(num%10);
        }
        else if (num > 40 && num < 50){
            val += romanHashMap.get(40);
            val += lessthan10(num%10);
        }
        else if (num > 50 && num < 90){
            val +=  romanHashMap.get(50);
            int remainder = num%50;
            for (int i = 0; i<remainder/10; i++){
                val += romanHashMap.get(10);
            }
            val += lessthan10(remainder%10);
        }
        else if (num > 90 && num < 100){
            val += romanHashMap.get(90);
            val += lessthan10(num%10);
        }
        return val;
    }

    String lessthan1k(int num){
        String val = "";
        if (romanHashMap.get(num) != null){
            return romanHashMap.get(num);
        }
        else if (num < 400){
            for (int i = 0; i<num/100; i++){
                val += romanHashMap.get(100);
            }
            val += lessthan100(num%100);
        }
        else if (num > 400 && num < 500){
            val +=  romanHashMap.get(400);
            val += lessthan100(num%400);
        }
        else if (num > 500 && num < 900){
            val =  romanHashMap.get(500);
            int remainder = num%500;
            for (int i = 0; i< remainder/100; i++){
                val += romanHashMap.get(100);
            }
            val += lessthan100(remainder%100);
        }
        else if (num > 900 && num < 1000){
            val = romanHashMap.get(900);
            val += lessthan100(num%900);
        }
        return val;
    }

    String lessthan10k(int num){
        String val = "";
        if (romanHashMap.get(num) != null){
            return romanHashMap.get(num);
        }
        else if (num < 4000){
            for (int i = 0; i<num/1000; i++){
                val += romanHashMap.get(1000);
            }
            val += lessthan1k(num%1000);
        }
        return val;
    }

    String getRomanNumber(int num){
        if (num <= 0)
            return null;
        else if (romanHashMap.get(num) != null)
            return romanHashMap.get(num);
        if (num < 10)
            return lessthan10(num);
        if (num < 100)
            return lessthan100(num);
        else if (num < 1000)
            return lessthan1k(num);
        else if (num < 10000)
            return lessthan10k(num);
        else
            return null;
    }

}

public class roman_numeral_converter {
    private JPanel panel1;
    private JTextField decimal_input;
    private JButton convert_button;
    private JLabel display_area;
    private JButton clear_button;


    romanNumber romanNumberObj = new romanNumber();


    public roman_numeral_converter() {
        convert_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int decimal = Integer.parseInt(decimal_input.getText());
                String roman = romanNumberObj.getRomanNumber(decimal);
                display_area.setText(roman);
            }
        });
        clear_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                display_area.setText("");
                decimal_input.setText("");
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("ROMAN NUMERAL CONVERTER");
        frame.setContentPane(new roman_numeral_converter().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(600,400);
        frame.setVisible(true);
    }

}
