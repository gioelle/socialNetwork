package com.claim;

import java.util.Properties;

import org.apache.coyote.http11.AbstractHttp11Protocol;
import org.springframework.boot.context.embedded.tomcat.TomcatConnectorCustomizer;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
@Configuration
@ComponentScan(basePackages="com.claim")
public class ApplicationConfig{
	
	 
	@Bean
	public JavaMailSender getJavaMailSender() {
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
	    mailSender.setHost("smtp.gmail.com");
	    mailSender.setPort(587);
	     
	    mailSender.setUsername("claimtest17@gmail.com"); //
	    mailSender.setPassword("TestPass");
	     
	    Properties props = mailSender.getJavaMailProperties();
	    props.put("mail.transport.protocol", "smtp");
	    props.put("mail.smtp.auth", "true");
	    props.put("mail.smtp.starttls.enable", "true");
	    props.put("mail.debug", "true");
	     
	    return mailSender;
	}
	
	
	  //Tomcat large file upload connection reset
  @Bean
  public TomcatEmbeddedServletContainerFactory tomcatEmbedded() {

      TomcatEmbeddedServletContainerFactory tomcat = new TomcatEmbeddedServletContainerFactory();

      tomcat.addConnectorCustomizers((TomcatConnectorCustomizer) connector -> {
          if ((connector.getProtocolHandler() instanceof AbstractHttp11Protocol<?>)) {
              ((AbstractHttp11Protocol<?>) connector.getProtocolHandler()).setMaxSwallowSize(-1);
          }
      });

      return tomcat;

  }
    
 
    
}

