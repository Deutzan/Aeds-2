    import java.io.FileReader;  // Permite a leitura de arquivos
    import java.io.BufferedReader; //Faz a leitura de arquivos de forma mais eficiente

    /*
    Coisas importantes para se lembrar:

    1) Em java tem que adicionar esses 2 java.io
        import java.io.FileReader;  // Permite a leitura de arquivos
        import java.io.BufferedReader; //Faz a leitura de arquivos de forma mais eficiente

    2) A Sintaxe para ler um arquivo é a seguinte:
        FileReader entrada = new FileReader("local do arquivo")      "Mostra qual arquivo será lido"
        BufferedReader ler = new BufferedReader(entrada);  "Faz a leitura em si da entrada"

    3) Não é exatamente necessário, mas é uma boa prática colocar a leitura do arquivo 
    dentro de um try/catch, para caso o arquivo não seja encontrado, o programa não quebre.

    4) Se cria uma variavel desse jeito para armazenar a linha que está sendo lida:
    String linha = ler.readLine();// o ler é o nome que vc atribui no BufferedReader
        
        "esse READLINE() lê a linha do arquivo até dar NULL e pula para a proxima"

    5) Para fazer a leitura  até EOF faz isso aqui:
    "que quando não houver mais linhas para ler, o readline() retorna null e sai do while"

        while((linha = ler.readLine()) != null){
        

        // os parses
        
        }

    6) O split ele é uma função atribuida a linha que dependendo de como que
    esta a entrada ele separa os dados em um vetor de strings, no caso do exemplo ele separa:
    "ELE DEVE FICAR NO INICIO DO WHILE"

        String[] dados = linha.split(" "); // separa os dados da linha em um vetor de strings

    7) O parse ele funciona para pegar os dados divididos em cada linha
    e converter para um tipo especifico,aqui um exemplo de conversão:

        int a = Integer.parseInt(dados[2]); // converte a string para inteiro

        double b = Double.parseDouble(dados[3]); // converte a string para double

        float c = Float.parseFloat(dados[4]); // converte a string para float

        boolean d = Boolean.parseBoolean(dados[4]); // converte a string para boolean

        String e = dados[5]; no caso da string n precisa de parse por ja ser uma String

    Caso voce tenha um objeto é só colocar ele no inicio,e trocar o por set e colocar o parse
    dentro do parenteses,exemplo:

        jogador.setNivel(Integer.parseInt(dados[2])); // converte a string para inteiro

    ============================================================================================
    */

    /*
    // ------- Exemplo de pub.in ------//

    3
    Luis Mago 25 Cajado Tunica Elfo
    Glauco Barbaro 30 Espada Armadura Fera
    Gabriel Curandeiro 20 Catalizador Túnica Humano

    */

    class jogador{
        private String nome;
        private String classe;
        private int nivel;
        private String arma;
        private String armadura;
        private String raca;

        // Getters e Setters para cada atributo da classe jogador
        public String getNome() {return nome;}
        public void setNome(String nome) {this.nome = nome;}
        
        public String getClasse() {return classe;}
        public void setClasse(String classe) {this.classe = classe;}

        public int getNivel() {return nivel;}
        public void setNivel(int nivel) {this.nivel = nivel;}

        public String getArma() {return arma;}
        public void setArma(String arma) {this.arma = arma;}

        public String getArmadura() {return armadura;}
        public void setArmadura(String armadura) {this.armadura = armadura;}
        
        public String getRaca() {return raca;}
        public void setRaca(String raca) {this.raca = raca;}

        // mostrar jogadores
        public void mostrarJogador() {
            System.out.println("// -------- jogador " + nome + "--------//");
            System.out.println("Classe: " + classe);
            System.out.println("Nível: " + nivel);
            System.out.println("Arma: " + arma);
            System.out.println("Armadura: " + armadura);
            System.out.println("Raça: " + raca);
            System.out.println("----------------------------------------");
        }
    }

    class LeituraDeArquivo{
        public static void main(String[] args) {
            
            try(FileReader entrada = new FileReader("pub.in")){ //mostra qual arquivo será lido
            BufferedReader ler = new BufferedReader(entrada); // faz a leitura em si da entrada

            int n = Integer.parseInt(ler.readLine()); // lê a primeira linha do arquivo e converte para inteiro
            jogador[] Herois = new jogador[n];        // agora aloca um vetor com aquele tamanho

            String linha; // No "String linha = ler.readLine(); " a linha salva oque vai ser lido no readline();
            
            int i = 0;
            while((linha = ler.readLine()) != null){ // enquanto a linha não for nula, ou seja, enquanto houver linhas para ler
                
                String[] dados = linha.split(" "); // separa os dados da linha em um vetor de strings
                Herois[i] = new jogador(); // cria um novo objeto jogador
                Herois[i].setNome(dados[0]); // seta o nome do jogador
                Herois[i].setClasse(dados[1]); // seta a classe do jogador
                Herois[i].setNivel(Integer.parseInt(dados[2])); // seta o nível do jogador
                Herois[i].setArma(dados[3]); // seta a arma do jogador
                Herois[i].setArmadura(dados[4]); // seta a armadura do jogador
                Herois[i].setRaca(dados[5]); // seta a raça do jogador
                i++; // incrementa o índice para o próximo jogador
            }
            for(int j = 0;j < Herois.length;j++){
                SelectionSort(Herois); // ordena os jogadores pelo nome
                System.out.println("Jogadores: ");
                Herois[j].mostrarJogador(); // mostra os jogadores
                System.out.println();
            }

        }
            catch(Exception e){
                e.printStackTrace();
            } 
    }

    public static void SelectionSort(jogador[] Herois) {
        int n = Herois.length;
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (Herois[j].getNivel() < Herois[minIndex].getNivel()) {
                    minIndex = j;
                }
            }
            jogador temp = Herois[minIndex];
            Herois[minIndex] = Herois[i];
            Herois[i] = temp;
        }
    }


    }
