package by.epam.crackertracker.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.core.io.Resource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

import java.util.Locale;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "by.epam.crackertracker")
public class WebConfig extends WebMvcConfigurerAdapter{

    public static final int ORDER = 1;
    public static final String SUFFIX = ".ftl";
    public static final String PREFIX = "";
    public static final String INDEX_PATH = "/";
    public static final String JSP_PATH = "/WEB-INF/views/";
    public static final String DEFAULT_ENCODING = "UTF-8";
    public static final String ENCODING = "text/html; charset=utf-8";
    public static final String RESOURCE_BUNDLE = "message";


    @Bean
    public ViewResolver getViewResolver() {
        FreeMarkerViewResolver freeMarkerViewResolver = new FreeMarkerViewResolver();
        freeMarkerViewResolver.setOrder(ORDER);
        freeMarkerViewResolver.setSuffix(SUFFIX);
        freeMarkerViewResolver.setPrefix(PREFIX);
        freeMarkerViewResolver.setContentType(ENCODING);
        return freeMarkerViewResolver;
    }

    @Bean
    public FreeMarkerConfigurer getFreeMarkerConfigurer() {
        FreeMarkerConfigurer freeMarkerConfigurer = new FreeMarkerConfigurer();
        freeMarkerConfigurer.setTemplateLoaderPaths(INDEX_PATH, JSP_PATH);
        freeMarkerConfigurer.setDefaultEncoding(DEFAULT_ENCODING);
        return freeMarkerConfigurer;
    }

    @Bean
    public ResourceBundleMessageSource messageSource() {
        ResourceBundleMessageSource source = new ResourceBundleMessageSource();
        source.setBasenames(RESOURCE_BUNDLE);
        source.setUseCodeAsDefaultMessage(true);
        source.setDefaultEncoding(DEFAULT_ENCODING);
        return source;
    }

    @Bean
    public LocaleResolver localeResolver() {
        SessionLocaleResolver localeResolver = new SessionLocaleResolver();
        localeResolver.setDefaultLocale(Locale.ENGLISH);
        return localeResolver;
    }


}