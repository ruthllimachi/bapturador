package com.bap.api.exception;

import com.bap.api.configurate.OUI;


public class ModelNotFoundException extends RuntimeException {

	private static final long serialVersion = OUI.serialOUI;
	
	public ModelNotFoundException(String mensaje) {
		super(mensaje);
		
	}

}
