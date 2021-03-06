package com.splwg.cm.domain.common.dao;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import com.splwg.base.api.GenericBusinessObject;
import com.splwg.base.api.datatypes.DateFormat;
import com.splwg.base.api.sql.PreparedStatement;
import com.splwg.base.api.sql.SQLResultRow;
import com.splwg.shared.logging.Logger;
import com.splwg.shared.logging.LoggerFactory;
import com.splwg.tax.domain.admin.accountRelationshipType.AccountRelationshipType_Id;
import com.splwg.tax.domain.admin.personRelationshipType.PersonRelationshipType_Id;
import com.splwg.tax.domain.customerinfo.account.Account;
import com.splwg.tax.domain.customerinfo.account.Account_Id;
import com.splwg.tax.domain.customerinfo.person.Person;
import com.splwg.tax.domain.customerinfo.person.Person_Id;

/**
 * 
 * @author ADA
 */
public class CmPersonDao extends GenericBusinessObject {

	
	 /**
     *  Recherche l'acteur principal d'un compte
     */
    public static final String GET_MAIN_PER_BY_ACCT =
        "select acct_per.per_id "
                        + "from ci_acct_per acct_per "
                        + "where acct_per.acct_id = :pAccountId "
                        + "and ( acct_per.acct_rel_type_cd = :pMainAcctPer or acct_per.acct_rel_type_cd = :pPrincAcctPer ) "
                        + "AND ROWNUM = 1";

    /**
     * Logger
     */
    private static final Logger LOGGER = LoggerFactory.getLogger( CmPersonDao.class );

    final static BigInteger CIVILITE_SEQ = new BigInteger( "4" );

    final DateFormat vDateFormat = new DateFormat( CmConstants.vDateFormat );

    /**
     * Constructeur
     */
    public CmPersonDao() {
        super();
    }

    /**
     * Recherche l'acteur principal d'un compte
     * 
     * @param pAccount le compte
     * @return une personne
     */
    public Person getMainPersonByAccount( Account pAccount ) {

        return getMainPersonByAccount( pAccount.getId() );
    }

    /**
     * Recherche l'acteur principal d'un compte
     * 
     * @param pAccountId ID du compte
     * @return une personne
     */
    public Person getMainPersonByAccount( Account_Id pAccountId ) {

        final PreparedStatement vPreparedStatement = this.createPreparedStatement( GET_MAIN_PER_BY_ACCT );
        Person vPerson = null;

        try {
            vPreparedStatement.bindId( "pAccountId", pAccountId );
            vPreparedStatement.bindId( "pMainAcctPer", new AccountRelationshipType_Id( CmConstants.MAIN_ACCT_PER_RELATIONSHIP ) );
            vPreparedStatement.bindId( "pPrincAcctPer", new AccountRelationshipType_Id( CmConstants.PRINC_ACCT_PER_RELATIONSHIP ) );
            final SQLResultRow vRow = vPreparedStatement.firstRow();

            if ( vRow != null ) {
                vPerson = new Person_Id( vRow.getString( "PER_ID" ) ).getEntity();
            }
        } catch ( final Exception vException ) {
            LOGGER.error( CmConstants.EXCEPTION, vException );
        } finally {
            if ( vPreparedStatement != null ) {
                vPreparedStatement.close();
            }
        }

        return vPerson;
    }

	
    /**
     * Recherche l'acteur reli� au point de balance avec le type de relation pass� en param�tre
     * @param pPersonId Person_Id
     * @param pRelationId PersonRelationshipType_Id
     * @return List Person
     */
    public List<Person> searchPersonPersonByRelation( Person_Id pPersonId, PersonRelationshipType_Id pRelationId ) {
        
    	final PreparedStatement vRequest =
            createPreparedStatement( " SELECT per.PER_ID1, per.PER_ID2 " + " FROM CI_PER_PER per " 
            				+ " WHERE ( per.PER_ID1 = '"
                            + pPersonId.getTrimmedValue() + "'" + " OR per.PER_ID2 = '" + pPersonId.getTrimmedValue() + "' )");
                            //+ " AND per.PER_REL_TYPE_CD = '" + pRelationId.getTrimmedValue() + "'" );
                            
        final List<Person> vList = new ArrayList<Person>();
        try {
            for ( final SQLResultRow vRow : vRequest.list() ) {
                final String vResultId1 = vRow.getString( "PER_ID1" );
                final String vResultId2 = vRow.getString( "PER_ID2" );
                if ( vResultId1 != null && vResultId1.equals( pPersonId.getTrimmedValue() ) ) {
                    vList.add( new Person_Id( vResultId2 ).getEntity() );
                } else if ( vResultId2 != null && vResultId2.equals( pPersonId.getTrimmedValue() ) ) {
                    vList.add( new Person_Id( vResultId1 ).getEntity() );
                }
            }
        } finally {
            vRequest.close();
        }

        return vList;

    }


}