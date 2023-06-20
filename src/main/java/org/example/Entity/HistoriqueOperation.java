package org.example.Entity;

import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDate;

public class HistoriqueOperation implements Serializable {
    private Long idOperation;
    private String operation;
    private double montant;
    private LocalDate date;
    private Long idClient;

    public HistoriqueOperation(String operation, double montant, LocalDate date) {
        this.operation = operation;
        this.montant = montant;
        this.date = date;
    }

    public HistoriqueOperation(String operation, double montant, LocalDate date, Long idClient) {
        this.operation = operation;
        this.montant = montant;
        this.date = date;
        this.idClient = idClient;
    }

    public Long getIdOperation() {
        return idOperation;
    }

    public void setIdOperation(Long idOperation) {
        this.idOperation = idOperation;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Long getIdClient() {
        return idClient;
    }

    public void setIdClient(Long idClient) {
        this.idClient = idClient;
    }
}
