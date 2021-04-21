package libreriadenumeros;

import java.util.Scanner;

public class LibreriaDeNumeros{    
    
    public static int codigoOperacion(String num1, String num2){
        String aux1 = valorAbsoluto(num1);
        String aux2 = valorAbsoluto(num2);
        if (aux1.length() > aux2.length()) aux2 = añadeCeros(num1, num2); //ambos positivos
        else aux1 = añadeCeros(num2, num1);
        if (num1.charAt(0) != '-' && num2.charAt(0) != '-'){ //para ambos números positivos
            for (int i = 0; i < num1.length(); i++){ //comprobando qué dígito es mayor
                if (aux1.charAt(i) > aux2.charAt(i)) return 1; //numero 1 es el mayor
                else if (aux2.charAt(i) > aux1.charAt(i)) return 2; //numero 2 es el mayor
            }
            return 9; //ambos positivos iguales
        }
        else if (num1.charAt(0) != '-' && num2.charAt(0) == '-'){ //num1 positivo num2 negativo
            for (int i = 0; i < aux1.length(); i++){ //comprobando qué dígito es mayor
                if (aux1.charAt(i)>aux2.charAt(i)) return 3; //num1 positivo y "mayor", num2 negativo y "menor"
                else if (aux2.charAt(i)>aux1.charAt(i)) return 4; //num1 positivo y "menor", num2 negativo y "mayor"
            }
            return 11; // num1 positivo, num2 negativo, ambos "iguales"
        }
        else if (num1.charAt(0) == '-' && num2.charAt(0) != '-'){ //num1 negativo num2 positivo
            for (int i = 0; i < aux2.length(); i++){ //comprobando qué dígito es mayor
                if (aux1.charAt(i)>aux2.charAt(i)) return 5; //num1 negaitvo y "mayor", num2 positivo y "menor"
                else if (aux1.charAt(i)<aux2.charAt(i)) return 6; //num1 negativo y "menor", num2 positivo y "mayor"
            }
            return 12; // num1 negativo, num2 positivo, ambos "iguales"
        }
        else if (num1.charAt(0) == '-' && num2.charAt(0) == '-'){ //ambos numeros negativos
            for (int i = 0; i < aux1.length(); i++){ //comprobando qué dígito es mayor
                if (aux1.charAt(i)>aux2.charAt(i)) return 7; //num1 negaitvo y "mayor", num2 positivo y "menor"
                else if (aux1.charAt(i)<aux2.charAt(i)) return 8; //num1 negativo y "menor", num2 positivo y "mayor"
            }
            return 10; //ambos negativos iguales
        }
        return 0;
    }
    
    public static String eliminarCeros(String num1){ //función para quitar ceros a la izquierda
        if (num1.charAt(0) == '-'){
            if (num1.charAt(1) == '0'){
                for (int i = 1; i < num1.length();i++)
                    if (num1.charAt(i) != '0') return ('-' + num1.substring(i));
            }
        }
        else{
            for(int i = 0; i < num1.length(); i++)
                if (num1.charAt(i)!='0') return num1.substring(i);
        }
        return num1;
    }
    
    public static String añadeCeros(String num1, String num2){ //función para agregar ceros a la izquierda al número más pequeño
        boolean negativo1=false, negativo2=false;
        if (num1.charAt(0) == '-'){
            negativo1 = true;
            num1 = valorAbsoluto(num1);
        }
        if (num2.charAt(0) == '-'){
            negativo2 = true;
            num2 = valorAbsoluto(num2);
        }
        int a = num1.length() - num2.length();
        if (a < 0){
            if (negativo1){
                num1 = valorAbsoluto(num1);
                for (int i = 0; i < Math.abs(a) ; i++) num1 = "0" + num1;
                return ('-' + num1);
            }
            else{
                for (int i = 0; i < Math.abs(a) ; i++) num1 = "0" + num1;
                return num1;
            }
        }
        else if (a > 0){
            if (negativo2){
                num2 = valorAbsoluto(num2);
                for (int i = 0; i < Math.abs(a) ; i++) num2 = "0" + num2;
                return ('-' + num2);
            }
            else{
                for (int i = 0; i < Math.abs(a) ; i++) num2 = "0" + num2;
                return num2;
            }
        }
        else return num2;
    }
            
