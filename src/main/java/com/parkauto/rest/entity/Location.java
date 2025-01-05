package com.parkauto.rest.entity;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "LOCATION")
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "IDLOCATION")
    private Long id;

    @Column(name = "DATELOCATION")
    private LocalDate dateLocation;

    @Column(name = "DATEDEBUT")
    private LocalDate dateDebut;

    @Column(name = "DATERETOUR")
    private LocalDate dateRetour;

    @ManyToMany
    @JoinTable(name = "LOCATION_CLIENT")
    private List<Client> clientList;

    public Location() {
        super();
    }

    public Location(Long id, LocalDate dateLocation, LocalDate dateDebut, LocalDate dateRetour, List<Client> clientList) {
        super();
        this.id = id;
        this.dateLocation = dateLocation;
        this.dateDebut = dateDebut;
        this.dateRetour = dateRetour;
        this.clientList = clientList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDateLocation() {
        return dateLocation;
    }

    public void setDateLocation(LocalDate dateLocation) {
        this.dateLocation = dateLocation;
    }

    public LocalDate getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(LocalDate dateDebut) {
        this.dateDebut = dateDebut;
    }

    public LocalDate getDateRetour() {
        return dateRetour;
    }

    public void setDateRetour(LocalDate dateRetour) {
        this.dateRetour = dateRetour;
    }

    public List<Client> getClientList() {
        return clientList;
    }

    public void setClientList(List<Client> clientList) {
        this.clientList = clientList;
    }
}
