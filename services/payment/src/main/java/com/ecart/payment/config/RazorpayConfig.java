package com.ecart.payment.config;

import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Value;


@Configuration
public class RazorpayConfig {

    @Value("${razorpay.key_id}")
    private String keyId;

    @Value("${razorpay.key_secret}")
    private String keySecret;

    @Bean
    public RazorpayClient razorpayClient() throws RazorpayException {
        return new RazorpayClient(keyId, keySecret);
    }
}

