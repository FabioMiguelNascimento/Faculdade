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
            case 1 -> aplicaCifraVigenere();
            case 2 -> removeCifraVigenere();
            case 0 -> IO.println("Programa encerrado");
        }
    }

    private static void removeCifraVigenere() {
        String texto = IO.readln("Texto a decifrar: ");
        String chave = IO.readln("Chave utilizada: ");
        IO.println("Resultado: " + Vigenere.decifraTextoVigenere(texto, chave));
    }

    private static void aplicaCifraVigenere() {
        String texto = IO.readln("Texto a cifrar: ");
        String chave = IO.readln("Chave utilizada: ");
        IO.println("Resultado: " + Vigenere.cifraTextoVigenere(texto, chave));
    }


}
