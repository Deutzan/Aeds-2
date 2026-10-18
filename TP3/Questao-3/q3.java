import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.Locale;

public class q3 {

    public static void main(String[] args) {

        Veiculo[] todos = LerCSV.ler("/tmp/veiculos.csv");
        Veiculo[] selecionados = new Veiculo[51];

        int quantidade = 0;

        try {
            BufferedReader entrada = new BufferedReader(
                new InputStreamReader(System.in)
            );

            String linha;

            while ((linha = entrada.readLine()) != null) {
                int id = Integer.parseInt(linha.trim());

                if (id == -1) {
                    break;
                }

                Veiculo veiculo = LerCSV.buscarPorId(todos, id);

                if (veiculo != null && quantidade < selecionados.length) {
                    selecionados[quantidade] = veiculo;
                    quantidade++;
                }
            }

        } catch (Exception e) {
            System.out.println("Erro ao ler a entrada.");
            e.printStackTrace();
        }

        Veiculo.HeapSort(selecionados, quantidade);
        LerCSV.printCarro(selecionados, quantidade);
    }
}

// LerCSV
class LerCSV {

    public static Veiculo[] ler(String caminho) {

        Veiculo[] veiculos = new Veiculo[501];
        int quantidade = 0;

        try {
            BufferedReader arquivo = new BufferedReader(
                new FileReader(caminho)
            );

            arquivo.readLine();

            String linha;

            while ((linha = arquivo.readLine()) != null
                    && quantidade < veiculos.length) {
                veiculos[quantidade] = Veiculo.ParseVeiculo(linha);
                quantidade++;
            }

            arquivo.close();

        } catch (Exception e) {
            System.out.println("Erro ao ler o arquivo.");
            e.printStackTrace();
        }

        return veiculos;
    }

    public static Veiculo buscarPorId(Veiculo[] veiculos, int id) {
        for (int i = 0; i < veiculos.length; i++) {
            if (veiculos[i] != null && veiculos[i].getId() == id) {
                return veiculos[i];
            }
        }

        return null;
    }

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
        return String.format(
            Locale.US, "%02d/%02d/%04d", dia, mes, ano
        );
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
        int id, String marca, String modelo, int ano,
        String categoria, String combustivel, int cilindros,
        double cilindrada, String transmissao, String tracao,
        double consumo_cidade, double consumo_estrada,
        double co2, boolean turbo, Data data_registro
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

    public static Veiculo ParseVeiculo(String linha) {
        String[] dados = linha.split(",", -1);

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
            id, marca, modelo, ano, categoria, combustivel,
            cilindros, cilindrada, transmissao, tracao,
            consumoCidade, consumoEstrada, co2, turbo, dataRegistro
        );
    }

    public String format() {
        return String.format(
            Locale.US,
            "[%d ## %s ## %s ## %d ## %s ## [%s] ## %d ## %.1f ## %s ## %s ## %.2f ## %.2f ## %.1f ## %b ## %s]",
            id, marca, modelo, ano, categoria, combustivel,
            cilindros, cilindrada, transmissao, tracao,
            consumo_cidade, consumo_estrada, co2, turbo,
            data_registro.format()
        );
    }

    // Compara CO2 em ordem decrescente.
    // Em caso de empate, compara transmissão em ordem crescente.
    public static int comparar(Veiculo a, Veiculo b) {
        int cmp = Double.compare(b.getCo2(), a.getCo2());

        if (cmp == 0) {
            cmp = a.getTransmissao().compareTo(b.getTransmissao());
        }

        return cmp;
    }

    // Organiza os elementos para manter a propriedade do heap.
    public static void heapify(Veiculo[] veiculos, int n, int i) {
        int maior = i;
        int esquerda = 2 * i + 1;
        int direita = 2 * i + 2;

        if (esquerda < n &&
            comparar(veiculos[esquerda], veiculos[maior]) > 0) {
            maior = esquerda;
        }

        if (direita < n &&
            comparar(veiculos[direita], veiculos[maior]) > 0) {
            maior = direita;
        }

        if (maior != i) {
            Veiculo temp = veiculos[i];
            veiculos[i] = veiculos[maior];
            veiculos[maior] = temp;

            heapify(veiculos, n, maior);
        }
    }

    // Ordena usando HeapSort.
    public static void HeapSort(Veiculo[] veiculos, int quantidade) {

        // Constrói o heap.
        for (int i = quantidade / 2 - 1; i >= 0; i--) {
            heapify(veiculos, quantidade, i);
        }

        // Extrai os elementos e ordena o vetor.
        for (int i = quantidade - 1; i > 0; i--) {
            Veiculo temp = veiculos[0];
            veiculos[0] = veiculos[i];
            veiculos[i] = temp;

            heapify(veiculos, i, 0);
        }
    }
}
