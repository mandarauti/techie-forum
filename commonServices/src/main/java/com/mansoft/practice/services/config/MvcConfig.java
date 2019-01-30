package com.mansoft.practice.services.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableWebMvc
@EnableAspectJAutoProxy
//@ImportResource({"classpath*:applicationContext.xml"})
public class MvcConfig extends WebMvcConfigurerAdapter {
//	@Override
//    public void  configureMessageConverters(List<HttpMessageConverter<?>> converters) {
//        converters.add(marshallingHttpMessageConverter());
//        converters.add(createXmlHttpMessageConverter());
//        converters.add(new MappingJackson2HttpMessageConverter());
//        super.configureMessageConverters(converters);
//    }
//
//    @Bean
//    public MarshallingHttpMessageConverter marshallingHttpMessageConverter() {
//        Jaxb2Marshaller jaxb2Marshaller = new Jaxb2Marshaller();
//        jaxb2Marshaller.setClassesToBeBound(EmployeeDO.class);
//        Resource schema = new ClassPathResource("employee.xsd");
//        jaxb2Marshaller.setSchema(schema);
//        MarshallingHttpMessageConverter converter = new MarshallingHttpMessageConverter();
//        converter.setMarshaller(jaxb2Marshaller);
//        converter.setUnmarshaller(jaxb2Marshaller);
//        converter.setSupportedMediaTypes(Arrays.asList(MediaType.APPLICATION_XML));
//
//        return converter;
//    }
//    
//    private HttpMessageConverter<Object> createXmlHttpMessageConverter() {
//        MarshallingHttpMessageConverter xmlConverter = 
//          new MarshallingHttpMessageConverter();
// 
//        XStreamMarshaller xstreamMarshaller = new XStreamMarshaller();
//        xmlConverter.setMarshaller(xstreamMarshaller);
//        xmlConverter.setUnmarshaller(xstreamMarshaller);
// 
//        return xmlConverter;
//    }
//	
//	@Override
//    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
//        converters.add(marshallingHttpMessageConverter());
//    }
//
//    @Bean
//    public MarshallingHttpMessageConverter marshallingHttpMessageConverter() {
//        Jaxb2Marshaller jaxb2Marshaller = new Jaxb2Marshaller();
//        jaxb2Marshaller.setPackagesToScan(new String[]{"com.mansoft.practice.services.domain"});
//
//        MarshallingHttpMessageConverter converter = new MarshallingHttpMessageConverter();
//        converter.setMarshaller(jaxb2Marshaller);
//        converter.setUnmarshaller(jaxb2Marshaller);
//        converter.setSupportedMediaTypes(Arrays.asList(MediaType.APPLICATION_XML));
//
//        return converter;
//    }

	

}