package com.splwg.cm.domain.admin.formRule;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.lang.StringUtils;

import com.splwg.base.api.QueryIterator;
import com.splwg.base.api.businessObject.COTSInstanceNode;
import com.splwg.base.api.businessService.BusinessServiceDispatcher;
import com.splwg.base.api.businessService.BusinessServiceInstance;
import com.splwg.base.api.datatypes.Money;
import com.splwg.base.api.sql.PreparedStatement;
import com.splwg.base.api.sql.SQLResultRow;
import com.splwg.base.domain.StandardMessages;
import com.splwg.base.domain.common.characteristicType.CharacteristicType;
import com.splwg.base.domain.common.currency.Currency;
import com.splwg.base.domain.common.currency.Currency_Id;
import com.splwg.base.support.context.Session;
import com.splwg.base.support.context.SessionHolder;
import com.splwg.cm.domain.customMessages.CmMessageRepository90002;
import com.splwg.shared.logging.Logger;
import com.splwg.shared.logging.LoggerFactory;
import com.splwg.tax.api.lookup.PaymentStatusLookup;
import com.splwg.tax.domain.admin.distributionRule.DistributionRule;
import com.splwg.tax.domain.admin.distributionRule.DistributionRuleCreatePaymentAlgorithmSpot;
import com.splwg.tax.domain.admin.matchType.MatchType;
import com.splwg.tax.domain.customerinfo.account.Account;
import com.splwg.tax.domain.customerinfo.account.Account_Id;
import com.splwg.tax.domain.customerinfo.serviceAgreement.ServiceAgreement;
import com.splwg.tax.domain.customerinfo.serviceAgreement.ServiceAgreement_Id;
import com.splwg.tax.domain.forms.taxForm.TaxForm_Id;
import com.splwg.tax.domain.payment.payment.Payment;
import com.splwg.tax.domain.payment.payment.PaymentSegment;
import com.splwg.tax.domain.payment.payment.PaymentSegment_DTO;
import com.splwg.tax.domain.payment.payment.Payment_DTO;
import com.splwg.tax.domain.payment.payment.Payment_Id;
import com.splwg.tax.domain.payment.paymentEvent.PaymentEvent;
import com.splwg.tax.domain.payment.paymentEvent.PaymentEventDistributionDetail;
import com.splwg.tax.domain.payment.paymentEvent.PaymentEvent_Id;
import com.splwg.tax.domain.payment.paymentEvent.PaymentTender;

/**
 * @author Deepak P
 *
@AlgorithmComponent (softParameters = { @AlgorithmSoftParameter (name = OverpaymentAdjustmentType, type = string)
 *            , @AlgorithmSoftParameter (name = InterestAdjustmentType, type = string)
 *            , @AlgorithmSoftParameter (name = PenaltyAdjustmentType, type = string)
 *            , @AlgorithmSoftParameter (name = ContributionAdjustmentType, type = string)
 *            , @AlgorithmSoftParameter (name = ObligationOverpaymentType, type = string)
 *            , @AlgorithmSoftParameter (name = ObligationContributionType, type = string)
 *            , @AlgorithmSoftParameter (name = FormType, type = string)})
 */
