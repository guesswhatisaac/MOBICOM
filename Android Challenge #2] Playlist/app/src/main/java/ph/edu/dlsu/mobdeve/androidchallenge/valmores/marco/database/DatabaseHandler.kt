// Roman, Isaac Nathan - S18

package ph.edu.dlsu.mobdeve.androidchallenge.valmores.marco.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

/*  This class is open for modification. Please refer to the "TODO" keywords to know what needs to \
 *  be accomplished.
 *
 *  The DatabaseHandler is a custom SQLiteOpenHelper class, which contains the logic for creating
 *  and upgrading the database.
 */

/*  TODO #1
 *      Supply the inheritance needed to make DatabaseHandler a SQLiteOpenHelper class.
 *      Additionally, make sure to supply the appropriate constructor.
 */
class DatabaseHandler(context: Context)
    : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    // All constant variables needed for the database; Do not modify
    companion object {
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "PlaylistDatabase"
        const val MEDIA_TABLE = "media_table"

        const val MEDIA_ID = "id"
        const val MEDIA_TITLE = "media_title"
        const val MEDIA_TYPE = "media_type"
        const val MEDIA_DURATION = "media_duration"
    }

    // Handles creation of the database
    override fun onCreate(db: SQLiteDatabase?) {

        // INT TEXT TEXT TEXT
        val CREATE_MEDIA_TABLE = """
            CREATE TABLE $MEDIA_TABLE (
                $MEDIA_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $MEDIA_TITLE TEXT,
                $MEDIA_TYPE TEXT,
                $MEDIA_DURATION TEXT
            )
        """.trimIndent()

        db?.execSQL(CREATE_MEDIA_TABLE)

        /*  TODO #3
         *      Insert five (5) media items into the table upon creation. This serves as the dummy
         *      data needed for the app. These should only be added on creation of the DB.
         *      You can create your own items; however, to make things easier for you, please just
         *      consider inserting the dummy information provided in the Canvas page.
         */
        val insertQueries = listOf(
            // songs based from yt video provided in canvas
            "INSERT INTO $MEDIA_TABLE ($MEDIA_TITLE, $MEDIA_TYPE, $MEDIA_DURATION) VALUES ('Aladin', 'Video', '2:08:00')",
            "INSERT INTO $MEDIA_TABLE ($MEDIA_TITLE, $MEDIA_TYPE, $MEDIA_DURATION) VALUES ('Weathering with you', 'Video', '1:54:00')",
            "INSERT INTO $MEDIA_TABLE ($MEDIA_TITLE, $MEDIA_TYPE, $MEDIA_DURATION) VALUES ('Pare ko', 'Video', '3:333')",
            "INSERT INTO $MEDIA_TABLE ($MEDIA_TITLE, $MEDIA_TYPE, $MEDIA_DURATION) VALUES ('Alapaap', 'Audio', '4:24')",
            "INSERT INTO $MEDIA_TABLE ($MEDIA_TITLE, $MEDIA_TYPE, $MEDIA_DURATION) VALUES ('Flower', 'Audio', '3:05')"
        )

        insertQueries.forEach { db?.execSQL(it) }
    }

    /*  Do not modify this method.
     *
     *  Handles the logic needed when updating the database.
     */
    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS $MEDIA_TABLE")
        onCreate(db)
    }
}
