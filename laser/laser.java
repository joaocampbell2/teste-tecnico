
import java.io.PrintWriter;
import java.util.Scanner;

public class laser {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);  // Para imprimir na saída padrão

        while (sc.hasNext()) {
            try {
                int A = sc.nextInt();
                int C = sc.nextInt();

                int[] blocos = new int[C];
                for (int i = 0; i < C; i++) {
                    blocos[i] = sc.nextInt();
                }

                int resposta = 0;
                for (int i = 1; i < C; i++) {
                    if (blocos[i] > blocos[i - 1]) {
                        resposta += blocos[i] - blocos[i - 1];
                    }
                }
                resposta += A - blocos[C - 1];

                out.println(resposta);
            } catch (Exception e) {
                break;
            }
        }
        out.close();
        sc.close();
    }
}

