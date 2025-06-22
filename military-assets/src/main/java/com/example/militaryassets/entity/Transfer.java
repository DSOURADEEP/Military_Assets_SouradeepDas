package com.example.militaryassets.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Transfer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Base fromBase;

    @ManyToOne
    private Base toBase;

    @ManyToOne
    private Asset asset;

    private int quantity;

    private LocalDateTime date;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Base getFromBase() { return fromBase; }
    public void setFromBase(Base fromBase) { this.fromBase = fromBase; }

    public Base getToBase() { return toBase; }
    public void setToBase(Base toBase) { this.toBase = toBase; }

    public Asset getAsset() { return asset; }
    public void setAsset(Asset asset) { this.asset = asset; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    public LocalDateTime getDate() { return date; }
    public void setDate(LocalDateTime date) { this.date = date; }
}

