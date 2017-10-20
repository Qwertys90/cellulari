package it.dstech.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;


@Entity
public class Cellulare {
@Id
@GeneratedValue
private int id;

private String marca;

private String modello;

private int quantità;

private int prezzo;

@ManyToMany(mappedBy="listaCellulari")
private List<User> user;

public List<User> getListaUser() {
	return user;
}



public int getQuantità() {
	return quantità;
}



public void setQuantità(int quantità) {
	this.quantità = quantità;
}



public List<User> getUser() {
	return user;
}



public void setUser(List<User> user) {
	this.user = user;
}



public void setListaUser(List<User> listaUser) {
	this.user = listaUser;
}

public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
}

public String getMarca() {
	return marca;
}

public void setMarca(String marca) {
	this.marca = marca;
}

public String getModello() {
	return modello;
}

public void setModello(String modello) {
	this.modello = modello;
}

public int getPrezzo() {
	return prezzo;
}

public void setPrezzo(int prezzo) {
	this.prezzo = prezzo;
}

@Override
public String toString() {
	return "Cellulare [id=" + id + ", marca=" + marca + ", modello=" + modello + ", prezzo=" + prezzo + "]";
}


}
