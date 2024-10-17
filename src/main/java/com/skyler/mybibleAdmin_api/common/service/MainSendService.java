package com.skyler.mybibleAdmin_api.common.service;//package com.skyler.mybibleAdmin_api.common.service;
//
//import jakarta.mail.MessagingException;
//import jakarta.mail.internet.MimeMessage;
//import lombok.RequiredArgsConstructor;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.mail.javamail.MimeMessageHelper;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.stereotype.Service;
//
//@Service
//@RequiredArgsConstructor
//public class MainSendService {
//
//    private final JavaMailSender javaMailSender;
//
//    public boolean sendAuthMail() {
////        PrincipalUser principalUser = (PrincipalUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
////        String toEmail = principalUser.getUser().getEmail();
//        String toEmail = "hhhsss0606@gmail.com";
//
//        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
//        try {
//            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, false, "utf-8");
//            helper.setSubject("겨자씨 이메일 인증");
//            helper.setFrom("test@gmail.com");
//            helper.setTo(toEmail);
//
//            String token = "12345";
//
//            mimeMessage.setText(
//                    "<div>" +
//                            "<h1>겨자씨 이메일 인증 메일</h1>" +
//                            "<p>이메일 인증을 완료하려면 아래의 버튼을 클릭하세요</p>" +
//                            "<a href=\"http://localhost:8080/health-check?token=" + token + "\">인증하기</a>" +
//                        "</div>", "utf-8", "html"
//
//            );
//            javaMailSender.send(mimeMessage);
//        } catch (MessagingException e) {
//            throw new RuntimeException(e);
//        }
//        return true;
//    }
//
//}
