import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class questao13{

public static void main(String[] args) throws Exception {
    Scanner sc = new Scanner(System.in);

    Veiculo[] veiculos = LerCSV.ler("veiculosJ.csv");
    ListaDupla lista = new ListaDupla();

    // PRIMEIRA PARTE DA ENTRADA
    int id = sc.nextInt();

    while (id != -1) {
        Veiculo veiculo = LerCSV.buscarPorId(veiculos, id);
        if (veiculo != null) {
            lista.inserirFim(veiculo);
        }
        id = sc.nextInt();
    }

    // SEGUNDA PARTE DA ENTRADA
    int quantidade = sc.nextInt();

    for (int i = 0; i < quantidade; i++) {
        String comando = sc.next();
        // INSERIR NO INÍCIO
        if(comando.equals("II")) {
            int idVeiculo = sc.nextInt();
            Veiculo veiculo = LerCSV.buscarPorId(veiculos, idVeiculo);
           
            if (veiculo != null) {
                lista.inserirInicio(veiculo);
            }
        }

        // INSERIR EM UMA POSIÇÃO
        else if (comando.equals("I*")) {
            int posicao = sc.nextInt();
            int idVeiculo = sc.nextInt();

            Veiculo veiculo = LerCSV.buscarPorId(veiculos, idVeiculo);

            if (veiculo != null) {
                lista.inserir(veiculo, posicao);
            }
        }

        // INSERIR NO FIM
        else if (comando.equals("IF")) {
            int idVeiculo = sc.nextInt();

            Veiculo veiculo = LerCSV.buscarPorId(veiculos, idVeiculo);
            if (veiculo != null) {
                lista.inserirFim(veiculo);
            }
        }

        // REMOVER DO INÍCIO
        else if (comando.equals("RI")) {
            if (!lista.vazia()) {

                Veiculo removido = lista.removerInicio();
                System.out.println("(R)" + removido.getMarca() +" " + removido.getModelo());
            }
        }

        // REMOVER DE UMA POSIÇÃO
        else if (comando.equals("R*")) {
            int posicao = sc.nextInt();

            if (posicao >= 0 && posicao < lista.getTamanho()) {
                Veiculo removido = lista.remover(posicao);
                System.out.println("(R)" + removido.getMarca() +" " + removido.getModelo());
            }
        }

        // REMOVER DO FIM
        else if (comando.equals("RF")) {
            if (!lista.vazia()) {
                Veiculo removido = lista.removerFim();
                System.out.println("(R)" + removido.getMarca() +" " + removido.getModelo());
            }
        }
    }

    // MOSTRAR LISTA
    lista.mostrar();
    sc.close();
}
}

class ListaDupla {
private Celula primeiro;
private Celula ultimo;
private int tamanho;

public ListaDupla() {
    primeiro = null;
    ultimo = null;
    tamanho = 0;
}


// INSERIR NO INÍCIO
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

// INSERIR NO FIM
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


// INSERIR EM UMA POSIÇÃO
public void inserir(Veiculo veiculo, int posicao) {

    if (posicao < 0 || posicao > tamanho) {
        return;
    }

    if (posicao == 0) {
        inserirInicio(veiculo);
        return;
    }

    if (posicao == tamanho) {
        inserirFim(veiculo);
        return;
    }

    Celula atual = buscarCelula(posicao);
    Celula nova = new Celula(veiculo);

    nova.ant = atual.ant;
    nova.prox = atual;

    atual.ant.prox = nova;
    atual.ant = nova;

    tamanho++;
}


// REMOVER DO INÍCIO
public Veiculo removerInicio() {
    if (primeiro == null) {
        return null;
    }

    Veiculo removido = primeiro.elemento;
    primeiro = primeiro.prox;

    if (primeiro != null) {
        primeiro.ant = null;
    } else {
        ultimo = null;
    }
    tamanho--;
    return removido;
}


// REMOVER DO FIM
public Veiculo removerFim() {
    if (ultimo == null) {
        return null;
    }

    Veiculo removido = ultimo.elemento;
    ultimo = ultimo.ant;

    if (ultimo != null) {
        ultimo.prox = null;
    } else {
        primeiro = null;
    }
    tamanho--;
    return removido;
}

// REMOVER DE UMA POSIÇÃO
public Veiculo remover(int posicao) {
    if (posicao < 0 || posicao >= tamanho) {
        return null;
    }

    if (posicao == 0) {
        return removerInicio();
    }

    if (posicao == tamanho - 1) {
        return removerFim();
    }

    Celula atual = buscarCelula(posicao);
    Veiculo removido = atual.elemento;
    atual.ant.prox = atual.prox;
    atual.prox.ant = atual.ant;

    tamanho--;
    return removido;
}

// BUSCAR CÉLULA
private Celula buscarCelula(int posicao) {
    Celula atual;

    if (posicao < tamanho / 2) {
        atual = primeiro;

        for (int i = 0; i < posicao; i++) {
            atual = atual.prox;
        }

    } else {
        atual = ultimo;

        for (int i = tamanho - 1; i > posicao; i--) {
            atual = atual.ant;
        }
    }

    return atual;
}


// MOSTRAR
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

    return String.format("%02d/%02d/%04d",dia,mes,ano);
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

// =================================================
// PARSE VEICULO
// =================================================

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

// =================================================
// FORMAT
// =================================================

public void format() {

    String combustivelFormatado =
        combustivel.replace(";", ",");

    System.out.println("[" +id + " ## " +marca + " ## " +modelo + " ## " +ano + " ## " +categoria + " ## [" +combustivelFormatado + "] ## " + cilindros + " ## " + String.format("%.1f", cilindrada) + " ## " +transmissao + " ## " +tracao + " ## " +String.format("%.2f", consumoCidade) + " ## " + String.format("%.2f", consumoEstrada) + " ## " +String.format("%.1f", co2) + " ## " +(turbo ? "true" : "false") + " ## " +dataRegistro.format() +"]");
}
}

class LerCSV {

public static Veiculo[] ler(String caminhoArquivo)
        throws IOException {

    BufferedReader br = new BufferedReader(new FileReader(caminhoArquivo));

    // Ignora cabeçalho
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

    Veiculo[] resultado = new Veiculo[quantidade];
    for (int i = 0; i < quantidade; i++) {
        resultado[i] = veiculos[i];
    }

    return resultado;
}

public static Veiculo buscarPorId(
        Veiculo[] veiculos,
        int id) {

    for (int i = 0; i < veiculos.length; i++) {

        if (veiculos[i].getId() == id) {
            return veiculos[i];
        }
    }

    return null;
}
}
