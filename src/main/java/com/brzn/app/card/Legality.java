package com.brzn.app.card;

import java.io.Serializable;

public class Legality implements Serializable {
	private String format;
	private String legality;

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public String getLegality() {
		return legality;
	}

	public void setLegality(String legality) {
		this.legality = legality;
	}
}