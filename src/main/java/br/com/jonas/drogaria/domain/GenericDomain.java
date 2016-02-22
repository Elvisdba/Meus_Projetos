package br.com.jonas.drogaria.domain;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;


@SuppressWarnings("serial")
@MappedSuperclass
public class GenericDomain implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long codigo;

	public long getCodigo() {
		return codigo;
	}

	public void setCodigo(long codigo) {
		this.codigo = codigo;
	}
	
	 @Override
	 public String toString() {
	     return String.format("%s[codigo=%d]", getClass().getSimpleName(), getCodigo());
	 }
}
