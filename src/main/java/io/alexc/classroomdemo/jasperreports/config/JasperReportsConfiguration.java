package io.alexc.classroomdemo.jasperreports.config;

import io.alexc.classroomdemo.jasperreports.SimpleReportExporter;
import io.alexc.classroomdemo.jasperreports.SimpleReportFiller;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JasperReportsConfiguration {

    @Bean
    public SimpleReportFiller reportFiller() {
        return new SimpleReportFiller();
    }

    @Bean
    public SimpleReportExporter reportExporter() {
        return new SimpleReportExporter();
    }

}
