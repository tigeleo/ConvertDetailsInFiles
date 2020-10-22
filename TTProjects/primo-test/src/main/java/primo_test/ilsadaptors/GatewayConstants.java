package primo_test.ilsadaptors;

import java.util.HashMap;
import java.util.Map;



public interface GatewayConstants {


	//general
	public static final String ILSG_LOGS = "ILSG_LOGS";


	// event types
	public static final String ILS_STATISTICS = "7001";
	public static final String ILS_CONFIGURATION_ERROR = "7002";
	public static final String ILS_RUN_ERROR = "7003";
	public static final String ILS_THREAD_POOL_SIZE_CHANGE = "7004";



	// General Defaults
	public static final String DEFAULT_LANG = "eng";
	public static final String NO_ADAPTOR = "NO_ADAPTOR";
	public static final String NO_TEMPLATE = "NO_TEMPLATE";


	// tables names
	public static final String GATEWAY_ADAPTORS_TABLE = ReferenceConstants.ILS_GATEWAY_ADAPTORS;
	public static final String GATEWAY_BASIC_CONFIGURATION_TABLE = ReferenceConstants.GATEWAY_BASIC_CONFIGURATION_TABLE;
	public static final String GATEWAY_ILSBASE_CONFIGURATION_TABLE = ReferenceConstants.GATEWAY_ILSBASE_CONFIGURATION_TABLE;
	public static final String GATEWAY_SERVICE_CONFIGURATION_TABLE = ReferenceConstants.GATEWAY_SERVICE_CONFIGURATION_TABLE;
	public static final String GATEWAY_SERVICE_PARAMS_CONFIGURATION_TABLE = ReferenceConstants.GATEWAY_SERVICE_PARAMS_CONFIGURATION_TABLE;


	// parameter names
	public static final String TIMEOUT_PARAM_NAME = "Timeout";
	public static final String THREAD_POOL_SIZE_PARAM_NAME = "Thread Pool Size";
	public static final String THREAD_POOL_CORE_SIZE_PARAM_NAME = "Thread Pool Core Size";
	public static final String POOL_QUEUE_SIZE_PARAM_NAME = "Pool Queue Size";
	public static final String MAX_ATTEMPTS_PARAM_NAME = "Max Attempts";
	public static final String ATTEMPTS_WAIT_TIME_PARAM_NAME = "Attempts Wait Time";
	public static final String SLOW_SRV_THRESHHOLD_PARAM_NAME = "Slow Service Threshold";
	public static final String STATISTICAL_LOG_INTERVAL_PARAM_NAME = "Statistical Log Interval";
	public static final String STATISTICAL_TIMER_PERIOD_PARAM_NAME = "Statistical Timer Period";
	public static final String REPORT_CONFIGURATION_PARAM_NAME = "Report Configuration";
	public static final String TIMER_ENABLED_PARAM_NAME = "Enabled Timers";
	public static final String RETRY_COUNT_PARAM_NAME = "Retry Count";
	public static final String MAX_HTTP_CONNNECTIONS_PER_HOST = "Max HTTP Connnections Per Host";
	public static final String API_BASE_PARAM_NAME = "api_base";
	public static final String COLLECTION_BASE_PARAM_NAME = "collection_base";
	public static final String ADAPTOR_ID_PARAM_NAME = "adaptor_id";
	public static final String SERVICE_NAME_PARAM_NAME = "service_name";
	public static final String SESSION_ID_PARAM_NAME = "session_id";
	public static final String CUSTOMER_CODE = "customer_code";
	public static final String ITEM_INDEX_PARAM_NAME = "item_index";
	public static final String PATRON_ID_PARAM_NAME = "patron_id";
	public static final String LANG_PARAM_NAME = "lang";
	public static final String PATRON_GROUP_ID_PARAM_NAME = "patron_group_id";
	public static final String PATRON_ILS_DB_PARAM_NAME = "patron_ils_db";
	public static final String IS_BULK_PARAM_NAME = "Is Bulk";
	public static final String INPUT_TRANSFORMATION_FILE_PARAM_NAME = "Input Transformation File";
	public static final String POST_INPUT_XML_FILE_REQ_PARAM_NAME = "postInputXML";
	public static final String DEFAULT_HTTP_POST_INPUT_XML_FILE_PARAM_NAME = "post_xml";
	public static final String HTTP_POST_INPUT_XML_FILE_REQ_PARAM_NAME = "httpInputPostFileParamName";

