/****************************************************************
 * PROGRAM DESCRIPTION:
 *
 * Custom Message Repository class for Message Category 90000.
 *
 * Class Generated on 2017-07-26-12.24.10. DO NOT MODIFY
 * Any change performed manually to this class will be
 * overridden by the execution of the batch job CMMSGGEN.
 *
 ****************************************************************/
package com.splwg.cm.domain.customMessages;

import com.splwg.base.api.datatypes.Date;
import com.splwg.base.domain.common.message.AbstractMessageRepository;
import com.splwg.base.domain.common.message.MessageParameters;
import com.splwg.cm.domain.customMessages.CmMessageRepository90003.Messages;
import com.splwg.shared.common.ServerMessage;

/**
 * Custom Message Repository class for Message Category 90000.
 *
 * Class Generated on 2017-07-26-12.24.10. DO NOT MODIFY
 * Any change performed manually to this class will be
 * overridden by the execution of the batch job CMMSGGEN.
*/
public class CmMessageRepository90000 extends AbstractMessageRepository {

    /**
    * Message Category Number 90000
    */
    public static final int MESSAGE_CATEGORY = 90000;

    private static CmMessageRepository90000 instance;

    public CmMessageRepository90000() {
        super(MESSAGE_CATEGORY);
    }

    private static CmMessageRepository90000 getCommonInstance() {
        if (instance == null) {
          instance = new CmMessageRepository90000();
        }
        return instance;
    }

    /**
    * Message Text: "%1 %2 %3 %4 %5 %6 %7 %8 %9"
    * @param param1 String Value for message parameter %1
    * @param param2 String Value for message parameter %2
    * @param param3 String Value for message parameter %3
    * @param param4 String Value for message parameter %4
    * @param param5 String Value for message parameter %5
    * @param param6 String Value for message parameter %6
    * @param param7 String Value for message parameter %7
    * @param param8 String Value for message parameter %8
    * @param param9 String Value for message parameter %9
    * @return ServerMessage
    */
    public static ServerMessage MSG_1(String param1, String param2, String param3, String param4, String param5, String param6, String param7, String param8, String param9) {
        MessageParameters params = new MessageParameters();
        params.addRawString(param1);
        params.addRawString(param2);
        params.addRawString(param3);
        params.addRawString(param4);
        params.addRawString(param5);
        params.addRawString(param6);
        params.addRawString(param7);
        params.addRawString(param8);
        params.addRawString(param9);
        return getCommonInstance().getMessage(Messages.MSG_1, params);
    }

    /**
    * Message Text: "Your session is waiting for a resource locked by another session."
    * @return ServerMessage
    */
    public static ServerMessage MSG_2() {
        MessageParameters params = new MessageParameters();
        return getCommonInstance().getMessage(Messages.MSG_2, params);
    }

    /**
    * Message Text: "SQL Error code %1 occurred in module %2"
    * @param param1 String Value for message parameter %1
    * @param param2 String Value for message parameter %2
    * @return ServerMessage
    */
    public static ServerMessage MSG_3(String param1, String param2) {
        MessageParameters params = new MessageParameters();
        params.addRawString(param1);
        params.addRawString(param2);
        return getCommonInstance().getMessage(Messages.MSG_3, params);
    }

    /**
    * Message Text: "Remote call variable is not found."
    * @return ServerMessage
    */
    public static ServerMessage MSG_4() {
        MessageParameters params = new MessageParameters();
        return getCommonInstance().getMessage(Messages.MSG_4, params);
    }

    /**
    * Message Text: "Invalid SQL statement.%1 %2  Contact your system administrator."
    * @param param1 String Value for message parameter %1
    * @param param2 String Value for message parameter %2
    * @return ServerMessage
    */
    public static ServerMessage MSG_5(String param1, String param2) {
        MessageParameters params = new MessageParameters();
        params.addRawString(param1);
        params.addRawString(param2);
        return getCommonInstance().getMessage(Messages.MSG_5, params);
    }

    /**
    * Message Text: "Invalid action in SQL statement.  Contact your system administrator."
    * @return ServerMessage
    */
    public static ServerMessage MSG_6() {
        MessageParameters params = new MessageParameters();
        return getCommonInstance().getMessage(Messages.MSG_6, params);
    }

    /**
    * Message Text: "Too many open cursors in the registry.  Contact your system administrator."
    * @return ServerMessage
    */
    public static ServerMessage MSG_7() {
        MessageParameters params = new MessageParameters();
        return getCommonInstance().getMessage(Messages.MSG_7, params);
    }

    /**
    * Message Text: "Concurrency Error.  Please retry this transaction."
    * @return ServerMessage
    */
    public static ServerMessage MSG_8() {
        MessageParameters params = new MessageParameters();
        return getCommonInstance().getMessage(Messages.MSG_8, params);
    }

    public static ServerMessage MSG_10() {
        MessageParameters params = new MessageParameters();
        return getCommonInstance().getMessage(Messages.MSG_10, params);
    }
    
    public static ServerMessage MSG_11(String param1) {
        MessageParameters params = new MessageParameters();
        params.addRawString(param1);
        return getCommonInstance().getMessage(Messages.MSG_11, params);
    }
    
    public static ServerMessage MSG_12(String param1) {
        MessageParameters params = new MessageParameters();
        params.addRawString(param1);
        return getCommonInstance().getMessage(Messages.MSG_12, params);
    }
    
    
    public static ServerMessage MSG_13(String param1,String param2) {
        MessageParameters params = new MessageParameters();
        params.addRawString(param1);
        params.addRawString(param2);
        return getCommonInstance().getMessage(Messages.MSG_13, params);
    }
    
    public static ServerMessage MSG_14() {
        MessageParameters params = new MessageParameters();
        return getCommonInstance().getMessage(Messages.MSG_14, params);
    }
    
    public static ServerMessage MSG_15() {
        MessageParameters params = new MessageParameters();
        return getCommonInstance().getMessage(Messages.MSG_15, params);
    }
    
    public static ServerMessage MSG_10001() {
        MessageParameters params = new MessageParameters();
        return getCommonInstance().getMessage(Messages.MSG_10001, params);
    }
    
    /**
    * Message Text: "SQL Statement Overflow."
    * @return ServerMessage
    */
    public static ServerMessage MSG_20() {
        MessageParameters params = new MessageParameters();
        return getCommonInstance().getMessage(Messages.MSG_20, params);
    }

    /**
    * Message Text: "Action invalid"
    * @return ServerMessage
    */
    public static ServerMessage MSG_201() {
        MessageParameters params = new MessageParameters();
        return getCommonInstance().getMessage(Messages.MSG_201, params);
    }

    /**
    * Message Text: "More switch invalid"
    * @return ServerMessage
    */
    public static ServerMessage MSG_202() {
        MessageParameters params = new MessageParameters();
        return getCommonInstance().getMessage(Messages.MSG_202, params);
    }

    /**
    * Message Text: "List count invalid"
    * @return ServerMessage
    */
    public static ServerMessage MSG_203() {
        MessageParameters params = new MessageParameters();
        return getCommonInstance().getMessage(Messages.MSG_203, params);
    }

