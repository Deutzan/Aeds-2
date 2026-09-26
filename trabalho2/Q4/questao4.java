package Q4;
import java.io.BufferedReader;
import java.io.FileReader;

public class questao4 {

    public static void main(String[] args) {

        // Lê todos os veículos do arquivo CSV
        Veiculo[] todos = LerCSV.ler("veiculosJ.csv");

        // Vetor para armazenar somente os veículos pedidos na entrada
        Veiculo[] selecionados = new Veiculo[50];

        int quantidade = 0;

        // Lê os IDs até encontrar -1
        try {
            BufferedReader entrada = new BufferedReader(
                new java.io.InputStreamReader(System.in)
            );

            String linha;

            while ((linha = entrada.readLine()) != null) {

                int id = Integer.parseInt(linha);

                if (id == -1) {
                    break;
                }

                Veiculo veiculo = LerCSV.buscarPorId(todos, id);

                if (veiculo != null) {
                    selecionados[quantidade] = veiculo;
                    quantidade++;
                }
            }

        } catch (Exception e) {
            System.out.println("Erro ao ler a entrada.");
            e.printStackTrace();
        }

        // Ordena somente os veículos selecionados
        Veiculo.Insertsort(selecionados, quantidade);

        // Imprime os veículos
        LerCSV.printCarro(selecionados, quantidade);
    }
}

// LerCSV
class LerCSV {

    public static Veiculo[] ler(String carro) {

        Veiculo[] veiculos = new Veiculo[501];
        int quantidade = 0;

        try {
            BufferedReader arquivo = new BufferedReader(new FileReader(carro));

            // Ignora o cabeçalho
            arquivo.readLine();

            String linha;
            while ((linha = arquivo.readLine()) != null) {
                Veiculo veiculo = Veiculo.ParseVeiculo(linha);
                veiculos[quantidade] = veiculo;
                quantidade++;
            }

            arquivo.close();

        } catch (Exception e) {
            System.out.println("Erro ao ler o arquivo.");
            e.printStackTrace();
        }
        return veiculos;
    }


    // Busca um veículo pelo ID
    public static Veiculo buscarPorId(Veiculo[] veiculos, int id) {
        for (int i = 0; i < veiculos.length; i++) {
            if (veiculos[i] != null &&
                veiculos[i].getId() == id) {
                return veiculos[i];
            }
        }
        return null;
    }


    // Imprime somente a quantidade de veículos selecionados
    public static void printCarro(Veiculo[] veiculos, int quantidade) {
        for (int i = 0; i < quantidade; i++) {
            if (veiculos[i] != null) {
                System.out.println(veiculos[i].format());
            }
        }
    }
}

// Data
class Data {
    private int dia;
    private int mes;
    private int ano;


    public Data() {
        this.dia = 0;
        this.mes = 0;
        this.ano = 0;
    }


    public Data(int dia, int mes, int ano) {
        this.dia = dia;
        this.mes = mes;
        this.ano = ano;
    }

    public int getDia() {
        return dia;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public static Data ParseDia(String dataRegistro) {
        String[] data = dataRegistro.split("-");
        int anoData = Integer.parseInt(data[0]);
        int mesData = Integer.parseInt(data[1]);
        int diaData = Integer.parseInt(data[2]);
        
        return new Data(diaData, mesData, anoData);
    }


    public String format() {
        return String.format("%02d/%02d/%04d",dia, mes,ano);
    }
}

// Veiculo
class Veiculo {
    private int id;
    private String marca;
    private String modelo;
    private int ano;
    private String categoria;
    private String combustivel;
    private int cilindros;
    private double cilindrada;
    private String transmissao;
    private String tracao;
    private double consumo_cidade;
    private double consumo_estrada;
    private double co2;
    private boolean turbo;
    private Data data_registro;


