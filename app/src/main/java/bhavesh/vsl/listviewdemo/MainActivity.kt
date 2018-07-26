package bhavesh.vsl.listviewdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        readFile() // call the readFile
    }

    // Create function to read files from storage [ START ]
    fun readFile(){

        var path = "/storage/sdcard0/"  // define path
        var f = File(path) // constrat file object

        // checking using exist method if path correct or wrong
        if(!f.exists()){
            // set alternative path if not find first path
            path = "/storage/emulated/0/"
            f = File(path)
        }

        // all files store into Arrary
        var files = f.list()

        // arrary to object using ArraryAdepter
        var fadpter = ArrayAdapter<String>(this@MainActivity,
                android.R.layout.simple_list_item_single_choice,files)

        lview.adapter = fadpter
    }
    // Create function to read files from storage [ END ]

}
