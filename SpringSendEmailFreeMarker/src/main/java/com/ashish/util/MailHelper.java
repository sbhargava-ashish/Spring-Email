package com.ashish.util;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.mail.internet.MimeMessage;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.scheduling.annotation.Async;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import freemarker.cache.FileTemplateLoader;
import freemarker.template.Configuration;

public class MailHelper {

	private static final Logger logger = LogManager.getLogger("javax.mail");

	@Autowired
	private JavaMailSenderImpl mailSender;

	@Autowired
	Configuration freeMarkerConfiguration;

	@Value("${subject}")
	private String subject;

	@Async
	public void sendTemplatedMimeMessage(final Map<String, Object> templatedMimeMessage, final String template,
			final List<String> toAddresses) throws IOException {

		FileTemplateLoader templateLoader = null;
		templateLoader = new FileTemplateLoader(new File("src/main/java"));
		freeMarkerConfiguration.setTemplateLoader(templateLoader);

		MimeMessagePreparator preparator;
		try {
			preparator = new MimeMessagePreparator() {

				String messageText = FreeMarkerTemplateUtils
						.processTemplateIntoString(freeMarkerConfiguration.getTemplate(template), templatedMimeMessage);

				public void prepare(MimeMessage message) throws Exception {

					MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED);

					for (String address : toAddresses) {
						helper.addTo(address);
					}

					helper.setFrom("avaya_hosted_sal@avaya.com", "Avaya Hosted SAL");
					File img = new File("src/main/java/AvayaLogo.png");
					File inactive = new File("src/main/java/Inactive.png");
				
					helper.addAttachment("AvayaLogo.png", img);
					helper.addAttachment("Inactive.png", inactive);
										helper.setSubject("Gateways Missing");
					
					helper.setText(messageText, true);

				}
			};
			
			mailSender.setDefaultEncoding("UTF-8");

			this.mailSender.send(preparator);

		} catch (org.springframework.mail.MailSendException cx) {
			logger.error("Failed to send email for templated message:" + "\nFrom:" + "\nTo:" + toAddresses
					+ "\nMessageMap:" + templatedMimeMessage, cx);
		} catch (Exception ex) {

			logger.error("Failed to send email for templated message:" + "\nFrom:" + "\nTo:" + toAddresses
					+ "\nMessageMap:" + templatedMimeMessage, ex);
		}

	}

}
