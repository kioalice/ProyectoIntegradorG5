package gr5.talentsypymes.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebFig implements WebMvcConfigurer {

    @Value("${talents&pymesapp.ruta.imagenes}")
    private String rutaImagenes;

    @Value("${talents&pymesapp.ruta.cv}")
    private String rutaCv;



    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //registry.addResourceHandler("/logos/**").addResourceLocations("file:c:/pymes/img-pymes/");
        registry.addResourceHandler("/logos/**").addResourceLocations("file:"+rutaImagenes);
        registry.addResourceHandler("/cv/**").addResourceLocations("file:"+rutaCv);
    }

}
