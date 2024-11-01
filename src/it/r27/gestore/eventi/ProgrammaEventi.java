package it.r27.gestore.eventi;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ProgrammaEventi {
    private String titolo;
    private List<Evento> eventi;

    public ProgrammaEventi(String titolo) {
        this.titolo = titolo;
        this.eventi = new ArrayList<>();
    }

    // Aggiunge un evento
    public void aggiungiEvento(Evento evento) {
        eventi.add(evento);
    }

    // Restituisce eventi in una certa data
    public List<Evento> getEventiPerData(LocalDate data) {
        List<Evento> eventiFiltrati = new ArrayList<>();
        for (Evento evento : eventi) {
            if (evento.getData().equals(data)) {
                eventiFiltrati.add(evento);
            }
        }
        return eventiFiltrati;
    }

    // Restituisce la lista degli eventi
    public List<Evento> getEventi() {
        return eventi;
    }

    // Restituisce il numero di eventi
    public int getNumeroEventi() {
        return eventi.size();
    }

    // Svuota la lista di eventi
    public void svuotaEventi() {
        eventi.clear();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(titolo).append(" (").append(getNumeroEventi()).append(" eventi)").append("\n");
        eventi.sort((e1, e2) -> e1.getData().compareTo(e2.getData()));
        for (Evento evento : eventi) {
            sb.append(evento.toString()).append("\n");
        }
        return sb.toString();
    }
}
