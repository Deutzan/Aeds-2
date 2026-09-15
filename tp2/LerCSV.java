import java.io.BufferedReader;
import java.io.FileReader;

public class LerCSV {

    public static Veiculo[] ler(String carro) {

        //-- vetor para armazenar os veículos --//
        Veiculo[] veiculos = new Veiculo[501];

        //-- Variável que indica quantos veículos já foram lidos --//
        int quantidade = 0;

        try {

            // Abre o arquivo para leitura.
            BufferedReader arquivo = new BufferedReader(new FileReader(carro));

            arquivo.readLine();

            String linha;

            //-- vai ler enquanto existir uma linha para ler --//
            while ((linha = arquivo.readLine()) != null) {//while != EOF
                
                String[] dados = linha.split(",");

                //-- converte os dados --//

                int id = Integer.parseInt(dados[0]);
                String marca = dados[1];
                String modelo = dados[2];
                int ano = Integer.parseInt(dados[3]);
                String categoria = dados[4];
                String combustivel = dados[5];
                int cilindros = Integer.parseInt(dados[6]);
                float cilindrada = Float.parseFloat(dados[7]);
                String transmissao = dados[8];
                String tracao = dados[9];
                float consumoCidade = Float.parseFloat(dados[10]);
                float consumoEstrada = Float.parseFloat(dados[11]);
                float co2 = Float.parseFloat(dados[12]);
                boolean turbo = Boolean.parseBoolean(dados[13]);

                //-- DATA --//
                String[] data = dados[14].split("-");
                int anoData = Integer.parseInt(data[0]);
                int mesData = Integer.parseInt(data[1]);
                int diaData = Integer.parseInt(data[2]);

                /* Cria o objeto Data.*/
                Data dataRegistro = new Data(anoData,mesData,diaData);

                //-- criao objeto veiculo --//
                Veiculo veiculo = new Veiculo(id,marca,modelo,ano,categoria,combustivel,cilindros,cilindrada,transmissao,tracao,consumoCidade,consumoEstrada,co2,turbo,dataRegistro
                );

                // Coloca o objeto Veiculo dentro do vetor.
                veiculos[quantidade] = veiculo;

                // Passa para a próxima posição.
                quantidade++;
            }

            // Fecha o arquivo depois de terminar a leitura.
            arquivo.close();

        } catch (Exception e) {

            // Caso aconteça algum erro durante a leitura.
            System.out.println("Erro ao ler o arquivo.");
        }

        // Retorna o vetor contendo os veículos.
        return veiculos;
    }

    //-- procura as info do carro apartir do id --//
    public static void printCarro(Veiculo[] veiculos, int id) {
    // Percorre todos os veículos
    for (int i = 0; i < veiculos.length; i++) {
        if (veiculos[i] != null && veiculos[i].getId() == id) {
            veiculos[i].printCarro();
            return;
        }
    }

    // Caso nenhum veículo tenha aquele ID
    System.out.println("Veiculo nao encontrado.");
}

}
