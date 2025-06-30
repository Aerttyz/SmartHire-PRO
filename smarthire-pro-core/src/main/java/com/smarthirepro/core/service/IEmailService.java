package com.smarthirepro.core.service;

import com.smarthirepro.core.dto.EmailRequest;
import java.util.concurrent.CompletableFuture;

public interface IEmailService {

    CompletableFuture<Void> enviarEmail(EmailRequest request);
}