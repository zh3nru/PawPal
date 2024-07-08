package ziac.learning.pawpal;

public class dbCredentialsTable {
    public static final String TABLE_NAME = "dbCredentials";
    public static final String COLUMN_ID = "userID";
    public static final String COLUMN_BIRTHDATE = "userBirthday";
    public static final String COLUMN_EMAIL = "userEmail";
    public static final String COLUMN_PASSWORD = "userPassword";

    public static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "("
            + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_EMAIL + " TEXT,"
            + COLUMN_BIRTHDATE + " TEXT, "
            + COLUMN_PASSWORD + " TEXT"
            + ")";
}
