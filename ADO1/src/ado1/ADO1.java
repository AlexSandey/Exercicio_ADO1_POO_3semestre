/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ado1;

import java.io.*;
import static java.lang.Float.parseFloat;
import java.text.DecimalFormat;
import static javafx.application.Platform.exit;

/**
 *
 * @author alexsandey
 */
public class ADO1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        // nome do arquivo
        String nomeDoArquivo1 = "pib.txt";
        String nomeDoArquivo2 = "regioes.txt";

        // linha temporaria
        String linha = null;
        float[] pibs = new float[27];
        String[] estados = new String[27];
        String[] regioes = new String[5];
        float[] pibRegioes = new float[5];
        String[] salvaSaida = new String[5];
        /*      ------------------------------------- */
 /*      Abertura de arquivo e loop de leitura */
 /*      ------------------------------------- */
        try {
            FileReader fileReader = new FileReader(nomeDoArquivo1);

            BufferedReader bufferedReader = new BufferedReader(fileReader);
            float somaPib = 0;
            int cont = 0;
            // loop por cada linha do arquivo
            while ((linha = bufferedReader.readLine()) != null) {
                String[] values = linha.split(";");
                somaPib = somaPib + (parseFloat(values[1]));
                pibs[cont] = parseFloat(values[1]);
                estados[cont] = values[0];
                cont++;
            }
            DecimalFormat decimal = new DecimalFormat("00.00");
            for (int i = 0; i < 27; i++) {
                float porcentagem = (pibs[i] * 100) / somaPib;
                System.out.println("O estado " + estados[i] + " contem % " + decimal.format(porcentagem) + " do PIB.");

            }
            //somaPib = somaPib+(values[2]);
            // feche o arquivo
            bufferedReader.close();
        } catch (FileNotFoundException ex) {
            System.out.println("Arquivo inexistente: '" + nomeDoArquivo1 + "'");
        } catch (IOException ex) {
            System.out.println("Erro lendo o arquivo '" + nomeDoArquivo1 + "'");
        }

        try {
            FileReader fileReader = new FileReader(nomeDoArquivo2);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            boolean itsEstado = false;
            String[] regiao = new String[5];
            int contRegiao = 0;
            float somaRegiao = 0;

            // loop por cada linha do arquivo
            while ((linha = bufferedReader.readLine()) != null) {
                itsEstado = false;
                if (!linha.equals("")) {
                    for (int i = 0; i < 27; i++) {
                        if (linha.equals(estados[i])) {
                            itsEstado = true;
                            somaRegiao = somaRegiao + pibs[i];
                            System.out.println(" PIB do Estado: " + linha + " incluido na regiao " + regiao[contRegiao - 1]);
                        }
                    }

                    if (itsEstado == false) {
                        regiao[contRegiao] = linha;
                        System.out.println("Região encontrada: " + linha);
                        contRegiao++;
                    }
                } else if (linha.equals("")) {
                    salvaSaida[contRegiao - 1] = ("pib da regiao " + regiao[contRegiao - 1] + " = " + somaRegiao);
                    System.out.println("pib da regiao " + regiao[contRegiao - 1] + " = " + somaRegiao);
                    somaRegiao = 0;
                    System.out.println(linha);
                }

            }
            //somaPib = somaPib+(values[2]);
            // feche o arquivo
            bufferedReader.close();
        } catch (FileNotFoundException ex) {
            System.out.println("Arquivo inexistente: '" + nomeDoArquivo1 + "'");
        } catch (IOException ex) {
            System.out.println("Erro lendo o arquivo '" + nomeDoArquivo1 + "'");
        }

        /*      ------------------------------------- */
 /*      Exemplo de escrita em arquivo         */
 /*      ------------------------------------- */
        String arquivoDeSaida = "saida.txt";

        try {

            FileWriter fileWriter = new FileWriter(arquivoDeSaida);

            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            
            for (int i = 0; i < 5; i++) {
                bufferedWriter.write(salvaSaida[i]);
                bufferedWriter.newLine();
            }
            

            // feche o arquivo
            bufferedWriter.close();
        } catch (IOException ex) {
            System.out.println("Erro de escrita em '" + arquivoDeSaida + "'");
        }

    }

}
