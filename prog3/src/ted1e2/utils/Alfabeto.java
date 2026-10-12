package ted1e2.utils;

public class Alfabeto {

    public static final int tamAlfabeto = 26;

    /**
     * @return retorna um vetor com as letras do alfabeto de A-Z minúsculas
     */
    public static char[] inicializaVetorAlfabeto() {
        char letra = 'a';
        char[] vetAlfabeto = new char[tamAlfabeto];
        for (int i = 0; i < tamAlfabeto; i++) {
            vetAlfabeto[i] = letra;
            letra++;
        }
        return vetAlfabeto;
    }

    /**
     * Retorna se um carácter está contido no vetor de letras do alfabeto
     *
     * @param letra letra a ser procurada no vetor de letras do alfabeto
     * @return True se contem, False se não contem
     */
    public static boolean alfabetoContemLetra(char letra) {
        char[] vetorAlfabeto = Alfabeto.inicializaVetorAlfabeto();
        for (char l : vetorAlfabeto) {
            if (l == letra) {
                return true;
            }
        }
        return false;
    }
}