    public static String Sumar(String num1, String num2){
        int op = codigoOperacion(num1,num2);
        //System.out.println("Sumar: num1: " + num1 + " | num2: " + num2 + " | op: " + op);
        int llevar = 0;  //llevar será el dígito de "acarreo" si la suma entre 2 números es mayor a 9
        String res = ""; //res será el resultado final de la suma
        int a;
        if (op == 1 || op == 7 || op == 9 || op == 10){ //ambos positivos o negativos, num1 "mayor" que num2
            String aux1 = valorAbsoluto(num1);
            String aux2 = valorAbsoluto(num2);
            if(aux1.length()>aux2.length()) aux2 = añadeCeros(aux1, aux2);
            else aux1 = añadeCeros(aux2,aux1);
            a = aux1.length();
            for (int i = a-1; i >= 0; i--){
                String b = "" + aux1.charAt(i); //usando el dìgito en la posición i
                String c = "" + aux2.charAt(i); //usando el dìgito en la posición i
                int aux = Integer.parseInt(b) + Integer.parseInt(c) + llevar; //en aux se guarda el número como int
                if (aux > 9){
                    res = aux%10 + res;
                    llevar = aux/10;
                }
                else{
                    res = aux + res;
                    llevar = 0;
                }
            }
            if (llevar>0) res = llevar + res;
            if(op == 1 || op == 9) return res; //si son ambos positivos
            else if (op == 7 || op == 10) return ('-' + res); //si son ambos negativos
        }
        else if (op == 2 || op == 8){ //ambos positivos o ambos negativos, num2 "mayor" que num1
            String aux1 = valorAbsoluto(num1);
            String aux2 = valorAbsoluto(num2);
            if(aux1.length()>aux2.length()) aux2 = añadeCeros(aux1, aux2);
            else aux1 = añadeCeros(aux2,aux1);
            aux1 = añadeCeros(aux2, aux1);
            a = aux2.length();
            for (int i = a-1; i >= 0; i--){
                String b = "" + aux1.charAt(i); //usando el dìgito en la posición i
                String c = "" + aux2.charAt(i); //usando el dìgito en la posición i
                int aux = Integer.parseInt(b) + Integer.parseInt(c) + llevar; //en aux se guarda el número como int
                if (aux > 9){
                    res = aux%10 + res;
                    llevar = aux/10;
                }
                else{
                    res = aux + res;
                    llevar = 0;
                }
            }
            if (llevar>0) res = llevar + res;
            if(op == 2) return res; //si son ambos positivos
            else if (op == 8) return ("-" + res); //si son ambos negativos
        }
        else if (op == 3 || op == 5){ //num1 positivo o negativo mayor, num2 negativo o positivo menor
            num2 = añadeCeros(num1,num2);
            if (op == 3){ //num1 positivo y mayor, num2 negativo y menor
                String aux2 = valorAbsoluto(num2);
                return Restar(num1, aux2);
            }
            else if (op == 5){ //num1 negativo y mayor, num2 positivo y menor
                String aux1 = valorAbsoluto(num1);
                return ('-' + Restar(aux1, num2));
            }
        }
        else if (op == 4 || op == 6){ //num1 positivo o negativo menor, num2 negativo o positivo mayor
            if(num1.length()>num2.length()) num2 = añadeCeros(num1, num2);
            else num1 = añadeCeros(num2,num1);
            if (op == 4){ //num1 positivo y menor, num2 negativo y mayor
                String aux2 = valorAbsoluto(num2);
                return ("-" + Restar(aux2, num1));
            }
            else if (op == 6){ //num1 negativo y menor, num2 positivo y mayor
                String aux1 = valorAbsoluto(num1);
                return Restar(num2, aux1);
            }
        }
        else if (op == 11 || op == 12){
            return "0";
        }
        return "0";
    }
    
