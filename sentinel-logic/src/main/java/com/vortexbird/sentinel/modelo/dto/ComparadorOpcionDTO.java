package com.vortexbird.sentinel.modelo.dto;

import java.util.Comparator;

public class ComparadorOpcionDTO implements Comparator<OpcionDTO>{

	@Override
	public int compare(OpcionDTO o1, OpcionDTO o2) {
		
		if (o1.getOrden() == null){
			return -1;
		}
		
		if (o1.getOrden() !=null && o2.getOrden() == null){
			return 1;
		}
		
		if (o1.getOrden()!=null && o2.getOrden()!=null){
			return o1.getOrden().compareTo(o2.getOrden());
		}
		
		return 0;
	}


}