package testeJava;
import java.util.*;

import static java.lang.Character.isDigit;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String expressao = "";
        while (!expressao.equals("0")){
            System.out.println("Insira sua expressão abaixo (Digite 0 para sair) :");
            expressao = sc.nextLine();
            if (!expressao.equals("0")){
                float resultado = calculaExpressao(expressao.replace(" ", ""));
                System.out.println("Resultado: " + resultado);
            }
        }
    }

    public static float calculaExpressao(String expressao){

        Deque<Float> stackNumero = new ArrayDeque<>();
        Deque<Character> stackOperador = new ArrayDeque<>();

        for (int i = 0; i < expressao.length(); i++) {
            char c = expressao.charAt(i);

            if(isDigit(c)){
                String num = "";
                while (i < expressao.length() && (isDigit(expressao.charAt(i)) || expressao.charAt(i) == '.')) {
                    num += (expressao.charAt(i));
                    i++;
                }
                i--;
                stackNumero.push(Float.parseFloat(num));
            }
            else if(c == '('){
                stackOperador.push(c);
            }
            else if(c == ')'){
                char topOperador;
                float topNumero1, topNumero2;
                while (!stackOperador.isEmpty() && stackOperador.peek() != '(') {
                    topNumero2 = stackNumero.pop();
                    topNumero1 = stackNumero.pop();
                    topOperador = stackOperador.pop();
                    stackNumero.push(executarOperacao(topOperador, topNumero1, topNumero2));
                }
                stackOperador.pop();
            }
            else if (c == '+' || c == '-' || c == '*' || c == '/' || c == '^') {
                char topOperador;
                float topNumero1, topNumero2;
                while(!stackOperador.isEmpty() && verificarPrecedencia(stackOperador.peek()) >= verificarPrecedencia(c) && stackNumero.size() >= 2){
                    topNumero2 = stackNumero.pop();
                    topNumero1 = stackNumero.pop();
                    topOperador = stackOperador.pop();
                    stackNumero.push(executarOperacao(topOperador, topNumero1, topNumero2));
                }
                stackOperador.push(c);
            }

        }

        while (!stackOperador.isEmpty()) {
            float topNumero2 = stackNumero.pop();
            float topNumero1 = stackNumero.pop();
            char topOperador = stackOperador.pop();
            stackNumero.push(executarOperacao(topOperador, topNumero1, topNumero2));
        }

        return stackNumero.pop();

    }

    static int verificarPrecedencia(char operador){
        if (operador == '+' || operador == '-'){
            return 1;
        }
        if (operador == '*' || operador == '/'){
            return 2;
        }
        if (operador == '^'){
            return 3;
        }
        return 0;
    }

    static float executarOperacao(char operador,float n1,float n2){
        return switch (operador) {
            case '+' -> (n1 + n2);
            case '-' -> (n1 - n2);
            case '*' -> (n1 * n2);
            case '/' -> (n1 / n2);
            case '^' -> (float) (Math.pow(n1, n2));
            default -> throw new IllegalArgumentException("Operador inválido: " + operador);
        };
    }
}

