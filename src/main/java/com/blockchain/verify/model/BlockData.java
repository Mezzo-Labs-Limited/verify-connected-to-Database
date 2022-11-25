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


@ToString
@Getter
@Setter


public class BlockData {
    private final String transaction;
    private final LocalDateTime timestamp;
    private Integer transactionID;

    public BlockData() throws IOException {

        this.transaction = this.Transaction();
        this.timestamp = LocalDateTime.now();
        this.transactionID = getTransactionID();
    }
    public String Transaction() throws IOException {

        List<String> stngFile = new ArrayList<String>();

        BufferedReader bfredr = new BufferedReader(new FileReader("C:\\Users\\Saima\\Documents\\projects\\REACT\\zeena-repo\\KTP-BC-without-DB-\\src\\main\\resources\\AuditFile_mezzolabs-my.sharepoint.com_25.11.2022_14.30.18.txt"));

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
        return Transaction();
    }


}
