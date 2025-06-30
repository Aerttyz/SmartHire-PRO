package com.smarthirepro.core.service.impl;

import com.smarthirepro.core.dto.EmailRequest;
import com.smarthirepro.core.service.IEmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class EmailService implements IEmailService {

    private static final Logger log = LoggerFactory.getLogger(EmailService.class);

    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String remetente;

    @Override
    @Async
    public CompletableFuture<Void> enviarEmail(EmailRequest request) {
        log.info("Processando envio de e-mail para {}", request.destinatario());
        try {
            SimpleMailMessage mensagem = new SimpleMailMessage();
            mensagem.setFrom(remetente);
            mensagem.setTo(request.destinatario());
            mensagem.setSubject(request.assunto());
            mensagem.setText(request.corpo());

            mailSender.send(mensagem);
            log.info("E-mail para {} enviado com sucesso!", request.destinatario());
            return CompletableFuture.completedFuture(null);
        } catch (MailException e) {
            log.error("Falha ao enviar e-mail para {}: {}", request.destinatario(), e.getMessage());
            return CompletableFuture.failedFuture(e);
        }
    }
}