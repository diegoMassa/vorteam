package com.vortexbird.sentinel.modelo.dto;

import java.util.Comparator;

public class ComparadorGrupoDTO implements Comparator<GrupoDTO>{

	@Override
	public int compare(GrupoDTO g1, GrupoDTO g2) {
		
		if (g1.getOrden() == null){
			return -1;
		}
		
		if (g1.getOrden() !=null && g2.getOrden() == null){
			return 1;
		}
		
		if (g1.getOrden()!=null && g2.getOrden()!=null){
			return g1.getOrden().compareTo(g2.getOrden());
		}
		
		return 0;
	}


}