    public Veiculo(
        int id,
        String marca,
        String modelo,
        int ano,
        String categoria,
        String combustivel,
        int cilindros,
        double cilindrada,
        String transmissao,
        String tracao,
        double consumo_cidade,
        double consumo_estrada,
        double co2,
        boolean turbo,
        Data data_registro
    ) {

        this.id = id;
        this.marca = marca;
        this.modelo = modelo;
        this.ano = ano;
        this.categoria = categoria;
        this.combustivel = combustivel;
        this.cilindros = cilindros;
        this.cilindrada = cilindrada;
        this.transmissao = transmissao;
        this.tracao = tracao;
        this.consumo_cidade = consumo_cidade;
        this.consumo_estrada = consumo_estrada;
        this.co2 = co2;
        this.turbo = turbo;
        this.data_registro = data_registro;
    }


    public int getId() {
        return id;
    }

    public String getMarca() {
        return marca;
    }

    public String getModelo() {
        return modelo;
    }

    public int getAno() {
        return ano;
    }

    public String getCategoria() {
        return categoria;
    }

    public String getCombustivel() {
        return combustivel;
    }

    public int getCilindros() {
        return cilindros;
    }

    public double getCilindrada() {
        return cilindrada;
    }

    public String getTransmissao() {
        return transmissao;
    }

    public String getTracao() {
        return tracao;
    }

    public double getConsumo_cidade() {
        return consumo_cidade;
    }

    public double getConsumo_estrada() {
        return consumo_estrada;
    }

    public double getCo2() {
        return co2;
    }

    public boolean isTurbo() {
        return turbo;
    }

    public Data getData_registro() {
        return data_registro;
    }

    // Parse do veículo
    public static Veiculo ParseVeiculo(String linha) {
        String[] dados = linha.split(",(?=(?:[^\\[]*\\[[^\\]]*\\])*[^\\]]*$)");

        int id = Integer.parseInt(dados[0]);
        String marca = dados[1];
        String modelo = dados[2];
        int ano = Integer.parseInt(dados[3]);
        String categoria = dados[4];
        String combustivel = dados[5];
        int cilindros = Integer.parseInt(dados[6]);
        double cilindrada = Double.parseDouble(dados[7]);
        String transmissao = dados[8];
        String tracao = dados[9];
        double consumoCidade = Double.parseDouble(dados[10]);
        double consumoEstrada = Double.parseDouble(dados[11]);
        double co2 = Double.parseDouble(dados[12]);
        boolean turbo = Boolean.parseBoolean(dados[13]);
        Data dataRegistro = Data.ParseDia(dados[14]);

        return new Veiculo(
            id,
            marca,
            modelo,
            ano,
            categoria,
            combustivel,
            cilindros,
            cilindrada,
            transmissao,
            tracao,
            consumoCidade,
            consumoEstrada,
            co2,
            turbo,
            dataRegistro
        );
    }

    // Format
    public String format() {
        String s = String.format("[%d ## %s ## %s ## %d ## %s ## %s ## %d ## %.1f ## %s ## %s ## %.2f ## %.2f ## %.1f ## %b ## ",
            id,
            marca,
            modelo,
            ano,
            categoria,
            combustivel,
            cilindros,
            cilindrada,
            transmissao,
            tracao,
            consumo_cidade,
            consumo_estrada,
            co2,
            turbo
        );
        s += data_registro.format();
        s += "]";
        return s;
    }

    // Insertion Sort
    public static void Insertsort(Veiculo[] veiculos, int quantidade) {
        // Começa na segunda posição
        for (int i = 1; i < quantidade; i++) {
            Veiculo atual = veiculos[i];
            int j = i - 1;
            while (j >= 0 &&veiculos[j].getMarca().compareTo(atual.getMarca()) > 0
            ) {
                veiculos[j + 1] = veiculos[j];
                j--;
            }
            // Coloca o veículo na posição correta
            veiculos[j + 1] = atual;
        }
    }
}
