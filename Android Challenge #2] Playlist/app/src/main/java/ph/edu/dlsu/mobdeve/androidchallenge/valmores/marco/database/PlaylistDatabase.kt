// Roman, Isaac Nathan - S18

package ph.edu.dlsu.mobdeve.androidchallenge.valmores.marco.database

import android.content.ContentValues
import android.content.Context
import ph.edu.dlsu.mobdeve.androidchallenge.valmores.marco.model.PlayListMediaItem

/*  This class is open for modification. Please refer to the "TODO" keywords to know what needs to
 *  be accomplished.
 *
 *  The PlaylistDatabase handles the DB operations of inserting, deleting, modifying, and querying.
 */
class PlaylistDatabase(context: Context) {
    // A private instance of the DB helper
    private var databaseHandler: DatabaseHandler

    // Initializes the databaseHandler instance using the context provided.
    init {
        this.databaseHandler = DatabaseHandler(context)
    }

    // Inserts a provided media item into the database. Returns the id provided by the DB.
    fun addMedia(media: PlayListMediaItem): Int {
        val db = databaseHandler.writableDatabase

        val contentValues = ContentValues()
        contentValues.put(DatabaseHandler.MEDIA_TITLE, media.title)
        contentValues.put(DatabaseHandler.MEDIA_TYPE, media.mediaType)
        contentValues.put(DatabaseHandler.MEDIA_DURATION, media.mediaDuration)

        val _id = db.insert(DatabaseHandler.MEDIA_TABLE, null, contentValues)
        db.close()

        return _id.toInt()
    }

    /*  TODO #1
     *      Supply the logic to complete the method that updates a provided media item's variables.
     *      Do not modify any of the provided lines of code -- only add to the method.
     */
    fun updateMedia(media: PlayListMediaItem) {
        val db = databaseHandler.writableDatabase

        val contentValues = ContentValues()
        contentValues.put(DatabaseHandler.MEDIA_TITLE, media.title)
        contentValues.put(DatabaseHandler.MEDIA_TYPE, media.mediaType)
        contentValues.put(DatabaseHandler.MEDIA_DURATION, media.mediaDuration)

        db.update(
            DatabaseHandler.MEDIA_TABLE,
            contentValues,
            "${DatabaseHandler.MEDIA_ID}=?",
            arrayOf(media.mediaId.toString())
        )

        db.close()
    }

    /*  TODO #2
     *      Supply the logic to complete the method that deletes the provided media item from the
     *      DB. Do not modify any of the provided lines of code -- only add to the method.
     */
    fun deleteMedia(media: PlayListMediaItem) {
        val db = databaseHandler.writableDatabase

        db.delete(
            DatabaseHandler.MEDIA_TABLE,
            "${DatabaseHandler.MEDIA_ID}=?",
            arrayOf(media.mediaId.toString())
        )

        db.close()
    }

    /*  TODO #3
     *      Supply the logic to complete the method that retrieves all media items from the DB and
     *      places them into an array list. The method also returns the created array list
     *      Do not modify any of the provided lines of code -- only add to the method.
     */
    fun getPlaylistMedia(): ArrayList<PlayListMediaItem> {
        val result = ArrayList<PlayListMediaItem>()
        val db = databaseHandler.readableDatabase

        val cursor = db.rawQuery("SELECT * FROM ${DatabaseHandler.MEDIA_TABLE}", null)

        if (cursor.moveToFirst()) {
            do {
                val id = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHandler.MEDIA_ID))
                val title = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHandler.MEDIA_TITLE))
                val type = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHandler.MEDIA_TYPE))
                val duration = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHandler.MEDIA_DURATION))

                result.add(PlayListMediaItem(id, title, type, duration))
            } while (cursor.moveToNext())
        }

        cursor.close()
        db.close()

        return result
    }
}
