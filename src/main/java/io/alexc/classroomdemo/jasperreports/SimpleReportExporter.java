package io.alexc.classroomdemo.jasperreports;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.export.*;
import org.springframework.stereotype.Component;

import java.io.OutputStream;

@Component
public class SimpleReportExporter {

    private JasperPrint jasperPrint;

    public SimpleReportExporter() {

    }

    public SimpleReportExporter(JasperPrint jasperPrint) {
        this.jasperPrint = jasperPrint;
    }

    public JasperPrint getJasperPrint() {
        return jasperPrint;
    }

    public void setJasperPrint(JasperPrint jasperPrint) {
        this.jasperPrint = jasperPrint;
    }

    public void exportToPdf(String fileName, String author, OutputStream outputStream) {

        JRPdfExporter exporter = new JRPdfExporter();

        exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
        // exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(fileName));
        exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(outputStream));

        SimplePdfReportConfiguration reportConfiguration = new SimplePdfReportConfiguration();
        reportConfiguration.setSizePageToContent(true);
        reportConfiguration.setForceLineBreakPolicy(false);

        SimplePdfExporterConfiguration exportConfig = new SimplePdfExporterConfiguration();
        exportConfig.setMetadataAuthor(author);
        exportConfig.setEncrypted(true);
        exportConfig.setAllowedPermissionsHint("PRINTING");

        exporter.setConfiguration(reportConfiguration);
        exporter.setConfiguration(exportConfig);

        try {
            exporter.exportReport();
        } catch (JRException e) {
            e.printStackTrace();
        }

    }

    public void exportToXlsx(String fileName, String sheetName, OutputStream outputStream) {

        JRXlsxExporter exporter = new JRXlsxExporter();

        exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
        exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(outputStream));

        SimpleXlsxReportConfiguration reportConfiguration = new SimpleXlsxReportConfiguration();
        reportConfiguration.setSheetNames(new String[] { sheetName });
        reportConfiguration.setCollapseRowSpan(false);

        exporter.setConfiguration(reportConfiguration);

        try {
            exporter.exportReport();
        } catch (JRException e) {
            e.printStackTrace();
        }

    }
}
