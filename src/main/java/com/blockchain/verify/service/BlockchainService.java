package com.blockchain.verify.service;

import com.blockchain.verify.entity.BlockchainEntity;
import com.blockchain.verify.miner.BlockchainMiner;
import com.blockchain.verify.model.Block;
import com.blockchain.verify.model.BlockData;
import com.blockchain.verify.validation.BlockchainValidator;

import java.io.IOException;
import java.util.ArrayList;

/**
 * 1. checkBlockHash: a simple helper function that makes sure the block contents match the hash
 * 2. checkBlockValidity: checks the validity of a block, given its parent and current system state.
 * (updated state is returned if block is valid)
 * 3. checkChain: check the validity of the entire chain, and compute the system state beginning at the genesis block.
 * This will return the system state if the chain is valid, and raise an error otherwise.
 * The checkChain only needs to be performed once when the chain is first downloaded.
 *
 * Create block -> validations -> add to blockchain**/

public class BlockchainService {
    private final BlockchainEntity blockchainEntity;
    private final BlockchainValidator blockchainValidator;
    private final BlockchainMiner blockchainMiner;

    public BlockchainService() throws IOException {
        blockchainEntity = new BlockchainEntity();
        blockchainValidator = new BlockchainValidator();
        blockchainMiner = new BlockchainMiner();

    }

    public ArrayList<Block> getBlockchain() {
        return blockchainEntity.getBlockchain();
    }

    public Block createBlock(BlockData blockData) {
        return blockchainEntity.createBlock(blockData);
    }

    public void addBlock(Block block) {
        blockchainEntity.addBlock(block);
    }

    public Block getLatestBlock() {
        return blockchainEntity.getLatestBlock();
    }
    public boolean isChainValid(){
        return BlockchainValidator.isChainValid(getBlockchain());
    }

    public boolean validateBlock(Block block){
        return blockchainValidator.validateBlockData(block)
                && blockchainValidator.validateBlockHash(block)
                && blockchainValidator.validateBlockId(getLatestBlock(), block)
                && blockchainValidator.validatePreviouseHashes(getLatestBlock(), block);
    }

    public void calculateProofOfWork(int difficulty, Block block){
        blockchainMiner.calculateProofOfWork(difficulty,block);
    }
}