	public static final String HTTP_POST_INPUT_XML_FILE_PARAM_NAME = "HTTP Post Input XML";
	public static final String RECORD_ID_LIST_PARAM_NAME = "record_id";
	public static final String HTTP_ACTION_PARAM_NAME = "HTTP Action";
	public static final String CHUNK_SIZE_PARAM_NAME = "Chunk Size";
	public static final String SERVICE_ORDER_PARAM_NAME = "Service Order";
	public static final String SERVICE_FACTORY_CLASS_PARAM_NAME = "Service Factory Class";
	public static final String SERVICE_FACTORY_PARAM_NAME = "Service Factory";

	//general
	public static final String ERROR_CODE = "errorCode";
	public static final String ERROR_MESSAGE = "errorMessage";
	public static final String CONFIGURATION_PARAMETERS = "configurationParameters";


	// HTTP Actions
	public static final String HTTP_GET = "GET";
	public static final String HTTP_POST = "POST";
	public static final String HTTP_DELETE = "DELETE";
	public static final String HTTP_PUT = "PUT";



	// error codes
	
	public static final String SERVICE_SUCCESSFUL = "0000";

	// 7001 - 7100 - Configuration Problems
	public static final String STS_SERVICE_NOT_AVAILABLE = "7001";
	public static final String STS_SERVICE_CONFIGURATION_PROBLEM = "7002";
	public static final String STS_ADAPTOR_CONFIGURATION_PROBLEM = "7003";
	public static final String STS_POOL_CONFIGURATION_PROBLEM = "7004";
	public static final String STS_GENERAL_CONFIGURATION_PROBLEM = "7005";
	public static final String STS_DB_CONF_LOAD_PROBLEM = "7006";
	public static final String STS_ADAPTOR_DB_CONF_LOAD_PROBLEM = "7007";
	public static final String STS_API_BASE_DB_CONF_LOAD_PROBLEM = "7008";
	public static final String STS_TEMPLATE_DB_CONF_LOAD_PROBLEM = "7009";
	public static final String STS_ILSG_NOT_INITIALIZED = "7010";
	public static final String STS_XSLT_LOADING_PROBLEM = "7011";
	public static final String STS_TIMERS_INIT_PROBLEM = "7012";

	// 7101-7200 - Running Services errors
	public static final String STS_ILSG_GENERAL_FAILURE = "7101";
	public static final String STS_TIMEOUT_ERROR = "7102"; // 7002
	public static final String STS_SERVICE_FAILURE = "7103"; // 7003
	public static final String STS_SPLITING_REQ_ERROR = "7104";
	public static final String STS_CREATING_HANDLER_ERROR = "7105";
	public static final String STS_POOL_ERROR = "7106";
	public static final String STS_POOL_NOT_AVAILABLE = "7107";
	public static final String STS_INTERRUPT_ERROR = "7108";
	public static final String STS_CANCELATION_ERROR = "7109";
	public static final String STS_CALL_URI_PREPARE_ERROR = "7110";
	public static final String STS_CALL_ILS_FAILURE = "7111";
	public static final String STS_HTTP_CALL_ILS_FAILURE = "7112";
	public static final String STS_IO_ILS_FAILURE = "7113";
	public static final String STS_XSLT_TRANSFORM_ERROR = "7114";
	public static final String STS_XML_PARSE_ERROR = "7115";
	public static final String STS_MISSING_ADAPTOR_ERROR = "7116";

	// 7201 - 7300 - Successfull status
	public static final String STS_SERVICE_SUCCESSFUL = "7201"; //7004

	// 7301 -7400 - Security status
	public static final String STS_SERVICE_NOT_AUTHORIZED = "7301"; //7005


	// values form AbstractAdaptorManager
	public static final String CLASS_KEY = "Class";
	public static final String INSTITUTION_MAPPING_TABLE_KEY = "Institution Mapping Table";

	// xml elements
	public static final String ALL_HANDLERS_RESULT_XML_ROOT_OP = "<handlers-results>";
	public static final String ALL_HANDLERS_RESULT_XML_ROOT_CL = "</handlers-results>";

