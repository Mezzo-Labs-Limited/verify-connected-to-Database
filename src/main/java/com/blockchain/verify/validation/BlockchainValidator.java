package com.blockchain.verify.validation;

import com.blockchain.verify.model.Block;
import com.blockchain.verify.util.Crypt;

import java.util.ArrayList;

public class BlockchainValidator {
    public boolean validatePreviouseHashes(Block previouseBlock, Block currentBlock){
        return previouseBlock.getHash().equals(currentBlock.getPreviousBlockHash());
    }

    public boolean validateBlockId(Block previousBlock, Block currentBlock){
        return previousBlock.getBlockID().equals(currentBlock.getBlockID() -1);
    }

    // if the block data hash and the block hash match that means its successful, return true in this case, if this fails return false.

    public boolean validateBlockHash(Block block){
        String expectedHash = Crypt.calculateHash(block.getBlockData().toString()
               + block.getTimestamp().toString()
                + block.getPreviousBlockHash()
                + block.getNonce());
        return block.getHash().equals(expectedHash);

    }

    public boolean validateBlockData(Block block){
        return block.getBlockData().getTransaction() != null;
             //   && block.getBlockData().getSource() !=null;
    }

    public static Boolean isChainValid(ArrayList<Block> blockchain){
        Block currentBlock;
        Block previousBlock;
        //loop through blockchain to check hashes:
        for(int i =1; i < blockchain.size(); i++){
            currentBlock = blockchain.get(i);
            previousBlock = blockchain.get(i-1);
            // compare registered hash and calculate hash:
            if(!currentBlock.getHash().equals(currentBlock.getHash())){
                System.out.println(("current hashes not equal"));
                return false;
            }
            //compare previous hash and registered previous hash
            if(!previousBlock.getHash().equals(currentBlock.getPreviousBlockHash())){
                System.out.println("previous hashes not equal");
                return false;

            }
        }
        return true;
    }
}
