package com.example.demo.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Locale;


@Configuration
public class WebConfig implements WebMvcConfigurer {

    /**
     * SessionLocaleResolver : stocke la locale dans la session HTTP
     * Avantages : persiste pendant toute la session utilisateur
     * Inconvénients : perdu à la fermeture du navigateur
     */
    @Bean
    public LocaleResolver localeResolver(){
        SessionLocaleResolver slr = new SessionLocaleResolver();
        slr.setDefaultLocale(Locale.ENGLISH);
        return slr;
    }

    /**
     * Intercepteur pour changer la locale via paramètre URL
     */
    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        LocaleChangeInterceptor interceptor = new LocaleChangeInterceptor();

        // Nom du paramètre URL pour changer la langue
        interceptor.setParamName("lang");

        // Optionnel : ignorer une requête invalide de changement de locale
        interceptor.setIgnoreInvalidLocale(true);

        return interceptor;
    }

    /**
     * Enregistrement de l'intercepteur dans la chaîne de traitement
     * L'intercepteur est appelé avant chaque requête
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(localeChangeInterceptor())
                .addPathPatterns("/**") // Toutes les URLs
                .excludePathPatterns("/api/**"); // Exclure les APIs
    }

    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:i18n/messages");
        messageSource.setDefaultLocale(Locale.ENGLISH);
        messageSource.setDefaultEncoding("UTF-8");
        messageSource.setFallbackToSystemLocale(false);
        return messageSource;
    }
}
