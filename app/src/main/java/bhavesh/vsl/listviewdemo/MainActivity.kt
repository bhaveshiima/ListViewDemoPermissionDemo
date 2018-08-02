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
import kotlinx.android.synthetic.main.vsllistview.view.*
import java.io.File
import java.util.*

class MainActivity : AppCompatActivity() {

    var stack = Stack<String>() // create stack

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Get Permission Status
        var permissionStatus = ContextCompat.checkSelfPermission(this,Manifest.permission.READ_EXTERNAL_STORAGE)

        // Check the Persmission Status (If Granted or Not)
        if(permissionStatus == PackageManager.PERMISSION_GRANTED){
            readFile()
        }else{
            // Request for Persmission [ START ]
            ActivityCompat.requestPermissions(this,
                    arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                    0)

            // Request for Persmission [ END rt]
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

        stack.add(path) // Add element into stack

        // all files store into Arrary
        var files = f.list()

        // arrary to object using ArraryAdepter
        /*
        var fadpter = ArrayAdapter<String>(this@MainActivity,
                android.R.layout.simple_list_item_single_choice,files)
        */

        var fadpter = ArrayAdapter<String>(this@MainActivity,
                R.layout.vsllistview,files)

        lview.adapter = fadpter

        // Display with Item you selected [ START ]
        lview.setOnItemClickListener { adapterView, view, i, l ->
            Toast.makeText(this@MainActivity,
                    view.tvlist1.text.toString(),
                    Toast.LENGTH_SHORT).show()


            // Now of click on any list name then it will open inner folders and files [ START ]
            path = path + view.tvlist1.text.toString() // it will add which item clicked
            // Now check it's file or folder
            var f_new = File(path)
            if(f_new.isDirectory){
                path = path + "/"
                stack.add(path) // Add element into stack
                // It's Folder
                var files_new = f_new.list()
                var fadpter_new = ArrayAdapter<String>(this@MainActivity,
                        R.layout.vsllistview,files_new)

                lview.adapter = fadpter_new

            }else{
                Toast.makeText(this ,
                        "OOPS..!!..It's File",
                        Toast.LENGTH_SHORT).show()
            }







            // Now of click on any list name then it will open inner folders and files [ END ]



        }
        // Display with Item you selected [ END ]
    }
    // Create function to read files from storage [ END ]


    // Override the onPermissionResult to display result after Allow the permission [ START ]
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
            readFile()
        }else{
            Toast.makeText(this ,
                            "You cannot read, first need to accept the permission",
                            Toast.LENGTH_SHORT).show()
        }
    }
    // Override the onPermissionResult to display result after Allow the permission [ END ]





    // Override the onBackPress method for back button [ START ]
    override fun onBackPressed() {
       // super.onBackPressed()

        if(!stack.empty()) {  // If stack is not empty then we will perform this operation
            var path = stack.pop()
            var f_new = File(path)
            var files_new = f_new.list()
            var fadpter_new = ArrayAdapter<String>(this@MainActivity,
                    R.layout.vsllistview, files_new)
            lview.adapter = fadpter_new
        }else{
            //Close the application [ START ]
            finish()
            //Close the application [ END ]
        }


    }
    // Override the onBackPress method for back button [ END ]











    // Get Country List
    /*
    fun readCountry(){
        var countryArr = resources.getStringArray(R.array.countryList)

        var countryAdepter = ArrayAdapter<String>(this@MainActivity,
                android.R.layout.simple_list_item_single_choice,countryArr)
        lview1.
    }
    */



}// Main Actvity Class
