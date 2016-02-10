package tn.welldone.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import tn.welldone.model.Contract;
import tn.welldone.model.Contract.ContractState;
import tn.welldone.model.ServiceProvider;
import tn.welldone.service.ContractBean;
import tn.welldone.service.InsuranceAgencyBean;

@Named("ContractController")
@RequestScoped
public class ContractController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4702932483504962891L;

	@EJB
	private ContractBean contractBean;

	@EJB
	private InsuranceAgencyBean insuranceAgencyBean;

	@Inject
	Logger log;

	private Contract contract = new Contract();

	private Contract selectedContract = new Contract();

	private ContractState[] contractStates;

	private ServiceProvider insuranceAgency = new ServiceProvider();

	private List<Contract> list;

	private List<ServiceProvider> insuranceAgencies;

	private Part file;

	@javax.annotation.PostConstruct
	public void init() {
		this.insuranceAgencies = insuranceAgencyBean.getInsuranceAgencies();
		this.contractStates = ContractState.values();
		list = contractBean.getContracts();
	}

	public String createNewContract() {
		return "addContract.faces";
	}

	public String createContract() throws IOException {
		contract.setInsuranceAgency(insuranceAgency);
		String fileName = contract.getInsuranceAgency().getCodeProvider() + "-"
				+ contract.getCodeContract() + "-" + this.generateNumber()
				+ getExtention(file);
		contract.setContractFile(fileName);
		contract = contractBean.addContract(contract, insuranceAgency);
		file.write("/var/www/mms/uploads/contract/" + fileName);
		return "listContracts.faces?faces-redirect=true";
	}

	private String generateNumber() {
		SecureRandom random = new SecureRandom();
		String rand = new BigInteger(130, random).toString(32);
		return rand;
	}

	private String getExtention(Part part) {
		String suffix = "";
		for (String cd : part.getHeader("content-disposition").split(";")) {
			if (cd.trim().startsWith("filename")) {
				String filename = cd.substring(cd.indexOf('=') + 1).trim()
						.replace("\"", "");
				if (filename.contains(".")) {

					suffix = filename.substring(filename.lastIndexOf('.'));
				}
				return suffix;
			}
		}
		return suffix;
	}

	public void downloadFile(int id) throws IOException {
		Contract c = contractBean.getContractById(id);
		String filePath = "/var/www/mms/uploads/contract/"
				+ c.getContractFile();
		log.log(Level.INFO, filePath);
		FacesContext facesContext = FacesContext.getCurrentInstance();
		HttpServletResponse response = (HttpServletResponse) facesContext
				.getExternalContext().getResponse();
		response.reset();
		response.setHeader("Content-Type", "application/pdf");
		response.setHeader("Content-Disposition",
				"attachment;filename=" + c.getContractFile());
		OutputStream responseOutputStream = response.getOutputStream();
		File file = new File(filePath);
		InputStream fis = new FileInputStream(file);
		byte[] bytesBuffer = new byte[2048];
		int bytesRead;
		while ((bytesRead = fis.read(bytesBuffer)) > 0) {
			responseOutputStream.write(bytesBuffer, 0, bytesRead);
		}
		responseOutputStream.flush();
		fis.close();
		responseOutputStream.close();
		facesContext.responseComplete();
	}

	public String showEditContract(int id) {
		Contract c = contractBean.getContractById(id);
		setSelectedContract(c);
		setInsuranceAgency(c.getInsuranceAgency());
		return "showEditContract.faces";
	}

	public String deleteContract(int id) {
		Contract c = contractBean.getContractById(id);
		contractBean.deleteContract(c);
		return "listContracts.faces?faces-redirect=true";
	}

	public String editContract() throws IOException {
		selectedContract.setInsuranceAgency(insuranceAgency);
		String fileName = selectedContract.getInsuranceAgency()
				.getCodeProvider()
				+ "-"
				+ selectedContract.getCodeContract()
				+ "-" + this.generateNumber() + getExtention(file);
		selectedContract.setContractFile(fileName);
		contractBean.editContract(selectedContract);
		file.write("/var/www/mms/uploads/contract/" + fileName);
		return "listContracts.faces?faces-redirect=true";
	}

	public String listContracts() {
		return "listContracts.faces?faces-redirect=true";
	}

	public ContractBean getContractBean() {
		return contractBean;
	}

	public void setContractBean(ContractBean contractBean) {
		this.contractBean = contractBean;
	}

	public Contract getContract() {
		return contract;
	}

	public void setContract(Contract contract) {
		this.contract = contract;
	}

	public List<Contract> getList() {
		return list;
	}

	public void setList(List<Contract> list) {
		this.list = list;
	}

	public Contract getSelectedContract() {
		return selectedContract;
	}

	public void setSelectedContract(Contract selectedContract) {
		this.selectedContract = selectedContract;
	}

	public InsuranceAgencyBean getInsuranceAgencyBean() {
		return insuranceAgencyBean;
	}

	public void setInsuranceAgencyBean(InsuranceAgencyBean insuranceAgencyBean) {
		this.insuranceAgencyBean = insuranceAgencyBean;
	}

	public ContractState[] getContractStates() {
		return contractStates;
	}

	public void setContractStates(ContractState[] contractStates) {
		this.contractStates = contractStates;
	}

	public List<ServiceProvider> getInsuranceAgencies() {
		return insuranceAgencies;
	}

	public void setInsuranceAgencies(List<ServiceProvider> insuranceAgencies) {
		this.insuranceAgencies = insuranceAgencies;
	}

	public ServiceProvider getInsuranceAgency() {
		return insuranceAgency;
	}

	public void setInsuranceAgency(ServiceProvider insuranceAgency) {
		this.insuranceAgency = insuranceAgency;
	}

	public Part getFile() {
		return file;
	}

	public void setFile(Part file) {
		this.file = file;
	}
}
