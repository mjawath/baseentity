package com.mycompany.entitybase;

import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.UUID;

@Component
public class CustomIdGenerator implements IdentifierGenerator {

    private static final String ENV_UUID = "uuid"; // Environment value for UUID
    private static final String ENV_AUTONUMBER = "autonumber"; // Environment value for auto-number
    private static final String ENV_KEY = "ID_GENERATION_STRATEGY"; // Environment key

    private static long counter = 1; // Counter for auto-number

    @Value("${id.generation.strategy:autonumber}") // Read the strategy from the property file

    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object object) {
        String strategy = System.getenv(ENV_KEY); // Get the strategy from environment variables

//
//        if (ENV_UUID.equalsIgnoreCase(strategy)) {
//            return UUID.randomUUID().toString(); // Generate UUID
//        } else if (ENV_AUTONUMBER.equalsIgnoreCase(strategy)) {
//            return String.valueOf(counter++); // Generate auto-number as a string
//        }
        if ("uuid".equalsIgnoreCase(strategy)) {
            return UUID.randomUUID().toString(); // Generate UUID
        } else if ("autonumber".equalsIgnoreCase(strategy)) {
            return String.valueOf(counter++); // Generate auto-number as a string
        } else {
            throw new IllegalArgumentException("Invalid ID generation strategy: " + strategy);
        }

    }
}