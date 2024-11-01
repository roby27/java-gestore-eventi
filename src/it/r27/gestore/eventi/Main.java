package it.r27.gestore.eventi;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

        ProgrammaEventi programmaEventi = new ProgrammaEventi("Eventi in programma");

        boolean esci = false;
        while (!esci) {
            System.out.println("\n--- Menu Programma Eventi ---");
            System.out.println("1. Aggiungi un evento");
            System.out.println("2. Effettua una prenotazione per un evento esistente");
            System.out.println("3. Effettua una disdetta per un evento esistente");
            System.out.println("4. Consulta la lista degli eventi in programma");
            System.out.println("5. Esci");
            System.out.print("Seleziona un'opzione: ");

            int scelta = scanner.nextInt();
            scanner.nextLine();

            switch (scelta) {
                case 1:
                    System.out.print("Inserisci il titolo dell'evento: ");
                    String titolo = scanner.nextLine();

                    System.out.print("Inserisci la data dell'evento (dd/MM/yyyy): ");
                    String dataEvento = scanner.nextLine();
                    LocalDate data = LocalDate.parse(dataEvento, dateFormatter);

                    System.out.print("Inserisci il numero totale di posti: ");
                    int numeroPostiTotali = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("L'evento è un concerto? (si/no): ");
                    String tipoEvento = scanner.nextLine().toLowerCase();

                    if (tipoEvento.equals("si")) {
                        System.out.print("Inserisci l'ora del concerto (HH:mm): ");
                        String oraConcerto = scanner.nextLine();
                        LocalTime ora = LocalTime.parse(oraConcerto, timeFormatter);

                        System.out.print("Inserisci il prezzo del biglietto: ");
                        double prezzo = scanner.nextDouble();
                        scanner.nextLine();

                        Concerto concerto = new Concerto(titolo, data, numeroPostiTotali, ora, prezzo);
                        programmaEventi.aggiungiEvento(concerto);
                        System.out.println("Concerto aggiunto con successo.");
                    } else {
                        Evento evento = new Evento(titolo, data, numeroPostiTotali);
                        programmaEventi.aggiungiEvento(evento);
                        System.out.println("Evento aggiunto con successo.");
                    }
                    break;

                case 2:
                    System.out.println("\n--- Lista Eventi ---");
                    List<Evento> eventi = programmaEventi.getEventi();
                    for (int i = 0; i < eventi.size(); i++) {
                        System.out.println((i + 1) + ". " + eventi.get(i));
                    }
                    System.out.print("Seleziona il numero dell'evento per cui vuoi prenotare: ");
                    int indiceEventoPrenotazione = scanner.nextInt() - 1;

                    if (indiceEventoPrenotazione >= 0 && indiceEventoPrenotazione < eventi.size()) {
                        System.out.print("Quante prenotazioni vuoi effettuare? ");
                        int numeroPrenotazioni = scanner.nextInt();
                        scanner.nextLine();

                        for (int i = 0; i < numeroPrenotazioni; i++) {
                            eventi.get(indiceEventoPrenotazione).prenota();
                        }
                    } else {
                        System.out.println("Evento inesistente.");
                    }
                    System.out.println("Operazione completata.");
                    break;

                case 3:
                    System.out.println("\n--- Lista Eventi ---");
                    eventi = programmaEventi.getEventi();
                    for (int i = 0; i < eventi.size(); i++) {
                        System.out.println((i + 1) + ". " + eventi.get(i));
                    }
                    System.out.print("Seleziona il numero dell'evento per cui vuoi disdire: ");
                    int indiceEventoDisdetta = scanner.nextInt() - 1;

                    if (indiceEventoDisdetta >= 0 && indiceEventoDisdetta < eventi.size()) {
                        System.out.print("Quante disdette vuoi effettuare? ");
                        int numeroDisdette = scanner.nextInt();
                        scanner.nextLine();

                        for (int i = 0; i < numeroDisdette; i++) {
                            eventi.get(indiceEventoDisdetta).disdici();
                        }
                    } else {
                        System.out.println("Evento inesistente.");
                    }
                    System.out.println("Operazione completata.");
                    break;

                case 4:
                    System.out.println("\n--- Programma completo ---");
                    System.out.println(programmaEventi);
                    break;

                case 5:
                    esci = true;
                    System.out.println("Uscita dal programma...");
                    break;

                default:
                    System.out.println("Opzione non valida. Riprova.");
            }
        }

        scanner.close();
    }
}
