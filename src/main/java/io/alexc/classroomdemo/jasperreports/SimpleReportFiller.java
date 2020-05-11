package io.alexc.classroomdemo.jasperreports;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRSaver;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

@Component
public class SimpleReportFiller {

    private String reportFileName;

    private JasperReport jasperReport;

    private JasperPrint jasperPrint;

    private Map<String, Object> paramaters;

    private JRBeanCollectionDataSource dataSource;

    public String getReportFileName() {
        return reportFileName;
    }

    public void setReportFileName(String reportFileName) {
        this.reportFileName = reportFileName;
    }

    public JasperReport getJasperReport() {
        return jasperReport;
    }

    public void setJasperReport(JasperReport jasperReport) {
        this.jasperReport = jasperReport;
    }

    public JasperPrint getJasperPrint() {
        return jasperPrint;
    }

    public void setJasperPrint(JasperPrint jasperPrint) {
        this.jasperPrint = jasperPrint;
    }

    public Map<String, Object> getParamaters() {
        return paramaters;
    }

    public void setParamaters(Map<String, Object> paramaters) {
        this.paramaters = paramaters;
    }

    public JRBeanCollectionDataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(JRBeanCollectionDataSource dataSource) {
        this.dataSource = dataSource;
    }


    public SimpleReportFiller(String reportFileName, JasperReport jasperReport, Map<String, Object> paramaters, JRBeanCollectionDataSource dataSource) {
        this.reportFileName = reportFileName;
        this.jasperReport = jasperReport;
        this.paramaters = paramaters;
        this.dataSource = dataSource;
    }

    public SimpleReportFiller() {
        paramaters = new HashMap<>();
    }

    public void prepareReport() {
        compileReport();
        fillReport();
    }

    public void compileReport() {

        try {
            InputStream reportStream = getClass().getResourceAsStream("/".concat(reportFileName).concat(".jrxml"));
            jasperReport = JasperCompileManager.compileReport(reportStream);
            JRSaver.saveObject(jasperReport, reportFileName.replace(".jrxml", ".jasper"));
        } catch (JRException e) {
            e.printStackTrace();
        }
    }

    public void fillReport() {
        try {
            jasperPrint = JasperFillManager.fillReport(jasperReport, paramaters, dataSource);
        } catch (JRException e) {
            e.printStackTrace();
        }
    }



}
