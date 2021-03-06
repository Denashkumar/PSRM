package com.splwg.cm.domain.testcase;

import java.math.BigInteger;
import java.util.GregorianCalendar;

import org.junit.Test;

import com.ibm.icu.math.BigDecimal;
import com.splwg.base.api.QueryIterator;
import com.splwg.base.api.businessObject.BusinessObjectDispatcher;
import com.splwg.base.api.businessObject.BusinessObjectInstance;
import com.splwg.base.api.businessObject.COTSInstanceList;
import com.splwg.base.api.businessObject.COTSInstanceListNode;
import com.splwg.base.api.businessService.BusinessServiceDispatcher;
import com.splwg.base.api.businessService.BusinessServiceInstance;
import com.splwg.base.api.datatypes.Bool;
import com.splwg.base.api.datatypes.Date;
import com.splwg.base.api.sql.PreparedStatement;
import com.splwg.base.api.sql.SQLResultRow;
import com.splwg.base.api.testers.ContextTestCase;
import com.splwg.base.domain.common.businessObject.BusinessObject;
import com.splwg.base.domain.common.businessObject.BusinessObject_Id;
import com.splwg.base.domain.common.characteristicType.CharacteristicType_Id;
import com.splwg.base.domain.todo.role.Role;
import com.splwg.base.domain.todo.role.Role_Id;
import com.splwg.tax.domain.admin.filingCalendar.FilingCalendar_Id;
import com.splwg.tax.domain.admin.filingCalendar.FilingPeriod_Id;
import com.splwg.tax.domain.customerinfo.person.Person;
import com.splwg.tax.domain.customerinfo.person.Person_Id;
import com.splwg.tax.domain.customerinfo.serviceAgreement.ServiceAgreement_Id;
import com.splwg.tax.domain.processFlow.ProcessFlowCharacteristic;
import com.splwg.tax.domain.processFlow.ProcessFlowCharacteristic_DTO;
import com.splwg.tax.domain.processFlow.ProcessFlowCharacteristic_Id;
import com.splwg.tax.domain.processFlow.ProcessFlow_Id;

public class ToDoTest extends ContextTestCase {

