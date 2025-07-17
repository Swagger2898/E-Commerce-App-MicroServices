package com.ecart.notification.email;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import com.ecart.notification.kafka.order.Product;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.thymeleaf.context.Context;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmailService {

     private final JavaMailSender jms;
     private final SpringTemplateEngine templateEngine;

     @Async
    public void sentPaymentSuccessEmail(
            String destinationEmail,
            String customerName,
            BigDecimal amount,
            String orderReference
     )throws MessagingException {
         MimeMessage mimeMessage = jms.createMimeMessage();
         MimeMessageHelper mimeMessageHelper= new MimeMessageHelper(mimeMessage,MimeMessageHelper.MULTIPART_MODE_RELATED, StandardCharsets.UTF_8.name());
         mimeMessage.setFrom("swapnilvrinda@gmail.com");
         final String templateName= EmailTemplates.PAYMENT_CONFIRMATION.getTemplate();

         Map<String, Object> variables = new HashMap<>();

         variables.put("customerName",customerName);
         variables.put("amount",amount);
         variables.put("orderReference",orderReference);

         Context context = new Context();
         context.setVariables(variables);
         mimeMessageHelper.setSubject(EmailTemplates.PAYMENT_CONFIRMATION.getSubject());

         try{
             String htmlTemplate = templateEngine.process(templateName,context);
             mimeMessageHelper.setText(htmlTemplate,true);

             mimeMessageHelper.setTo(destinationEmail);
             jms.send(mimeMessage);
             log.info(String.format("INFO-Email successfully sent to %s with template %s, ",destinationEmail,templateName));
         }catch(MessagingException e){
             log.warn("WARNING - Cannot send email to {}", destinationEmail);
         }
     }


    @Async
    public void sentOrderConfirmationEmail(
            String destinationEmail,
            String customerName,
            BigDecimal amount,
            String orderReference,
            List<Product> products
    )throws MessagingException {
        MimeMessage mimeMessage = jms.createMimeMessage();
        MimeMessageHelper mimeMessageHelper= new MimeMessageHelper(mimeMessage,MimeMessageHelper.MULTIPART_MODE_RELATED, StandardCharsets.UTF_8.name());
        mimeMessage.setFrom("swapnilvrinda@gmail.com");
        final String templateName= EmailTemplates.ORDER_CONFIRMATION.getTemplate();

        Map<String, Object> variables = new HashMap<>();

        variables.put("customerName",customerName);
        variables.put("totalAmount",amount);
        variables.put("orderReference",orderReference);
        variables.put("products", products);

        Context context = new Context();
        context.setVariables(variables);
        mimeMessageHelper.setSubject(EmailTemplates.ORDER_CONFIRMATION.getSubject());

        try{
            String htmlTemplate = templateEngine.process(templateName,context);
            mimeMessageHelper.setText(htmlTemplate,true);

            mimeMessageHelper.setTo(destinationEmail);
            jms.send(mimeMessage);
            log.info(String.format("INFO-Email successfully sent to %s with template %s, ",destinationEmail,templateName));
        }catch(MessagingException e){
            log.warn("WARNING - Cannot send email to {}", destinationEmail);
        }
    }

}
