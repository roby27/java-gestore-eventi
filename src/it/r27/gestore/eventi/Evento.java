package it.r27.gestore.eventi;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Evento {
    private String titolo;
    private LocalDate data;
    private int numeroPostiTotali;
    private int numeroPostiPrenotati;

    public Evento(String titolo, LocalDate data, int numeroPostiTotali) throws IllegalArgumentException {
        if (data.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("La data dell'evento non può essere nel passato.");
        }
        if (numeroPostiTotali <= 0) {
            throw new IllegalArgumentException("Il numero di posti deve essere positivo.");
        }
        this.titolo = titolo;
        this.data = data;
        this.numeroPostiTotali = numeroPostiTotali;
        this.numeroPostiPrenotati = 0;
    }

    // Getter e Setter
    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        if (data.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("La data dell'evento non può essere nel passato.");
        }
        this.data = data;
    }

    public int getNumeroPostiTotali() {
        return numeroPostiTotali;
    }

    public int getNumeroPostiPrenotati() {
        return numeroPostiPrenotati;
    }

    // Metodi
    public void prenota() {
        if (LocalDate.now().isAfter(data)) {
            System.out.println("L'evento è già passato.");
        } else if (numeroPostiPrenotati >= numeroPostiTotali) {
            System.out.println("Non ci sono posti disponibili.");
        } else {
            numeroPostiPrenotati++;
        }
    }

    public void disdici() {
        if (LocalDate.now().isAfter(data)) {
            System.out.println("L'evento è già passato.");
        } else if (numeroPostiPrenotati == 0) {
            System.out.println("Non ci sono prenotazioni da disdire.");
        } else {
            numeroPostiPrenotati--;
        }
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return data.format(formatter) + " - " + titolo + " - Posti Totali: " + numeroPostiTotali +
                ", Posti Prenotati: " + numeroPostiPrenotati;
    }
}