	// test generation constants:
	public static final String TEST_INFO_FILE_PATH = "TEST_INFO_FILE_PATH";
	public static final String REPONSE_FILE_PATH = "REPONSE_FILE_PATH";
	public static final String INPUT_FILE_DIR = "INPUT_FILE_DIR";
	public static final String INPUT_FILE_PREFIX = "INPUT_FILE_PREFIX";
	public static final String REQUEST_QUERY = "REQUEST_QUERY";
	public static final String METHOD_NAME = "METHOD_NAME";
	public static final String API_URL = "API_URL";
	public static final String TEST_HEADER = "==== TEST_HEADER ====";
	public static final String TEST_FOOTER = "==== TEST_FOOTER ====";



	// timer names - GatewayManager
	public static final String PROCESS_CALL_FULL_TIMER = "processCallFullTimer";
	public static final String SPLIT_REQUESTS_TIMER = "splitRequestsTimer";
	public static final String CREATE_HANDLERS_TIMER = "createHandlersTimer";
	public static final String SUBMIT_REQUESTS_TIMER = "submitRequestsTimer";
	public static final String MERGE_RESULTS_TIMER = "mergeResultsTimer";

	// timer names - SingleRequestHandler
	public static final String SINGLE_PROCESS_CALL_TIMER = "singleProcessCallTimer";
	public static final String SINGLE_XSLT_TRANSFORM_TIMER = "singleXSLTTransformTimer";

	// timer names - MultipleRequestHandler
	public static final String MULTIPLE_PROCESS_CALL_TIMER = "multipleProcessCallTimer" ;
	public static final String MULTIPLE_XSLT_TRANSFORM_TIMER = "multipleXSLTTransformTimer" ;

	// timer names - Empty RequestHandler
	public static final String EMPTY_PROCESS_CALL_TIMER = "emptyProcessCallTimer" ;


	// timer names - Default Adaptor
	public static final String DEFAULT_ADAPTOR_EXECUTE_SERVICE_TIMER = "defaultAdaptorExecuteServiceTimer";
	public static final String DEFAULT_ADAPTOR_CALL_ILS_TIMER = "defaultAdaptorCallILSTimer";



	// general root point name
	public static final String TIMER_FULL_TIME = "TIMER_FULL_TIME";

	// points - GatewayManager
	public static final String FINISH_SUBMIT_POINT = "finishSubmitPoint";


	// points - SingleRequestHandler
	public static final String SINGLE_ADD_PARAMETER_POINT = "singleAddParameterPoint";
	public static final String SINGLE_PREPARE_CALL_URI_POINT = "singlePrepareCallURIPoint";
	public static final String SINGLE_PREPARE_INPUT_XML_POINT = "singlePrepareInputXMLPoint";
	public static final String SINGLE_CALL_ADAPTOR_POINT = "singleCallAdaptorPoint";
	public static final String SINGLE_GENERATE_RESULT_POINT = "singleGenerateResultPoint";
	public static final String SINGLE_XSLT_TRANSFORM_POINT = "singleXSLTTransformPoint";

	// timer points - Multiple Request handler
	public static final String MULTIPLE_SUBMIT_HANDLERS_POINT ="multipleSubmitHandlersPoint";
	public static final String MULTIPLE_FINISH_WAIT_POINT = "multipleFinishWaitPoint";
	public static final String MULTIPLE_XSLT_TRANSFORM_POINT = "multipleXSLTTransformPoint";


	// timer points - Empty Request handler
	public static final String EMPTY_RECEIVED_ADAPTOR_RESPONSE_POINT ="emptyReceivedAdaptorResponsePoint";

	// timer points - Default Adaptor
	public static final String DEFAULT_ADAPTOR_PREPARE_METHOD_POINT = "defaultAdaptorPrepareMethodPoint";
	public static final String DEFAULT_ADAPTOR_AFTER_HTTP_POINT = "defaultAdaptorAfterHttpPoint";

	public enum TimerName {
		PROCESS_CALL_FULL_TIMER ("processCallFullTimer",new String[]{}),
		SPLIT_REQUESTS_TIMER (GatewayConstants.SPLIT_REQUESTS_TIMER,new String[]{}),
		CREATE_HANDLERS_TIMER (GatewayConstants.CREATE_HANDLERS_TIMER,new String[]{}),
		SUBMIT_REQUESTS_TIMER (GatewayConstants.SUBMIT_REQUESTS_TIMER,new String[]{GatewayConstants.FINISH_SUBMIT_POINT}),
		MERGE_RESULTS_TIMER (GatewayConstants.MERGE_RESULTS_TIMER,new String[]{}),