public class CmDistributionRuleCreatePaymentOnDnsAlgoComp_Impl extends CmDistributionRuleCreatePaymentOnDnsAlgoComp_Gen
		implements DistributionRuleCreatePaymentAlgorithmSpot {
	
	private static final Logger logger = LoggerFactory.getLogger(CmDistributionRuleCreatePaymentOnDnsAlgoComp_Impl.class);
	
	
	private PaymentEvent paymentEvent;
	//private PaymentEvent_Id paymentEventId;
	private DistributionRule distributionRule;
	private Money amount;
	private String characteristicValueFk1;
	private String adhocCharacteristicValue;
	private BigInteger sequence;
	private Payment_Id paymentId;
	String taxId = null;
	String accountId = null;
	PreparedStatement psPreparedStatement = null;
	Money epfAmount = Money.ZERO;
	Money erAmount = Money.ZERO;
	Money atmpAmount = Money.ZERO;
	Money tenderAmount = Money.ZERO;
	Currency currency = null;
	Map<ServiceAgreement, Money> obligationMoneyMap = new HashMap<>();
	String obligationContributionArr[] = null;
	String obligationOverPaymentArr[] = null;
	String adjustmentTypeContributionArr[] = null;
	String adjustmentTypePenalityArr[] = null; 
	String adjustmentTypeMajorationArr[] = null; 
	String adjustmentTyepeOverpaymentArr[] = null; 
	Session session = null;
	Boolean unidentifiedObligationFlag = false;
	
	
	@Override
	public void invoke() {
		
		logger.info("characteristicFK: " + this.characteristicValueFk1);
		System.out.println("characteristicFK: " + this.characteristicValueFk1);
		System.out.println("adhocCharacteristicValue: " + this.adhocCharacteristicValue);
		logger.info("Amount: " + this.amount);
		System.out.println("Amount: " + this.amount);		
		logger.info("Sequence: " + this.sequence);
		System.out.println("Sequence: " + this.sequence);		
		session = SessionHolder.getSession();
		
		logger.info("paymentEvent: " + this.paymentEvent);
		System.out.println("paymentEvent: " + this.paymentEvent);
		taxId = getTaxFormIdFromPortalId(this.adhocCharacteristicValue);
		//taxId = "768493889427";
		accountId = new TaxForm_Id(taxId).getEntity().getAccountId().getIdValue();
		System.out.println("Account ID: " + accountId);
		currency = new TaxForm_Id(taxId).getEntity().getAccountId().getEntity().getCurrency();
		System.out.println("Currency: " + currency);
		
		
		
		LinkedHashMap<HashMap<String, Money>, HashMap<String, HashMap<List<String>, List<Money>>>> debtOblMap = new LinkedHashMap<HashMap<String, Money>, HashMap<String, HashMap<List<String>, List<Money>>>>();
		//String paymentEventId = String.valueOf(this.paymentEvent.getId());
		for (PaymentTender payDTO : this.paymentEvent.getPaymentTenders().asSet()){
		    System.out.println(payDTO.getCheckNumber());
		    System.out.println(payDTO.getTenderAmount());
		     //tenderAmount = new Money(String.valueOf(payDTO.getTenderAmount()), currency.getId());
		    
		for (PaymentEventDistributionDetail payDetail : this.paymentEvent.getPaymentEventDistributionDetails().asSet()){    
		if (payDetail.getAmount().isEqualTo(payDTO.getTenderAmount()))	{
			debtOblMap = getDebtObligation(this.adhocCharacteristicValue);
			/*if(!this.adhocCharacteristicValue.isEmpty()){
		 debtOblMap = getDebtObligation(this.adhocCharacteristicValue);
	    }else{
	     debtOblMap = getDebtObligation(paymentEventId);
	    }*/
	    
		logger.info("debtOblMap size: " + debtOblMap.size());
		logger.info("debtOblMap: " + debtOblMap);
		System.out.println("debtOblMap: " + debtOblMap.size());
		ServiceAgreement debtObligation = null;
		Money debtMoneyforSingleSA = Money.ZERO;
		Money totalDebtAmountToBePaid = Money.ZERO;
		Money actualMoneyValue = this.amount;
		String periodValue = null;
		
		if(!debtOblMap.isEmpty()) {
		for(Map.Entry<HashMap<String, Money>, HashMap<String, HashMap<List<String>,List<Money>>>> debtMapObj : debtOblMap.entrySet()){
			HashMap<String,Money> moneyMapkey = debtMapObj.getKey();
			HashMap<String, HashMap<List<String>,List<Money>>> moneyMap = debtMapObj.getValue();
			for(Map.Entry<String, Money> moneyMapObj : moneyMapkey.entrySet() ){
				Money moneyMapList = moneyMapObj.getValue();
				totalDebtAmountToBePaid = moneyMapList.add(totalDebtAmountToBePaid);
			}
			logger.info("Sum of Obligation Amount:: " + totalDebtAmountToBePaid);
			System.out.println("Sum of Obligation Amount:: " + totalDebtAmountToBePaid);

			if(!totalDebtAmountToBePaid.isZero() && !this.amount.isZero() && this.amount.isGreaterThanOrEqual(totalDebtAmountToBePaid)){ 
				logger.info("###Input Amount is greater than sum of obligation amount.Creating payment for equal distribution##" );
				System.out.println("###Input Amount is greater than sum of obligation amount.Creating payment for equal distribution##" );
				HashMap<String,Money> moneyMapValue = debtMapObj.getKey();
				Money overPayAmount = Money.ZERO;
				for (Map.Entry<String, Money> moneyEntry : moneyMapValue.entrySet()) {
					ServiceAgreement_Id sa_id = new ServiceAgreement_Id(moneyEntry.getKey());
					logger.info("ServiceAgreement_Id: " + sa_id);
					System.out.println("ServiceAgreement_Id, : " + sa_id);
					debtObligation = (ServiceAgreement) sa_id.getEntity();
					logger.info("ServiceAgreement: " + debtObligation);
					System.out.println("ServiceAgreement: " + debtObligation);
					debtMoneyforSingleSA = moneyEntry.getValue();
					int payAmount = Math.round(debtMoneyforSingleSA.getAmount().floatValue());
					debtMoneyforSingleSA = new Money(String.valueOf(payAmount), currency.getId());
					logger.info("DebtMoney: " + debtMoneyforSingleSA);
					logger.info("Amount before the payment creation:: " + this.amount);
					System.out.println("Amount before the payment creation:: " + this.amount);
					obligationMoneyMap.put(debtObligation, debtMoneyforSingleSA);
					
		        }
				overPayAmount = this.amount.subtract(totalDebtAmountToBePaid);
				if(!overPayAmount.isZero() && overPayAmount.isPositive()){

					logger.info("********Creating OverPayment and the Amount :: " + overPayAmount);
					for (Map.Entry<String, Money> moneyMapObj : moneyMapkey.entrySet()) {
						String monthObligation = moneyMapObj.getKey();
						ServiceAgreement_Id saId = new ServiceAgreement_Id(monthObligation);
						String saType = saId.getEntity().getServiceAgreementType().getId().getSaType().trim();
						if("O-EPF".equalsIgnoreCase(saType)) {
							epfAmount = moneyMapObj.getValue().add(epfAmount);
						} else if("O-EATMP".equalsIgnoreCase(saType)) {
							atmpAmount = moneyMapObj.getValue().add(atmpAmount);
						} else if("O-ER".equalsIgnoreCase(saType)) {
							erAmount = moneyMapObj.getValue().add(erAmount);
						}
					}
					int prorateMoney = 0;
					Money additionMoney = Money.ZERO;
					Money moneyToBePaid = Money.ZERO;//new Money(String.valueOf(prorateMoney), currency.getId());
					Money finalOblgMoney = Money.ZERO;
					for (int i = 0; i < obligationOverPaymentArr.length; i++) {
						if("E-AVPF".equalsIgnoreCase(obligationOverPaymentArr[i])) {
							prorateMoney = Math.round(epfAmount.getAmount().floatValue()
									/ totalDebtAmountToBePaid.getAmount().floatValue()
									* overPayAmount.getAmount().floatValue());
						} else if("E-AVATMP".equalsIgnoreCase(obligationOverPaymentArr[i])) {
							prorateMoney = Math.round(atmpAmount.getAmount().floatValue()
									/ totalDebtAmountToBePaid.getAmount().floatValue()
									* overPayAmount.getAmount().floatValue());
						} else if("E-AVCR".equalsIgnoreCase(obligationOverPaymentArr[i])) {
							prorateMoney = Math.round(erAmount.getAmount().floatValue()
									/ totalDebtAmountToBePaid.getAmount().floatValue()
									* overPayAmount.getAmount().floatValue());
						}
						//Proration and Round off Logic
						if(i == obligationOverPaymentArr.length-1) {
							moneyToBePaid = overPayAmount.subtract(additionMoney);
						} else {
							finalOblgMoney = new Money(String.valueOf(prorateMoney), currency.getId()).add(finalOblgMoney);
							if(overPayAmount.subtract(finalOblgMoney).isEqualTo(new Money("1", currency.getId()))) {
								moneyToBePaid = new Money(String.valueOf(prorateMoney - 1), currency.getId());
							} else {
								moneyToBePaid = new Money(String.valueOf(prorateMoney), currency.getId());
							}
						}
						additionMoney = additionMoney.add(moneyToBePaid);								
						
						String division = getDivisionByObligationType(obligationOverPaymentArr[i]);					
						String obligationId = createObligation(accountId, division , obligationOverPaymentArr[i]);
						logger.info("OverPayment Created against the Obligation ID" + obligationId
								+ "for account ID: " + this.characteristicValueFk1);
						ServiceAgreement_Id sa_id = new ServiceAgreement_Id(obligationId);
						logger.info("ServiceAgreement_Id: " + sa_id);
						System.out.println("ServiceAgreement_Id, : " + sa_id);
						debtObligation = (ServiceAgreement) sa_id.getEntity();
						logger.info("ServiceAgreement: " + debtObligation);
						System.out.println("ServiceAgreement: " + debtObligation);
						logger.info("OverPayAmount Amount " + overPayAmount +"OverPayAmount Amount per SA: " + moneyToBePaid);
						System.out.println("OverPayAmount Amount " + overPayAmount +"OverPayAmount Amount per SA: " + moneyToBePaid);
						obligationMoneyMap.put(debtObligation, moneyToBePaid);
					}
				
				
				}
				this.createFrozenPayment(obligationMoneyMap);
					
			} else {
				for(Entry<String, HashMap<List<String>, List<Money>>> moneyMapObj : moneyMap.entrySet()){
					  periodValue = moneyMapObj.getKey(); 
					  actualMoneyValue = this.amount;
					  HashMap<List<String>,List<Money>>  finalMoneyMap = moneyMapObj.getValue();
					  Money monthObligationMoney = Money.ZERO;
					  Money finalOblMoney = Money.ZERO;
					  Money additionMoney = Money.ZERO;
					  for(Map.Entry<List<String>, List<Money>> moneyEntry : finalMoneyMap.entrySet()){
						  List<String> obligIdList = moneyEntry.getKey();
						  if(!isNull(moneyEntry) && moneyEntry.getValue().size()>=1){ 
							  List<Money> moneyList = moneyEntry.getValue();
							 	for(int i=0;i<moneyList.size();i++){
							 		monthObligationMoney = moneyList.get(i).add(monthObligationMoney);
							 	}
							 	if(!monthObligationMoney.isZero() && this.amount.isLessThanOrEqual(monthObligationMoney)){
							 		logger.info("###Creating payment for same month obligations####" );
									System.out.println("###Creating payment for same month obligations####" );
							 		for(int i=0;i<moneyList.size();i++){
								 		Money obligationMoney = moneyList.get(i);
								 		
								 		logger.info("obligation Money: " + obligationMoney);
								 		logger.info("Screen Amount: " + this.amount);
								 		logger.info("Month Obligation Money: " + monthObligationMoney);
								 		logger.info("Actual Money Value: " + actualMoneyValue);
								 	    String oblStr = obligIdList.get(i);
								 	   int prorateMoney = 0;
										if(i == moneyList.size()-1) {
											debtMoneyforSingleSA = actualMoneyValue.subtract(additionMoney);
										} else {
											prorateMoney = Math.round(actualMoneyValue.getAmount().floatValue()
													/ monthObligationMoney.getAmount().floatValue()
													* obligationMoney.getAmount().floatValue());
											finalOblMoney = new Money(String.valueOf(prorateMoney), currency.getId()).add(finalOblMoney);
											if(actualMoneyValue.subtract(finalOblMoney).isEqualTo(new Money("1", currency.getId()))) {
												debtMoneyforSingleSA = new Money(String.valueOf(prorateMoney - 1), currency.getId());
											} else {
												debtMoneyforSingleSA = new Money(String.valueOf(prorateMoney), currency.getId());
											}
										}
										additionMoney = additionMoney.add(debtMoneyforSingleSA);
										ServiceAgreement_Id sa_id = new ServiceAgreement_Id(oblStr);
										logger.info("ServiceAgreement_Id: " + sa_id);
										System.out.println("ServiceAgreement_Id: " + sa_id);
										debtObligation = (ServiceAgreement) sa_id.getEntity();
										logger.info("ServiceAgreement: " + debtObligation);
										System.out.println("ServiceAgreement: " + debtObligation);
										logger.info("prorateMoney: " + debtMoneyforSingleSA);
										logger.info("Amount before the payment creation:: " + this.amount);
										System.out.println("Amount before the payment creation:: " + this.amount);
										obligationMoneyMap.put(debtObligation, debtMoneyforSingleSA);
								 	}
							 		if (!this.amount.isZero() && this.amount.isPositive()) {
										this.createFrozenPayment(obligationMoneyMap);
									}
							 	} else { 
							 		logger.info("###Creating payment for sequence month obligations####" );
									System.out.println("###Creating payment for sequence month obligations####" );
							 		for (Map.Entry<List<String>,List<Money>> moneyEntryy : finalMoneyMap.entrySet()) {
							 			List<String> obligIdListt = moneyEntryy.getKey();
							 			List<Money> moneyListt = moneyEntry.getValue();
							 			for(int i=0;i<obligIdListt.size();i++){
							 				ServiceAgreement_Id sa_id = new ServiceAgreement_Id(obligIdListt.get(i));
											logger.info("ServiceAgreement_Id: " + sa_id);
											System.out.println("ServiceAgreement_Id: " + sa_id);
											debtObligation = (ServiceAgreement) sa_id.getEntity();
											logger.info("ServiceAgreement: " + debtObligation);
											System.out.println("ServiceAgreement: " + debtObligation);
											debtMoneyforSingleSA = moneyListt.get(i);
											int payAmount = Math
													.round(debtMoneyforSingleSA.getAmount().floatValue());
											debtMoneyforSingleSA = new Money(String.valueOf(payAmount), currency.getId());
											System.out.println("DebtMoney: " + debtMoneyforSingleSA);
											logger.info("DebtMoney:" + debtMoneyforSingleSA);
											logger.info("Amount before the payment creation :: " + this.amount);
											System.out
													.println("Amount before the payment creation:: " + this.amount);
											obligationMoneyMap.put(debtObligation, debtMoneyforSingleSA);
										}
							 			if (!this.amount.isZero() && this.amount.isPositive()) {
											this.createFrozenPayment(obligationMoneyMap);
										}
									}
								}
							}
						}
					}
				}
		      }
			}else if(!this.amount.isZero() && this.amount.isPositive()) {
				

				
				logger.info("There is no obligation to pay: Creating OverPayment- the Amount is:: "	+ this.amount);
				Money debtMoneyforOverPaySA = Money.ZERO;
				int prorateMoney = Math.round(this.amount.getAmount().floatValue() / obligationOverPaymentArr.length);
					for (int i = 0; i < obligationOverPaymentArr.length; i++) {
					String division = getDivisionByObligationType(obligationOverPaymentArr[i]);					
					String obligationId = createObligation(accountId, division , obligationOverPaymentArr[i]);
					logger.info("OverPayment Created against the Obligation ID" + obligationId + "for account ID: " + this.characteristicValueFk1);
					ServiceAgreement_Id sa_id = new ServiceAgreement_Id(obligationId);
					logger.info("ServiceAgreement_Id: " + sa_id);
					System.out.println("ServiceAgreement_Id, : " + sa_id);
					debtObligation = (ServiceAgreement) sa_id.getEntity();
					logger.info("ServiceAgreement: " + debtObligation);
					System.out.println("ServiceAgreement: " + debtObligation);
					if(i == obligationOverPaymentArr.length-1) {
						int length = obligationOverPaymentArr.length-1;
						prorateMoney = prorateMoney * length;
						Money finalMoney = new Money(String.valueOf(prorateMoney), currency.getId());
						debtMoneyforOverPaySA = this.amount.subtract(finalMoney);
					} else {
						debtMoneyforOverPaySA = new Money(String.valueOf(prorateMoney), currency.getId());
						
					}
					logger.info("Amount before the payment creation:: " + obligationMoneyMap);
					obligationMoneyMap.put(debtObligation, debtMoneyforOverPaySA);
				}
				
				this.createFrozenPayment(obligationMoneyMap);
			
			}
				} else {
			logger.info("Inside else loop of error message.");
			//addError(StandardMessages.fieldInvalid("The tender amount and payment amount should be equal."));
		    addError(CmMessageRepository90002.MSG_250());
		}
		}
		}
		}
	
        
	/**
	 * @param obligation
	 * @param money
	 */
	private void createFrozenPayment(Map<ServiceAgreement, Money> obligationMoneyMap) {
		
		Set<ServiceAgreement> oblKey = obligationMoneyMap.keySet();
		Account_Id obligationId = oblKey.iterator().next().getAccount().getId();
		Money moneyToSubtract = Money.ZERO;
		Payment_DTO paymentDTO = (Payment_DTO) this.createDTO(Payment.class);
		paymentDTO.setAccountId(new Account_Id(String.valueOf(obligationId.getIdValue())));
		System.out.println("Account Id for payment: " +  String.valueOf(obligationId.getIdValue()));
		//paymentDTO.setAccountId(new Account_Id(String.valueOf(obligationId)));
		paymentDTO.setPaymentAmount(this.amount);
		paymentDTO.setCurrencyId(currency.getId());
		paymentDTO.setSequence(this.sequence);
		//paymentDTO.setPaymentEventId(new PaymentEvent_Id("245693748074"));
		paymentDTO.setPaymentEventId(this.paymentEvent.getId()); 
        paymentDTO.setPaymentStatus(PaymentStatusLookup.constants.FREEZABLE);
		Payment payment = paymentDTO.newEntity();
        
		logger.info("paymentId: " +  payment.getId());
		System.out.println("paymentId: " +  payment.getId());
		
        /*PaymentEvent paymentEvent = (PaymentEvent) paymentDTO.getPaymentEventId().getEntity();
		Payment payment = paymentEvent.createPayment(paymentDTO);*/
	
		PaymentSegment_DTO paymentSegmentDTO = (PaymentSegment_DTO) this.createDTO(PaymentSegment.class);
		for (Entry<ServiceAgreement, Money> obliMoneyma : obligationMoneyMap.entrySet()){
			paymentSegmentDTO.setServiceAgreementId(obliMoneyma.getKey().getId());
			paymentSegmentDTO.setCurrencyId(currency.getId());
			paymentSegmentDTO.setPaySegmentAmount(obliMoneyma.getValue());
			paymentSegmentDTO.setPaymentId(payment.getId());
			paymentSegmentDTO.newEntity();
			moneyToSubtract = moneyToSubtract.add(obliMoneyma.getValue());
			System.out.println("PaySegment Id:: " + paymentSegmentDTO.getEntity().getId());
			logger.info("PaySegment Id:: " + paymentSegmentDTO.getEntity().getId());
		}
		if (payment.getPaymentStatus().isFreezable()) {
			payment.freeze();
		}
		this.amount = this.amount.subtract(moneyToSubtract);;
		if (this.notNull(payment)) {
			this.paymentId = payment.getId();
		}
		
	}

	public String createObligation(String accountId, String division, String obligationType) {

		  // Business Service Instance
		  BusinessServiceInstance bsInstance = BusinessServiceInstance.create("C1-FindCreateObligation");

		  // Populate BS parameters if available
		  if (null != accountId && null != division && null != obligationType) {
		   COTSInstanceNode group = bsInstance.getGroupFromPath("input");
		   group.set("accountId", accountId);
		   group.set("division", division);
		   group.set("obligationType", obligationType);
		  }

		  return executeBSAndCreateObligation(bsInstance);

		 }

	/**
	 * @param bsInstance
	 * @return
	 */
	private String executeBSAndCreateObligation(BusinessServiceInstance bsInstance) {
		  // TODO Auto-generated method stub
		  bsInstance = BusinessServiceDispatcher.execute(bsInstance);
		  String obligationId = null;
		  System.out.println(getSystemDateTime().getDate());
		  // Getting the list of results
		  COTSInstanceNode group = bsInstance.getGroupFromPath("output");

		  // If list IS NOT empty
		  if (group != null) {
		   obligationId = group.getString("obligationId");
		  }
		  logger.info("obligationId " +obligationId); 
		  System.out.println("obligationId " +obligationId); 
		  return obligationId;

		 }
	
	/**
	 * @param saType
	 * @return
	 */
	private String getDivisionByObligationType(String saType) {
		PreparedStatement preparedStatement = createPreparedStatement(
				"SELECT CIS_DIVISION FROM CI_SA_TYPE where SA_TYPE_CD= \'" + saType + "\'",	"SELECT");
		preparedStatement.setAutoclose(false);
		String division = null;

		try {
			SQLResultRow sql = preparedStatement.firstRow();
			division = sql.getString("CIS_DIVISION");
		} catch (Exception exception) {
			exception.printStackTrace();
		} finally {
			preparedStatement.close();
			preparedStatement = null;
		}
		return division;

	}
	
	/**
	 * @param portalId
	 * @return
	 */
	private String getTaxFormIdFromPortalId(String portalId) {
		PreparedStatement preparedStatement = createPreparedStatement(
				"SELECT TAX_FORM_ID FROM CI_TAX_FORM_CHAR where ADHOC_CHAR_VAL= \'" + portalId + "\'",	"SELECT");
		preparedStatement.setAutoclose(false);
		String taxFormId = null;

		try {
			SQLResultRow sql = preparedStatement.firstRow();
			taxFormId = sql.getString("TAX_FORM_ID");
		} catch (Exception exception) {
			exception.printStackTrace();
		} finally {
			preparedStatement.close();
			preparedStatement = null;
		}
		return taxFormId;

	}

	
	@SuppressWarnings("deprecation")
	private LinkedHashMap<HashMap<String, Money>, HashMap<String, HashMap<List<String>, List<Money>>>> getDebtObligation(String adhocCharacteristicValue) {
		
		PreparedStatement psPreparedStatement = null;

		String obligationContribution = getObligationContributionType();
		String obligationOverPayment = getObligationOverpaymentType();
		String adjustmentTypeContribution = getContributionAdjustmentType();
		String adjustmentTypePenality = getPenaltyAdjustmentType();
		String adjustmentTypeMajoration = getInterestAdjustmentType();
		String adjustmentTyepeOverpayment = getOverpaymentAdjustmentType();
		String paymentEventIdQuery = String.valueOf(this.paymentEvent.getId());
		//String paymentEventIdQuery = "095986165920";
		
		if(obligationContribution.contains(",") || obligationOverPayment.contains(",") || adjustmentTypeContribution.contains(",")
				 ||  adjustmentTypePenality.contains(",") ||
				 adjustmentTypeMajoration.contains(",") || adjustmentTyepeOverpayment.contains(",")){
			
			 obligationContributionArr = obligationContribution.split(","); 
			 obligationOverPaymentArr = obligationOverPayment.split(","); 
			 adjustmentTypeContributionArr = adjustmentTypeContribution.split(","); 
			 adjustmentTypePenalityArr = adjustmentTypePenality.split(","); 
			 adjustmentTypeMajorationArr = adjustmentTypeMajoration.split(","); 
			 adjustmentTyepeOverpaymentArr = adjustmentTyepeOverpayment.split(","); 
			 obligationContribution = "'" + StringUtils.join(obligationContributionArr,"','") + "'";
			 obligationOverPayment = "'" + StringUtils.join(obligationOverPaymentArr,"','") + "'";
			 adjustmentTypeContribution = "'" + StringUtils.join(adjustmentTypeContributionArr,"','") + "'";
			 adjustmentTypePenality = "'" + StringUtils.join(adjustmentTypePenalityArr,"','") + "'";
			 adjustmentTypeMajoration = "'" + StringUtils.join(adjustmentTypeMajorationArr,"','") + "'";
			 adjustmentTyepeOverpayment = "'" + StringUtils.join(adjustmentTyepeOverpaymentArr,"','") + "'";
			
		}

		String period = null;
		HashMap<String, Money> debtOblMap = new HashMap<String, Money>();
		HashMap<String, HashMap<List<String>,List<Money>>> periodMap = new HashMap<String, HashMap<List<String>,List<Money>>>();
	    LinkedHashMap<HashMap<String, Money>, HashMap<String, HashMap<List<String>,List<Money>>>> debtPriorityMap = new LinkedHashMap<HashMap<String, Money>, HashMap<String, HashMap<List<String>,List<Money>>>>();
	 if(!("0000000000".equalsIgnoreCase(this.adhocCharacteristicValue))){
	    psPreparedStatement = createPreparedStatement("select distinct OBL.acct_id,OBL.SA_ID,OBL.SA_TYPE_CD,OBL.SA_STATUS_FLG,"
	    		+ "ADJ.ADJ_TYPE_CD,ADJ.ADJ_ID,ADJ.ADJ_AMT,OBL.START_DT,ADJ.CRE_DT from CI_SA OBL,CI_ADJ ADJ,ci_ft FT "
	    		+ "where ADJ.SA_ID=OBL.SA_ID and FT.SA_ID=OBL.SA_ID and OBL.SA_ID in(select sa.sa_id from ci_sa sa,"
	    		+ "ci_sa_char sach,ci_tax_form_char taxch where taxch.tax_form_id=sach.CHAR_VAL_FK1 and sach.sa_id = sa.sa_id "
	    		+ "and taxch.adhoc_char_val= \'"+this.adhocCharacteristicValue+"\') and ADJ.ADJ_TYPE_CD "
	    		+ "IN("+adjustmentTypeContribution+","+adjustmentTypePenality+","+adjustmentTypeMajoration+","+adjustmentTyepeOverpayment+") "
	    		+ "and OBL.SA_TYPE_CD in("+obligationContribution+") and OBL.SA_STATUS_FLG=40 ORDER BY OBL.START_DT","select");
		} else if("0000000000".equalsIgnoreCase(this.adhocCharacteristicValue)){
 
		 psPreparedStatement = createPreparedStatement("select distinct OBL.acct_id,OBL.SA_ID,OBL.SA_TYPE_CD,OBL.SA_STATUS_FLG,"
		 		+ "OBL.START_DT from CI_SA OBL where"
		 		+ "OBL.SA_ID in(SELECT TNDR.SA_ID FROM CI_TNDR_SRCE TNDR,"
		 		+ "CI_PEVT_DTL_ST PEVT WHERE TNDR.EXT_SOURCE_ID=PEVT.EXT_SOURCE_ID AND PEVT.PAY_EVENT_ID=\'"+paymentEventIdQuery+"\')"
		 	    + "and OBL.SA_TYPE_CD in('PAI-SUSP') and OBL.SA_STATUS_FLG=20 ORDER BY OBL.START_DT","select");
		 unidentifiedObligationFlag = true;
	 }          
	    
	    psPreparedStatement.setAutoclose(false);
			try {
				
				QueryIterator<SQLResultRow> result = psPreparedStatement.iterate();
				List<Money> moneyList = new ArrayList<Money>();
				List<String> oblgList = new ArrayList<String>();
				List<String> saIdList = new  ArrayList<String>();
				HashMap<List<String>, List<Money>> oblMoneyMap = new HashMap<List<String>, List<Money>>();
				while (result.hasNext()) {
					System.out.println("I am In");
					SQLResultRow lookUpValue = result.next();
					System.out.println(lookUpValue.getString("SA_ID"));
					
					if(!saIdList.contains(lookUpValue.getString("SA_ID"))){
						saIdList.add(lookUpValue.getString("SA_ID"));
					try {
						psPreparedStatement = createPreparedStatement("SELECT SUM(CUR_AMT) AS \"Total\" from CI_FT where SA_ID = "+ lookUpValue.getString("SA_ID"), "select");
						psPreparedStatement.setAutoclose(false);
						QueryIterator<SQLResultRow> oblResultIterator = psPreparedStatement.iterate();
						while (oblResultIterator.hasNext()) {
							System.out.println("I am In");
							SQLResultRow oblResult = oblResultIterator.next();
							System.out.println(lookUpValue.getString("SA_ID"));
							if (oblResult.getString("Total") != null && Integer.parseInt(oblResult.getString("Total")) > 0) {
								debtOblMap.put(lookUpValue.getString("SA_ID"), new Money(oblResult.getString("Total"), new Currency_Id("XOF")));
								logger.info("debtOblMap Total:: " + debtOblMap);
								
								if(null == period || lookUpValue.getString("START_DT").equalsIgnoreCase(period)){
									period = lookUpValue.getString("START_DT");
									moneyList.add(new Money(oblResult.getString("Total"), new Currency_Id("XOF")));
									oblgList.add(lookUpValue.getString("SA_ID"));
									oblMoneyMap = new HashMap<List<String>,List<Money>>();
									oblMoneyMap.put(oblgList, moneyList);
									periodMap.put(period, oblMoneyMap);
								} else if(!lookUpValue.getString("START_DT").equalsIgnoreCase(period)) {
									moneyList = new ArrayList<Money>();
									oblgList = new ArrayList<String>();
									oblMoneyMap = new HashMap<List<String>,List<Money>>();
									moneyList.add(new Money(oblResult.getString("Total"), new Currency_Id("XOF")));
									oblgList.add(lookUpValue.getString("SA_ID"));
									oblMoneyMap.put(oblgList, moneyList);
									periodMap.put(lookUpValue.getString("START_DT"), oblMoneyMap);
									period = lookUpValue.getString("START_DT");
								}
							}
						}	
						if(unidentifiedObligationFlag){
							ServiceAgreement_Id undefinedSaId = new ServiceAgreement_Id(saIdList.get(0));
							ServiceAgreement undefinedSa = (ServiceAgreement) undefinedSaId.getEntity();
					    	obligationMoneyMap.put(undefinedSa,this.amount);
					    	this.createFrozenPayment(obligationMoneyMap);
					    }
						if (!debtOblMap.isEmpty() && !periodMap.isEmpty()) {
							debtPriorityMap.put(debtOblMap, periodMap);
						} else if(!unidentifiedObligationFlag) {
							addError(StandardMessages.fieldInvalid(
									"The DNS ID mentioned for payment is not linked with ID-DNS characteristic type"));
						}
					} catch (Exception exception) {
						exception.printStackTrace();
					}
				}
					
			}
				//debtPriorityMap.put(debtOblMap, periodMap);
		} catch (Exception exception) {
				exception.printStackTrace();
			} finally {
				psPreparedStatement.close();
				psPreparedStatement = null;
			}
		//}
		return debtPriorityMap;
	}

	@Override
	public void setPaymentEvent(PaymentEvent arg0) {
		// TODO Auto-generated method stub
		paymentEvent = arg0;
	}

	@Override
	public void setDistributionRule(DistributionRule arg0) {
		// TODO Auto-generated method stub
		distributionRule = arg0;

	}

	@Override
	public void setAmount(Money arg0) {
		// TODO Auto-generated method stub
		amount = arg0;

	}

	@Override
	public void setCharacteristicType(CharacteristicType arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setCharacteristicValue(String arg0) {
		// TODO Auto-generated method stub


	}

	@Override
	public void setAdhocCharacteristicValue(String arg0) {
		adhocCharacteristicValue = arg0;
		
	}

	@Override
	public void setCharacteristicValueFk1(String arg0) {
		// TODO Auto-generated method stub
		characteristicValueFk1 = arg0;

	}

	@Override
	public void setCharacteristicValueFk2(String arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setCharacteristicValueFk3(String arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setCharacteristicValueFk4(String arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setCharacteristicValueFk5(String arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setTenderAccount(Account arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setMatchType(MatchType arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setMatchValue(String arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setSequence(BigInteger arg0) {
		// TODO Auto-generated method stub
		sequence = arg0;
		
	}

	@Override
	public Payment_Id getPaymentId() {
		// TODO Auto-generated method stub
		return null;
	}
	}