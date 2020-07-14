package com.app;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.app.dao.IEmployeeInputDao;
import com.app.pojo.EmployeeInput;
import com.app.service.IEmployeeInputService;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.HtmlExporter;
import net.sf.jasperreports.engine.export.JRCsvExporter;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRXmlExporter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleHtmlExporterOutput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfExporterConfiguration;
import net.sf.jasperreports.export.SimplePdfReportConfiguration;
import net.sf.jasperreports.export.SimpleWriterExporterOutput;
import net.sf.jasperreports.export.SimpleXlsxReportConfiguration;
import net.sf.jasperreports.export.SimpleXmlExporterOutput;

@SpringBootApplication

public class SpringbootJasperReportApplication {


	
	@Autowired
	private IEmployeeInputService Sr;
	
	@Autowired
	IEmployeeInputDao dao;
	

	@GetMapping("/getEmp")
	public List<EmployeeInput> getEmp()
	{
		
		return dao.findAll();
	}
	@GetMapping("/report/{reportFormat}")
	public void generateReport(@PathVariable String reportFormat,HttpServletResponse response) throws JRException, IOException 
	{
		JasperPrint jasperPrint=Sr.exportReport();
		  if(reportFormat.equalsIgnoreCase("pdf")) 
		  {
			

			    final OutputStream outStream = response.getOutputStream();
			    JRPdfExporter exporter=new JRPdfExporter();
			    exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
			    exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(outStream));
			    
			    SimplePdfReportConfiguration reportConfig=new SimplePdfReportConfiguration();
			    
			    reportConfig.setSizePageToContent(true);
			    reportConfig.setForceLineBreakPolicy(false);
			   
			    SimplePdfExporterConfiguration exportConfig= new SimplePdfExporterConfiguration();
			    exportConfig.setMetadataAuthor("CDAC-MUMBAI");
			    exportConfig.setEncrypted(true);
			  	exportConfig.setAllowedPermissionsHint("PRINTING");
			    exporter.setConfiguration(reportConfig);
			    exporter.setConfiguration(exportConfig);
			    exporter.exportReport();
			    response.setContentType("application/x-html");
			    response.setHeader("Content-disposition","inline; filename=\"employee.pdf\""); 
			   // request.setAttribute("exportIndentObject", exporter);
			    ByteArrayOutputStream outputByteArray = new ByteArrayOutputStream();
			    outStream.write(outputByteArray.toByteArray());
			   outStream.flush();
			   outStream.close();

	           // response.setRenderParameter("action", "searchProductsBegin");
			   
		  }
		 
		
		if(reportFormat.equalsIgnoreCase("xml"))
		{
			 response.setContentType("application/x-xml");
			 response.setHeader("Content-disposition", "inline; filename=employee.xml");
			  final OutputStream outStream = response.getOutputStream();
			  JRXmlExporter exporter=new JRXmlExporter();
			  
			  exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
			  exporter.setExporterOutput(new SimpleXmlExporterOutput(outStream));
			
			  exporter.exportReport();
			 
			  
		}
		 if(reportFormat.equalsIgnoreCase("html"))
	        {
			 	response.setContentType("application/x-html");
			    response.setHeader("Content-disposition", "inline; filename=employee.html");
			    final OutputStream outStream = response.getOutputStream();
	        	HtmlExporter exporter = new HtmlExporter();
	        	exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
	        	exporter.setExporterOutput(
	        	  new SimpleHtmlExporterOutput(outStream));
	        	 
	        	exporter.exportReport();
	        }
		 if(reportFormat.equalsIgnoreCase("xls"))
		 {		
			 	response.setContentType("application/x-xls");
			    response.setHeader("Content-disposition", "inline; filename=employee.xls");

			    final OutputStream outStream = response.getOutputStream();
			    JRXlsxExporter exporter = new JRXlsxExporter();
			  
			    exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
			    exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(outStream ));
			    
			    SimpleXlsxReportConfiguration reportConfig= new SimpleXlsxReportConfiguration();
			    
