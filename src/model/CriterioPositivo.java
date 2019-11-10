package model;

import java.util.ArrayList;
import java.util.List;

public class CriterioPositivo implements Criterio{
	
	@Override
    public List<Evento> Criterio(List<Evento> listaEventos) {
        List<Evento> eventosPositivos = new ArrayList<>();
        
        for(Evento evento : listaEventos) {
            if(evento.getTipo().equalsIgnoreCase("Positivo")) {
                eventosPositivos.add(evento);
            } 
        }
        return eventosPositivos;
    }
}
