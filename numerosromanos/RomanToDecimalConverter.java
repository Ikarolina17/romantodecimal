import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class RomanToDecimalConverter {
    
    // Mapa de valores romanos
    private static final Map<Character, Integer> romanMap = new HashMap<>();
    
    static {
        romanMap.put('I', 1);
        romanMap.put('V', 5);
        romanMap.put('X', 10);
        romanMap.put('L', 50);
        romanMap.put('C', 100);
        romanMap.put('D', 500);
        romanMap.put('M', 1000);
    }

    public static int romanToDecimal(String roman) {
        int decimal = 0;
        int previousValue = 0;

        for (int i = roman.length() - 1; i >= 0; i--) {
            char romanChar = roman.charAt(i);
            int currentValue = romanMap.get(romanChar);

            // Si el valor actual es menor que el anterior, se resta, si no, se suma
            if (currentValue < previousValue) {
                decimal -= currentValue;
            } else {
                decimal += currentValue;
            }

            previousValue = currentValue;
        }

        
        return decimal;
    }

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Por favor, introduce al menos un número en notación romana como argumento.");
            return;
        }

        // Nombre del archivo CSV de salida
        String outputFileName = "RomanToDecimal.csv";

        try (FileWriter writer = new FileWriter(outputFileName)) {
            writer.append("Roman,Decimal\n");

            for (String roman : args) {
                int decimalValue = romanToDecimal(roman);
                System.out.println("El valor decimal de " + roman + " es " + decimalValue);
                
                // Escribir en el archivo CSV
                writer.append(roman).append(",").append(String.valueOf(decimalValue)).append("\n");
            }

            System.out.println("El archivo CSV se ha generado correctamente: " + outputFileName);

        } catch (IOException e) {
            System.out.println("Error al escribir el archivo CSV: " + e.getMessage());
        }
    }
}