			    exporter.exportReport();
			    
			    
			
		 }
		 if(reportFormat.equalsIgnoreCase("csv"))
		 {
			 response.setContentType("application/x-csv");
			 response.setHeader("Content-disposition", "inline; filename=employee.csv");
			    final OutputStream outStream = response.getOutputStream();
			  JRCsvExporter exporter = new JRCsvExporter();
			  
			  exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
			  exporter.setExporterOutput(new SimpleWriterExporterOutput(outStream));			 
			
			
			 exporter.exportReport();
		 }
	}
	@GetMapping("/table/{reportFormat}")
	public void generateTable(@PathVariable String reportFormat,HttpServletResponse response,HttpServletRequest request) throws FileNotFoundException,JRException,ServletException,IOException
	{
		JasperPrint jasperPrint=Sr.exportTable();
		  if(reportFormat.equalsIgnoreCase("pdf")) 
		  {
			

			    final OutputStream outStream = response.getOutputStream();
			    JRPdfExporter exporter=new JRPdfExporter();
			    exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
			    exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(outStream));
			    
			    SimplePdfReportConfiguration reportConfig=new SimplePdfReportConfiguration();
			    
			    reportConfig.setSizePageToContent(true);
			    reportConfig.setForceLineBreakPolicy(false);
			   
			    SimplePdfExporterConfiguration exportConfig= new SimplePdfExporterConfiguration();
			    exportConfig.setMetadataAuthor("CDAC-MUMBAI");
			    exportConfig.setEncrypted(true);
			  	exportConfig.setAllowedPermissionsHint("PRINTING");
			    exporter.setConfiguration(reportConfig);
			    exporter.setConfiguration(exportConfig);
			    exporter.exportReport();
			    response.setContentType("application/x-html");
			    response.setHeader("Content-disposition","inline; filename=\"order.pdf\""); 
			    request.setAttribute("exportIndentObject", exporter);
			    ByteArrayOutputStream outputByteArray = new ByteArrayOutputStream();
			    outStream.write(outputByteArray.toByteArray());
			   outStream.flush();
			   outStream.close();

	           // response.setRenderParameter("action", "searchProductsBegin");
			   
		  }

			if(reportFormat.equalsIgnoreCase("xml"))
			{
				 response.setContentType("application/x-xml");
				 response.setHeader("Content-disposition", "inline; filename=employee.xml");
				  final OutputStream outStream = response.getOutputStream();
				  JRXmlExporter exporter=new JRXmlExporter();
				  
				  exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
				  exporter.setExporterOutput(new SimpleXmlExporterOutput(outStream));
				
				  exporter.exportReport();
				 
				  
			}
			 if(reportFormat.equalsIgnoreCase("html"))
		        {
				 	response.setContentType("application/x-html");
				    response.setHeader("Content-disposition", "inline; filename=employee.html");
				    final OutputStream outStream = response.getOutputStream();
		        	HtmlExporter exporter = new HtmlExporter();
		        	exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
		        	exporter.setExporterOutput(
		        	  new SimpleHtmlExporterOutput(outStream));
		        	 
		        	exporter.exportReport();
		        }
			 if(reportFormat.equalsIgnoreCase("xls"))
			 {		
				 	response.setContentType("application/x-xls");
				    response.setHeader("Content-disposition", "inline; filename=employee.xls");

				    final OutputStream outStream = response.getOutputStream();
				    JRXlsxExporter exporter = new JRXlsxExporter();
				  
				    exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
				    exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(outStream ));
				    
				    SimpleXlsxReportConfiguration reportConfig= new SimpleXlsxReportConfiguration();
				    reportConfig.setSheetNames(new String[] { "employee Data" });
				    reportConfig.setOnePagePerSheet(true);
				    reportConfig.setWhitePageBackground(false);
			
				    reportConfig.setRemoveEmptySpaceBetweenRows(true);
				    reportConfig.setRemoveEmptySpaceBetweenColumns(true);
				    reportConfig.setDetectCellType(true);
				    reportConfig.setIgnorePageMargins(true);
				    reportConfig.setCollapseRowSpan(true);
				    reportConfig.setCellHidden(true);
				    reportConfig.setFreezeRow(4);
				    reportConfig.setWrapText(true);
				    reportConfig.setAutoFitPageHeight(true);
				    reportConfig.setShrinkToFit(true);
				    exporter.setConfiguration(reportConfig);
				    exporter.exportReport();
				    
				    
				
			 }
			 if(reportFormat.equalsIgnoreCase("csv"))
			 {
				 response.setContentType("application/x-csv");
				 response.setHeader("Content-disposition", "inline; filename=employee.csv");
				    final OutputStream outStream = response.getOutputStream();
				  JRCsvExporter exporter = new JRCsvExporter();
				  
				  exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
				  exporter.setExporterOutput(new SimpleWriterExporterOutput(outStream));			 
				
				
				 exporter.exportReport();
			 }
	}
	
	
	public static void main(String[] args) {
		SpringApplication.run(SpringbootJasperReportApplication.class, args);
	}

}
