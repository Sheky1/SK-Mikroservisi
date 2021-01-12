package com.sk.korisnicki.listener;


import com.sk.korisnicki.dto.MatchesDto;
import com.sk.korisnicki.servis.EmailService;

import javax.jms.JMSException;
import javax.jms.Message;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class EmailListener {

    private MessageHelper messageHelper;
    private EmailService emailService;

    public EmailListener(MessageHelper messageHelper, EmailService emailService) {
        this.messageHelper = messageHelper;
        this.emailService = emailService;
    }

    @JmsListener(destination = "${destination.sendEmails}", concurrency = "5-10")
    public void addOrder(Message message) throws JMSException {
        MatchesDto matchesDto = messageHelper.getMessage(message, MatchesDto.class);
        emailService.sendSimpleMessage("mradovic01011111@gmail.com", "subject", matchesDto.toString());
    }
}
