package dbhelper;

import android.provider.BaseColumns;


public class DBContract {

    private DBContract() {
    }

    public static class UsersEntry implements BaseColumns {

        public static final String TABLE_NAME = "contacts";
        public static final String COLUMN_NAME_FIRSTNAME_AND_LASTNAME = "name";
        public static final String COLUMN_NAME_EMAIL = "email";
        public static final String COLUMN_NAME_TEL = "tel";
        public static final String COLUMN_NAME_DATE = "date";
        public static final String COLUMN_NAME_TIME = "time";


    }
}
