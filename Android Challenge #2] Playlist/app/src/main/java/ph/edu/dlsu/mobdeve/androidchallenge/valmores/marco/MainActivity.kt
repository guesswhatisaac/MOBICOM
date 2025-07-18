// Roman, Isaac Nathan - S18

package ph.edu.dlsu.mobdeve.androidchallenge.valmores.marco

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import ph.edu.dlsu.mobdeve.androidchallenge.valmores.marco.adapter.PlaylistAdapter
import ph.edu.dlsu.mobdeve.androidchallenge.valmores.marco.database.PlaylistDatabase
import ph.edu.dlsu.mobdeve.androidchallenge.valmores.marco.databinding.ActivityMainBinding
import ph.edu.dlsu.mobdeve.androidchallenge.valmores.marco.databinding.DialogueInsertMediaBinding
import ph.edu.dlsu.mobdeve.androidchallenge.valmores.marco.model.PlayListMediaItem
import ph.edu.dlsu.mobdeve.androidchallenge.valmores.marco.SwipeCallback

/*  This class is open for modification. Please refer to the "TODO" keywords to know what needs to
 *  be accomplished.
 *
 *  The MainActivity handles the logic needed in the only full screen activity of the app. In this
 *  class, we should see the RecyclerView. To add a media item, the user must click on the add
 *  button (FloatActionButton), which opens up a dialogue box. The dialogue box then handles the
 *  insertion of a media item through a PlaylistDatabase instance. The MainActivity also allows for
 *  deletion of media items through swiping the ViewHolders. Modification of a media item is done by
 *  clicking on a ViewHolder. Lastly, the activity class is also in charge of getting all media
 *  items from the DB as an array list, so that the RecyclerView can properly display the
 *  information.
 */
class MainActivity : AppCompatActivity() {
    // ViewBinding used throughout the class.
    private lateinit var binding: ActivityMainBinding
    // An instance of the RecyclerView's adapter.
    private lateinit var playlistAdapter: PlaylistAdapter
    // An ItemTouchHelper instance used for handling swiping actions.
    private lateinit var itemTouchHelper: ItemTouchHelper

    /*  Do not modify this method.
     *
     *  Handles the initialization of the views and loading of data.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.fabAddMedia.setOnClickListener {
            showCustomDialogue().show()
        }

        val playlistDatabase = PlaylistDatabase(applicationContext)
        playlistAdapter = PlaylistAdapter(playlistDatabase.getPlaylistMedia(), this)
        binding.playlist.layoutManager = LinearLayoutManager(applicationContext)
        binding.playlist.adapter = playlistAdapter

        val swipeCallback = SwipeCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT)
        swipeCallback.playlistAdapter = playlistAdapter
        itemTouchHelper = ItemTouchHelper(swipeCallback)
        itemTouchHelper.attachToRecyclerView(binding.playlist)
    }

    /*  Only modify the portion where it requires
     *
     *  Creates a dialogue box that handles adding a media item to the DB. The user can input
     *  information in the fields provided.
     */
    private fun showCustomDialogue(): Dialog {
        return this.let {
            val builder = AlertDialog.Builder(it)
            val dialogueInsertMediaBinding: DialogueInsertMediaBinding = DialogueInsertMediaBinding.inflate(it.layoutInflater)

            with(builder) {
                // Logic for insertion of a media item into the DB
                setPositiveButton("SAVE", DialogInterface.OnClickListener { _, _ ->
                    val playlistDatabase = PlaylistDatabase(applicationContext)

                    /*  TODO #1
                     *      Supply the logic to add a media item to the DB by using addMedia()
                     *      of the PlaylistDatabase.
                     *      Do not modify anything above this statement.
                     */
                    val title = dialogueInsertMediaBinding.mediaAddTitle.text.toString().trim()
                    val duration = dialogueInsertMediaBinding.mediaAddDuration.text.toString().trim()
                    val mediaType = dialogueInsertMediaBinding.mediaAddType.selectedItem.toString().trim()

                    // validate inputs
                    if (title.isNotEmpty() && duration.isNotEmpty()) {
                        try {
                            val newMedia = PlayListMediaItem(title, mediaType, duration)
                            val newId = playlistDatabase.addMedia(newMedia)
                            newMedia.mediaId = newId
                            playlistAdapter.addPlayListMediaItem(newMedia)
                        } catch (e: Exception) {
                            e.printStackTrace()
                        }
                    }

                    // Do not modify anything below this statement.
                    binding.playlist.smoothScrollToPosition(playlistAdapter.itemCount - 1)
                })
                // If the user cancels, nothing happens.
                setNegativeButton("Cancel", DialogInterface.OnClickListener { _, _ -> })

                setView(dialogueInsertMediaBinding.root)
                create()
            }
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}

