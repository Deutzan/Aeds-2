import java.io.BufferedReader;
import java.io.FileReader;

public class Q7 {
    public static void main(String[] args){
        Veiculo[] veiculos = LerCSV.ler("javinha/veiculos.csv");

        Veiculo.Bucketsort(veiculos);
        Veiculo.printPos(veiculos);
    }
}

class LerCSV {

    public static Veiculo[] ler(String carro) {

        Veiculo[] veiculos = new Veiculo[501];

        int quantidade = 0;

        try {
            // Abre o arquivo para leitura.
            BufferedReader arquivo = new BufferedReader(new FileReader(carro));

            arquivo.readLine();

            String linha;

            while ((linha = arquivo.readLine()) != null) {
   
                // ParseVeiculo transforma a linha em um objeto Veiculo
                Veiculo veiculo = Veiculo.ParseVeiculo(linha);

                veiculos[quantidade] = veiculo;

                quantidade++;
            }

            arquivo.close();

        } catch (Exception e) {

            System.out.println("Erro ao ler o arquivo.");
            e.printStackTrace(); //--> recomendação para caso caia na exceção ele mostre oque esta realmente acontecendo --//
        }

        return veiculos;
    }


    public static void printCarro(Veiculo[] veiculos, int id) {

        for (int i = 0; i < veiculos.length; i++) {

            if (veiculos[i] != null && veiculos[i].getId() == id) {

                System.out.println(veiculos[i].format());
                return;
            }
        }

        System.out.println("Veiculo nao encontrado.");
    }
}


//Data
class Data{
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

//Veiculo
class Veiculo {
//-- atributos --//
private int id;
private String marca;
private String modelo;
private int ano;
private String categoria;
private String combustivel;
private int cilindros;
private float cilindrada;
private String transmissao;
private String tracao;
private double consumo_cidade;
private double consumo_estrada;
private double co2;
private boolean turbo;
private Data data_registro;

//-- construtor --//
public Veiculo(int id, String marca, String modelo, int ano, String categoria, String combustivel, int cilindros, float cilindrada, String transmissao, String tracao, float consumo_cidade, float consumo_estrada, float co2, boolean turbo, Data data_registro) {
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

    public float getCilindrada() {
        return cilindrada;
    }
    public void setCilindrada(float cilindrada) {
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
        float cilindrada = Float.parseFloat(dados[7]);
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
    String s ="[" + id + " ## " + marca + " ## " + modelo + " ## " + ano + " ## " + categoria + " ## " + combustivel + " ## " + cilindros + " ## " + cilindrada + " ## " + transmissao + " ## " + tracao + " ## " + consumo_cidade + " ## " + consumo_estrada + " ## " + co2 + " ## " + turbo + " ## ";
    s += data_registro.format() + "]";
    return s;
   }

   public static void Bucketsort(Veiculo[] veiculos){
    //-- criando e botando em um vetor as cilindradas
    double[] h = new double[501];
    double maior = 0,menor = 9;
    for(int i = 0; i < veiculos.length; i++){
        if (veiculos[i] != null) {
            h[i] = veiculos[i].getCilindrada();
            if (h[i] < menor) {
                menor = h[i];
            }

            if (h[i] > maior) {
                maior = h[i];
            }
        }
        }

    //-- cria os 10 buckets e coloca em uma matriz para bucket[balde que esta][quantidade] --//
    Veiculo[][] buckets = new Veiculo[10][501];

    // Guarda quantos veículos existem dentro de cada bucket
    int[] quantidade = new int[10];

    for (int i = 0; i < veiculos.length; i++) {
        if (veiculos[i] != null) {
            double cilindrada = h[i];
        
            int indiceBucket = (int)(cilindrada / 0.8);
        // Caso a cilindrada seja exatamente 8.0
            if (indiceBucket == 10) {
            indiceBucket = 9;
            }
        
        // Coloca o VEÍCULO completo no bucket
        buckets[indiceBucket][quantidade[indiceBucket]] = veiculos[i];

        quantidade[indiceBucket]++;
        }
    }

    //-- Insertion Sort dentro de cada bucket --//
    for (int b = 0; b < 10; b++) {
        for (int i = 1; i < quantidade[b]; i++) {
            Veiculo atual = buckets[b][i];
            int j = i - 1;

            while (j >= 0 &&
                buckets[b][j].getCilindrada() > atual.getCilindrada()) {
                buckets[b][j + 1] = buckets[b][j];
                j--;
            }
            buckets[b][j + 1] = atual;
        }
    }

    //-- Coloca os buckets ordenados de volta em veiculos --//
    int posicao = 0;

    for (int b = 0; b < 10; b++) {
        for (int j = 0; j < quantidade[b]; j++) {
            veiculos[posicao] = buckets[b][j];
            posicao++;
        }
    }

   }
   public static void printPos(Veiculo[] veiculos){
    System.out.print("[");

    for (int i = 0; i < veiculos.length; i++) {
    if (veiculos[i] != null) {
        System.out.print("veiculos[" + i + "]");
        if (i < veiculos.length - 1) {
            System.out.print(", ");
            }
        }
    }
    System.out.println("]");
   }

}