    /**
    * Message Text: "Must exist switch invalid"
    * @return ServerMessage
    */
    public static ServerMessage MSG_204() {
        MessageParameters params = new MessageParameters();
        return getCommonInstance().getMessage(Messages.MSG_204, params);
    }

    /**
    * Message Text: "Start Date must be on or before End Date"
    * @return ServerMessage
    */
    public static ServerMessage MSG_205() {
        MessageParameters params = new MessageParameters();
        return getCommonInstance().getMessage(Messages.MSG_205, params);
    }

    /**
    * Message Text: "Are you sure you want to delete this object?"
    * @return ServerMessage
    */
    public static ServerMessage MSG_206() {
        MessageParameters params = new MessageParameters();
        return getCommonInstance().getMessage(Messages.MSG_206, params);
    }

    /**
    * Message Text: "Required value(s) %1 %2 %3 %4 %5 %6 %7 %8 %9 not provided."
    * @param param1 String Value for message parameter %1
    * @param param2 String Value for message parameter %2
    * @param param3 String Value for message parameter %3
    * @param param4 String Value for message parameter %4
    * @param param5 String Value for message parameter %5
    * @param param6 String Value for message parameter %6
    * @param param7 String Value for message parameter %7
    * @param param8 String Value for message parameter %8
    * @param param9 String Value for message parameter %9
    * @return ServerMessage
    */
    public static ServerMessage MSG_207(String param1, String param2, String param3, String param4, String param5, String param6, String param7, String param8, String param9) {
        MessageParameters params = new MessageParameters();
        params.addRawString(param1);
        params.addRawString(param2);
        params.addRawString(param3);
        params.addRawString(param4);
        params.addRawString(param5);
        params.addRawString(param6);
        params.addRawString(param7);
        params.addRawString(param8);
        params.addRawString(param9);
        return getCommonInstance().getMessage(Messages.MSG_207, params);
    }

    /**
    * Message Text: "%1 %3 must be on or before %2 %4."
    * @param param1 String Value for message parameter %1
    * @param param2 String Value for message parameter %2
    * @param param3 String Value for message parameter %3
    * @param param4 String Value for message parameter %4
    * @return ServerMessage
    */
    public static ServerMessage MSG_208(String param1, String param2, String param3, String param4) {
        MessageParameters params = new MessageParameters();
        params.addRawString(param1);
        params.addRawString(param2);
        params.addRawString(param3);
        params.addRawString(param4);
        return getCommonInstance().getMessage(Messages.MSG_208, params);
    }

    /**
    * Message Text: "%1 %2 duplicate key error"
    * @param param1 String Value for message parameter %1
    * @param param2 String Value for message parameter %2
    * @return ServerMessage
    */
    public static ServerMessage MSG_251(String param1, String param2) {
        MessageParameters params = new MessageParameters();
        params.addRawString(param1);
        params.addRawString(param2);
        return getCommonInstance().getMessage(Messages.MSG_251, params);
    }

    /**
    * Message Text: "%1 %2 not found"
    * @param param1 String Value for message parameter %1
    * @param param2 String Value for message parameter %2
    * @return ServerMessage
    */
    public static ServerMessage MSG_252(String param1, String param2) {
        MessageParameters params = new MessageParameters();
        params.addRawString(param1);
        params.addRawString(param2);
        return getCommonInstance().getMessage(Messages.MSG_252, params);
    }

    /**
    * Message Text: "%1 field missing."
    * @param param1 String Value for message parameter %1
    * @return ServerMessage
    */
    public static ServerMessage MSG_253(String param1) {
        MessageParameters params = new MessageParameters();
        params.addRawString(param1);
        return getCommonInstance().getMessage(Messages.MSG_253, params);
    }

    /**
    * Message Text: "%1 field not numeric"
    * @param param1 String Value for message parameter %1
    * @return ServerMessage
    */
    public static ServerMessage MSG_254(String param1) {
        MessageParameters params = new MessageParameters();
        params.addRawString(param1);
        return getCommonInstance().getMessage(Messages.MSG_254, params);
    }

    /**
    * Message Text: "Program array limit overflow happens at position %1"
    * @param param1 String Value for message parameter %1
    * @return ServerMessage
    */
    public static ServerMessage MSG_255(String param1) {
        MessageParameters params = new MessageParameters();
        params.addRawString(param1);
        return getCommonInstance().getMessage(Messages.MSG_255, params);
    }

    /**
    * Message Text: "%1 %2 field invalid"
    * @param param1 String Value for message parameter %1
    * @param param2 String Value for message parameter %2
    * @return ServerMessage
    */
    public static ServerMessage MSG_256(String param1, String param2) {
        MessageParameters params = new MessageParameters();
        params.addRawString(param1);
        params.addRawString(param2);
        return getCommonInstance().getMessage(Messages.MSG_256, params);
    }

    /**
    * Message Text: "Record not found for %1 %2 %3 %4 %5"
    * @param param1 String Value for message parameter %1
    * @param param2 String Value for message parameter %2
    * @param param3 String Value for message parameter %3
    * @param param4 String Value for message parameter %4
    * @param param5 String Value for message parameter %5
    * @return ServerMessage
    */
    public static ServerMessage MSG_257(String param1, String param2, String param3, String param4, String param5) {
        MessageParameters params = new MessageParameters();
        params.addRawString(param1);
        params.addRawString(param2);
        params.addRawString(param3);
        params.addRawString(param4);
        params.addRawString(param5);
        return getCommonInstance().getMessage(Messages.MSG_257, params);
    }

    /**
    * Message Text: "No changes may be made to %1 on an existing record."
    * @param param1 String Value for message parameter %1
    * @return ServerMessage
    */
    public static ServerMessage MSG_258(String param1) {
        MessageParameters params = new MessageParameters();
        params.addRawString(param1);
        return getCommonInstance().getMessage(Messages.MSG_258, params);
    }

    /**
    * Message Text: "No %1 Information."
    * @param param1 String Value for message parameter %1
    * @return ServerMessage
    */
    public static ServerMessage MSG_259(String param1) {
        MessageParameters params = new MessageParameters();
        params.addRawString(param1);
        return getCommonInstance().getMessage(Messages.MSG_259, params);
    }

    /**
    * Message Text: "%1 %2 %3 %4 %5 %6 %7 %8 %9 combination, duplicate key error"
    * @param param1 String Value for message parameter %1
    * @param param2 String Value for message parameter %2
    * @param param3 String Value for message parameter %3
    * @param param4 String Value for message parameter %4
    * @param param5 String Value for message parameter %5
    * @param param6 String Value for message parameter %6
    * @param param7 String Value for message parameter %7
    * @param param8 String Value for message parameter %8
    * @param param9 String Value for message parameter %9
    * @return ServerMessage
    */
    public static ServerMessage MSG_260(String param1, String param2, String param3, String param4, String param5, String param6, String param7, String param8, String param9) {
        MessageParameters params = new MessageParameters();
        params.addRawString(param1);
        params.addRawString(param2);
        params.addRawString(param3);
        params.addRawString(param4);
        params.addRawString(param5);
        params.addRawString(param6);
        params.addRawString(param7);
        params.addRawString(param8);
        params.addRawString(param9);
        return getCommonInstance().getMessage(Messages.MSG_260, params);
    }

