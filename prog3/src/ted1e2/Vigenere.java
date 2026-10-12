package ted1e2;

import ted1e2.utils.Alfabeto;

import static ted1e2.utils.Alfabeto.tamAlfabeto;

public class Vigenere {

    // VALORES CONSTANTES
    static final int tamLinha = tamAlfabeto;
    static final int tamColuna = tamAlfabeto;

    /**
     * @return retorna uma matriz de caracteres para a cifra de Vigenere
     */
    public static char[][] inicializaMatrizVigenere() {
        char[][] matriz = new char[tamLinha][tamColuna];
        char letra = 'a';
        for (int l = 0; l < tamLinha; l++) {
            for (int c = 0; c < tamColuna; c++) {
                // A posição atual da matriz recebe a letra atual
                matriz[l][c] = letra;
                // Incrementa a letra para cada coluna adicionando na sequência horizontal correta
                letra++;
                // Se a letra for maior que 'z'
                if (letra > 'z') {
                    // Inicializa a letra com 'a' novamente
                    letra = 'a';
                }
            }
            // Incrementa a letra na troca de linha adicionando na sequência vertical correta
            letra++;
        }
        return matriz;
    }

    /**
     * Encontra as posições da letra do texto e da chave
     *
     * @param letraTexto letra do texto para pesquisar no alfabeto
     * @param letraChave letra da chave para pesquisar no alfabeto
     * @return Retorna um vetor de duas posições onde 0 = posição da letra do texto e 1 = posição da letra da chave
     */
    public static int[] retornaCoordenadasCifra(char letraTexto, char letraChave) {
        char[] alfabeto = Alfabeto.inicializaVetorAlfabeto();
        int[] vetCoordenadas = new int[2];
        // Encontra a posição da letra do texto que vai ser cifrado
        for (int i = 0; i < tamAlfabeto; i++) {
            if (letraTexto == alfabeto[i]) {
                vetCoordenadas[0] = i;
            }
        }
        // Encontra a posição da letra da chave usada na cifra
        for (int i = 0; i < tamAlfabeto; i++) {
            if (letraChave == alfabeto[i]) {
                vetCoordenadas[1] = i;
            }
        }
        return vetCoordenadas;
    }

    /**
     * Encontra a posição da letra do texto original no alfabeto
     *
     * @param letraTextoCifrado letra do texto para pesquisar no alfabeto
     * @param letraChave        letra da chave para pesquisar no alfabeto
     * @return Retorna a posição da letra do texto original no alfabeto
     */
    public static int retornaPosicaoLetraOriginal(char letraTextoCifrado, char letraChave) {
        char[][] matrizVigenere = inicializaMatrizVigenere();
        int posLetraOriginal = -1;
        for (int l = 0; l < tamLinha; l++) {
            if (matrizVigenere[l][0] == letraChave) {
                for (int c = 0; c < tamColuna; c++) {
                    if (matrizVigenere[l][c] == letraTextoCifrado) {
                        posLetraOriginal = c;
                    }
                }
            }
        }
        return posLetraOriginal;
    }

    /**
     * Ajusta a chave da cifra quando ela for menor que o texto
     *
     * @param texto vetor com os caracteres do texto
     * @param chave vetor com os caracteres da chave
     * @return retorna um novo vetor de caracteres com a nova chave do tamanho do texto
     */
    public static char[] ajustaChaveCifra(char[] texto, char[] chave) {
        if (texto.length == chave.length)
            return chave;
        else {
            int tamChaveAntiga = chave.length;
            int idxChave = 0;
            char[] novaChave = new char[texto.length];
            for (int i = 0; i < texto.length; i++) {
                novaChave[i] = chave[idxChave];
                idxChave++;
                if (idxChave == tamChaveAntiga)
                    idxChave = 0;
            }
            return novaChave;
        }
    }

    /**
     * Retorna a letra numa coordenada da matriz de Vigenere
     *
     * @param posLinha  posição da linha da letra
     * @param posColuna posição da coluna da letra
     * @return Retorna a letra localizada na posição passada como parâmetro
     */
    public static char retornaLetraDaMatriz(int posLinha, int posColuna) {
        char[][] matriz = inicializaMatrizVigenere();
        return matriz[posLinha][posColuna];
    }

