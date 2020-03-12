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


    public static void main(String[] args) {

        String nomeDoArquivo1 = "pib.txt";
        String nomeDoArquivo2 = "regioes.txt";

        String linha = null;
        float[] pibs = new float[27];
        String[] estados = new String[27];
        String[] salvaSaida = new String[5];

        try {
            FileReader fileReader = new FileReader(nomeDoArquivo1);

            BufferedReader bufferedReader = new BufferedReader(fileReader);
            float somaPib = 0;
            int cont = 0;
            
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
           
            bufferedReader.close();
        } catch (FileNotFoundException ex) {
            System.out.println("Arquivo inexistente: '" + nomeDoArquivo1 + "'");
        } catch (IOException ex) {
            System.out.println("Erro lendo o arquivo '" + nomeDoArquivo1 + "'");
        }

       

        String arquivoDeSaida = "saida.txt";

        try {

            FileWriter fileWriter = new FileWriter(arquivoDeSaida);

            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            
            for (int i = 0; i < 5; i++) {
                bufferedWriter.write(salvaSaida[i]);
                bufferedWriter.newLine();
            }
            

            bufferedWriter.close();
        } catch (IOException ex) {
            System.out.println("Erro de escrita em '" + arquivoDeSaida + "'");
        }

    }

}
