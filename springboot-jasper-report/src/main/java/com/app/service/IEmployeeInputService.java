package com.app.service;

import java.io.FileNotFoundException;
import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;

public interface IEmployeeInputService{

	JasperPrint exportReport()throws JRException,FileNotFoundException,IOException;
	JasperPrint exportTable() throws JRException,FileNotFoundException,IOException;
}
