
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Baralho {
    public static void main(String[] args) {

        List<Carta> listaCartasJogo;
        listaCartasJogo = new ArrayList<>();

        List<Carta> listaCartasJogadores;
        listaCartasJogadores = new ArrayList<>();

        Baralho bar = new Baralho();
        Baralho b = new Baralho();

        listaCartasJogo = b.embaralha(bar.listaCartas);
        Scanner entrada = new Scanner(System.in);
        Integer quantidadeJogadores = 0;
        try {
            System.out.println("Quantos jogadores possui a mesa?");
            quantidadeJogadores = entrada.nextInt();
            if(quantidadeJogadores>=2) {
                //ok
            }
        } catch (Exception e) {
            e.getMessage();
        }

        for (Integer i = 1; i <= quantidadeJogadores; i++) {
            listaCartasJogadores = b.darCartas(listaCartasJogo);

            listaCartasJogo.remove(listaCartasJogo.size()-2);
            listaCartasJogo.remove(listaCartasJogo.size()-1);

            System.out.println("Jogador nº " + i.toString());
            StringBuilder sb = new StringBuilder();
            listaCartasJogadores.forEach((c) -> {
                sb.append( "Carta: ").append(c.getNome()).append(" | Naipe: ").append(c.getNaipe()).append("\n");
            });

            System.out.println(sb.toString());
        }

        Integer valorTot = Baralho.retornaValorApostas(quantidadeJogadores ,listaCartasJogadores);
        listaCartasJogadores = b.dar5Cartas(listaCartasJogo);

        Integer totalApostas = valorTot;
        StringBuilder sb = new StringBuilder();
        listaCartasJogadores.forEach((c) -> {
            sb.append( "Carta: ").append(c.getNome()).append(" | Naipe: ").append(c.getNaipe()).append("\n");
        });
        System.out.println("5 CARTAS FINAIS:");

        System.out.println(sb.toString());

        System.out.println("Quem ganhou?");
        Integer jogadorVencedor = entrada.nextInt();

        System.out.println("O jogador vencedor é o nº: " + jogadorVencedor + " e ganhou " + totalApostas + " fichas");

    }

    List<Carta> listaCartas;

    public Baralho() {
        listaCartas = new ArrayList<>();
        String[] naipes = {"Paus", "Ouros", "Copas", "Espadas"};
        int pos = 0;
        Carta c;

        while (pos < 4) {
            for (int i = 1; i <= 10; i++) {
                c = new Carta();
                if(i==1) {
                    c.setNome("Ás");
                }else {
                    c.setNome(i + "");
                }
                c.setNaipe(naipes[pos]);
                listaCartas.add(c);
            }

            c = new Carta();
            c.setNome("Valete");
            c.setNaipe(naipes[pos]);
            listaCartas.add(c);
            c = new Carta();
            c.setNome("Dama");
            c.setNaipe(naipes[pos]);
            listaCartas.add(c);
            c = new Carta();
            c.setNome("Rei");
            c.setNaipe(naipes[pos]);
            listaCartas.add(c);
            pos++;
        }
    }

    public static Integer retornaValorApostas(Integer quantidadeJogadores, List<Carta> listaCartasJogadores) {

        Scanner entrada = new Scanner(System.in);
        Integer totalAposta = 0;
        Integer primeiroValor = 0;
        for (Integer i = 1; i <= quantidadeJogadores; i++) {
            boolean isTrue = true;
            while (isTrue) {
                System.out.println("Jogador nº " + i + " Você quer apostar? Sim ou Não");
                String respostaJogador = entrada.nextLine();
                if(respostaJogador.equalsIgnoreCase("Não")) {
                    System.out.println("Mãos dos jogadores restantes: ");

                    StringBuilder sb = new StringBuilder();
                    listaCartasJogadores.forEach((c) -> {
                        sb.append( "Carta: ").append(c.getNome()).append(" | Naipe: ").append(c.getNaipe()).append("\n");
                    });
                    System.out.println(sb.toString());
                    break;
                }

                if(respostaJogador.equalsIgnoreCase("Sim")) {
                    System.out.println("Jogador nº " + i + " Quanto você aposta?");
                    Integer valorAposta = entrada.nextInt();
                    primeiroValor = valorAposta;
                    totalAposta += valorAposta;
                    isTrue = false;
                    continue;
                }
            }
        }

        System.out.println("Valor parcial das apostas: " + totalAposta);
        Integer valorApostadoIndividual = totalAposta / quantidadeJogadores;
        if(valorApostadoIndividual != primeiroValor) {
            Baralho.retornaValorApostas(quantidadeJogadores, listaCartasJogadores);
            return totalAposta;
        } else {
            return 	totalAposta;
        }
    }


    public List<Carta> imprimeBaralho(List<Carta> listaCartas){
        StringBuilder sb = new StringBuilder();
        listaCartas.forEach((c) -> {
            sb.append("Carta: ").append(c.getNome()).append(" | Naipe: ").append(c.getNaipe()).append("\n");
        });

        System.out.println(sb.toString());
        return listaCartas;
    }

    public boolean temCarta(List<Carta> listaCartas) {
        if(!listaCartas.isEmpty()) {
            System.out.println("Tem carta");
            return true;
        } else {
            System.out.println("Não tem carta");
            return false;
        }
    }

    public List<Carta> dar5Cartas(List<Carta> listaCartas) {
        List<Carta> cincoCartasJogadores;
        cincoCartasJogadores = new ArrayList<>();

        if(!listaCartas.isEmpty() && listaCartas != null) {

            cincoCartasJogadores.add(listaCartas.get(0));
            cincoCartasJogadores.add(listaCartas.get(1));
            cincoCartasJogadores.add(listaCartas.get(2));
            cincoCartasJogadores.add(listaCartas.get(3));
            cincoCartasJogadores.add(listaCartas.get(4));

            return cincoCartasJogadores;

        }
        return cincoCartasJogadores;
    }

    public List<Carta> darCartas(List<Carta> listaCartas) {
        List<Carta> duasCartasJogadores;
        duasCartasJogadores = new ArrayList<>();

        if(!listaCartas.isEmpty() && listaCartas != null) {
            Carta c;
            c = new Carta();

            Carta c2;
            c2 = new Carta();

            c = listaCartas.get(listaCartas.size()-1);
            c2 = listaCartas.get(listaCartas.size()-2);

            duasCartasJogadores.add(c);
            duasCartasJogadores.add(c2);

            listaCartas.remove(listaCartas.size()-1);
            listaCartas.remove(listaCartas.size()-2);
            Baralho.baralhoAtualizado(listaCartas);


        } else {
            System.out.println("Não tem carta");
        }
        return duasCartasJogadores;
    }

    public static List<Carta> baralhoAtualizado(List<Carta> listaCartas) {
        return listaCartas;
    }

    public  List<Carta> baralhoAtualizado() {
        return listaCartas;
    }


    public List<Carta> embaralha(List<Carta> listaCartas){
        Random rand = new Random();

        Carta temp = null;

        for (int i = 0; i < listaCartas.size(); i++) {
            int randomIndexToSwap = rand.nextInt(listaCartas.size());
            temp = listaCartas.get(randomIndexToSwap);
            listaCartas.set(randomIndexToSwap, temp);
            listaCartas.set(i, temp);
        }

        StringBuilder sb = new StringBuilder();
        listaCartas.forEach((c) -> {
            sb.append("Carta: ").append(c.getNome()).append(" | Naipe: ").append(c.getNaipe()).append("\n");
        });
        return listaCartas;
    }

}
