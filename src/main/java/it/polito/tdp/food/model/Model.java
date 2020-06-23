package it.polito.tdp.food.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.jgrapht.Graphs;
import org.jgrapht.alg.connectivity.ConnectivityInspector;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;


import it.polito.tdp.food.db.FoodDao;

public class Model {
	
	private FoodDao dao;
	private SimpleWeightedGraph<String,DefaultWeightedEdge> grafo;
	private List<String> porzioni;
	private Set<String> vertici;
	private List<Arco> archi;
	private List<String> best;
	private int pesoTotale;
	
	
	
	public Model() {
		dao = new FoodDao();
	}
	
	public void creaGrafo(int calorie) {
		
		this.porzioni = new ArrayList<>(this.dao.listPortions(calorie));
		this.vertici = new HashSet<>(this.porzioni);
		this.grafo = new SimpleWeightedGraph<String,DefaultWeightedEdge>(DefaultWeightedEdge.class);
		
		Graphs.addAllVertices(this.grafo, this.porzioni);
		
		this.archi = new ArrayList<>(this.dao.getArchi(this.vertici));
		
		for(Arco a: this.archi) {
			Graphs.addEdge(this.grafo, a.getProdotto1(), a.getProdotto2(),a.getPeso());
		}
		
		
		
		
	}
	
	public List<String> getPorzioni(){
		return this.porzioni;
	}
	
	public int getNUmeroVertici() {
		return this.grafo.vertexSet().size();
	}
	
	
	public int getNumeroArchi() {
		return this.grafo.edgeSet().size();
	}
	
	
	public String getElencoConnessi(String porzione){
		
		List<String> vicini;
		String elenco = "";
		
		vicini = new ArrayList<>(Graphs.neighborListOf(this.grafo, porzione));
		
		
		elenco += "Porzioni connesse a "+porzione+":\n";
		
		for(String v: vicini) {
			elenco+= v+" peso: "+this.grafo.getEdgeWeight(this.grafo.getEdge(porzione, v))+"\n";
		}
		
		
		return elenco;
	}
	
	
	public void trovaPercoso(String sorgente , int max) {
		
		this.best = null;
		this.pesoTotale = 0;
		
		List<String> parziale  = new ArrayList<>();
		parziale.add(sorgente);
		
		ricorsione(parziale, max);
	}

	public void ricorsione(List<String> parziale, int max) {
		
		if(parziale.size()==max) {
			int peso = calcolaPeso(parziale);
			if(peso>this.pesoTotale) {
				this.pesoTotale=peso;
				this.best = new ArrayList<>(parziale);
			}
			return;
		}
		
		String ultimo = parziale.get(parziale.size()-1);
		
		List<String> vicini = Graphs.neighborListOf(this.grafo, ultimo);
		
		for(String v: vicini) {
			if(!parziale.contains(v)) {
				parziale.add(v);
				ricorsione(parziale,max);
				parziale.remove(parziale.size()-1);
				
			}
		}
		
		
		
	}

	public int calcolaPeso(List<String> parziale) {
		
		int peso = 0;
		List<String> tempP = new ArrayList<>(parziale);
		String precedente  = tempP.get(0);
		tempP.remove(precedente);
		for(String p: tempP) {
			peso+= this.grafo.getEdgeWeight(this.grafo.getEdge(precedente, p));
			precedente  = p;
		}
		
		
		return peso;
	}

	public List<String> getBest() {
		return best;
	}

	public int getPesoTotale() {
		return pesoTotale;
	}
	
	
	
	
}