		SINGLE_PROCESS_CALL_TIMER (GatewayConstants.SINGLE_PROCESS_CALL_TIMER,new String[]{ GatewayConstants.SINGLE_ADD_PARAMETER_POINT,
																							GatewayConstants.SINGLE_PREPARE_CALL_URI_POINT,
																							GatewayConstants.SINGLE_PREPARE_INPUT_XML_POINT,
																							GatewayConstants.SINGLE_CALL_ADAPTOR_POINT,
																							GatewayConstants.SINGLE_GENERATE_RESULT_POINT,}),
		SINGLE_XSLT_TRANSFORM_TIMER (GatewayConstants.SINGLE_XSLT_TRANSFORM_TIMER,new String[]{GatewayConstants.SINGLE_XSLT_TRANSFORM_POINT}),

		MULTIPLE_PROCESS_CALL_TIMER (GatewayConstants.MULTIPLE_PROCESS_CALL_TIMER,new String[]{ GatewayConstants.MULTIPLE_SUBMIT_HANDLERS_POINT,
																								GatewayConstants.MULTIPLE_FINISH_WAIT_POINT}),
		MULTIPLE_XSLT_TRANSFORM_TIMER (GatewayConstants.MULTIPLE_XSLT_TRANSFORM_TIMER,new String[]{GatewayConstants.MULTIPLE_XSLT_TRANSFORM_POINT}),

		EMPTY_PROCESS_CALL_TIMER (GatewayConstants.EMPTY_PROCESS_CALL_TIMER,new String[]{GatewayConstants.EMPTY_RECEIVED_ADAPTOR_RESPONSE_POINT}),

		DEFAULT_ADAPTOR_EXECUTE_SERVICE_TIMER (GatewayConstants.DEFAULT_ADAPTOR_EXECUTE_SERVICE_TIMER,new String[]{GatewayConstants.DEFAULT_ADAPTOR_PREPARE_METHOD_POINT}),
		DEFAULT_ADAPTOR_CALL_ILS_TIMER (GatewayConstants.DEFAULT_ADAPTOR_CALL_ILS_TIMER,new String[]{GatewayConstants.DEFAULT_ADAPTOR_AFTER_HTTP_POINT});

	    String timerName;
	    String[] points;
	    Map<String,String> pointsMap = new HashMap<String,String>();

		private TimerName(String name,String[] points) {
			this.timerName = name;
			this.points = points == null?new String[]{}:points;
			for (String point : points) {
				pointsMap.put(point, point);
			}
		}

		public String getPoint(String pointName){
			return pointsMap.get(pointName);
		}

		public String getTimerName() {
			return timerName;
		}



		public String[] getPoints() {
			return points;
		}


	}
	/**
	 * Enumeration of the TimerLogMode used to set the way
	 * <code>ILSGTimer</code> instances set log their statistical information
	 * <ul>
	 * 	<li>NONE - No timers are logged.</li>
	 * 	<li>DETAILED - Logs each <code>ILSGTimer</code> run into LOG4J file</li>
	 * 	<li>SUMMARY - Will send the log to the <code>StatisticsInfo</code> to perform further processing</li>
	 * 	<li>ALL - Both DETAILED and SUMMARY info will be logged</li>
	 * </ul>
	 *
	 * @author mosheh
	 */
	public static enum TimerLogMode {
		NONE,DETAILED,SUMMARY,ALL;
	}

	/**
	 * Enumeration of the TimerLogType used to set target
	 * <code>StatisticsInfo</code> instances log their statistical information.
	 * <ul>
	 * 	<li>NONE - No statistical info is logged.</li>
	 * 	<li>FILE - Logs statistical info to log file</li>
	 * 	<li>DATABASE - Logs statistical info to the database using the <code>EventService</code></li>
	 * 	<li>ALL - Logs statistical info to both log file and database</li>
	 * </ul>
	 *
	 * @author mosheh
	 */
	public static enum TimerLogType{
		NONE,FILE,DATABASE,ALL;
	}



}
