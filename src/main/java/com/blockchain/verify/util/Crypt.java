package com.blockchain.verify.util;

import org.apache.commons.codec.digest.DigestUtils;

public class Crypt {

    public static String calculateHash(String dataToHash){
        return new DigestUtils("SHA3-256").digestAsHex(dataToHash);
    }
}