    /**
    * Message Text: "%1 %2 %3 %4 %5 %6 %7 %8 %9 combination, not found"
    * @param param1 String Value for message parameter %1
    * @param param2 String Value for message parameter %2
    * @param param3 String Value for message parameter %3
    * @param param4 String Value for message parameter %4
    * @param param5 String Value for message parameter %5
    * @param param6 String Value for message parameter %6
    * @param param7 String Value for message parameter %7
    * @param param8 String Value for message parameter %8
    * @param param9 String Value for message parameter %9
    * @return ServerMessage
    */
    public static ServerMessage MSG_261(String param1, String param2, String param3, String param4, String param5, String param6, String param7, String param8, String param9) {
        MessageParameters params = new MessageParameters();
        params.addRawString(param1);
        params.addRawString(param2);
        params.addRawString(param3);
        params.addRawString(param4);
        params.addRawString(param5);
        params.addRawString(param6);
        params.addRawString(param7);
        params.addRawString(param8);
        params.addRawString(param9);
        return getCommonInstance().getMessage(Messages.MSG_261, params);
    }

    /**
    * Message Text: "Invalid Date. Format should be %1."
    * @param param1 String Value for message parameter %1
    * @return ServerMessage
    */
    public static ServerMessage MSG_262(String param1) {
        MessageParameters params = new MessageParameters();
        params.addRawString(param1);
        return getCommonInstance().getMessage(Messages.MSG_262, params);
    }

    /**
    * Message Text: "%1 field should be greater than or equal to zero."
    * @param param1 String Value for message parameter %1
    * @return ServerMessage
    */
    public static ServerMessage MSG_263(String param1) {
        MessageParameters params = new MessageParameters();
        params.addRawString(param1);
        return getCommonInstance().getMessage(Messages.MSG_263, params);
    }

    /**
    * Message Text: "This entry exists on table %1 (%2 %3)"
    * @param param1 String Value for message parameter %1
    * @param param2 String Value for message parameter %2
    * @param param3 String Value for message parameter %3
    * @return ServerMessage
    */
    public static ServerMessage MSG_264(String param1, String param2, String param3) {
        MessageParameters params = new MessageParameters();
        params.addRawString(param1);
        params.addRawString(param2);
        params.addRawString(param3);
        return getCommonInstance().getMessage(Messages.MSG_264, params);
    }

    /**
    * Message Text: "Module not enabled in the Feature configuration."
    * @return ServerMessage
    */
    public static ServerMessage MSG_265() {
        MessageParameters params = new MessageParameters();
        return getCommonInstance().getMessage(Messages.MSG_265, params);
    }

    /**
    * Message Text: "This entry exists on table %1 (%2 %3 %4 %5 %6 %7 %8 %9)"
    * @param param1 String Value for message parameter %1
    * @param param2 String Value for message parameter %2
    * @param param3 String Value for message parameter %3
    * @param param4 String Value for message parameter %4
    * @param param5 String Value for message parameter %5
    * @param param6 String Value for message parameter %6
    * @param param7 String Value for message parameter %7
    * @param param8 String Value for message parameter %8
    * @param param9 String Value for message parameter %9
    * @return ServerMessage
    */
    public static ServerMessage MSG_266(String param1, String param2, String param3, String param4, String param5, String param6, String param7, String param8, String param9) {
        MessageParameters params = new MessageParameters();
        params.addRawString(param1);
        params.addRawString(param2);
        params.addRawString(param3);
        params.addRawString(param4);
        params.addRawString(param5);
        params.addRawString(param6);
        params.addRawString(param7);
        params.addRawString(param8);
        params.addRawString(param9);
        return getCommonInstance().getMessage(Messages.MSG_266, params);
    }

    /**
    * Message Text: "<alert> License key has expired."
    * @return ServerMessage
    */
    public static ServerMessage MSG_267() {
        MessageParameters params = new MessageParameters();
        return getCommonInstance().getMessage(Messages.MSG_267, params);
    }

    /**
    * Message Text: "<warning> License key will expire in %1 day(s)."
    * @param param1 String Value for message parameter %1
    * @return ServerMessage
    */
    public static ServerMessage MSG_268(String param1) {
        MessageParameters params = new MessageParameters();
        params.addRawString(param1);
        return getCommonInstance().getMessage(Messages.MSG_268, params);
    }

    /**
    * Message Text: "Error in the get more process. At least 1 of the following field(s): %1%2%3%4%5%6%7%8%9  must be specified."
    * @param param1 String Value for message parameter %1
    * @param param2 String Value for message parameter %2
    * @param param3 String Value for message parameter %3
    * @param param4 String Value for message parameter %4
    * @param param5 String Value for message parameter %5
    * @param param6 String Value for message parameter %6
    * @param param7 String Value for message parameter %7
    * @param param8 String Value for message parameter %8
    * @param param9 String Value for message parameter %9
    * @return ServerMessage
    */
    public static ServerMessage MSG_269(String param1, String param2, String param3, String param4, String param5, String param6, String param7, String param8, String param9) {
        MessageParameters params = new MessageParameters();
        params.addRawString(param1);
        params.addRawString(param2);
        params.addRawString(param3);
        params.addRawString(param4);
        params.addRawString(param5);
        params.addRawString(param6);
        params.addRawString(param7);
        params.addRawString(param8);
        params.addRawString(param9);
        return getCommonInstance().getMessage(Messages.MSG_269, params);
    }

    /**
    * Message Text: "%1 field should be greater than zero."
    * @param param1 String Value for message parameter %1
    * @return ServerMessage
    */
    public static ServerMessage MSG_270(String param1) {
        MessageParameters params = new MessageParameters();
        params.addRawString(param1);
        return getCommonInstance().getMessage(Messages.MSG_270, params);
    }

    /**
    * Message Text: "Parameter value %1 is not valid for algorithm %2."
    * @param param1 String Value for message parameter %1
    * @param param2 String Value for message parameter %2
    * @return ServerMessage
    */
    public static ServerMessage MSG_300(String param1, String param2) {
        MessageParameters params = new MessageParameters();
        params.addRawString(param1);
        params.addRawString(param2);
        return getCommonInstance().getMessage(Messages.MSG_300, params);
    }

    /**
    * Message Text: "Algorithm parameter %1 is missing."
    * @param param1 String Value for message parameter %1
    * @return ServerMessage
    */
    public static ServerMessage MSG_301(String param1) {
        MessageParameters params = new MessageParameters();
        params.addRawString(param1);
        return getCommonInstance().getMessage(Messages.MSG_301, params);
    }

    /**
    * Message Text: "Algorithm parameter %1 must be numeric."
    * @param param1 String Value for message parameter %1
    * @return ServerMessage
    */
    public static ServerMessage MSG_302(String param1) {
        MessageParameters params = new MessageParameters();
        params.addRawString(param1);
        return getCommonInstance().getMessage(Messages.MSG_302, params);
    }

