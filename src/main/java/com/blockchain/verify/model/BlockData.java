package com.blockchain.verify.model;

import com.blockchain.verify.util.Crypt;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * This class hold the data that is stored on the blockchain.
 * */

@ToString
@Getter
@Setter
public class BlockData {
    private final String transaction;
    private final LocalDateTime timestamp;
    private Integer transactionID;

    public BlockData() throws IOException {
        this.transaction = this.transaction();
        this.timestamp = LocalDateTime.now();
        this.transactionID = getTransactionID();
    }

    public BlockData(String transaction, LocalDateTime timestamp, Integer transactionID) {
        this.transaction = transaction;
        this.timestamp = timestamp;
        this.transactionID = transactionID;
    }

    /**
     * The code below reads the data from the log file and hashes the output.
     * The output is stored in Transaction variable
     * */
    public String transaction() throws IOException {

        List<String> stngFile = new ArrayList<String>();

        BufferedReader bfredr = new BufferedReader(new FileReader("C:\\Users\\Zeena\\Documents\\KTP project\\Desktop app\\util_ui\\log.txt"));

        String text = bfredr.readLine();
        while (text != null) {
            stngFile.add(text);
            text = bfredr.readLine();
        }
        bfredr.close();
        String[] array = stngFile.toArray(new String[0]);

        Arrays.toString(array);
        for (String eachstring : array) {

          LocalDateTime timestamp = LocalDateTime.now();
            int nonce= 1;
            return Crypt.calculateHash(eachstring
                        +timestamp.toString()
                        +nonce);
        }
        return transaction();
    }


}