    public static String Restar(String num1, String num2){
        String res = "";
        int prestado = 0;
        int op = codigoOperacion(num1, num2);
        //System.out.println("Restar: num1: " + num1 + " | num2: " + num2 + " | op: " + op);
        if (op == 9 || op == 10) return "0"; //numeros iguales
        else if (op == 1 || op == 7){ //numero 1 mayor que número 2, ambos positivos o negativos
            String aux1 = valorAbsoluto(num1);
            String aux2 = valorAbsoluto(num2);
            //System.out.println("aux1: " + aux1 + "\naux2: " + aux2);
            aux2 = añadeCeros(aux1,aux2);
            for(int i = aux1.length()-1; i>=0; i--){
                String b = "" + aux1.charAt(i); //usando el dìgito en la posición i
                String c = "" + aux2.charAt(i); //usando el dìgito en la posición i
                if ((aux1.charAt(i) - prestado) >= aux2.charAt(i)){
                    int resta = Integer.parseInt(b) - prestado - Integer.parseInt(c);
                    res = resta + res;
                        prestado = 0;
                }
                else{
                    int resta = Integer.parseInt(b) + 10 - prestado - Integer.parseInt(c);
                    res = resta + res;
                    prestado = 1;
                }
            }
            if (op == 1) return res;
            else if (op == 7) return ("-" + res);
        }
        else if (op == 2 || op == 8){ //numero 2 mayor que número 1, ambos positivos o negativos
            String aux1 = valorAbsoluto(num1);
            String aux2 = valorAbsoluto(num2);
            aux1 = añadeCeros(aux2,aux1);
            //System.out.println("aux1: " + aux1 + "\naux2: " + aux2);
            for(int i = aux2.length()-1; i>=0; i--){
                String b = "" + aux1.charAt(i); //usando el dìgito en la posición i
                String c = "" + aux2.charAt(i); //usando el dìgito en la posición i
                if ((aux2.charAt(i) - prestado) >= aux1.charAt(i)){
                    int resta = Integer.parseInt(c) - prestado - Integer.parseInt(b);
                    res = resta + res;
                    prestado = 0;
                }
                else{
                    int resta = Integer.parseInt(c) + 10 - prestado - Integer.parseInt(b);
                    res = resta + res;
                    prestado = 1;
                }
            }
            if (op == 2) return('-' + res);
            else if (op == 8) return res;
        }
        else if (op == 3 || op == 5){ //num1 mayor (positivo o negativo), num2 menor (negativo o positivo)
            String aux1 = valorAbsoluto(num1);
            String aux2 = valorAbsoluto(num2);
            if (op == 3) return Sumar(aux1,aux2); //num1 mayor (positivo) num2 menor (positivo)
            else if (op == 5) return ('-' + Sumar(aux1, aux2));
        }
        else if (op == 4 || op == 6){
            String aux1 = valorAbsoluto(num1);
            String aux2 = valorAbsoluto(num2);
            if (op == 4) return Sumar(aux1,aux2);
            else if (op == 6) return ("-" + Sumar(aux1,aux2));
        }
        else if (op == 11) return Sumar(num1, valorAbsoluto(num2));
        else if (op == 12) return ("-" + Sumar(valorAbsoluto(num1), num2));
        return "0";
    }
    
    public static String Multiplicar(String num1, String num2){ // es una multiplicación básica
        int multi = Integer.parseInt((num1)) * Integer.parseInt(num2);
        String resultado = Integer.toString(multi);
        return resultado;
    }
    
    public static String multKaratsuba(String num1, String num2){//multiplicación recursiva con suelos y techos de ambos números
        int op = codigoOperacion(num1, num2);
        String aux1 = valorAbsoluto(num1);
        String aux2 = valorAbsoluto(num2);
        if (aux1.length() + aux2.length() <= 3){
            return Multiplicar(num1,num2);
        }
        else{
            if (aux1.length() > aux2.length()) aux2 = añadeCeros(aux1,aux2);
            else if (aux1.length() < aux2.length()) aux1 = añadeCeros(aux2,aux1);
            int n = Math.max(aux1.length(), aux2.length()); //regla: suelo = n/2; techo = n/2
            String num1_suelo = aux1.substring(0, n/2); //a = primera mitad del primer número
            String num1_techo = aux1.substring(n/2, n); //b = segunda mitad del primer número
            String num2_suelo = aux2.substring(0, n/2); //c = primera mitad del segundo número
            String num2_techo = aux2.substring(n/2, n); //d = segunda mitad del segundo número
            String p = Sumar(num1_suelo, num1_techo); //p = a + b
            String q = Sumar(num2_suelo, num2_techo); //q = c + d            
            String kar1 = multKaratsuba(num1_suelo, num2_suelo); // a * c
            String kar2 = multKaratsuba(num1_techo, num2_techo); // b * d
            String kar3 = multKaratsuba(p, q); // p * q
            String temp = Restar(kar3, kar1); // pq - ac
            String kar4 = Restar(temp, kar2); // (pq - ac) - bd
            double m = (double) n;
            for (int i = 0; i < 2 * Math.ceil(m/2); i++){
                if (i < Math.ceil(m/2)) kar4+="0";
                kar1+="0";
            }
            String temp2 = Sumar(kar1,kar4);
            temp2 = Sumar(temp2, kar2);
            if (op == 3 || op == 4 || op == 5 || op == 6) return ("-" + temp2);
            else return temp2;
        }
    }

