import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class questao12{

public static void main(String[] args) throws Exception {
    Scanner sc = new Scanner(System.in);

    int n;
    Veiculo[] veiculos = LerCSV.ler("veiculosJ.csv");

    Pilha pilha = new Pilha();

    // PRIMEIRA PARTE DA ENTRADA
    int id = sc.nextInt();

    while (id != -1) {
        Veiculo veiculo = LerCSV.buscarPorId(veiculos, id);

        if (veiculo != null) {
            pilha.inserir(veiculo);
        }

        id = sc.nextInt();
    }

    // SEGUNDA PARTE DA ENTRADA
    int quantidade = sc.nextInt();

    for (int i = 0; i < quantidade; i++) {

        String comando = sc.next();

        // INSERIR
        if (comando.equals("I")) {

            int idVeiculo = sc.nextInt();

            Veiculo veiculo = LerCSV.buscarPorId(veiculos, idVeiculo);

            if (veiculo != null) {
                pilha.inserir(veiculo);
            }

        }

        // REMOVER
        else if (comando.equals("R")) {

            if (!pilha.vazia()) {

                Veiculo removido = pilha.remover();

                System.out.println("(R)" + removido.getMarca()
                        + " " + removido.getModelo());
            }
        }
    }

    // MOSTRAR PILHA DO TOPO PARA A BASE
    pilha.mostrar();

    sc.close();
}
}


class Pilha {
private Celula topo;
private int tamanho;

public Pilha() {
    topo = null;
    tamanho = 0;
}

// INSERIR NO TOPO
public void inserir(Veiculo veiculo) {

    Celula nova = new Celula(veiculo);

    nova.prox = topo;
    topo = nova;

    tamanho++;
}

// REMOVER DO TOPO
public Veiculo remover() {

    if (topo == null) {
        return null;
    }

    Veiculo removido = topo.elemento;

    topo = topo.prox;
    tamanho--;

    return removido;
}

// VERIFICAR SE ESTÁ VAZIA
public boolean vazia() {
    return topo == null;
}

// MOSTRAR DO TOPO PARA A BASE
public void mostrar() {

    Celula atual = topo;

    while (atual != null) {

        atual.elemento.format();

        atual = atual.prox;
    }
}
}

// =====================================================
// CÉLULA
// =====================================================

class Celula {
public Veiculo elemento;
public Celula prox;

public Celula(Veiculo elemento) {
    this.elemento = elemento;
    this.prox = null;
}
}

// =====================================================
// DATA
// =====================================================

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
    return String.format("%02d/%02d/%04d", dia, mes, ano);
}

public static Data ParseDia(String s) {
    String[] partes = s.split("-");
    int ano = Integer.parseInt(partes[0]);
    int mes = Integer.parseInt(partes[1]);
    int dia = Integer.parseInt(partes[2]);
    return new Data(dia, mes, ano);
}
}

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

public static Veiculo ParseVeiculo(String s) {
    String[] partes = s.split(",");

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

public void format() {
    String combustivelFormatado = combustivel.replace(";", ",");
    System.out.println("[" + id + " ## " + marca + " ## " + modelo + " ## " +ano + " ## " +categoria + " ## [" +combustivelFormatado + "] ## " +cilindros + " ## " + String.format("%.1f", cilindrada) + " ## " + transmissao + " ## " + tracao + " ## " +String.format("%.2f", consumoCidade) + " ## " +String.format("%.2f", consumoEstrada) + " ## " +String.format("%.1f", co2) + " ## " +(turbo ? "true" : "false") + " ## " + dataRegistro.format() +"]");
}
}


class LerCSV {
public static Veiculo[] ler(String caminhoArquivo) throws IOException {
    BufferedReader br = new BufferedReader(new FileReader(caminhoArquivo));

    // Ignora o cabeçalho
    br.readLine();

    Veiculo[] veiculos = new Veiculo[1000];
    int quantidade = 0;
    String linha;

    while ((linha = br.readLine()) != null) {
        if (!linha.isEmpty()) {
            veiculos[quantidade] = Veiculo.ParseVeiculo(linha);
            quantidade++;
        }
    }

    br.close();

    // Cria vetor exatamente do tamanho necessário
    Veiculo[] resultado = new Veiculo[quantidade];

    for(int i = 0; i < quantidade; i++) {
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
