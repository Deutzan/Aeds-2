import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Q5{
    public static void main(String [] args){
        Veiculo[] veiculos = LerCSV.ler("/tmp/veiculos.csv");
        Arvore arvore = new Arvore();

        try (Scanner scan = new Scanner(System.in)) {

            // PRIMEIRA PARTE: INSERCAO
            String entrada = scan.nextLine();

            while (!entrada.equals("FIM")) {
                int id = Integer.parseInt(entrada);

                for (Veiculo v : veiculos) {
                    if (v != null && v.getId() == id) {
                        arvore.inserir(v);
                        break;
                    }
                }
                entrada = scan.nextLine();
            }

            // SEGUNDA PARTE: PESQUISA
            entrada = scan.nextLine();

            while (!entrada.equals("FIM")) {

                boolean encontrado = arvore.pesquisar(entrada);

                if (encontrado) {
                    System.out.println("SIM");
                } else {
                    System.out.println("NAO");
                }
                entrada = scan.nextLine();
            }
            // CAMINHAMENTO CENTRAL
            arvore.caminharCentral();
            
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
}
}

class LerCSV {

    public static Veiculo[] ler(String carro) {

        Veiculo[] veiculos = new Veiculo[501];

        int quantidade = 0;

        try {
            // Abre o arquivo para leitura.
            try (BufferedReader arquivo = new BufferedReader(new FileReader(carro))) {

            arquivo.readLine();

            String linha;

            while ((linha = arquivo.readLine()) != null) {
   
                // ParseVeiculo transforma a linha em um objeto Veiculo
                Veiculo veiculo = Veiculo.ParseVeiculo(linha);

                veiculos[quantidade] = veiculo;

                quantidade++;
            }

            }

        } catch (IOException | NumberFormatException | ArrayIndexOutOfBoundsException e) {

            System.out.println("Erro ao ler o arquivo.");
            System.err.println("Detalhes do erro: " + e.getMessage());
        }

        return veiculos;
    }


    public static void printCarro(Veiculo[] veiculos, int id) {

        for (Veiculo veiculo : veiculos) {

            if (veiculo != null && veiculo.getId() == id) {

                System.out.println(veiculo.format());
                return;
            }
        }

        System.out.println("Veiculo nao encontrado.");
    }
}

class Data {
    private int dia;
    private int mes;
    private int ano;

    //-- construtor --//
    public Data(){
        this.dia = 00;
        this.mes = 00;
        this.ano = 0000;
    }
    
    public Data(int dia,int mes,int ano){
        this.dia = dia;
        this.mes = mes;
        this.ano = ano;
    }

    //-- gets e sets --//
    public int getDia(){
        return dia;
    }
    public void setDia(int dia){
        this.dia = dia;
    }

    public int getMes(){
        return mes;
    }
    public void setMes(int mes){
        this.mes = mes;
    }

    public int getAno(){
        return ano;
    }
    public void setdia(int ano){
        this.ano = ano;
    }

    public static Data ParseDia(String dataRegistro){
        String[] data = dataRegistro.split("-");
        int anoData = Integer.parseInt(data[0]);
        int mesData = Integer.parseInt(data[1]);
        int diaData = Integer.parseInt(data[2]);
        return new Data(diaData, mesData, anoData);
    }




    /* printagem da data */
    public String format(){
        String s = String.format("%02d/%02d/%04d", dia, mes, ano);
        return s;
    }

}

class Veiculo {
//-- atributos --//
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

//-- construtor --//
public Veiculo(int id, String marca, String modelo, int ano, String categoria, String combustivel, int cilindros, double cilindrada, String transmissao, String tracao, float consumo_cidade, float consumo_estrada, float co2, boolean turbo, Data data_registro) {
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

//-- getters e setters --//
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getMarca() {
        return marca;
    }
    public void setMarca(String marca) {
        this.marca = marca;
    }
    
    public String getModelo() {
        return modelo;
    }
    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public int getAno() {
        return ano;
    }
    public void setAno(int ano) {
        this.ano = ano;
    }

    public String getCategoria() {
        return categoria;
    }
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getCombustivel() {
        return combustivel;
    }
    public void setCombustivel(String combustivel) {
        this.combustivel = combustivel;
    }

    public int getCilindros() {
        return cilindros;
    }
    public void setCilindros(int cilindros) {
        this.cilindros = cilindros;
    }

    public double getCilindrada() {
        return cilindrada;
    }
    public void setCilindrada(double cilindrada) {
        this.cilindrada = cilindrada;
    }

    public String getTransmissao() {
        return transmissao;
    }
    public void setTransmissao(String transmissao) {
        this.transmissao = transmissao;
    }

    public String getTracao() {
        return tracao;
    }
    public void setTracao(String tracao) {
        this.tracao = tracao;
    }

    public double getConsumo_cidade() {
        return consumo_cidade;
    }
    public void setConsumo_cidade(double consumo_cidade) {
        this.consumo_cidade = consumo_cidade;
    }

    public double getConsumo_estrada() {
        return consumo_estrada;
    }
    public void setConsumo_estrada(double consumo_estrada) {
        this.consumo_estrada = consumo_estrada;
    }

    public double getCo2() {
        return co2;
    }
    public void setCo2(double co2) {
        this.co2 = co2;
    }

    public boolean isTurbo() {
        return turbo;
    }
    public void setTurbo(boolean turbo) {
        this.turbo = turbo;
    }

    public Data getData_registro() {
        return data_registro;
    }
    public void setData_registro(Data data_registro) {
        this.data_registro = data_registro;
    }

    public static Veiculo ParseVeiculo(String linha) {

        String[] dados = linha.split(",");

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
        float consumoCidade = Float.parseFloat(dados[10]);
        float consumoEstrada = Float.parseFloat(dados[11]);
        float co2 = Float.parseFloat(dados[12]);
        boolean turbo = Boolean.parseBoolean(dados[13]);

        // Chama o ParseData para transformar a data
        Data dataRegistro = Data.ParseDia(dados[14]);

        // Cria e retorna o veículo
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

    public String format() {
    String s = "[" + id + " ## " + marca + " ## " + modelo + " ## " + ano + " ## "
            + categoria + " ## " + "[" + combustivel + "]" + " ## " + cilindros + " ## "
            + cilindrada + " ## " + transmissao + " ## " + tracao + " ## "
            + String.format("%.2f", consumo_cidade) + " ## "
            + String.format("%.2f", consumo_estrada) + " ## "
            + String.format("%.1f", co2) + " ## "
            + turbo + " ## ";

    s += data_registro.format() + "]";

    return s;
}
}


class No {
    Veiculo elemento;
    No esq;
    No dir;

    public No(Veiculo elemento) {
        this.elemento = elemento;
        this.esq = null;
        this.dir = null;
    }
}

class Arvore {
    private No raiz;

    public Arvore() {
        raiz = null;
    }

    // INSERCAO
    public void inserir(Veiculo elemento) throws Exception {
        raiz = inserir(elemento, raiz);
    }

    private No inserir(Veiculo elemento, No i) throws Exception {
        if (i == null) {
            i = new No(elemento);

        } else if (elemento.getModelo().compareTo(i.elemento.getModelo()) < 0) {
            i.esq = inserir(elemento, i.esq);

        } else if (elemento.getModelo().compareTo(i.elemento.getModelo()) > 0) {
            i.dir = inserir(elemento, i.dir);

        } else {
            // Modelo repetido: nao insere.
        }

        return i;
    }

    // PESQUISA
    public boolean pesquisar(String modelo) {
        System.out.print("raiz ");
        return pesquisar(modelo, raiz);
    }

    private boolean pesquisar(String modelo, No i) {

        if (i == null) {
            return false;

        } else if (modelo.equals(i.elemento.getModelo())) {
            return true;

        } else if (modelo.compareTo(i.elemento.getModelo()) < 0) {
            System.out.print("esq ");
            return pesquisar(modelo, i.esq);

        } else {
            System.out.print("dir ");
            return pesquisar(modelo, i.dir);
        }
    }

    // CAMINHAMENTO CENTRAL (EM ORDEM)
    public void caminharCentral() {
        caminharCentral(raiz);
    }

    private void caminharCentral(No i) {

        if (i != null) {
            caminharCentral(i.esq);
            System.out.println(i.elemento.format());
            caminharCentral(i.dir);
        }
    }
}