    public static String Dividir(String num1, int num2, String opcion){ //listo
        String aux2 = Integer.toString(num2);
        int op = codigoOperacion(num1,aux2);
        //System.out.println("Dividir: num1: " + num1 + " | num2: " + num2 + " | opción: " + opcion);
        num1 = valorAbsoluto(num1);
        num2 = Math.abs(num2);
        String resultado = "";
        int acarreo = 0; //el acarreo será 0 inicialmente
        for(int i = 0; i < num1.length(); i++){ //iterando el dividendo
            String b = "" + num1.charAt(i);
            int x = acarreo * 10 + Integer.parseInt(b); //preparando el numero para ser dividido
            resultado = resultado + Integer.toString(x / num2); //agregando el resultado con el cuociente parcial
            acarreo = x % num2; //preparar el acarreo para la siguiente iteración
        }
        String resto = Integer.toString(acarreo);
        if (opcion.equals("dividir")){
            if (op == 3 || op == 4 || op == 5 || op == 6 || op == 11 || op == 12) return ("-" + eliminarCeros(resultado));
            else return eliminarCeros(resultado);
        }
        else if (opcion.equals("resto")) return resto;
        return ""; //retorna string vacio si el número ingresado no tiene nada válido
    }
    
    
    public static String Potencia(String num1, String num2){ //potencia recursiva de orden O(log(n))
        num1 = eliminarCeros(num1);
        //System.out.println("Potencia: num1: " + num1 + " | num2: " + num2);
        if (num2.equals("0")) return "1"; //caso base
        else if ("0".equals(Dividir(num2, 2, "resto"))) return eliminarCeros(multKaratsuba(Potencia(num1, Dividir(num2,2,"dividir")), Potencia(num1, Dividir(num2,2,"dividir")))); // si num2 es impar
        return eliminarCeros(multKaratsuba(num1, multKaratsuba(Potencia(num1, Dividir(num2,2,"dividir")), Potencia(num1, Dividir(num2,2,"dividir")))));  
    }
    
    
    
    public static String valorAbsoluto(String num1){ //quita el signo - del string, si lo tuviera.
        if (num1.charAt(0)=='-'){
            return num1.substring(1);
        }
        else return num1;
    }
    
    public static void main(String[] args){
        System.out.print("1. Sumar números\n2. Restar números\n3. Multiplicar números\n4. Dividir Números\n5. Obtener resto entre números\n6. Potencia entre números\n7. Valor absoluto de un número\n-> ");
        Scanner sc = new Scanner (System.in);
        String num1, num2;
        int opcion = sc.nextInt();
        int divisor;
        switch(opcion){
            case 1: //suma
                System.out.print("Ingrese primer número: ");
                num1 = sc.next();
                System.out.print("Ingrese segundo número: ");
                num2 = sc.next();
                System.out.println(eliminarCeros(Sumar(num1, num2)));
                break;
            case 2: //resta
                System.out.print("Ingrese primer número: ");
                num1 = sc.next();
                System.out.print("Ingrese segundo número: ");
                num2 = sc.next();
                System.out.println(eliminarCeros(Restar(num1, num2)));
                break;
            case 3: //multiplicar
                System.out.print("Ingrese factor: ");
                num1 = sc.next();
                System.out.print("Ingrese factor: ");
                num2 = sc.next();
                System.out.println(eliminarCeros(multKaratsuba(num1,num2)));
                break;
            case 4: //dividir
                System.out.print("Ingrese dividendo: ");
                num1 = sc.next();
                System.out.print("Ingrese divisor: ");
                num2 = sc.next();
                divisor = Integer.parseInt(num2);
                System.out.println(Dividir(num1,divisor,"dividir"));
                break;
            case 5: //módulo
                System.out.print("Ingrese dividendo: ");
                num1 = sc.next();
                System.out.print("Ingrese divisor: ");
                num2 = sc.next();
                divisor = Integer.parseInt(num2);
                System.out.println(Dividir(num1,divisor,"resto"));
                break;
            case 6: //potencia
                System.out.print("Ingrese base: ");
                num1 = sc.next();
                System.out.print("Ingrese exponente: ");
                num2 = sc.next();
                System.out.println(eliminarCeros(Potencia(num1,num2)));
                break;
            case 7: //valor absoluto
                System.out.print("Ingrese número: ");
                num1 = sc.next();
                System.out.println(valorAbsoluto(num1));
                break;
            case 8: //eliminar ceros
                System.out.print("Ingrese número: ");
                num1 = sc.next();
                System.out.println(eliminarCeros(num1));
                break;
            case 9: //comprobar numero mayor
                System.out.print("Ingrese número 1 : ");
                num1 = sc.next();
                System.out.print("Ingrese número 2 : ");
                num2 = sc.next();
                System.out.println(codigoOperacion(num1, num2));
                break;
            case 10: //añadir ceros
                System.out.print("Ingrese número 1: ");
                num1 = sc.next();
                System.out.print("Ingrese número 2: ");
                num2 = sc.next();
                System.out.println(añadeCeros(num1,num2));
                break;
        }
    }
}
