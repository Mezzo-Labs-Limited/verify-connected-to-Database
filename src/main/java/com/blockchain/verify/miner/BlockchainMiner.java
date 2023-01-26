package com.blockchain.verify.miner;

import com.blockchain.verify.model.Block;
import com.blockchain.verify.util.Crypt;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BlockchainMiner {

    /**
     * Keeps recalculating a random hash until that hash has the same number of zero's at the difficulty integer
     * */
    public boolean calculateProofOfWork(int difficulty, Block block) {
        int nonce = 0;
        int numberOfZerosInHash = countCharacters(calculateHash(block, nonce), '0'); // initially calculate hash

        while (numberOfZerosInHash != difficulty) {
            nonce++;
            String recalculatedHash = calculateHash(block, nonce);
            numberOfZerosInHash = countCharacters(recalculatedHash, '0'); // keep recalculating hash

            log.info("Number of zero's in hash: {}, hash: {}", numberOfZerosInHash, recalculatedHash);
        }

        log.info("You have solved the algorithm, proof of work complete!");
        return true;
    }

    private String calculateHash(Block block, int nonce) {
        return Crypt.calculateHash(block.getBlockData().toString()
                + block.getTimestamp().toString()
                + block.getPreviousBlockHash()
                + nonce);
    }

    public int countCharacters(String str, char c)
    {
        int count = 0;

        for(int i=0; i < str.length(); i++)
        {    if(str.charAt(i) == c)
            count++;
        }

        return count;
    }
}
