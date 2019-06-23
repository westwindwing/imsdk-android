package com.qunar.im.base.util;

public class Constants {
    public static String AA_PAY_URL = "";
    public static String CHECK_CONFIG = "";
    public static String CLIENT_NAME = "qtalk";
    public static String COMPANY = "qunar";
    public static String HONGBAO_BALANCE = "";
    public static String HONGBAO_URL = "";
    public static String MY_HONGBAO = "";
    public static String SEARCH_HISTORY_PREFIX = "";
    public static final String SHARE_TRAVER = "https://fx.dujia.qunar.com/";
    public static final String SIGN_SALT = "00d8c4642c688fd6bfa9a41b523bdb6b";
    public static String TEMPLATE_FILE_NAME = "message_template.html";
    public static String THANKS_URL = "";
    public static final String TRANSFER_URL = "https://qcweb.qunar.com/webchat/sesstran_his?to=%s&from=%s+&u=%s&k=%s";

    public static class BroadcastFlag {
        public static final String GET_UNREAD_OPS_MSG = "com.qunar.im.GET_UNREAD_OPS_MSG";
        public static final String HAS_NEW_OPS_MSG = "com.qunar.im.HAS_NEW_OPS_MSG";
        public static final String MESSAGE_HAS_FULLY_RECEIVED = "com.qunar.im.MSG_HAS_FULLY_RECEIVED";
        public static String PUSH_SERVICE = "com.qunar.im.PUSH_SERVICE";
        public static String RELOAD_CONFIG = "com.qunar.corp.ops.amd.RELOAD_CONFIG";
        public static String START_AMD_ACTION = "com.qunar.corp.ops.amd.START_AMD";
        public static String STOP_AMD_ACTION = "com.qunar.corp.ops.amd.STOP_AMD";
    }

    public static class BundleKey {
        public static final String ADDRESS = "Address";
        public static final String CAN_BACK = "canback";
        public static final String CONVERSATION_ID = "cid";
        public static final String DOMAIN_LIST_URL = "domainUrl";
        public static final String FILE_NAME = "filename";
        public static final String HOME_TAB = "home_tab";
        public static final String IMAGE_ON_LOADING = "image_loading";
        public static final String IMAGE_URL = "image_url";
        public static final String IS_FROM_SHARE = "isFromShare";
        public static final String IS_SHORTCUT_SCAN = "is_from_shortcut_scan";
        public static final String IS_SHORTCUT_SEARCH = "is_from_shortcut_search";
        public static final String IS_SWITCH_ACCOUNT = "is_switch_account";
        public static final String IS_TRANS = "sel_trans_user";
        public static final String IS_TRANS_MULTI_IMG = "is_trans_multi_img";
        public static final String LATITUDE = "Latitude";
        public static final String LOCATION_NAME = "location_name";
        public static final String LOCATION_TYPE = "location_type";
        public static final String LONGITUDE = "Longitude";
        public static final String MESSAGE = "message";
        public static final String MESSAGE_ID = "message_id";
        public static final String MOBILE = "mobile";
        public static final String NAV_ADD_NAME = "nav_add_name";
        public static final String NAV_ADD_URL = "nav_add_url";
        public static final String ORIGIN_FROM = "origin_from";
        public static final String ORIGIN_TO = "origin_to";
        public static final String RESULT_DOMAIN_ID = "domain_ID";
        public static final String RESULT_HOST_NAME = "host_name";
        public static final String SELECT_COUNTRY_ID = "country_id";
        public static final String SHARE_EXTRA_KEY = "ShareData";
        public static final String SMS_CODE = "sms_code";
        public static final String TRANS_MSG = "trans_msg";
        public static final String TRANS_MSG_JSON = "trans_msg_json";
        public static final String TRANS_MULTI_IMG = "trans_multi_img";
        public static final String WEB_FROM = "from";
        public static final String WEB_LOGIN_RESULT = "qauth.im.complete";
        public static final String WORK_WORLD_BROWERSING = "work_world_browersing";
        public static final String WORK_WORLD_ITEM = "WORK_WORLD_ITEM";
    }

