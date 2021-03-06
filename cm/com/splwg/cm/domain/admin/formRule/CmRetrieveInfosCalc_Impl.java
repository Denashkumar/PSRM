package com.splwg.cm.domain.admin.formRule;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.splwg.base.api.businessObject.BusinessObjectDispatcher;
import com.splwg.base.api.businessObject.BusinessObjectInstance;
import com.splwg.base.api.businessObject.COTSInstanceListNode;
import com.splwg.base.api.businessObject.COTSInstanceNode;
import com.splwg.base.api.businessObject.SchemaInstance;
import com.splwg.base.api.businessService.BusinessServiceDispatcher;
import com.splwg.base.api.businessService.BusinessServiceInstance;
import com.splwg.base.api.sql.PreparedStatement;
import com.splwg.base.api.sql.SQLResultRow;
import com.splwg.base.support.schema.BusinessObjectInfo;
import com.splwg.base.support.schema.MaintenanceObjectInfo;
import com.splwg.base.support.schema.MaintenanceObjectInfoCache;
import com.splwg.cm.domain.common.businessComponent.CmPersonSearchComponent;
import com.splwg.cm.domain.customMessages.CmMessageRepository90000;
import com.splwg.tax.domain.admin.formRule.ApplyFormRuleAlgorithmInputData;
import com.splwg.tax.domain.admin.formRule.ApplyFormRuleAlgorithmInputOutputData;
import com.splwg.tax.domain.admin.formRule.FormRuleBORuleProcessingAlgorithmSpot;
import com.splwg.tax.domain.admin.idType.IdType_Id;
import com.splwg.tax.domain.customerinfo.account.Account;
import com.splwg.tax.domain.customerinfo.account.Account_Id;
import com.splwg.tax.domain.customerinfo.person.Person;

/**
 * @author Khadim Cisse
 *
 * @AlgorithmComponent ()
 */
public class CmRetrieveInfosCalc_Impl extends CmRetrieveInfosCalc_Gen implements FormRuleBORuleProcessingAlgorithmSpot {
	ApplyFormRuleAlgorithmInputData inputData;
	ApplyFormRuleAlgorithmInputOutputData inputOutputData;
	private BusinessObjectInstance ruleInstance;

	@Override
	public void invoke() {
		BusinessObjectInstance formBoInstance = (BusinessObjectInstance) inputOutputData.getFormBusinessObject();
		COTSInstanceNode group = formBoInstance.getGroupFromPath("informationSalaries");
		Iterator<COTSInstanceListNode> listSalaries = group.getList("informationSalariesList").iterator();
		this.initInfosSalaries(listSalaries);
	}

	@Override
	public ApplyFormRuleAlgorithmInputOutputData getApplyFormRuleAlgorithmInputOutputData() {
		return inputOutputData;
	}

	@Override
	public void setApplyFormRuleAlgorithmInputData(ApplyFormRuleAlgorithmInputData applyFormRuleAlgorithmInputData) {
		this.inputData = applyFormRuleAlgorithmInputData;
	}

	@Override
	public void setApplyFormRuleAlgorithmInputOutputData(
			ApplyFormRuleAlgorithmInputOutputData applyFormRuleAlgorithmInputOutputData) {
		this.inputOutputData = applyFormRuleAlgorithmInputOutputData;
	}
	
