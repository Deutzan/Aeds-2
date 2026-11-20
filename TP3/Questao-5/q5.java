import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Locale;
import java.util.Scanner;

// Q13
public class q5 {

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);

        Veiculo[] veiculos = LerCSV.ler("/tmp/veiculos.csv");
        ListaDupla lista = new ListaDupla();

        // Leitura dos IDs
        int id = sc.nextInt();

        while (id != -1) {
            Veiculo veiculo = LerCSV.buscarPorId(veiculos, id);
            if (veiculo != null) {
                lista.inserirFim(veiculo);
            }
            id = sc.nextInt();
        }

        // Ordenacao
        lista.quicksort();

        // Mostrar lista
        lista.mostrar();
        sc.close();
    }
}

// ListaDupla
class ListaDupla {
    private Celula primeiro;
    private Celula ultimo;
    private int tamanho;

    public ListaDupla() {
        primeiro = null;
        ultimo = null;
        tamanho = 0;
    }

    // Inserir no inicio
    public void inserirInicio(Veiculo veiculo) {
        Celula nova = new Celula(veiculo);
        nova.prox = primeiro;
        nova.ant = null;

        if (primeiro != null) {
            primeiro.ant = nova;
        } else {
            ultimo = nova;
        }
        primeiro = nova;
        tamanho++;
    }

    // Inserir no fim
    public void inserirFim(Veiculo veiculo) {
        Celula nova = new Celula(veiculo);
        nova.prox = null;
        nova.ant = ultimo;

        if (ultimo != null) {
            ultimo.prox = nova;
        } else {
            primeiro = nova;
        }
        ultimo = nova;
        tamanho++;
    }

    // Comparar
    private int comparar(Veiculo a, Veiculo b) {
        int cmp = Double.compare(a.getConsumoEstrada(), b.getConsumoEstrada());

        if (cmp == 0) {
            cmp = a.getMarca().compareTo(b.getMarca());
        }

        if (cmp == 0) {
            cmp = Integer.compare(a.getAno(), b.getAno());
        }

        return cmp;
    }

    // Quicksort
    public void quicksort() {
        if (tamanho > 1) {
            quicksort(primeiro, ultimo, 0, tamanho - 1);
        }
    }

    private void quicksort(Celula esq, Celula dir, int iEsq, int iDir) {
        Celula i = esq;
        Celula j = dir;
        int ii = iEsq;
        int jj = iDir;

        // Pivo: elemento do meio
        Celula meio = esq;
        int passos = (iEsq + iDir) / 2 - iEsq;
        for (int k = 0; k < passos; k++) {
            meio = meio.prox;
        }
        Veiculo pivo = meio.elemento;

        while (ii <= jj) {
            while (comparar(i.elemento, pivo) < 0) {
                i = i.prox;
                ii++;
            }
            while (comparar(j.elemento, pivo) > 0) {
                j = j.ant;
                jj--;
            }
            if (ii <= jj) {
                Veiculo tmp = i.elemento;
                i.elemento = j.elemento;
                j.elemento = tmp;

                i = i.prox;
                ii++;
                j = j.ant;
                jj--;
            }
        }

        if (iEsq < jj) {
            quicksort(esq, j, iEsq, jj);
        }
        if (ii < iDir) {
            quicksort(i, dir, ii, iDir);
        }
    }

    // Mostrar
    public void mostrar() {
        Celula atual = primeiro;
        while (atual != null) {
            atual.elemento.format();
            atual = atual.prox;
        }
    }

    public boolean vazia() {
        return tamanho == 0;
    }

    public int getTamanho() {
        return tamanho;
    }
}

// Celula
class Celula {

    public Veiculo elemento;
    public Celula ant;
    public Celula prox;

    public Celula(Veiculo elemento) {
        this.elemento = elemento;
        this.ant = null;
        this.prox = null;
    }
}

// Data
class Data {

    private int dia;
    private int mes;
    private int ano;

