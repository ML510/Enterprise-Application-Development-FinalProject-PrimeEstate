package com.realestate.management.service.impl;

import com.realestate.management.repository.MailRepository;
import com.realestate.management.service.MailService;
import com.realestate.management.util.ApiResponse;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class MailServiceImpl implements MailService {

    private final MailRepository mailRepository;
    private final JavaMailSender mailSender;

    @Override
    public ApiResponse subscribe( String email) {
        Boolean isSubscribe = mailRepository.subscribe(email);

        if(isSubscribe){
            try {
                MimeMessage mimeMessage = mailSender.createMimeMessage();
                MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");

                helper.setFrom("praveenwps1212@gmail.com");
                helper.setTo(email);
                helper.setSubject("Subscription Confirmation");
                helper.setText("""

                        <!doctype html>
                        <html lang="en">
                        <head>
                          <meta charset="UTF-8" />
                          <meta name="viewport" content="width=device-width,initial-scale=1.0" />
                          <meta http-equiv="x-ua-compatible" content="ie=edge" />
                          <title>Prime State Subscription</title>
                        </head>
                        <body style="margin:0; padding:0; background:#f8f9fc; font-family:Manrope, Inter, Arial, sans-serif;">

                          <div style="display:none; max-height:0; overflow:hidden; opacity:0; color:transparent;">
                            Your Prime State property alert subscription is active.
                          </div>

                          <table role="presentation" width="100%" cellpadding="0" cellspacing="0" border="0" style="background:#f8f9fc; padding:24px 0;">
                            <tr>
                              <td align="center">

                                <table role="presentation" width="620" cellpadding="0" cellspacing="0" border="0"
                                  style="width:620px; max-width:620px; background:#ffffff; border-radius:18px; overflow:hidden; box-shadow:0 18px 45px -22px rgba(15,23,42,0.25);">

                                  <tr>
                                    <td style="background:linear-gradient(135deg,#0f172a 0%, #75269d 55%, #1e293b 100%); padding:32px 30px;">
                                      <p style="margin:0 0 8px 0; color:#e2e8f0; font-size:11px; letter-spacing:2px; text-transform:uppercase; font-weight:700;">
                                        Prime State Luxury Properties
                                      </p>
                                      <h1 style="margin:0; color:#ffffff; font-size:28px; line-height:1.2; font-weight:800;">
                                        Subscription Confirmed
                                      </h1>
                                      <p style="margin:10px 0 0 0; color:#e2e8f0; font-size:14px; line-height:1.6;">
                                        You will now receive the latest property updates from Prime State.
                                      </p>
                                    </td>
                                  </tr>

                                  <tr>
                                    <td style="padding:26px 30px 12px 30px;">
                                      <table role="presentation" width="100%" cellpadding="0" cellspacing="0" border="0"
                                        style="border:1px solid #e2e8f0; border-radius:14px;">
                                        <tr>
                                          <td style="padding:20px;">
                                            <h2 style="margin:0 0 10px 0; color:#0f172a; font-size:20px; font-weight:800;">
                                              What You’ll Get
                                            </h2>
                                            <p style="margin:0; color:#475569; font-size:14px; line-height:1.8;">
                                              New listings, featured properties, and curated real estate opportunities delivered to your inbox.
                                            </p>
                                          </td>
                                        </tr>
                                      </table>
                                    </td>
                                  </tr>

                                  <tr>
                                    <td style="padding:14px 30px 28px 30px;">
                                      <table role="presentation" cellpadding="0" cellspacing="0" border="0">
                                        <tr>
                                          <td align="center" style="background:#75269d; border-radius:12px;">
                                            <a href="http://localhost:4200/" target="_blank"
                                              style="display:inline-block; padding:14px 24px; color:#ffffff; text-decoration:none; font-weight:700; font-size:15px; border-radius:12px;">
                                              Browse Properties
                                            </a>
                                          </td>
                                        </tr>
                                      </table>
                                    </td>
                                  </tr>

                                  <tr>
                                    <td style="background:#0f172a; padding:18px 30px;">

                                      <p style="margin:0; color:#94a3b8; font-size:12px; line-height:1.6;">
                                        © 2024 Prime State.\s
                                        <a href="" style="color:#e2e8f0; text-decoration:underline;">Unsubscribe</a>
                                      </p>
                                    </td>
                                  </tr>

                                </table>

                              </td>
                            </tr>
                          </table>
                        </body>
                        </html>

                    """, true);

                mailSender.send(mimeMessage);
            }catch (MessagingException e){
                e.printStackTrace();
            }
        }
        return new ApiResponse<>(isSubscribe, isSubscribe ? "Email subscribed successfully" : "Email subscription failed", null, LocalDateTime.now());
    }

}