    /**
    * Message Text: "%1 %2 %3 %4 %5 %6 %7 %8 %9 you have entered already exists."
    * @param param1 String Value for message parameter %1
    * @param param2 String Value for message parameter %2
    * @param param3 String Value for message parameter %3
    * @param param4 String Value for message parameter %4
    * @param param5 String Value for message parameter %5
    * @param param6 String Value for message parameter %6
    * @param param7 String Value for message parameter %7
    * @param param8 String Value for message parameter %8
    * @param param9 String Value for message parameter %9
    * @return ServerMessage
    */
    public static ServerMessage MSG_303(String param1, String param2, String param3, String param4, String param5, String param6, String param7, String param8, String param9) {
        MessageParameters params = new MessageParameters();
        params.addRawString(param1);
        params.addRawString(param2);
        params.addRawString(param3);
        params.addRawString(param4);
        params.addRawString(param5);
        params.addRawString(param6);
        params.addRawString(param7);
        params.addRawString(param8);
        params.addRawString(param9);
        return getCommonInstance().getMessage(Messages.MSG_303, params);
    }

    /**
    * Message Text: "The entry you are trying to duplicate from no longer exists."
    * @return ServerMessage
    */
    public static ServerMessage MSG_304() {
        MessageParameters params = new MessageParameters();
        return getCommonInstance().getMessage(Messages.MSG_304, params);
    }

    /**
    * Message Text: "While duplicating the data, an error (%1, %2) was detected for %3 %4 %5 %6 %7 %8 %9. "
    * @param param1 String Value for message parameter %1
    * @param param2 String Value for message parameter %2
    * @param param3 String Value for message parameter %3
    * @param param4 String Value for message parameter %4
    * @param param5 String Value for message parameter %5
    * @param param6 String Value for message parameter %6
    * @param param7 String Value for message parameter %7
    * @param param8 String Value for message parameter %8
    * @param param9 String Value for message parameter %9
    * @return ServerMessage
    */
    public static ServerMessage MSG_305(String param1, String param2, String param3, String param4, String param5, String param6, String param7, String param8, String param9) {
        MessageParameters params = new MessageParameters();
        params.addRawString(param1);
        params.addRawString(param2);
        params.addRawString(param3);
        params.addRawString(param4);
        params.addRawString(param5);
        params.addRawString(param6);
        params.addRawString(param7);
        params.addRawString(param8);
        params.addRawString(param9);
        return getCommonInstance().getMessage(Messages.MSG_305, params);
    }

    /**
    * Message Text: "You do not have security rights for this action."
    * @return ServerMessage
    */
    public static ServerMessage MSG_401() {
        MessageParameters params = new MessageParameters();
        return getCommonInstance().getMessage(Messages.MSG_401, params);
    }

    /**
    * Message Text: "You are not allowed to access (directly/indirectly) to this account."
    * @return ServerMessage
    */
    public static ServerMessage MSG_501() {
        MessageParameters params = new MessageParameters();
        return getCommonInstance().getMessage(Messages.MSG_501, params);
    }

    /**
    * Message Text: "An attempt to log a To Do Entry for message %1, %2 failed with message %3, %4."
    * @param param1 String Value for message parameter %1
    * @param param2 String Value for message parameter %2
    * @param param3 String Value for message parameter %3
    * @param param4 String Value for message parameter %4
    * @return ServerMessage
    */
    public static ServerMessage MSG_601(String param1, String param2, String param3, String param4) {
        MessageParameters params = new MessageParameters();
        params.addRawString(param1);
        params.addRawString(param2);
        params.addRawString(param3);
        params.addRawString(param4);
        return getCommonInstance().getMessage(Messages.MSG_601, params);
    }

    /**
    * Message Text: "Must Exist SW is required"
    * @return ServerMessage
    */
    public static ServerMessage MSG_901() {
        MessageParameters params = new MessageParameters();
        return getCommonInstance().getMessage(Messages.MSG_901, params);
    }

    /**
    * Message Text: "Must Exist Switch (%1) is invalid"
    * @param param1 String Value for message parameter %1
    * @return ServerMessage
    */
    public static ServerMessage MSG_902(String param1) {
        MessageParameters params = new MessageParameters();
        params.addRawString(param1);
        return getCommonInstance().getMessage(Messages.MSG_902, params);
    }

    /**
    * Message Text: "Process Date/Time is required"
    * @return ServerMessage
    */
    public static ServerMessage MSG_903() {
        MessageParameters params = new MessageParameters();
        return getCommonInstance().getMessage(Messages.MSG_903, params);
    }

    /**
    * Message Text: "Batch Code is required"
    * @return ServerMessage
    */
    public static ServerMessage MSG_904() {
        MessageParameters params = new MessageParameters();
        return getCommonInstance().getMessage(Messages.MSG_904, params);
    }

    /**
    * Message Text: "Thread Count is required"
    * @return ServerMessage
    */
    public static ServerMessage MSG_905() {
        MessageParameters params = new MessageParameters();
        return getCommonInstance().getMessage(Messages.MSG_905, params);
    }

    /**
    * Message Text: "Thread Number is required"
    * @return ServerMessage
    */
    public static ServerMessage MSG_906() {
        MessageParameters params = new MessageParameters();
        return getCommonInstance().getMessage(Messages.MSG_906, params);
    }

    /**
    * Message Text: "Maximum rollback reached"
    * @return ServerMessage
    */
    public static ServerMessage MSG_907() {
        MessageParameters params = new MessageParameters();
        return getCommonInstance().getMessage(Messages.MSG_907, params);
    }

    /**
    * Message Text: "Process instance error"
    * @return ServerMessage
    */
    public static ServerMessage MSG_908() {
        MessageParameters params = new MessageParameters();
        return getCommonInstance().getMessage(Messages.MSG_908, params);
    }

    /**
    * Message Text: "SQL statement overflow"
    * @return ServerMessage
    */
    public static ServerMessage MSG_909() {
        MessageParameters params = new MessageParameters();
        return getCommonInstance().getMessage(Messages.MSG_909, params);
    }

    /**
    * Message Text: "Business Date (%1) override (%2)"
    * @param param1 String Value for message parameter %1
    * @param param2 String Value for message parameter %2
    * @return ServerMessage
    */
    public static ServerMessage MSG_910(String param1, String param2) {
        MessageParameters params = new MessageParameters();
        params.addRawString(param1);
        params.addRawString(param2);
        return getCommonInstance().getMessage(Messages.MSG_910, params);
    }

    /**
    * Message Text: "Overflow in compute statement (%1)"
    * @param param1 String Value for message parameter %1
    * @return ServerMessage
    */
    public static ServerMessage MSG_911(String param1) {
        MessageParameters params = new MessageParameters();
        params.addRawString(param1);
        return getCommonInstance().getMessage(Messages.MSG_911, params);
    }

    /**
    * Message Text: "Action is required."
    * @return ServerMessage
    */
    public static ServerMessage MSG_912() {
        MessageParameters params = new MessageParameters();
        return getCommonInstance().getMessage(Messages.MSG_912, params);
    }

    /**
    * Message Text: "Action is invalid"
    * @return ServerMessage
    */
    public static ServerMessage MSG_913() {
        MessageParameters params = new MessageParameters();
        return getCommonInstance().getMessage(Messages.MSG_913, params);
    }