    public Data() {
        dia = 0;
        mes = 0;
        ano = 0;
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

    public String format() {
        return String.format(Locale.US, "%02d/%02d/%04d", dia, mes, ano);
    }

    public static Data ParseDia(String s) {
        String[] partes = s.split("-");

        int ano = Integer.parseInt(partes[0]);
        int mes = Integer.parseInt(partes[1]);
        int dia = Integer.parseInt(partes[2]);
        return new Data(dia, mes, ano);
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
    private double consumoCidade;
    private double consumoEstrada;
    private double co2;
    private boolean turbo;
    private Data dataRegistro;

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
            double consumoCidade,
            double consumoEstrada,
            double co2,
            boolean turbo,
            Data dataRegistro) {

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
        this.consumoCidade = consumoCidade;
        this.consumoEstrada = consumoEstrada;
        this.co2 = co2;
        this.turbo = turbo;
        this.dataRegistro = dataRegistro;
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

    public double getConsumoCidade() {
        return consumoCidade;
    }

    public double getConsumoEstrada() {
        return consumoEstrada;
    }

    public double getCo2() {
        return co2;
    }

    public boolean getTurbo() {
        return turbo;
    }

    public Data getDataRegistro() {
        return dataRegistro;
    }

    // Parse veiculo
    public static Veiculo ParseVeiculo(String s) {

        String[] partes = s.split(",", -1);

        int id = Integer.parseInt(partes[0]);
        String marca = partes[1];
        String modelo = partes[2];
        int ano = Integer.parseInt(partes[3]);
        String categoria = partes[4];
        String combustivel = partes[5];
        int cilindros = Integer.parseInt(partes[6]);
        double cilindrada = Double.parseDouble(partes[7]);
        String transmissao = partes[8];
        String tracao = partes[9];
        double consumoCidade = Double.parseDouble(partes[10]);
        double consumoEstrada = Double.parseDouble(partes[11]);
        double co2 = Double.parseDouble(partes[12]);
        boolean turbo = partes[13].equals("true");
        Data dataRegistro = Data.ParseDia(partes[14]);

        return new Veiculo(
                id, marca, modelo, ano, categoria, combustivel,
                cilindros, cilindrada, transmissao, tracao,
                consumoCidade, consumoEstrada, co2, turbo, dataRegistro);
    }

    // Format
    public void format() {
        String combustivelFormatado = combustivel.replace(";", ",");

        System.out.println("[" + id + " ## " + marca + " ## " + modelo + " ## " + ano
                + " ## " + categoria + " ## [" + combustivelFormatado + "] ## " + cilindros
                + " ## " + String.format(Locale.US, "%.1f", cilindrada)
                + " ## " + transmissao + " ## " + tracao
                + " ## " + String.format(Locale.US, "%.2f", consumoCidade)
                + " ## " + String.format(Locale.US, "%.2f", consumoEstrada)
                + " ## " + String.format(Locale.US, "%.1f", co2)
                + " ## " + (turbo ? "true" : "false")
                + " ## " + dataRegistro.format() + "]");
    }
}

// LerCSV
class LerCSV {

    public static Veiculo[] ler(String caminhoArquivo) throws IOException {

        BufferedReader br = new BufferedReader(new FileReader(caminhoArquivo));

        // Ignora cabecalho
        br.readLine();
        Veiculo[] veiculos = new Veiculo[1000];
        int quantidade = 0;
        String linha;

        while ((linha = br.readLine()) != null) {
            linha = linha.trim();
            if (!linha.isEmpty() && quantidade < veiculos.length) {
                veiculos[quantidade] = Veiculo.ParseVeiculo(linha);
                quantidade++;
            }
        }

        br.close();

        Veiculo[] resultado = new Veiculo[quantidade];
        for (int i = 0; i < quantidade; i++) {
            resultado[i] = veiculos[i];
        }

        return resultado;
    }

    public static Veiculo buscarPorId(Veiculo[] veiculos, int id) {
        for (int i = 0; i < veiculos.length; i++) {
            if (veiculos[i].getId() == id) {
                return veiculos[i];
            }
        }

        return null;
    }
}