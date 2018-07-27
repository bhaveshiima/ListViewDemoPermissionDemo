package bhavesh.vsl.listviewdemo

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var permissionStatus = ContextCompat.checkSelfPermission(this,Manifest.permission.READ_EXTERNAL_STORAGE)

        if(permissionStatus == PackageManager.PERMISSION_GRANTED){
            readFile()
        }else{
            // Request for Persmission [ START ]

           /* ActivityCompat.requestPermissions(this,
                    arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),(0))
            */

            // Request for Persmission [ END ]
        }

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

    // Get Country List
    /*
    fun readCountry(){
        var countryArr = resources.getStringArray(R.array.countryList)

        var countryAdepter = ArrayAdapter<String>(this@MainActivity,
                android.R.layout.simple_list_item_single_choice,countryArr)
        lview1.
    }
    */

}