    /**
    * Message Text: "Process Date/Time is required"
    * @return ServerMessage
    */
    public static ServerMessage MSG_914() {
        MessageParameters params = new MessageParameters();
        return getCommonInstance().getMessage(Messages.MSG_914, params);
    }

    /**
    * Message Text: "File Name overflow."
    * @return ServerMessage
    */
    public static ServerMessage MSG_915() {
        MessageParameters params = new MessageParameters();
        return getCommonInstance().getMessage(Messages.MSG_915, params);
    }

    /**
    * Message Text: "The Thread Number has too few digits."
    * @return ServerMessage
    */
    public static ServerMessage MSG_916() {
        MessageParameters params = new MessageParameters();
        return getCommonInstance().getMessage(Messages.MSG_916, params);
    }

    /**
    * Message Text: "Owner Flag (%1) is invalid."
    * @param param1 String Value for message parameter %1
    * @return ServerMessage
    */
    public static ServerMessage MSG_917(String param1) {
        MessageParameters params = new MessageParameters();
        params.addRawString(param1);
        return getCommonInstance().getMessage(Messages.MSG_917, params);
    }
    
    
    /**
     * Le total des salari�s renseign� dans la section synth�se (1%) est diff�rent du nombre de salari�s d�clar�s (2%)
     * @param totalSalOnSyntheseSection
     * @param totalSalOnEmployeeSection
     * @return ServerMessage
     */
    public static ServerMessage MSG_10004(String totalSalOnSyntheseSection, String totalSalOnEmployeeSection) {
        MessageParameters params = new MessageParameters();
        params.addRawString(totalSalOnSyntheseSection);
        params.addRawString(totalSalOnEmployeeSection);
        return getCommonInstance().getMessage(Messages.MSG_10004, params);
    }
    /**
     * La date de sortie du salari� %1 %2  doit etre sup�rieur a la date de d�but de la p�riode de cotisation : %3
     * @param prenom
     * @param nom
     * @param dateDebutPeriode
     * @return ServerMessage
     */
    public static ServerMessage MSG_10005(String prenom, String nom, String dateDebutPeriode) {
        MessageParameters params = new MessageParameters();
        params.addRawString(prenom);
        params.addRawString(nom);
        params.addRawString(dateDebutPeriode);
        return getCommonInstance().getMessage(Messages.MSG_10005, params);
    }
	
	public static ServerMessage MSG_6000(String paramValue) {
        MessageParameters params = new MessageParameters();
        params.addRawString(paramValue);
        return getCommonInstance().getMessage(Messages.MSG_6000, params);
    }
    
	
	public static ServerMessage MSG_6001(String paramValue) {
        MessageParameters params = new MessageParameters();
        params.addRawString(paramValue);
        return getCommonInstance().getMessage(Messages.MSG_6001, params);
    }
    
 	public static ServerMessage MSG_6002(String paramValue) {
        MessageParameters params = new MessageParameters();
        params.addRawString(paramValue);
        return getCommonInstance().getMessage(Messages.MSG_6002, params);
    }
    
    public static ServerMessage MSG_6003(String paramValue) {
        MessageParameters params = new MessageParameters();
        params.addRawString(paramValue);
        return getCommonInstance().getMessage(Messages.MSG_6003, params);
    }
    
    public static ServerMessage MSG_6004(String paramValue) {
        MessageParameters params = new MessageParameters();
        params.addRawString(paramValue);
        return getCommonInstance().getMessage(Messages.MSG_6004, params);
    }
    
    public static ServerMessage MSG_6005(String paramValue) {
        MessageParameters params = new MessageParameters();
        params.addRawString(paramValue);
        return getCommonInstance().getMessage(Messages.MSG_6005, params);
    }
    
    public static ServerMessage MSG_6006(String paramValue) {
        MessageParameters params = new MessageParameters();
        params.addRawString(paramValue);
        return getCommonInstance().getMessage(Messages.MSG_6006, params);
    }
    
    public static ServerMessage MSG_7000() {
        return getCommonInstance().getMessage(Messages.MSG_7000);
    }
    
    public static ServerMessage MSG_7001() {
        return getCommonInstance().getMessage(Messages.MSG_7001);
    }
    
    public static ServerMessage MSG_7002(String idType, String idNumber) {
        MessageParameters params = new MessageParameters();
        params.addRawString(idNumber);
        params.addRawString(idType);
        return getCommonInstance().getMessage(Messages.MSG_7002, params);
    }
    
    
    public static ServerMessage MSG_7003(String idNumber) {
        MessageParameters params = new MessageParameters();
        params.addRawString(idNumber);
       // params.addRawString(idType);
        return getCommonInstance().getMessage(Messages.MSG_7003, params);
    }
    
    public static ServerMessage MSG_7004() {
        MessageParameters params = new MessageParameters();
        //params.addRawString(typeDeclaration);
       // params.addRawString(idType);
        return getCommonInstance().getMessage(Messages.MSG_7004, params);
    }
    
    public static ServerMessage MSG_7005() {
        MessageParameters params = new MessageParameters();
        return getCommonInstance().getMessage(Messages.MSG_7005, params);
    }
  
    public static ServerMessage MSG_7006() {
        //MessageParameters params = new MessageParameters();
        return getCommonInstance().getMessage(Messages.MSG_7006);
    }
    
    public static ServerMessage MSG_7007(Date dateDebutCotisation) {
        MessageParameters params = new MessageParameters();
        params.addRawString(dateDebutCotisation.toString());
        return getCommonInstance().getMessage(Messages.MSG_7007, params);
    }
    
    public static ServerMessage MSG_7008(Date dateFinCotisation) {
        MessageParameters params = new MessageParameters();
        params.addRawString(dateFinCotisation.toString());
        return getCommonInstance().getMessage(Messages.MSG_7008, params);
    }
    
    public static ServerMessage MSG_7009(Date dateDebutCotisation, Date dateFinCotisation) {
        MessageParameters params = new MessageParameters();
        params.addRawString(dateDebutCotisation.toString());
        params.addRawString(dateFinCotisation.toString());
        return getCommonInstance().getMessage(Messages.MSG_7009, params);
    }
    
    public static ServerMessage MSG_7010(Date dateDebutCotisation, Date dateFinCotisation) {
        MessageParameters params = new MessageParameters();
        params.addRawString(dateDebutCotisation.toString());
        params.addRawString(dateFinCotisation.toString());
        return getCommonInstance().getMessage(Messages.MSG_7010, params);
    }
    
    public static ServerMessage MSG_7011(String identifiant) {
        MessageParameters params = new MessageParameters();
        params.addRawString(identifiant);
        //params.addRawString(prenom);
        //params.addRawString(nom);
        return getCommonInstance().getMessage(Messages.MSG_7011, params);
    }
    
    public static ServerMessage MSG_7012(String identifiant) {
        MessageParameters params = new MessageParameters();
        params.addRawString(identifiant);
        return getCommonInstance().getMessage(Messages.MSG_7012, params);
    }
    
    public static ServerMessage MSG_7013(String identifiant) {
        MessageParameters params = new MessageParameters();
        params.addRawString(identifiant);
        return getCommonInstance().getMessage(Messages.MSG_7013, params);
    }
    
