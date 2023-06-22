package com.cibertec.edu.services;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cibertec.edu.models.ProductoReport;
import com.cibertec.edu.models.Productos;
import com.cibertec.edu.repositories.ProductoDao;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;




@Service
public class ProductoServiceImpl implements ProductoService {
	
	@Autowired
	private ProductoDao productoRepository;
	

	@Override
	public List<Productos> getAllProductos() {
		// TODO Auto-generated method stub
		return this.productoRepository.findAll();
	}


	@Override
	public InputStream getReportProductos() throws Exception {
		try {
			List<Productos> listaProductos = this.getAllProductos();
			List<ProductoReport> listaData = new ArrayList<ProductoReport>();
			
			listaData.add(new ProductoReport());
			listaData.get(0).setProductosList(listaProductos);
			JRBeanCollectionDataSource dts = new JRBeanCollectionDataSource(listaData);
			
			Map<String,Object> parameters = new HashMap<>();
			parameters.put("IMAGE_PATH", "https://www.google.com/url?sa=i&url=https%3A%2F%2Fes.wikipedia.org%2Fwiki%2FComercio_electr%25C3%25B3nico&psig=AOvVaw2l4wkaZTUtR7_9HpvaLV_v&ust=1687521117467000&source=images&cd=vfe&ved=0CBEQjRxqFwoTCNDS3JXo1v8CFQAAAAAdAAAAABAE");
			
			JasperReport jasperReportObj = getJasperReportCompiled();
			JasperPrint jPrint = JasperFillManager.fillReport(jasperReportObj, parameters, dts);
			InputStream result = new ByteArrayInputStream(JasperExportManager.exportReportToPdf(jPrint));
			return result;
			
		} catch (JRException ex) {
			throw ex;
		}
	}
	
	private JasperReport getJasperReportCompiled() {
		try {
			InputStream productoReportStream = getClass().getResourceAsStream("/jasper/productos_report.jrxml");
			JasperReport jasper = JasperCompileManager.compileReport(productoReportStream);
			return jasper;
		} catch (Exception e){
			return null;
		}
	}

	
	

}
