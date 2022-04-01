package it.devlecce.ApplicazioneRestSpring.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Utente {
    @Id
    @GeneratedValue
    private Long id;
    private String nome;
    private String cognome;
    private Date datadinascita;

    public Utente() {
    }

    public Utente(String nome, String cognome, Date datadinascita) {
        this.nome = nome;
        this.cognome = cognome;
        this.datadinascita = datadinascita;
    }

    public Utente(Long id, String nome, String cognome, Date datadinascita) {
        this.id = id;
        this.nome = nome;
        this.cognome = cognome;
        this.datadinascita = datadinascita;
    }

    public Utente(String nome, String cognome) {
        this.nome = nome;
        this.cognome = cognome;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public Date getDatadinascita() {
        return datadinascita;
    }

    public void setDatadinascita(Date datadinascita) {
        this.datadinascita = datadinascita;
    }
}
