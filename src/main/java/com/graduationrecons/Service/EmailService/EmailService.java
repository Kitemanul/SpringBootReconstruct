package com.graduationrecons.Service.EmailService;


import org.springframework.stereotype.Service;

@Service
public interface EmailService {

     public void SendEmail(String toEmail,String code);
}