	@Test
	public void test() {
				
		startChanges();
		
		BusinessServiceInstance bsInstancee = BusinessServiceInstance.create("CM-PAYDNS");//8629186835

		bsInstancee.set("perId", "3428975080");//results/chkBox
		bsInstancee = BusinessServiceDispatcher.execute(bsInstancee);
		COTSInstanceList listt = bsInstancee.getList("results");
		Bool checkBoxValue = Bool.FALSE;
		if (!listt.isEmpty()) {
			COTSInstanceListNode nextElt = listt.iterator().next();
			if (nextElt != null) {
				nextElt.getBoolean("chkBox");//results/montantTotal
				System.out.println("montantTotal:: " + nextElt.getString("montantTotal"));
				System.out.println("check Box: " + nextElt.getBoolean("chkBox"));
				checkBoxValue = nextElt.getBoolean("chkBox");
			}

		}
		
		BusinessServiceInstance businessServiceInstance = BusinessServiceInstance.create("F1-AddToDoEntry");
		Role_Id toDoRoleId = new Role_Id("CM-REGTODO");//CMRDNS
		
		ProcessFlow_Id processFlowId = new ProcessFlow_Id("90133679228305");
		System.out.println(processFlowId.getIdValue());
		Role toDoRole = toDoRoleId.getEntity();
		businessServiceInstance.getFieldAndMDForPath("sendTo").setXMLValue("SNDR");
		businessServiceInstance.getFieldAndMDForPath("subject").setXMLValue("Batch Update from PSRM");
		businessServiceInstance.getFieldAndMDForPath("toDoType").setXMLValue("CM-REGTO");//CMDNS
		businessServiceInstance.getFieldAndMDForPath("assignedUser").setXMLValue("DENASH");
		businessServiceInstance.getFieldAndMDForPath("toDoRole").setXMLValue(toDoRole.getId().getTrimmedValue());
		businessServiceInstance.getFieldAndMDForPath("drillKey1").setXMLValue("90133679228305");//963579919834
		businessServiceInstance.getFieldAndMDForPath("messageCategory").setXMLValue("90007");//90000
		businessServiceInstance.getFieldAndMDForPath("messageNumber").setXMLValue("301");//10002
		businessServiceInstance.getFieldAndMDForPath("messageParm1").setXMLValue("denash.kumar@5iapps");
		businessServiceInstance.getFieldAndMDForPath("messageParm2").setXMLValue(String.valueOf(908070));
		businessServiceInstance.getFieldAndMDForPath("messageParm3").setXMLValue("Reg.CSV");//SONATEL
		businessServiceInstance.getFieldAndMDForPath("sortKey1").setXMLValue("90133679228305");
		BusinessServiceDispatcher.execute(businessServiceInstance);
		saveChanges();
		getSession().commit();
		
		String query = "select ENTITY_NAME Nom_Employeur, Tab_Dates.ANNEE ANNEE, Tab_Dates.DEBUT DEBUT,"
				+ " Tab_Dates.FIN FIN, SUM(CAST (Tab_Salaire.Salaire_Periode as NUMBER(20))) TOTAL_SALAIRE"
				+ " from (select distinct id_employeur Employeur, substr(TO_CHAR(date_debut_periode_cotisation,'DD/MM/YYYY'),-2)"
				+ " Annee, min (date_debut_periode_cotisation) Debut, max(date_fin_periode_cotisation) Fin"
				+ " from cm_dmt_historique where NUMERO_PIECE = '12475841756235' group by id_employeur,"
				+ " substr(TO_CHAR(date_debut_periode_cotisation,'DD/MM/YYYY'),-2) order by Annee) Tab_Dates,"
				+ "(select distinct id_employeur Employeur, substr(TO_CHAR(date_debut_periode_cotisation,'DD/MM/YYYY'),-2)"
				+ " Annee, date_debut_periode_cotisation Debut, date_fin_periode_cotisation Fin,"
				+ " salaire_contractuel * (substr(TO_CHAR(date_fin_periode_cotisation,'DD/MM/YYYY'),4,2)-substr(TO_CHAR(date_debut_periode_cotisation, 'DD/MM/YYYY'),4,2)+1) Salaire_Periode"
				+ " from cm_dmt_historique where NUMERO_PIECE = '12475841756235' order by Annee) Tab_Salaire, ci_per_name"
				+ " where Tab_Dates.Annee = Tab_Salaire.Annee and Tab_Dates.Employeur = Tab_Salaire.Employeur"
				+ " and per_id = Tab_Salaire.Employeur group by ENTITY_NAME, Tab_Dates.Annee, Tab_Dates.Debut, Tab_Dates.Fin order by Tab_Dates.Debut";
				PreparedStatement preparedStatementt = createPreparedStatement(query, "SELECT");
				QueryIterator<SQLResultRow> resultIterator = null;
				try {
				resultIterator = preparedStatementt.iterate();
				while (resultIterator.hasNext()) {
				SQLResultRow result = (SQLResultRow) resultIterator.next();
				
				result.getString("NOM_EMPLOYEUR");
				result.getString("ANNEE");
				result.getDate("DEBUT");
				result.getDate("FIN");
				result.getBigDecimal("TOTAL_SALAIRE"); 
				}
				} catch(Exception e) {
				e.printStackTrace();
				} 
		
		
		GregorianCalendar gregorianCalendar1 = new GregorianCalendar(); 
		Date currentDate1 = new Date(gregorianCalendar1.get(GregorianCalendar.YEAR),
				gregorianCalendar1.get(GregorianCalendar.MONTH), gregorianCalendar1.get(GregorianCalendar.DAY_OF_MONTH));
		System.out.println(currentDate1);

		PreparedStatement preparedStatement = createPreparedStatement("select BEGIN_DT from "
				+ "CI_FILING_CAL_PERIOD where filing_cal_cd = 'CM-BILLCAL' and BEGIN_DT = TRUNC(sysdate)",
				"SELECT");
		/*preparedStatement.bindString("factorCharValue", factorCharValue, null);
		preparedStatement.bindString("factor", factorId, null);
		preparedStatement.bindString("effectiveDate", effectiveDate, null);*/
		preparedStatement.setAutoclose(false);
		BigDecimal factorValue = new BigDecimal("130213.19128693462");
		factorValue = factorValue.setScale(0, BigDecimal.ROUND_UP);
		//int factorValueI = BigDecimal.ROUND_UP;
		System.out.println("Round:: " + factorValue);
		QueryIterator<SQLResultRow> result = null;

		try {
			result = preparedStatement.iterate();
			while (result.hasNext()) {
				SQLResultRow lookUpValue = result.next();
				System.out.println(lookUpValue.getString("BEGIN_DT"));
				
				String fac = lookUpValue.getString("BEGIN_DT");
			}
		} catch (Exception exception) {
			exception.printStackTrace();
		} finally {
			preparedStatement.close();
			preparedStatement = null;
			result.close();
		}
		
		
		BigDecimal detailValue = BigDecimal.TEN;
		System.out.println("detailValue:: " + detailValue.multiply(BigDecimal.valueOf(3)));
		BigDecimal detailValue1 =  detailValue;
		System.out.println(detailValue1);
		GregorianCalendar gregorianCalendar = new GregorianCalendar(); 
		Date currentDate = new Date(gregorianCalendar.get(GregorianCalendar.YEAR),
				gregorianCalendar.get(GregorianCalendar.MONTH), gregorianCalendar.get(GregorianCalendar.DAY_OF_MONTH));
		System.out.println("currentDate:: " + currentDate);
		ProcessFlowCharacteristic_DTO processFlowCharacteristic_DTO = createDTO(ProcessFlowCharacteristic.class);
		ProcessFlowCharacteristic_Id pp = new ProcessFlowCharacteristic_Id(new ProcessFlow_Id("04440845150240"),
				new CharacteristicType_Id("CM-BILID"), new BigInteger("1"));
		processFlowCharacteristic_DTO.setId(pp);
		processFlowCharacteristic_DTO.setCharacteristicValueForeignKey1("442153335507");
		processFlowCharacteristic_DTO.newEntity();
		
		ProcessFlowCharacteristic_DTO processFlowCharacteristi_DTO = createDTO(ProcessFlowCharacteristic.class);
		ProcessFlowCharacteristic_Id pp1 = new ProcessFlowCharacteristic_Id(new ProcessFlow_Id("04440845150240"),
				new CharacteristicType_Id("CM-STAT"), new BigInteger("1"));
		processFlowCharacteristi_DTO.setId(pp1);
		processFlowCharacteristi_DTO.setCharacteristicValue("INPROGRESS");
		processFlowCharacteristi_DTO.newEntity();
		
		
		try {
			BusinessServiceInstance businessServiceInstancee = BusinessServiceInstance.create("CM-BILLCOMPLETE");
			businessServiceInstancee.set("billId", "862918605045");
			BusinessServiceDispatcher.execute(businessServiceInstancee);
			COTSInstanceList list = businessServiceInstancee.getList("results");
			System.out.println("Result: " + list.isEmpty());
			System.out.println("I have done my job");
		} catch(Exception exception) {
			exception.printStackTrace();
		}
		
		Date endDate = new Date(2018, 12, 31);
		 FilingCalendar_Id filingCalender = new FilingCalendar_Id("REALPROP");
			FilingPeriod_Id filingPeriod = new FilingPeriod_Id(filingCalender, endDate);
		
		/*	TaxBill_DTO billDTO = createDTO(TaxBill.class);
		    billDTO.setTaxRoleId(new TaxRole_Id("8629186299"));
		    billDTO.setTaxBillTypeId(new TaxBillType_Id("PRENATAL BILL"));
		    billDTO.setFilingPeriodId(filingPeriod);
		    billDTO.setTaxBillStartDate(new Date(2018, 01, 01));//current date
		    billDTO.setTaxBillEndDate(new Date(2018, 01, 01));//current date
		    billDTO.setBusinessObjectId(new BusinessObject_Id("CM-TaxBillAndPrint"));//make all the BUS_OBJ_CD as soft parameter
		    billDTO.setCalculationControlVersionId(new CalculationControlVersion_Id(new CalculationControl_Id("PRENATAL CONTROL"), new Date(2018, 8, 01)));
		    billDTO.setServiceAgreementId(new ServiceAgreement_Id("8629186152"));
		    billDTO.setTaxYear(BigInteger.valueOf(2018));
		    billDTO.setMonitorControlDate(currentDate);
		    billDTO.setAutomaticProcessing(AutomaticProcessingLookup.constants.AUTO_PROCESS);
		    billDTO.newEntity();*/
		    //billDTO.setStatus("READYTOCOMP");
		    //billDTO.getEntity().setDTO(billDTO);
		    
		    try {
		    	BusinessObject businessObject = new BusinessObject_Id("CM-TaxBillAndPrint").getEntity();
				BusinessObjectInstance boi = BusinessObjectInstance.create(businessObject);		
				boi.set("taxBillId", "862918605045");//boStatus		
				BusinessObjectInstance dispatchedBoi = BusinessObjectDispatcher.read(boi);
				System.out.println("BO_Schema" +   dispatchedBoi.getDocument().asXML());
				
				dispatchedBoi.set("boStatus", "GENERATED");
				dispatchedBoi = BusinessObjectDispatcher.update(dispatchedBoi);//calcControl
			    System.out.println("#### BO Instance Schema after VALIDATE: " +dispatchedBoi.getDocument().asXML());
				
				
				dispatchedBoi.set("boStatus", "READYTOCOMP");
				dispatchedBoi = BusinessObjectDispatcher.update(dispatchedBoi);//calcControl
			    System.out.println("#### BO Instance Schema after VALIDATE: " +dispatchedBoi.getDocument().asXML());
				  
			    dispatchedBoi.set("boStatus", "COMPLETED");
			    dispatchedBoi = BusinessObjectDispatcher.update(dispatchedBoi);
			    System.out.println("#### BO Instance Schema after VALIDATE: " +dispatchedBoi.getDocument().asXML());
				
				
			} catch (Exception exception) {
				System.out.println("Exception in Bill generation and competion" + exception);
			}
			saveChanges();
			getSession().commit();
			
		  //  System.out.println(billDTO.getEntity().getId());
		
		String factorId = "CM-PRENATAL";
		String effectiveDate = "2018-08-01";
		String factorCharValue = "DOCUMENT2";

			/*PreparedStatement preparedStatement = createPreparedStatement(
					"SELECT FACTOR_VAL FROM C1_FACTOR_VALUE where FACTOR_CHAR_VAL=\'"+factorCharValue+"\' and FACTOR_CD=\'"+factorId+"\' and TO_CHAR(EFFDT,'YYYY-MM-DD') <=\'"+effectiveDate+"\' order by EFFDT DESC",
					"SELECT");
			preparedStatement.bindString("factorCharValue", factorCharValue, null);
			preparedStatement.bindString("factor", factorId, null);
			preparedStatement.bindString("effectiveDate", effectiveDate, null);
			preparedStatement.setAutoclose(false);
			BigDecimal factorValue = BigDecimal.ZERO;
			QueryIterator<SQLResultRow> result = null;

			try {
				result = preparedStatement.iterate();
				while (result.hasNext()) {
					SQLResultRow lookUpValue = result.next();
					System.out.println(lookUpValue.getString("FACTOR_VAL"));
					
					String fac = lookUpValue.getString("FACTOR_VAL");
					factorValue = BigDecimal.valueOf(Double.valueOf(fac));
				}
			} catch (Exception exception) {
				exception.printStackTrace();
			} finally {
				preparedStatement.close();
				preparedStatement = null;
				result.close();
			}
		System.out.println("factorValue: " + factorValue);*/
		
		
		ServiceAgreement_Id saId = new ServiceAgreement_Id("0959861947");
		System.out.println(saId.getEntity().getServiceAgreementType().getId().getSaType());
		PreparedStatement preparedStatements = createPreparedStatement(
				"SELECT FACTOR_VAL FROM C1_FACTOR_VALUE where FACTOR_CHAR_VAL='DOCUMENT1' and FACTOR_CD='CM-PRENATAL' and EFFDT <='2018-08-01' order by EFFDT DESC",
				"SELECT");
		//preparedStatements.bindString("factorCharValue", "DOCUMENT1", null);
		//preparedStatements.bindString("factor", "CM-PRENATAL", null);
		//preparedStatements.bindString("effectiveDate", "2018-08-01", null);
		SQLResultRow sqlResultRow = preparedStatements.firstRow();
		System.out.println(sqlResultRow.getString("FACTOR_VAL"));
		
		BusinessServiceInstance bsInstance = BusinessServiceInstance.create("C1-TXRLDIVS");//8629186835

		bsInstance.set("taxRoleId", "8629186835");
		bsInstance = BusinessServiceDispatcher.execute(bsInstance);
		COTSInstanceList list = bsInstance.getList("results");
		String resultat = null;
		if (!list.isEmpty()) {
			COTSInstanceListNode nextElt = list.iterator().next();
			if (nextElt != null) {
				System.out.println("Division: " + nextElt.getString("division"));
				System.out.println("Description: " + nextElt.getString("description"));
				resultat = nextElt.getString("division");
			}

		}
		
		System.out.println(resultat);

		String listeAccounts = null;
		// Business Service Instance
		/*BusinessServiceInstance bsInstance = BusinessServiceInstance.create("C1-GetPersonAccounts");

		bsInstance.set("personId", "5527931408");
		bsInstance = BusinessServiceDispatcher.execute(bsInstance);

		Iterator<COTSInstanceListNode> iterator = bsInstance.getList("results").iterator();
		while (iterator.hasNext()) {
			COTSInstanceListNode nextElt = iterator.next();
			System.out.println("AccountId: " + nextElt.getXMLString("accountId"));
			
			System.out.println("AccountInfo: " + nextElt.getString("accountInfo"));
			listeAccounts = nextElt.getXMLString("accountId");

		}
		System.out.println(listeAccounts);
		Account acc = new Account_Id(listeAccounts).getEntity();
		System.out.println(acc.getCurrency());
	    System.out.println(new Account_Id(listeAccounts).getEntity().getCurrency().toString());
		System.out.println(new Account_Id("0459358013").getEntity().getCurrency());
		String cccc = new Account_Id("0459358013").getEntity().getId().getIdValue();*/
		  
		
		
		/*BusinessServiceInstance bsInstance = BusinessServiceInstance.create("C1-PersonSearchByIdTypeNumber");
		  String personId = "";
		  bsInstance.set("idType", "NIN" );
		  bsInstance.set("idNumber", "97534689" );
		  bsInstance = BusinessServiceDispatcher.execute(bsInstance);
		  
		     // Getting the list of results
	        COTSInstanceList list = bsInstance.getList("results");
	        
	        // If list IS NOT empty
	        if(!list.isEmpty()){

	            // Get the first result
	            COTSInstanceListNode firstRow = list.iterator().next();
	            
	            // Return the person entity
	            System.out.println(firstRow.getString("personId"));
	            
	        }

		  Iterator<COTSInstanceListNode> iterator = bsInstance.getList("results").iterator();
		  while (iterator.hasNext()) {
		   COTSInstanceListNode nextElt = iterator.next();
		   //System.out.println("AccountId: " + nextElt.getNumber("accountId"));
		   System.out.println("PersonId: " + nextElt.getString("personId"));

		  }
		*/
		
		Date listeSequences = null;
		/*String valuationType = "CM-PRENATAL";
		String query = "SELECT EFFDT FROM C1_FACTOR_VALUE where FACTOR_CD= \'"+valuationType+"\' order by EFFDT DESC";
		PreparedStatement preparedStatement = createPreparedStatement(query, "SELECT");
		SQLResultRow sql = preparedStatement.firstRow();
		listeSequences = sql.getDate("EFFDT");
		System.out.println("listeSequences:: " + String.valueOf(listeSequences));
		BusinessServiceInstance bsInstance1 = BusinessServiceInstance.create("C1-FactorValueRetriever");

		COTSInstanceNode group = bsInstance1.getGroupFromPath("input");
		//COTSInstanceListNode firstRow = group.getList("newAdjustments").newChild();
		// COTSInstanceListNode firstRow = list.iterator().next();
		group.set("factor", "CM-PRENATAL");
		group.setXMLString("effectiveDate", "2018-08-01");	
		group.setXMLString("factorCharValue", "DOCUMENT1");		
		bsInstance1 = BusinessServiceDispatcher.execute(bsInstance1);
		COTSInstanceNode output = bsInstance1.getGroupFromPath("output");
		System.out.println(output.getXMLString("factorValue"));*/
		
		/*GregorianCalendar gregorianCalendar=new GregorianCalendar(); 
		gregorianCalendar.get(GregorianCalendar.MONTH);
		gregorianCalendar.get(GregorianCalendar.DAY_OF_MONTH);
		gregorianCalendar.get(GregorianCalendar.YEAR);
		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy,MM,dd");  
		   LocalDateTime noww = LocalDateTime.now();  
		   System.out.println(dtf.format(noww));  
		Date endDate = new Date(2017, 1, 1);//12-31-2018//C1-OBLTAXR//C1-FactorValueRetriever
		
		BusinessServiceInstance bsInstance1 = BusinessServiceInstance.create("C1-FactorValueRetriever");

		COTSInstanceNode group = bsInstance1.getGroupFromPath("input");
		//COTSInstanceListNode firstRow = group.getList("newAdjustments").newChild();
		// COTSInstanceListNode firstRow = list.iterator().next();
		group.set("factor", "PLF_IPRES_CRRG_01");
		group.setXMLString("effectiveDate", "2017-01-01");
		bsInstance1 = BusinessServiceDispatcher.execute(bsInstance1);
		COTSInstanceNode output = bsInstance1.getGroupFromPath("output");
		System.out.println(output.getXMLString("factorValue"));
		

		Date listeSequences = null;
		String valuationType = "COUNTYPROPTAX";
		String query = "SELECT EFFDT FROM C1_FACTOR_VALUE where FACTOR_CD= \'"+valuationType+"\' order by EFFDT DESC";
		PreparedStatement preparedStatement = createPreparedStatement(query, "SELECT");
		SQLResultRow sql = preparedStatement.firstRow();
		listeSequences = sql.getDate("EFFDT");
		
		while (iter1.hasNext()) {
			SQLResultRow result = (SQLResultRow) iter1.next();
			listeSequences = result.getDate("EFFDT");
		}
   
		
		
		BusinessServiceInstance bsInstance1 = BusinessServiceInstance.create("C1-OBLTAXR");

		bsInstance1.set("accountId", "1759812828");
		bsInstance1.set("taxRoleId", "1759812078");
		bsInstance1 = BusinessServiceDispatcher.execute(bsInstance1);
		List<String> oblist = new ArrayList<String>();
		List<String> oblist1 = new ArrayList<String>();
		Iterator<COTSInstanceListNode> iterator1 = bsInstance1.getList("results").iterator();
		while (iterator1.hasNext()) {
			COTSInstanceListNode nextElt = iterator1.next();
			System.out.println("obligationsId: " + nextElt.getXMLString("obligationsId"));
			System.out.println("obligationsInfo: " + nextElt.getString("obligationsInfo"));
			oblist.add(nextElt.getXMLString("obligationsId"));
			oblist1.add(nextElt.getXMLString("obligationsInfo"));
		}
		
		System.out.println(oblist);
		System.out.println(oblist1);
		
		BigDecimal detailValue =  BigDecimal.ZERO;
		
		new CmPersonSearchComponent.Factory();
		CmPersonSearchComponent perSearch = Factory.newInstance();
		IdType_Id idType = new IdType_Id("NIN");
		// log.info("*****ID Type: " + idType.getTrimmedValue());
		Person person = perSearch.searchPerson(idType.getEntity(), "22656265243623");
		String personId = person.getId().getIdValue();
		
		
		
		//12-31-2018
		Asset_DTO asset = createDTO(Asset.class);
	    asset.setAssetTypeId(new AssetType_Id("PRENATAL"));//getAssetType()
	    asset.setStatus("ACTIVE");
	    asset.setBusinessObjectId(new BusinessObject_Id("C1-RealPropertyAsset"));//getAssetBusinessObjectId()
	    asset.setCreDt(endDate);
	    asset.newEntity();
	    
	    Asset_Id ass = asset.getEntity().getId();
	    
	    System.out.println("ass: " +  ass.getIdValue());
	    
		java.util.Date now = new java.util.Date();
		int year = now.getYear();
		
		//check the existence of tax type(ServiceTypeId) for this account
		
		TaxRole_DTO taxRole = createDTO(TaxRole.class);
	    taxRole.setAccountId(new Account_Id("0459358013"));
	    taxRole.setAssetId(asset.getEntity().getId());
	    taxRole.setServiceTypeId(new ServiceType_Id("PRENATAL"));
	    taxRole.setStartDate(new Date(2018, 01, 01));
	    taxRole.setBusinessObjectId(new BusinessObject_Id("C1-TaxRoleAsset"));               
	    taxRole.newEntity();
	    
	    System.out.println(taxRole.getEntity().getId());
		
		Valuation_DTO valuationDTO = createDTO(Valuation.class);
	    valuationDTO.setValuationTypeId(new ValuationType_Id("BENEFIT VALUATION"));
		valuationDTO.setAssetId(asset.getEntity().getId());
		valuationDTO.setValuationDate(endDate);
		valuationDTO.setFilingPeriodId(filingPeriod);
		valuationDTO.setBusinessObjectId(new BusinessObject_Id("C1-Valuation"));
		valuationDTO.setStatus("ACTIVE");
		valuationDTO.setTaxYear(BigInteger.valueOf(year));
		valuationDTO.newEntity();
		
		System.out.println(valuationDTO.getEntity().getId().toString());
		    
	    ValuationDetail_DTO valuationDetailDTO = createDTO(ValuationDetail.class);	    
		BigDecimal detailValue = new BigDecimal("2250");
		valuationDetailDTO.setDetailValue(detailValue);
	
		valuationDetailDTO.setCurrencyId(new Currency_Id("XOF"));
		valuationDetailDTO.setValueDetailTypeId(new ValueDetailType_Id("PRENATAL VALUATION"));
		valuationDetailDTO.setId(new ValuationDetail_Id(valuationDTO.getEntity().getId(), BigInteger.valueOf(1)));
		valuationDetailDTO.newEntity();
		
		System.out.println(valuationDetailDTO.getEntity().getId().toString());
		
		
		//Find the obligation linked to this acc of type and tax role id and which is Active
	    ServiceAgreement_DTO obligation = createDTO(ServiceAgreement.class);
	    obligation.setAccountId(new Account_Id("0459358013"));
	    obligation.setTaxRoleId(taxRole.getEntity().getId());
	    obligation.setServiceAgreementTypeId(new ServiceAgreementType_Id(new CisDivision_Id("CSS"), "PRENATAL"));
	    obligation.setFilingPeriodId(filingPeriod);
	    obligation.setStartDate(new Date(2018, 01, 01));//current date
	    obligation.setEndDate(new Date(2018, 12, 31));//remove this
	    obligation.setStatus(ServiceAgreementStatusLookup.constants.PENDING_START);
	    obligation.newEntity();
	    
	    System.out.println(obligation.getEntity().getId());
	    
		/*BusinessServiceInstance bsInstance = BusinessServiceInstance.create("C1-PRAT-ACCT");
		bsInstance.set("personId", "0323502602");
		bsInstance.set("accountType", "ATM/PF/V");
		bsInstance = BusinessServiceDispatcher.execute(bsInstance);

		// Getting the list of results
		COTSInstanceList list = bsInstance.getList("results");

		// If list IS NOT empty
		if (!list.isEmpty()) {

			// Get the first result
			COTSInstanceListNode firstRow = list.iterator().next();
			System.out.println(firstRow.getXMLString("accountId"));

			// Return the person entity

		}*/

	
		
		
		

		//BusinessServiceDispatcher.execute(businessServiceInstance);
	
		
	        
	        // Business Service Instance
	       // BusinessServiceInstance bsInstance = BusinessServiceInstance.create("C1-GETRELPER");
	        
	        //Person_Id person = new Person_Id("3547540160");
	        //bsInstance.getFieldAndMDForPath("person").setXMLValue(person);
	        //bsInstance.set("person", new Person_id(person));
	        
	        // Execute BS and return the person Id if exists
	         //executeBSAndRetrievePerson(bsInstance);
	        
		
	}
	
	private Person executeBSAndRetrievePerson(BusinessServiceInstance bsInstance) {

        // Executing BS
        bsInstance = BusinessServiceDispatcher.execute(bsInstance);
        
        // Getting the list of results
        COTSInstanceList list = bsInstance.getList("results");
        
        // If list IS NOT empty
        if(!list.isEmpty()){

            // Get the first result
            COTSInstanceListNode firstRow = list.iterator().next();
            
            // Return the person entity
            return new Person_Id(firstRow.getString("perId")).getEntity();
            
        }
        
        return null;
    }
	
	
	
	/*@Test
	public void givenUsingJDK7Nio2_whenMovingFile_thenCorrect() throws IOException {
		String fineName = "";
	    Path fileToMovePath = Files.createFile(Paths.get("D:\\PSRM\\Bala\\cnt1790655.ppt"));
	    Path targetPath = Paths.get("D:\\PSRM\\");
	 
	    Files.move(fileToMovePath, targetPath.resolve(fileToMovePath.getFileName()));
	}*/

}
