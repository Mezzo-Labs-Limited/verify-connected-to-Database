package com.blockchain.verify.model;

import java.time.LocalDateTime;
import java.util.Random;
import com.blockchain.verify.util.Crypt;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Getter
@ToString
@Slf4j

public class Block {

    private final LocalDateTime timestamp;
    private final String hash;
    private final String previousBlockHash;
    private final BlockData blockData;
    private final Integer blockID;
    private final Integer nonce;

    public Block(String previousBlockHash, Integer blockID, BlockData blockData){

        this.timestamp = LocalDateTime.now();
        this.previousBlockHash = previousBlockHash;
        this.blockID = blockID;
        this.blockData = blockData;
        Random random = new Random();
        this.nonce = random.nextInt();
        this.hash = this.calculateBlockHash();
    }

    public String calculateBlockHash(){
        return Crypt.calculateHash(blockData.toString()
                +timestamp.toString()
                +previousBlockHash
                +nonce);
    }

}