    /**
     * Retorna a letra do alfabeto com base na posição recebida
     *
     * @param posLetra posição da letra no alfabeto
     * @return Retorna a letra localizada na posição passada como parâmetro ou um espaço se a letra não for encontrada
     */
    public static char retornaLetraAlfabeto(int posLetra) {
        char[] alfabeto = Alfabeto.inicializaVetorAlfabeto();
        for (int i = 0; i < tamAlfabeto; i++) {
            if (posLetra == i) {
                return alfabeto[i];
            }
        }
        return ' ';
    }

    /**
     * Cifra um texto normal usando a cifra de Vigenere
     *
     * @param texto que vai ser cifrado
     * @param chave que vai servir para cifrar o texto
     * @return Retorna uma String com o texo cifrado em letras minúsculas
     */
    public static String cifraTextoVigenere(String texto, String chave) {
        // Transforma as 'Strings' recebidas em dois vetores de caracteres
        char[] vetTexto = texto.toLowerCase().toCharArray();
        char[] vetChave = chave.toLowerCase().toCharArray();
        // Declara o vetor de coordenadas que vai receber as posições das letras na matriz
        int[] vetCoordenadas;
        // Guarda o tamanho do texto para o laço ficar legível sem percorrer um vetor em específico
        int tamTexto = vetTexto.length;
        // Cria uma instância do StringBuilder para fazer a concatenação de 'Strings' dentro do laço
        StringBuilder resultado = new StringBuilder();
        // Ajusta a chave da cifra quando for necessário
        vetChave = ajustaChaveCifra(vetTexto, vetChave);
        // Percorre os vetores de caracteres e faz a cifra de cada uma das letras
        for (int i = 0; i < tamTexto; i++) {
            // Letra atual percorrida no vetor de texto
            char letraAtual = vetTexto[i];
            // Busca as respetivas posições das letras atuais do texto e da chave
            if (Alfabeto.alfabetoContemLetra(letraAtual)) {
                vetCoordenadas = retornaCoordenadasCifra(vetTexto[i], vetChave[i]);
                // Adiciona a letra encontrada usando as posições do "vetCoordenadas" na String de resultado
                resultado.append(retornaLetraDaMatriz(vetCoordenadas[0], vetCoordenadas[1]));
            } else
                resultado.append(letraAtual);
        }
        // Retorna o texto cifrado
        return resultado.toString();
    }

    /**
     * Decifra um texto cifrado com Vigenere
     *
     * @param textoCifrado Texto cifrado
     * @param chave        Chave para decifrar
     * @return Texto decifrado
     */
    public static String decifraTextoVigenere(String textoCifrado, String chave) {
        // Transforma as 'Strings' recebidas em dois vetores de caracteres
        char[] vetTextoCifrado = textoCifrado.toLowerCase().toCharArray();
        char[] vetChave = chave.toLowerCase().toCharArray();
        // Guarda o tamanho do texto para o laço ficar legível sem percorrer um vetor em específico
        int tamTexto = vetTextoCifrado.length;
        // Cria uma instância do StringBuilder para fazer a concatenação de 'Strings' dentro do laço
        StringBuilder resultado = new StringBuilder();
        // Ajusta a chave da cifra quando for necessário
        vetChave = ajustaChaveCifra(vetTextoCifrado, vetChave);
        // Percorre os vetores de caracteres e decifra cada uma das letras
        for (int i = 0; i < tamTexto; i++) {
            // Letra atual percorrida no vetor de texto
            char letraAtual = vetTextoCifrado[i];
            // Busca as respetivas posições das letras atuais do texto e da chave
            if (Alfabeto.alfabetoContemLetra(letraAtual)) {
                // Armazena a posição da letra original no alfabeto
                int posLetraOriginal = retornaPosicaoLetraOriginal(vetTextoCifrado[i], vetChave[i]);
                // Adiciona a letra encontrada
                resultado.append(retornaLetraAlfabeto(posLetraOriginal));
            } else {
                resultado.append(letraAtual);
            }
        }
        // Retorna o texto decifrado
        return resultado.toString();
    }
}
