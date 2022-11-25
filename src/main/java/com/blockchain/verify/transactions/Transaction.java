package com.blockchain.verify.transactions;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Transaction {
    public String Transaction() throws IOException {
        List<String> stngFile = new ArrayList<String>();
        BufferedReader bfredr = new BufferedReader(new FileReader
                ("C:\\Users\\Zeena\\Downloads\\AuditFile_mezzolabs-my.sharepoint.com_21.11.2022_11.6.38.json"));

        String text = bfredr.readLine();
        while (text != null) {
            stngFile.add(text);
            text = bfredr.readLine();
        }
        bfredr.close();
        String[] array = stngFile.toArray(new String[0]);

       Arrays.toString(array);
        for (String eachstring : array) {
            String str = String.join(",", array);
            return str;
        }

        return Transaction();
    }

}

    


