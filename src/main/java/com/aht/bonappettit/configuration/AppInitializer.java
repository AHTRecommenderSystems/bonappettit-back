package com.aht.bonappettit.configuration;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.request.RequestContextListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import com.aht.bonappettit.AppConfig;
import com.sun.jersey.spi.spring.container.servlet.SpringServlet;

public class AppInitializer implements WebApplicationInitializer {

	public void onStartup(ServletContext container) throws ServletException {
		FilterRegistration.Dynamic encodingFilter = container.addFilter("encoding-filter", new CharacterEncodingFilter());
	    encodingFilter.setInitParameter("encoding", "UTF-8");
	    encodingFilter.setInitParameter("forceEncoding", "true");
	    encodingFilter.addMappingForUrlPatterns(null, true, "/*");
				
		final AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
		context.register(AppConfig.class);
		context.setServletContext(container);
		
	    container.addListener(new ContextLoaderListener(context));  
	    container.addListener(new RequestContextListener());  

	    final SpringServlet servlet = new SpringServlet();  
	    final ServletRegistration.Dynamic jerseyServlet = container.addServlet("jersey-serlvet", servlet);  
	    jerseyServlet.addMapping("/rest/*");  
	    jerseyServlet.addMapping("/application.wadl");  
	    jerseyServlet.setInitParameter("com.sun.jersey.config.property.packages", "com.aht.bonappettit.webservices");  
	    container.setInitParameter("contextConfigLocation", "");  
	}
}