    public static ServerMessage MSG_7014(String identifiant) {
        MessageParameters params = new MessageParameters();
        params.addRawString(identifiant);
        return getCommonInstance().getMessage(Messages.MSG_7014, params);
    }
    
    public static ServerMessage MSG_7016(String identifiant) {
        MessageParameters params = new MessageParameters();
        params.addRawString(identifiant);
        return getCommonInstance().getMessage(Messages.MSG_7016, params);
    }
    public static ServerMessage MSG_7017(String identifiant) {
        MessageParameters params = new MessageParameters();
        params.addRawString(identifiant);
        return getCommonInstance().getMessage(Messages.MSG_7017, params);
    }
    public static ServerMessage MSG_7018(String identifiant) {
        MessageParameters params = new MessageParameters();
        params.addRawString(identifiant);
        return getCommonInstance().getMessage(Messages.MSG_7018, params);
    }
    public static ServerMessage MSG_7019(String identifiant) {
        MessageParameters params = new MessageParameters();
        params.addRawString(identifiant);
        return getCommonInstance().getMessage(Messages.MSG_7019, params);
    }
    public static ServerMessage MSG_7020(String identifiant) {
        MessageParameters params = new MessageParameters();
        params.addRawString(identifiant);
        return getCommonInstance().getMessage(Messages.MSG_7020, params);
    }
    public static ServerMessage MSG_7021(String identifiant) {
        MessageParameters params = new MessageParameters();
        params.addRawString(identifiant);
        return getCommonInstance().getMessage(Messages.MSG_7021, params);
    }
    public static ServerMessage MSG_7022(String identifiant) {
        MessageParameters params = new MessageParameters();
        params.addRawString(identifiant);
        return getCommonInstance().getMessage(Messages.MSG_7022, params);
    }
    public static ServerMessage MSG_7023(String identifiant) {
        MessageParameters params = new MessageParameters();
        params.addRawString(identifiant);
        return getCommonInstance().getMessage(Messages.MSG_7023, params);
    }
    
    public static ServerMessage MSG_7024(String identifiant) {
        MessageParameters params = new MessageParameters();
        params.addRawString(identifiant);
        return getCommonInstance().getMessage(Messages.MSG_7024, params);
    }
    public static ServerMessage MSG_7025(String identifiant) {
        MessageParameters params = new MessageParameters();
        params.addRawString(identifiant);
        return getCommonInstance().getMessage(Messages.MSG_7025, params);
    }
    public static ServerMessage MSG_7026(String identifiant) {
        MessageParameters params = new MessageParameters();
        params.addRawString(identifiant);
        return getCommonInstance().getMessage(Messages.MSG_7026, params);
    }
    public static ServerMessage MSG_7027(String identifiant) {
        MessageParameters params = new MessageParameters();
        params.addRawString(identifiant);
        return getCommonInstance().getMessage(Messages.MSG_7027, params);
    }
    
    public static ServerMessage MSG_7028() {
        MessageParameters params = new MessageParameters();
        //params.addRawString(identifiant);
        return getCommonInstance().getMessage(Messages.MSG_7028, params);
    }
  
    public static ServerMessage MSG_7029(String identifiant) {
        MessageParameters params = new MessageParameters();
        params.addRawString(identifiant);
        return getCommonInstance().getMessage(Messages.MSG_7029, params);
    }
    public static ServerMessage MSG_7030(String identifiant) {
        MessageParameters params = new MessageParameters();
        params.addRawString(identifiant);
        return getCommonInstance().getMessage(Messages.MSG_7030, params);
    }
    public static ServerMessage MSG_7031(String identifiant) {
        MessageParameters params = new MessageParameters();
        params.addRawString(identifiant);
        return getCommonInstance().getMessage(Messages.MSG_7031, params);
    }
    public static ServerMessage MSG_7032(String identifiant) {
        MessageParameters params = new MessageParameters();
        params.addRawString(identifiant);
        return getCommonInstance().getMessage(Messages.MSG_7032, params);
    }
  
    public static ServerMessage MSG_7033() {
        MessageParameters params = new MessageParameters();
        //params.addRawString(identifiant);
        return getCommonInstance().getMessage(Messages.MSG_7033, params);
    }
    
    public static ServerMessage MSG_7034(String identifiant) {
        MessageParameters params = new MessageParameters();
        params.addRawString(identifiant);
        return getCommonInstance().getMessage(Messages.MSG_7034, params);
    }
    
    public static ServerMessage MSG_7035(String identifiant) {
        MessageParameters params = new MessageParameters();
        params.addRawString(identifiant);
        return getCommonInstance().getMessage(Messages.MSG_7035, params);
    }
    
    public static ServerMessage MSG_7036(String identifiant) {
        MessageParameters params = new MessageParameters();
        params.addRawString(identifiant);
        return getCommonInstance().getMessage(Messages.MSG_7036, params);
    }
    
    public static ServerMessage MSG_7037(String identifiant) {
        MessageParameters params = new MessageParameters();
        params.addRawString(identifiant);
        return getCommonInstance().getMessage(Messages.MSG_7037, params);
    }
    
    public static ServerMessage MSG_7038(String identifiant) {
        MessageParameters params = new MessageParameters();
        params.addRawString(identifiant);
        return getCommonInstance().getMessage(Messages.MSG_7038, params);
    }


    public static class Messages {
		public static final int MSG_6000 = 6000;
    	public static final int MSG_6001 = 6001;
        public static final int MSG_6002 = 6002;
        public static final int MSG_6003 = 6003;
        public static final int MSG_6004 = 6004;
        public static final int MSG_6005 = 6005;
        public static final int MSG_6006 = 6006;
        
        /**
    	 * Message texty : Le type d'identifiant ne doit pas etre vide
    	 */
    	public static final int MSG_7000 = 7000;
    	/**
    	 * Message Text : le numero d'identifiant ne doit pas etre vide 
    	 */
    	public static final int MSG_7001 = 7001;
    	/**
    	 * Plusieurs person utilisent l'identifiant %1 de type %2
    	 */
    	public static final int MSG_7002 = 7002;
    	
    	/**
    	 * L'employeur d'identifiant %1 de type %2 n'existe pas
    	 */
    	public static final int MSG_7003 = 7003;
    	
    	/**
    	 * Le type de d�claration ne doit pas etre vide
    	 */
    	public static final int MSG_7004 = 7004;
    	
    	/**
    	 * Le date de d�but de d�claration ne doit pas etre vide
    	 */
    	public static final int MSG_7005 = 7005;
    	
    	/**
    	 * La date de fin de cotisation ne doit pas etre vide
    	 */
    	public static final int MSG_7006 = 7006;
    	
    	/**
    	 * La date de d�but cotisation %1 ne correspond pas au premier du mois.
    	 */
    	public static final int MSG_7007 = 7007;
    	
    	/**
    	 * La date de fin %1 doit correspondre � la fin du mois
    	 */
    	public static final int MSG_7008 = 7008;
    	
    	/**
    	 * Les dates de d�but %1 et de fin %2 doivent etre dans le meme mois.
    	 */
    	public static final int MSG_7009 = 7009;
    	
    	/**
    	 * La p�riode du %1 au %2 n'est pas trimestrielle
    	 */
    	public static final int MSG_7010 = 7010;
    	
