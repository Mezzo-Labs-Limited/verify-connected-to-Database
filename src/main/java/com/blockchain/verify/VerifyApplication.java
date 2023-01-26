package com.blockchain.verify;

import com.blockchain.verify.entity.TransactionEntity;
import com.blockchain.verify.entity.TransactionRepository;
import com.blockchain.verify.model.Block;
import com.blockchain.verify.model.BlockData;
import com.blockchain.verify.service.BlockchainService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.event.EventListener;

import java.util.List;

@SpringBootApplication
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
public class VerifyApplication {

    public static void main(String[] args) {
        SpringApplication.run(VerifyApplication.class, args);
    }
}
