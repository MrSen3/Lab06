package it.polito.tdp.meteo;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import it.polito.tdp.meteo.bean.Citta;
import it.polito.tdp.meteo.bean.Rilevamento;
import it.polito.tdp.meteo.bean.SimpleCity;
import it.polito.tdp.meteo.db.MeteoDAO;

public class Model {

	private final static int COST = 100;
	private final static int NUMERO_GIORNI_CITTA_CONSECUTIVI_MIN = 3;
	private final static int NUMERO_GIORNI_CITTA_MAX = 6;
	private final static int NUMERO_GIORNI_TOTALI = 15;
	
	MeteoDAO meteoDao;

	public Model() {
		meteoDao=new MeteoDAO();
	}

	public String getUmiditaMedia(int mese) {
		
		Citta milano = new Citta("Milano");
		Citta torino = new Citta("Torino");
		Citta genova = new Citta("Genova");

		//Questo metodo mi ritorna una lista di rilevamenti contenenti tutti i rilevamenti in un certo mese per una certa città 
		milano.setRilevamenti(meteoDao.getAllRilevamentiLocalitaMese(mese, "Milano"));
		torino.setRilevamenti(meteoDao.getAllRilevamentiLocalitaMese(mese, "Torino"));
		genova.setRilevamenti(meteoDao.getAllRilevamentiLocalitaMese(mese, "Genova"));

		double mediaMilano = calcolaMedia(milano.getRilevamenti());
		double mediaTorino = calcolaMedia(torino.getRilevamenti());
		double mediaGenova = calcolaMedia(genova.getRilevamenti());
		
		String risultato = "Milano: " +mediaMilano+ "\nTorino: " +mediaTorino+"\nGenova: "+mediaGenova;
		
		return risultato;
	}

	public String trovaSequenza(int mese) {
		String risultato="";
		//Lista della migliore sequenza di città
		List<SimpleCity> best = new LinkedList<SimpleCity>();
		//Costo minimo della sequenza
		double costo_best=0.0;
		
		Set<Citta> parziale = new HashSet<Citta>();
		
		cerca(parziale, 0, mese);
		
		//devo ciclare best per costruirmi la stringa
		
		return risultato;
	}
	
	public void cerca(Set<Citta> parziale, int L, int mese){
		
		//Bisogna controllare che la città stia almeno 3 giorni di fila
		
		
		//Casi terminali
		
		//Se la città è stata inserita più di 6 volte allora ritorna e con il backtracking
		if(contaCitta()>NUMERO_GIORNI_CITTA_MAX){
			return;
		}
		
		//Ho assegnato a ogni giorno una citta
		if(parziale.size()==NUMERO_GIORNI_TOTALI) {
			return;
		}
		
		
		
		//recursive
		//backtracking
		
		
	}

	private Double punteggioSoluzione(List<SimpleCity> soluzioneCandidata) {

		double score = 0.0;
		return score;
	}

	private boolean controllaParziale(List<SimpleCity> parziale) {

		return true;
	}

	
	private int contaCitta(Citta citta, Set<Citta> parziale) {
		int counter=0;
		
		for(SimpleCity s: parziale) {
			if(s.equals(citta)) {
				counter++;
			}
		}
		
		return counter;
	}
		
	private Double calcolaMedia(List<Rilevamento> rilevamenti) {
		
		double somma=0.0;
		for(Rilevamento r: rilevamenti) {
			somma+=(double)r.getUmidita();
		}
		double media = somma/(rilevamenti.size());
		return media;
	}
}