	private String getFactorVal(String factor, String dateDebutCotisation, String dateFinCotisation ){
		PreparedStatement preparedStatement = createPreparedStatement("SELECT FACTOR_VAL FROM C1_FACTOR_VALUE where FACTOR_CD=:factor and TO_CHAR(EFFDT,'DD/MM/YYYY') <=:effectiveDate order by EFFDT DESC","SELECT");
		preparedStatement.bindString("factor",factor, null);
		preparedStatement.bindString("effectiveDate", dateFinCotisation,null);
		SQLResultRow sqlResultRow = preparedStatement.firstRow();
		return sqlResultRow.getString("FACTOR_VAL");
	}
	

	
	private void initInfosSalaries(Iterator<COTSInstanceListNode> listSalaries)	{
		SchemaInstance formInstance = this.inputOutputData.getFormBusinessObject();
		String dateDebutCotisation=formInstance.getFieldAndMDForPath("informationEmployeur/dateDebutCotisation/asCurrent").getXMLValue();
		String dateFinCotisation=formInstance.getFieldAndMDForPath("informationEmployeur/dateFinCotisation/asCurrent").getXMLValue();
		String idType=formInstance.getFieldAndMDForPath("informationEmployeur/typeIdentifiant/asCurrent").getXMLValue();
		String idNumber=formInstance.getFieldAndMDForPath("informationEmployeur/idNumber/asCurrent").getXMLValue();
		String raisonSociale=formInstance.getFieldAndMDForPath("informationEmployeur/raisonSociale/asCurrent").getXMLValue();
		String address1=formInstance.getFieldAndMDForPath("informationEmployeur/adresse/asCurrent").getXMLValue();
		
		this.ruleInstance = this.getRuleDetails();
		String pflSmig = this.getFactorVal(this.ruleInstance.getString("pflSmig"), dateDebutCotisation, dateFinCotisation);
		String plfCssCpf = this.getFactorVal(this.ruleInstance.getString("plfCssCpf"), dateDebutCotisation, dateFinCotisation);
		String plfCssCatmp = this.getFactorVal(this.ruleInstance.getString("plfCssCatmp"), dateDebutCotisation, dateFinCotisation);
		String plfIpresCrrg = this.getFactorVal(this.ruleInstance.getString("plfIpresCrrg"), dateDebutCotisation, dateFinCotisation);
		String plfIpresCrcc = this.getFactorVal(this.ruleInstance.getString("plfIpresCrcc"), dateDebutCotisation, dateFinCotisation);
		String txeCssCpf = this.getFactorVal(this.ruleInstance.getString("txeCssCpf"), dateDebutCotisation, dateFinCotisation);
		String txeIpresCrrg = this.getFactorVal(this.ruleInstance.getString("txeIpresCrrg"), dateDebutCotisation, dateFinCotisation);
		String txeIpresCrcc = this.getFactorVal(this.ruleInstance.getString("txeIpresCrcc"), dateDebutCotisation, dateFinCotisation);
		String txeCssCatmp = this.getAtRateEmployer(idType, idNumber);
		 while (listSalaries.hasNext()) {
			   COTSInstanceListNode nextSalarie = listSalaries.next();
			   if (nextSalarie != null) {
				   nextSalarie.getFieldAndMDForPath("smig/asCurrent").setXMLValue(pflSmig);
				   nextSalarie.getFieldAndMDForPath("plafondCssCpf/asCurrent").setXMLValue(plfCssCpf);
				   nextSalarie.getFieldAndMDForPath("plafondCssAtMp/asCurrent").setXMLValue(plfCssCatmp);
				   nextSalarie.getFieldAndMDForPath("plafondIpresCrrg/asCurrent").setXMLValue(plfIpresCrrg);
				   nextSalarie.getFieldAndMDForPath("plafondIpresCrcc/asCurrent").setXMLValue(plfIpresCrcc);
				   nextSalarie.getFieldAndMDForPath("tauxCssCpf/asCurrent").setXMLValue(txeCssCpf);
				   nextSalarie.getFieldAndMDForPath("tauxCssCatmp/asCurrent").setXMLValue(txeCssCatmp);
				   nextSalarie.getFieldAndMDForPath("tauxIpresCrrg/asCurrent").setXMLValue(txeIpresCrrg);
				   nextSalarie.getFieldAndMDForPath("tauxIpresCrcc/asCurrent").setXMLValue(txeIpresCrcc);
				   
				   }  
			   }
		 
		 //Chargement person Id dans le formulaire
		 String personId=getPersonId(idType, idNumber);
		 formInstance.getFieldAndMDForPath("taxpayerPersonID").setXMLValue(personId);
		 formInstance.getFieldAndMDForPath("primaryTaxpayerName").setXMLValue(raisonSociale);
		 formInstance.getFieldAndMDForPath("primaryTaxpayerIdType").setXMLValue(idType);
		 formInstance.getFieldAndMDForPath("primaryTaxpayerIdValue").setXMLValue(idNumber);
		 formInstance.getFieldAndMDForPath("address1").setXMLValue(address1);
		 
		 BusinessServiceInstance bsInstance = BusinessServiceInstance.create("C1-GetPersonAccounts");
		 bsInstance.set("personId", personId);
		 bsInstance = BusinessServiceDispatcher.execute(bsInstance);
		//List des comptes ratach�s � l'employeur
		Iterator<COTSInstanceListNode> iterator = bsInstance.getList("results").iterator();
		String accountId = null;
		String query = "select acct_id, count(acct_id) as NOMBRE_ACCOUNT from ci_acct_per where PER_ID =:perId  group by acct_id";
		PreparedStatement preparedStatement = createPreparedStatement(query, "SELECT");
		preparedStatement.bindString("perId", personId, null);
		SQLResultRow sqlResultRow = preparedStatement.firstRow();
		BigInteger nombreAccount = null;
		if (sqlResultRow != null) {
			accountId = sqlResultRow.getString("ACCT_ID");
			nombreAccount = sqlResultRow.getInteger("NOMBRE_ACCOUNT");
			System.out.println("RESULTAT SQL= " + accountId + "   " +nombreAccount);
		}
		if (isNull(nombreAccount) || nombreAccount.intValue() ==0 )  addError(CmMessageRepository90000.MSG_7038(personId));
		if (nombreAccount.intValue() > 1)  throw new RuntimeException("Plusieurs compte existe");
		formInstance.getFieldAndMDForPath("account").setXMLValue(accountId);
			 
	}
	
	
	
