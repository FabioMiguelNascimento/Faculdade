package ted1e2;

import ted1e2.utils.Alfabeto;

public class Cesar {

    /**
     * Cifra a mensagem
     *
     * @param texto        Texto da mensagem
     * @param deslocamento Valor do deslocamento
     * @return Mensagem cifrada
     */
    public static String cifraTextoCesar(String texto, int deslocamento) {
        // Transforma as 'Strings' recebidas em dois vetores de caracteres
        char[] vetTexto = texto.toLowerCase().toCharArray();
        // Cria uma instância do StringBuilder para fazer a concatenação de 'Strings' dentro do laço
        StringBuilder resultado = new StringBuilder();
        for (char letraAtual : vetTexto) {
            if (Alfabeto.alfabetoContemLetra(letraAtual))
                resultado.append(retornaNovaLetra(deslocamento, letraAtual));
            else
                resultado.append(letraAtual);
        }
        return resultado.toString();
    }

    /**
     * Decifra a mensagem
     *
     * @param texto        Texto da mensagem
     * @param deslocamento Valor do deslocamento
     * @return Mensagem decifrada
     */
    public static String decifraTextoCesar(String texto, int deslocamento) {
        // Transforma as 'Strings' recebidas em dois vetores de caracteres
        char[] vetTexto = texto.toLowerCase().toCharArray();
        // Cria uma instância do StringBuilder para fazer a concatenação de 'Strings' dentro do laço
        StringBuilder resultado = new StringBuilder();
        for (char letraAtual : vetTexto) {
            if (Alfabeto.alfabetoContemLetra(letraAtual))
                resultado.append(retornaLetraOriginal(deslocamento, letraAtual));
            else
                resultado.append(letraAtual);
        }
        return resultado.toString();
    }

    /**
     * Encontra a letra na nova posição
     *
     * @param deslocamento Valor do deslocamento
     * @param letra        Letra que será usada para encontrar a nova letra
     * @return A nova letra calculada
     */
    public static char retornaNovaLetra(int deslocamento, char letra) {
        char[] vetAlfabeto = Alfabeto.inicializaVetorAlfabeto();
        int posNovaLetra = 0;
        int posLetraAtual = 0;
        for (int i = 0; i < vetAlfabeto.length; i++) {
            // Encontra a posição da letra atual
            if (letra == vetAlfabeto[i])
                posLetraAtual = i;

            // Encontra a posição da nova letra
            if ((posLetraAtual + deslocamento) >= vetAlfabeto.length) {
                posNovaLetra = (posLetraAtual + deslocamento) - vetAlfabeto.length;
            } else {
                posNovaLetra = posLetraAtual + deslocamento;
            }
        }
        return vetAlfabeto[posNovaLetra];
    }

    /**
     * Encontra a letra na posição original
     *
     * @param deslocamento Valor do deslocamento
     * @param letra        Letra que será usada para encontrar a posição original
     * @return A letra original calculada
     */
    public static char retornaLetraOriginal(int deslocamento, char letra) {
        char[] vetAlfabeto = Alfabeto.inicializaVetorAlfabeto();
        int posLetraOriginal = 0;
        int posLetraAtual = 0;
        for (int i = 0; i < vetAlfabeto.length; i++) {
            // Encontra a posição da letra atual
            if (letra == vetAlfabeto[i])
                posLetraAtual = i;

            // Encontra a posição da nova letra
            if ((posLetraAtual - deslocamento) < 0) {
                posLetraOriginal = (posLetraAtual - deslocamento) + vetAlfabeto.length;
            } else {
                posLetraOriginal = posLetraAtual - deslocamento;
            }
        }
        return vetAlfabeto[posLetraOriginal];
    }
}
