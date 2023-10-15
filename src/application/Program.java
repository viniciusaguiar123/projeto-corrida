package application;

import entities.Corrida;
import service.CorridaService;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.*;

public class Program {

    public static void main(String[] args) {

        SimpleDateFormat dtfHora = new SimpleDateFormat("HH:mm:ss.SSS");
        SimpleDateFormat dtfTempoVoltas = new SimpleDateFormat("mm:ss.SSS");

        ArrayList<Corrida> corridaList = new ArrayList<>();

        Map<String, Long> pilotos = new HashMap<>();

        LinkedHashMap<String, Double> pilotosVelocidade = new LinkedHashMap<>();

        String path = "D://temp/in.txt";

        try(BufferedReader br = new BufferedReader(new FileReader(path))){

            String line = br.readLine();
            while(line != null) {
                String[] fields = line.split("[ ]");
                Date hora = dtfHora.parse(fields[0]);
                String piloto = String.format(fields[1]);
                int numeroVoltas = Integer.parseInt(fields[2]);
                Date tempoVoltas = dtfTempoVoltas.parse(fields[3]);
                String virgulasVelMediaVoltas = fields[4].replaceAll(",",".");
                Double velMediaVoltas = Double.parseDouble(virgulasVelMediaVoltas);

                corridaList.add(new Corrida(hora, piloto, numeroVoltas, tempoVoltas, velMediaVoltas));

                if (pilotos.containsKey(piloto)) {
                    int votesSoFar = Math.toIntExact(pilotos.get(piloto));
                    pilotos.put(piloto, tempoVoltas.getTime() + votesSoFar);
                } else {
                    pilotos.put(piloto, tempoVoltas.getTime());
                }

                if (pilotosVelocidade.containsKey(piloto)) {
                    Double velocidade =  pilotosVelocidade.get(piloto);
                    pilotosVelocidade.put(piloto, velMediaVoltas + velocidade);

                }
                else {
                    pilotosVelocidade.put(piloto, velMediaVoltas);
                }

                line = br.readLine();

            };

            Corrida melhorVolta = CorridaService.max(corridaList);
            System.out.print("Melhor volta da Corrida: ");
            System.out.println(melhorVolta);
            System.out.println();

            System.out.println("Velocidade Média de cada piloto: ");
            for(String key : pilotosVelocidade.keySet()){
                System.out.println(key + " - " + String.format("%.3f", pilotosVelocidade.get(key)));
            }
            System.out.println();


            System.out.println("Tempo total de prova de cada piloto:");
            for(String key : pilotos.keySet()){
                System.out.println(key + " - " + dtfTempoVoltas.format(pilotos.get(key)));
            }
            System.out.println();

            corridaList.sort(Collections.reverseOrder(Comparator.comparing(Corrida::getNumeroVoltas)));
            Set<Corrida> filtrarCorridaList = new HashSet<>(corridaList);
            int posicao = 1;

            System.out.println("Posições: ");
            for(Corrida c : filtrarCorridaList){
                System.out.print((posicao++) + " - " + c );
                System.out.println();
            }

        } catch(IOException | ParseException e) {
            System.out.println("Error: " + e.getMessage());
        }

    }

}
