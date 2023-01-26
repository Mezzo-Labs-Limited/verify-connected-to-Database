package com.blockchain.verify.entity;

import com.blockchain.verify.model.BlockData;


import java.io.IOException;
import java.time.LocalDateTime;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity(name = "transaction")
public class TransactionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String txnHash;
    private LocalDateTime timestamp;


    public TransactionEntity() {
    }

    public TransactionEntity(BlockData blockData) throws IOException {
        this.id = id;
        this.txnHash = blockData.transaction();
        this.timestamp = blockData.getTimestamp();
    }

   // @Override
   // public String toString() {
   //    return String.format(
   //            "TransactionEntity[id=%d, txnHash='%s']",
   //           txnHash);
   //}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTxnHash() {
        return txnHash;
    }

    public void setTxnHash(String txnHash) {
        this.txnHash = txnHash;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}