    	/**
    	 * Le format de l'identifiant %1 est incorrect
    	 */
    	public static final int MSG_7011 = 7011;
    	
    	/**
    	 * La date effective du r�gime cadre du mois 1 est obligatoire pour l'employ� d'identifiant %1
    	 */
    	public static final int MSG_7012 = 7012;
    	
    	/**
    	 * La date d'effect du r�gime cadre du mois 2 est obligatoire pour l'employ� d'identifiant %1
    	 */
    	public static final int MSG_7013 = 7013;
    	
    	/**
    	 * La date d'effect du r�gime cadre du mois 3 est obligatoire pour l'employ� d'identifiant %1
    	 */
    	public static final int MSG_7014 = 7014;
    	
    	/**
    	 * Total salaire assujettis PF mois 1 de l'employ� d'identifiant %1 est vide 
    	 */
    	public static final int MSG_7016 = 7016;
    	
    	/**
    	 * Total salaire assujettis AT/MP mois 1 de l'employ� d'identifiant %1 est vide 
    	 */
    	public static final int MSG_7017 = 7017;
    	
    	/**
    	 * Total salaire assujettis regime g�n�ral mois 1 de l'employ� d'identifiant %1 est vide 
    	 */
    	public static final int MSG_7018 = 7018;
    	
    	/**
    	 * Total salaire assujettis r�gime cadre mois 1 de l'employ� d'identifiant %1 est vide 
    	 */
    	public static final int MSG_7019 = 7019;
    	
    	/**
    	 * Total salaire assujettis PF mois 2 de l'employ� d'identifiant %1 est vide 
    	 */
    	public static final int MSG_7020 = 7020;
    	
    	/**
    	 * Total salaire assujettis AT/MP mois 2 de l'employ� d'identifiant %1 est vide 
    	 */
    	public static final int MSG_7021 = 7021;
    	
    	/**
    	 * Total salaire assujettis r�gime g�n�ral du mois 2 de l'employ� d'identifiant %1 est vide 
    	 */
    	public static final int MSG_7022 = 7022;
    	
     	/**
    	 * Total salaire assujettis r�gime cadre mois 2 de l'employ� d'identifiant %1 est vide 
    	 */
    	public static final int MSG_7023 = 7023;
    	
    	
    	
    	/**
    	 * Total salaire assujettis PF mois 3 de l'employ� d'identifiant %1 est vide 
    	 */
    	public static final int MSG_7024 = 7024;
    	
    	/**
    	 * Total salaire assujettis AT/MP mois 3 de l'employ� d'identifiant %1 est vide 
    	 */
    	public static final int MSG_7025 = 7025;
    	
    	/**
    	 * Total salaire assujettis r�gime g�n�ral mois 3 de l'employ� d'identifiant %1 est vide 
    	 */
    	public static final int MSG_7026 = 7026;
    	
     	/**
    	 * Total salaire assujettis r�gime cadre mois 3 de l'employ� d'identifiant %1 est vide 
    	 */
    	public static final int MSG_7027 = 7027;
    	
    	/**
    	 * Lors de la d�claration du premi�re mois du trimestre, les informations du deuxi�me et du troisi�me mois
    	 * doivent etre vide
    	 */
    	public static final int MSG_7028 = 7028;
    	
    	
    	/**
    	 * Total salaire assujettis PF mois 2 de l'employ� d'identifiant %1 est vide 
    	 */
    	public static final int MSG_7029 = 7029;
    	
    	/**
    	 * Total salaire assujettis AT/MP mois 2 de l'employ� d'identifiant %1 est vide 
    	 */
    	public static final int MSG_7030 = 7030;
    	
    	/**
    	 * Total salaire assujettis regime g�n�ral mois 2 de l'employ� d'identifiant %1 est vide 
    	 */
    	public static final int MSG_7031 = 7031;
    	
    	/**
    	 * Total salaire assujettis r�gime cadre mois 2 de l'employ� d'identifiant %1 est vide 
    	 */
    	public static final int MSG_7032 = 7032;
    	
    	/**
    	 * Lors de la d�claration du deuxieme mois du trimestre, les informations du troisi�me mois
    	 * doivent etre vide
    	 */
    	public static final int MSG_7033 = 7033;
    	
    	
    	/**
    	 * Total salaire assujettis PF mois 3 de l'employ� d'identifiant %1 est vide 
    	 */
    	public static final int MSG_7034 = 7034;
    	
    	/**
    	 * Total salaire assujettis AT/MP mois 3 de l'employ� d'identifiant %1 est vide 
    	 */
    	public static final int MSG_7035 = 7035;
    	
    	/**
    	 * Total salaire assujettis regime g�n�ral mois 3 de l'employ� d'identifiant %1 est vide 
    	 */
    	public static final int MSG_7036 = 7036;
    	
    	/**
    	 * Total salaire assujettis r�gime cadre mois 3 de l'employ� d'identifiant %1 est vide 
    	 */
    	public static final int MSG_7037 = 7037;
    	
    	
      	/**
    	 * L'employeur n'est pas associe a un compte
    	 */
    	public static final int MSG_7038 = 7038;
    	
        /**
        * Message Text: "%1 %2 %3 %4 %5 %6 %7 %8 %9"
        */
        public static final int MSG_1 = 1;

        /**
        * Message Text: "Your session is waiting for a resource locked by another session."
        */
        public static final int MSG_2 = 2;

        /**
        * Message Text: "SQL Error code %1 occurred in module %2"
        */
        public static final int MSG_3 = 3;

        /**
        * Message Text: "Remote call variable is not found."
        */
        public static final int MSG_4 = 4;

        /**
        * Message Text: "Invalid SQL statement.%1 %2  Contact your system administrator."
        */
        public static final int MSG_5 = 5;

        /**
        * Message Text: "Invalid action in SQL statement.  Contact your system administrator."
        */
        public static final int MSG_6 = 6;

        /**
        * Message Text: "Too many open cursors in the registry.  Contact your system administrator."
        */
        public static final int MSG_7 = 7;

        /**
        * Message Text: "Concurrency Error.  Please retry this transaction."
        */
        public static final int MSG_8 = 8;

        /**
         * Attention, il n�existe pas de compte ou le compte saisie n�a pas de Tax role associ�e 
         */
        public static final int MSG_10 = 10;
        
        /**
         * Le montant total est: %1
         */
        public static final int MSG_11 = 11;
        
        /**
         * L'emploeyeur de ninea : %1 n'existe pas.
         */
        public static final int MSG_12 = 12;
        
        /**
         * L�employeur n�existe pas dans le syst�me, 
         * Veuillez saisir le formulaire d�immatriculation Employeur 
         */
        public static final int MSG_10001 = 10001;  
        
        /**
         * L'employeur d'identififiant %1 a moins de %2 employ�s 
         * donc il doit regler ses cotisations tous les 3 mois de chaque ann�e 
         * et par cons�quent les dates de fin de cotisation doivent obligatoirement 
         * �tre  31/03 ou 30/06 ou 30/09 ou 31/12
         */
        public static final int MSG_13=13;
        
        
        /**
         * La date de fin de cotisation doit obligatoirement
         *  debuter par les jours 30 ou 31 ou 28 ou 29.
         */
        public static final int MSG_14=14;
        
