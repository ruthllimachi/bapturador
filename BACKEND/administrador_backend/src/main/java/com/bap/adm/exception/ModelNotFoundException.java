package com.bap.adm.exception;

import com.bap.adm.configurate.OUI;


public class ModelNotFoundException extends RuntimeException {

	private static final long serialVersion = OUI.serialOUI;
	
	public ModelNotFoundException(String mensaje) {
		super(mensaje);
		
	}

}
