package model;

import java.util.ArrayList;
import java.util.List;

public class CriterioNegativo implements Criterio{
	
	@Override
    public List<Evento> Criterio(List<Evento> listaEventos) {
        List<Evento> eventosNegativos = new ArrayList<>();
        
        for(Evento evento : listaEventos) {
            if(evento.getTipo().equalsIgnoreCase("Negativo")) {
                eventosNegativos.add(evento);
            } 
        }
        return eventosNegativos;
    }
}