        /**
         * Plusieurs Obligations de m�mes types existent sur la m�me p�riode 
         */
        public static final int MSG_15 = 15;
        
        
        /**
        * Message Text: "SQL Statement Overflow."
        */
        public static final int MSG_20 = 20;

        /**
        * Message Text: "Action invalid"
        */
        public static final int MSG_201 = 201;

        /**
        * Message Text: "More switch invalid"
        */
        public static final int MSG_202 = 202;

        /**
        * Message Text: "List count invalid"
        */
        public static final int MSG_203 = 203;

        /**
        * Message Text: "Must exist switch invalid"
        */
        public static final int MSG_204 = 204;

        /**
        * Message Text: "Start Date must be on or before End Date"
        */
        public static final int MSG_205 = 205;

        /**
        * Message Text: "Are you sure you want to delete this object?"
        */
        public static final int MSG_206 = 206;

        /**
        * Message Text: "Required value(s) %1 %2 %3 %4 %5 %6 %7 %8 %9 not provided."
        */
        public static final int MSG_207 = 207;

        /**
        * Message Text: "%1 %3 must be on or before %2 %4."
        */
        public static final int MSG_208 = 208;

        /**
        * Message Text: "%1 %2 duplicate key error"
        */
        public static final int MSG_251 = 251;

        /**
        * Message Text: "%1 %2 not found"
        */
        public static final int MSG_252 = 252;

        /**
        * Message Text: "%1 field missing."
        */
        public static final int MSG_253 = 253;

        /**
        * Message Text: "%1 field not numeric"
        */
        public static final int MSG_254 = 254;

        /**
        * Message Text: "Program array limit overflow happens at position %1"
        */
        public static final int MSG_255 = 255;

        /**
        * Message Text: "%1 %2 field invalid"
        */
        public static final int MSG_256 = 256;

        /**
        * Message Text: "Record not found for %1 %2 %3 %4 %5"
        */
        public static final int MSG_257 = 257;

        /**
        * Message Text: "No changes may be made to %1 on an existing record."
        */
        public static final int MSG_258 = 258;

        /**
        * Message Text: "No %1 Information."
        */
        public static final int MSG_259 = 259;

        /**
        * Message Text: "%1 %2 %3 %4 %5 %6 %7 %8 %9 combination, duplicate key error"
        */
        public static final int MSG_260 = 260;

        /**
        * Message Text: "%1 %2 %3 %4 %5 %6 %7 %8 %9 combination, not found"
        */
        public static final int MSG_261 = 261;

        /**
        * Message Text: "Invalid Date. Format should be %1."
        */
        public static final int MSG_262 = 262;

        /**
        * Message Text: "%1 field should be greater than or equal to zero."
        */
        public static final int MSG_263 = 263;

        /**
        * Message Text: "This entry exists on table %1 (%2 %3)"
        */
        public static final int MSG_264 = 264;

        /**
        * Message Text: "Module not enabled in the Feature configuration."
        */
        public static final int MSG_265 = 265;

        /**
        * Message Text: "This entry exists on table %1 (%2 %3 %4 %5 %6 %7 %8 %9)"
        */
        public static final int MSG_266 = 266;

        /**
        * Message Text: "<alert> License key has expired."
        */
        public static final int MSG_267 = 267;

        /**
        * Message Text: "<warning> License key will expire in %1 day(s)."
        */
        public static final int MSG_268 = 268;

        /**
        * Message Text: "Error in the get more process. At least 1 of the following field(s): %1%2%3%4%5%6%7%8%9  must be specified."
        */
        public static final int MSG_269 = 269;

        /**
        * Message Text: "%1 field should be greater than zero."
        */
        public static final int MSG_270 = 270;

        /**
        * Message Text: "Parameter value %1 is not valid for algorithm %2."
        */
        public static final int MSG_300 = 300;

        /**
        * Message Text: "Algorithm parameter %1 is missing."
        */
        public static final int MSG_301 = 301;

        /**
        * Message Text: "Algorithm parameter %1 must be numeric."
        */
        public static final int MSG_302 = 302;

        /**
        * Message Text: "%1 %2 %3 %4 %5 %6 %7 %8 %9 you have entered already exists."
        */
        public static final int MSG_303 = 303;

        /**
        * Message Text: "The entry you are trying to duplicate from no longer exists."
        */
        public static final int MSG_304 = 304;

        /**
        * Message Text: "While duplicating the data, an error (%1, %2) was detected for %3 %4 %5 %6 %7 %8 %9. "
        */
        public static final int MSG_305 = 305;

        /**
        * Message Text: "You do not have security rights for this action."
        */
        public static final int MSG_401 = 401;

        /**
        * Message Text: "You are not allowed to access (directly/indirectly) to this account."
        */
        public static final int MSG_501 = 501;

        /**
        * Message Text: "An attempt to log a To Do Entry for message %1, %2 failed with message %3, %4."
        */
        public static final int MSG_601 = 601;

        /**
        * Message Text: "Must Exist SW is required"
        */
        public static final int MSG_901 = 901;

        /**
        * Message Text: "Must Exist Switch (%1) is invalid"
        */
        public static final int MSG_902 = 902;

        /**
        * Message Text: "Process Date/Time is required"
        */
        public static final int MSG_903 = 903;

        /**
        * Message Text: "Batch Code is required"
        */
        public static final int MSG_904 = 904;

        /**
        * Message Text: "Thread Count is required"
        */
        public static final int MSG_905 = 905;

        /**
        * Message Text: "Thread Number is required"
        */
        public static final int MSG_906 = 906;

        /**
        * Message Text: "Maximum rollback reached"
        */
        public static final int MSG_907 = 907;

        /**
        * Message Text: "Process instance error"
        */
        public static final int MSG_908 = 908;

        /**
        * Message Text: "SQL statement overflow"
        */
        public static final int MSG_909 = 909;

        /**
        * Message Text: "Business Date (%1) override (%2)"
        */
        public static final int MSG_910 = 910;

        /**
        * Message Text: "Overflow in compute statement (%1)"
        */
        public static final int MSG_911 = 911;

        /**
        * Message Text: "Action is required."
        */
        public static final int MSG_912 = 912;

        /**
        * Message Text: "Action is invalid"
        */
        public static final int MSG_913 = 913;

        /**
        * Message Text: "Process Date/Time is required"
        */
        public static final int MSG_914 = 914;

        /**
        * Message Text: "File Name overflow."
        */
        public static final int MSG_915 = 915;

        /**
        * Message Text: "The Thread Number has too few digits."
        */
        public static final int MSG_916 = 916;

        /**
        * Message Text: "Owner Flag (%1) is invalid."
        */
        public static final int MSG_917 = 917;
        
        /**
         * Message Text: "Le total des salari�s renseign� dans la section synth�se (1%) est diff�rent du nombre de salari�s d�clar�s (2%)"
         */
         public static final int MSG_10004 = 10004;
         
         /**
          * Message Text: "La date de sortie du salari� %1 %2  doit etre sup�rieur a la date de d�but de la p�riode de cotisation : %3"
          */
         public static final int MSG_10005 = 10005;


    }

}