	public List<Account> getAccountsByIdPerson(String idEmployeur) {
		List<Account> listeAccounts = new ArrayList<Account>();
		// Business Service Instance
		BusinessServiceInstance bsInstance = BusinessServiceInstance.create("C1-GetPersonAccounts");
		bsInstance.set("personId", idEmployeur);
		bsInstance = BusinessServiceDispatcher.execute(bsInstance);
		//List des comptes ratach�s � l'employeur
		Iterator<COTSInstanceListNode> iterator = bsInstance.getList("results").iterator();
		while (iterator.hasNext()) {
			COTSInstanceListNode nextElt = iterator.next();
			Account_Id accountId=new Account_Id(nextElt.getNumber("accountId").toString());
			listeAccounts.add(accountId.getEntity());
		}
		return listeAccounts;
	}
	/**
	 * Permet de recuperer le taux AT de l'employeur
	 * @param idType type d'identifiant de l'employeur
	 * @param idNumber numero d'identifiant de l'employeur
	 * @return le taux AT de l'employeur
	 */
	private String getAtRateEmployer(String idType, String idNumber ){
		//Business Servivce pour recup�rer la personne
	    BusinessServiceInstance businessServiceInstance=BusinessServiceInstance.create("C1-PersonSearchByIdTypeNumber");      
        businessServiceInstance.getFieldAndMDForPath("idType").setXMLValue(idType);
        businessServiceInstance.getFieldAndMDForPath("idNumber").setXMLValue(idNumber);
        businessServiceInstance=BusinessServiceDispatcher.execute(businessServiceInstance);
        String personId=businessServiceInstance.getFieldAndMDForPath("results[1]/personId").getXMLValue();
        
        //Invocation du BO pour r�cup�rer la valeur du Taux AT
        BusinessObjectInstance businessObjectInstance=BusinessObjectInstance.create("CM-PersonIndividualChar");
        businessObjectInstance.getFieldAndMDForPath("personId").setXMLValue(personId);
        businessObjectInstance= BusinessObjectDispatcher.read(businessObjectInstance);
        
        //Il faut verifier si null
        String tauxAt=businessObjectInstance.getFieldAndMDForPath("personChar[charTypeCD='CM-ATRAT']/adhocCharVal").getXMLValue();
        return tauxAt;
	}
	

	private String getPersonId(String IdType, String idNumber){
				CmPersonSearchComponent perSearch = new CmPersonSearchComponent.Factory().newInstance();
				IdType_Id idType = new IdType_Id(IdType);
				Person person=perSearch.searchPerson(idType.getEntity(), idNumber);
				if (notNull(person))
					return person.getId().getTrimmedValue();
				return null;
	}


	/**
	 * Permet de recuperer une instance du BO du Form Rule
	 * @return BusinessObjectInstance du BO du Form Rule
	 */
	private BusinessObjectInstance getRuleDetails() {
		MaintenanceObjectInfo moInfo = MaintenanceObjectInfoCache
				.getMaintenanceObjectInfo(this.inputData.getFormRuleId().metaInfo().getTableId());
		BusinessObjectInfo boInfo = moInfo.determineBusinessObjectInfo(this.inputData.getFormRuleId());
		BusinessObjectInstance boInstance = BusinessObjectInstance.create(boInfo.getBusinessObject());
		boInstance.set("formRuleGroup", this.inputData.getFormRuleId().getFormRuleGroup().getId().getIdValue());
		boInstance.set("formRule", this.inputData.getFormRuleId().getFormRule());
		boInstance = BusinessObjectDispatcher.read(boInstance);
		return boInstance;
	}

}
