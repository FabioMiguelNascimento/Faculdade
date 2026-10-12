package ted1e2;

public class Criptografia {
    static void main() {
        int opc = -1;
        while (opc != 0) {
            exibeMenu();
            opc = Integer.parseInt(IO.readln("Opção: "));
            executaFuncao(opc);
        }
    }

    public static void exibeMenu() {
        IO.println("CRIPTOGRAFIA:");
        IO.println("1 - Criptografar");
        IO.println("2 - Descriptografar");
        IO.println("0 - Sair");
    }

    public static void executaFuncao(int opc) {
        switch (opc) {
            case 1 -> cifrar();
            case 2 -> decifrar();
            case 0 -> IO.println("Programa encerrado");
        }
    }

    private static void cifrar() {
        String texto = IO.readln("Texto a cifrar: ");
        String chave = IO.readln("Chave utilizada: ");
        int deslocamento = Integer.parseInt(IO.readln("Deslocamento: "));
        // Cifra o texto e a chave da mensagem usando cifra de Cesar
        String textoCesar = Cesar.cifraTextoCesar(texto, deslocamento);
        String chaveCesar = Cesar.cifraTextoCesar(chave, deslocamento);
        // Aplica cifra de Vigenere ao resultado da cifra de Cesar
        String resultado = Vigenere.cifraTextoVigenere(textoCesar, chaveCesar);
        IO.println("Resultado: " + resultado);
    }

    private static void decifrar() {
        String texto = IO.readln("Texto a decifrar: ");
        String chave = IO.readln("Chave utilizada: ");
        int deslocamento = Integer.parseInt(IO.readln("Deslocamento: "));
        // Cifra a chave da mensagem usando Cesar
        String chaveCesar = Cesar.cifraTextoCesar(chave, deslocamento);
        // Decifra o texto que veio com cifra de Cesar aplicada usando a chave com a cifra de Cesar
        String textoCesar = Vigenere.decifraTextoVigenere(texto, chaveCesar);
        // Decifra mensagem com cifra de Cesar
        String resultado = Cesar.decifraTextoCesar(textoCesar, deslocamento);
        IO.println("Resultado: " + resultado);
    }

}
