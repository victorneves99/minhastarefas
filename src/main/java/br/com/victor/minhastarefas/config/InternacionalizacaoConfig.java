package br.com.victor.minhastarefas.config;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

@Configuration
public class InternacionalizacaoConfig {

    @Bean
    public MessageSource messageSource() {

        ReloadableResourceBundleMessageSource mSource = new ReloadableResourceBundleMessageSource();

        mSource.setBasename("classpath:messages");
        mSource.setDefaultEncoding("UTF-8");
        mSource.setDefaultLocale(Locale.getDefault());

        return mSource;
    }

    @Bean
    public LocalValidatorFactoryBean validatorFactoryBean() {

        LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
        bean.setValidationMessageSource(messageSource());
        return bean;

    }

}
