package com.blockchain.verify.controller;

import com.blockchain.verify.entity.TransactionEntity;
import com.blockchain.verify.entity.TransactionRepository;
import com.blockchain.verify.model.BlockData;
import com.blockchain.verify.service.BlockchainService;
import com.blockchain.verify.model.Block;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 1. blockchain will be represented using an array list
 * 2. This arraylist will have blocks
 * 3. The blocks will be a POJO, with fields like: ID, hash, timestamp and data.
 * 4. The data field will also be a java object, with fields like source and hash of file.
 * 5. To test we will create a block and add it to the array list and then return it in the display blockchain API.
 **/

/**
 * 1. create a @PostMapping method that accepts JSON with fields of block class
 * 2. The new block that will be added to the blockchain will have attributes from block class and blockData class
 * 3. Then add the new block to the blockchain
 * 4. Test the post request using Postman application. The Postman application will call the URL /add-block and then add the new block to the blockchain and display it.
 * 5. Add hash function to the block, use blockData + timestamp + previousBlockHash + nonce.
 *
 */

/**
 *
 */
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
@Transactional
public class BlockchainController {

    private final BlockchainService blockchainService;
    private final TransactionRepository transactionRepository;

//    public BlockchainController() throws IOException {
//        blockchainService = new BlockchainService();
//    }
//
//    // used for tests to give us access to a blockchain service for testing
//    public BlockchainController(BlockchainService blockchainService) {
//        this.blockchainService = blockchainService;
//    }


    @GetMapping("/display-blockchain")
    public ArrayList<Block> display() {
        if (blockchainService.getBlockchain().size() < 5) {
            populateBlockchainFromTheDatabase();
        }

        return blockchainService.getBlockchain();
    }

    private void populateBlockchainFromTheDatabase() {
        transactionRepository.findAll().stream()
                .map(transactionEntity -> new BlockData(transactionEntity.getTxnHash(), transactionEntity.getTimestamp(), transactionEntity.getId()))
                .forEach(blockData -> {
                    Block block = blockchainService.createBlock(blockData);
                    blockchainService.addBlock(block);
                });
    }


    @PostMapping("/add-block")
    public String addBlock(@RequestBody BlockData blockData) {
        /**
         * create block
         * */
        Block block = blockchainService.createBlock(blockData);

        /**
         * validation
         * */
        boolean validationResult = blockchainService.validateBlock(block);
        if(!validationResult){
            return "Block Invalid, cannot be added!";
        }

        /**
         * proof of work
         * */
        blockchainService.calculateProofOfWork(15, block);

        // add block
      /*  TransactionEntity entity = new TransactionEntity();
        //entity.setId(blockData.getTransactionID());
        entity.setTimestamp(blockData.getTimestamp());
        entity.setTxnHash(blockData.getTransaction());

        transactionRepository.save(entity);

       */
        blockchainService.addBlock(block);

        return "You have successfully added a block!";
    }
}

