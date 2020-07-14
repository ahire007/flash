package com.app.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import com.app.dao.IEmployeeInputDao;
import com.app.pojo.EmployeeInput;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRSaver;
@Service
public class EmployeeInputService implements IEmployeeInputService {
	@Autowired
	IEmployeeInputDao dao;
	
	@Override
	public JasperPrint exportReport() throws JRException,FileNotFoundException,IOException{
		// TODO Auto-generated method stub
		List<EmployeeInput> emp=dao.findAll();
		File file=ResourceUtils.getFile("classpath:employeeReport.jrxml");
		
		JasperReport jasperReport=JasperCompileManager.compileReport(file.getAbsolutePath());
		JRBeanCollectionDataSource dataSource=new JRBeanCollectionDataSource(emp);
		JRSaver.saveObject(jasperReport, "emp.jasper");
		Map<String,Object> parameters=new HashMap<>();
		parameters.put("createdBy", "cdac");
		JasperPrint jasperPrint=JasperFillManager.fillReport(jasperReport,parameters,dataSource);
		return jasperPrint;
	}

	@Override
	@SuppressWarnings("unchecked")
	public JasperPrint exportTable() throws JRException, FileNotFoundException, IOException {
		// TODO Auto-generated method stub
		List<EmployeeInput> emp=dao.findAll();
		File file=ResourceUtils.getFile("classpath:employee_input.jrxml");
		
		JasperReport jasperReport=JasperCompileManager.compileReport(file.getAbsolutePath());
		  JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(emp);
		  @SuppressWarnings("rawtypes")
			Map params = new HashMap();
	        params.put("datasource", ds);
	        params.put("Id", true);
	        params.put("FirstName", true);
	        params.put("LastName", true);
	        params.put("EmpType", false);
	        params.put("Designation", true);
	        params.put("Dob", true);
	        params.put("Doj", true);

	        JasperPrint jprint = JasperFillManager.fillReport(jasperReport,params, new JREmptyDataSource());
	        return jprint;
	}

}
