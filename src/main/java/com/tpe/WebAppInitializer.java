package com.tpe;
//Java tabanlı web uygulamaları web.xml dosyasi ile config edilir
//bu classı web.xml yerine kullanırız.


import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

//AbstractAnnotationConfigDispatcherServletInitializer : DispatcherServlet konfigurasyonu ve başlatılması icin gerekli adımları gosterir.

public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {


    @Override
    protected Class<?>[] getRootConfigClasses() {//dataya erişim : hibernate, JDBC.
        return new Class[]{
                RootConfig.class
        };
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {//viewresolver,handlermapping
        return new Class[]{
                WebMvcConfig.class
        };
    }

    @Override//hangi url ile gelen istekleri servlet tarafından karşılanmasını sağlar.
    protected String[] getServletMappings() {
        return new String[]{
                "/"
        };
    }
}