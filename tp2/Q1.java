import java.util.Scanner;

public class Q1 {

    public static void main(String[] args) {

        //-- Lê todos os veículos do arquivo CSV --//
        Veiculo[] veiculos = LerCSV.ler("veiculos.csv");

        Scanner scan = new Scanner(System.in);
        int idProcurado;

        idProcurado = scan.nextInt();

        if (idProcurado != -1) {
            LerCSV.printCarro(veiculos, idProcurado);
        }

        while (idProcurado != -1){
        idProcurado = scan.nextInt();

            if (idProcurado != -1) {
                LerCSV.printCarro(veiculos, idProcurado);
            }


         }

        scan.close();
}
}