    public static class BundleValue {
        public static final String HONGBAO = "H";
        public static final String ORDER_HANDLE = "OH";
        public static final String ORDER_MANAGE = "OM";
        public static final String TRAVEL = "T";
        public static final String UC_LOGIN = "UCL";
    }

    public static class Config {
        public static String CHECKCONFI_URL = "";
        public static final int HTTPS_PORT = 443;
        public static String HTTP_SERV_URL = "https://qtapi.qunar.com";
        public static final long MAX_EMOJICON_SIZE = 204800;
        public static String PERSISTENT_IMAGE = (PUB_FILE_SERVER + "/file/v2/stp");
        public static int PUBLIC_XMPP_PORT = 5223;
        public static String PUB_FILE_SERVER = "https://qt.qunar.com";
        public static String PUB_LOGINTYPE = "smscode";
        public static String PUB_NET_XMPP_Domain = "ejabhost1";
        public static String PUB_NET_XMPP_Host = "qt.qunar.com";
        public static String PUB_SMS_TOKEN = "";
        public static String PUB_SMS_VERIFY = "";
        public static final String QR_SCHEMA = "qtalk";
        public static final int TIMEOUT = 5000;
        public static String UPLOAD_CHECK_LINK = (PUB_FILE_SERVER + "/file/v2/inspection/");
        public static String UPLOAD_FILE_LINK_ONLINE = (PUB_FILE_SERVER + "/file/v2/upload/");
    }

    public static class Preferences {
        public static final String COMPANY = "startalk_company";
        public static final String PATCH_TIMESTAMP = "patch_timestamp";
        public static final String buddytime = "buddytime";
        public static final String emoticon = "emoticon";
        public static final String fullname = "fullname";
        public static final String lastMySelf = "lastMySelf";
        public static final String lastuserdomain = "LastUserDomain";
        public static final String lastuserid = "LastUserId";
        public static final String password = "password";
        public static final String qchat_conversation_params_version = "conversation_params_version";
        public static final String qchat_is_merchant = "qchatIsMerchant";
        public static final String qchat_org = "qchat_org";
        public static final String qchat_qvt = "qvt";
        public static final String session = "session";
        public static final String username = "username";
        public static final String usertoken = "userToken";
    }

    public static class SYS {
        public static final String CONTACTS_MAP = "contacts_map";
        public static final String CONVERSATION_PARAMS = "kConversationParam";
        public static final String DND_LIST = "dnd_list";
        public static final String EMOTICON_DIR = "qtalk_emoticon";
        public static final String EMOTICON_FAVORITE_DIR = "emoticon_favorite";
        public static final String MARKUP_NAME = "kMarkupNames";
        public static final int MAX_JOIN_CHATROOM_COUNT = 99;
        public static final String MY_EMOTION_KEY = "kCollectionCacheKey";
        public static final String MY_TOP_KEY = "kStickJidDic";
        public static final String NICK_UID_MAP = "nick_uid_map";
        public static final String NOTIFICATION_CONFIG = "kNotificationSetting";
        public static final String ROBOT_SESSION_ID = "robots_session";
        public static final String SYSTEM_MESSAGE = "SystemMessage";
    }

    public static final class SearchExtension {
        public static final String MORE_CHATROOM = "查找更多群聊";
        public static final String MORE_CONTACT = "查找更多联系人";
        public static final String MORE_PLATFORM = "查找更多公众号";
    }

    public static final class StatisticsConfig {
        public static final String CTYPE = "qtalk_android";
        public static final boolean ENCRYPT_ENABLE = false;
        public static final boolean STATISTICS_LOCAL = false;
        public static final String STATISTICS_URL = "http://sk.qunar.com/c";
        public static final String VERSION = "v1.0";
    }

    public static final class WorkWorldState {
        public static final String LIKE = "2";
        public static final String NOTICE = "1";
        public static final String WORKWORLD = "0";
    }
}
