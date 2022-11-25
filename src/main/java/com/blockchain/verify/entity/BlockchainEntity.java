package com.blockchain.verify.entity;

import com.blockchain.verify.model.Block;
import com.blockchain.verify.model.BlockData;

import java.io.IOException;
import java.util.ArrayList;

public class BlockchainEntity {
    private ArrayList<Block> blockchain;

    public BlockchainEntity()throws IOException{
        blockchain = new ArrayList<>();
        blockchain.add(0,createGenesisBlock(new BlockData()));
    }

    public Block createGenesisBlock(BlockData blockData){return new Block("0", blockchain.size() + 1, blockData);}
    public Block createBlock(BlockData blockData){
        return new Block(getLatestBlock().getHash(), blockchain.size() +1, blockData);
    }
    public void addBlock(Block block){blockchain.add(block);}

    public ArrayList<Block> getBlockchain(){ return blockchain;}

    public Block getLatestBlock(){ return blockchain.get(blockchain.size() -1);}
}
