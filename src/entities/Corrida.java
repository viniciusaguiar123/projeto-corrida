package entities;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

public class Corrida implements Comparable<Corrida>{

    ArrayList<Corrida> list = new ArrayList<>();

    SimpleDateFormat dtfHora = new SimpleDateFormat("HH:mm:ss.SSS");
    SimpleDateFormat dtfTempoVoltas = new SimpleDateFormat("mm:ss.SSS");
    private Date hora;
    private String piloto;
    private Integer numeroVoltas;
    private Date tempoVoltas;
    private Double velMediaVoltas;


    public Corrida(Date hora, String piloto, Integer numeroVoltas, Date tempoVoltas, Double velMediaVoltas) {
        this.hora = hora;
        this.piloto = piloto;
        this.numeroVoltas = numeroVoltas;
        this.tempoVoltas = tempoVoltas;
        this.velMediaVoltas = velMediaVoltas;
    }

    public Date getHora() {
        return hora;
    }

    public void setHora(Date hora) {
        this.hora = hora;
    }

    public String getPiloto() {
        return piloto;
    }

    public void setPiloto(String piloto) {
        this.piloto = piloto;
    }

    public Integer getNumeroVoltas() {
        return numeroVoltas;
    }

    public void setNumeroVoltas(Integer numeroVoltas) {
        this.numeroVoltas = numeroVoltas;
    }

    public Date getTempoVoltas() {
        return tempoVoltas;
    }

    public void setTempoVoltas(Date tempoVoltas) {
        this.tempoVoltas = tempoVoltas;
    }

    public Double getVelMediaVoltas() {
        return velMediaVoltas;
    }

    public void setVelMediaVoltas(Double velMediaVoltas) {
        this.velMediaVoltas = velMediaVoltas;
    }

    @Override
    public int compareTo(Corrida other) {
        return tempoVoltas.compareTo(other.getTempoVoltas());
    }

    @Override
    public boolean equals(Object o) {
        Corrida comparing = (Corrida) o;
        int votesSoFar = getNumeroVoltas();
        if(CharSequence.compare(this.getPiloto(), comparing.getPiloto()) == 0
                && this.getPiloto().contentEquals(comparing.getPiloto())) {
                votesSoFar += (int) getTempoVoltas().getTime();
                return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return 0;
    }

    public long soma(Object o) {
        Corrida comparing = (Corrida) o;
        int teste = 0;
        if (CharSequence.compare(this.getPiloto(), comparing.getPiloto()) == 0
                && this.getPiloto().contentEquals(comparing.getPiloto())) {
             teste += (int) tempoVoltas.getTime();
        }
        return teste;
    }

    @Override
    public String toString() {
        return  piloto  +
                ", Volta: " + numeroVoltas +
                ", Tempo da volta: " + dtfTempoVoltas.format(tempoVoltas);
    }

}
