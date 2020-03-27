package ir.airport.myapplication.DB

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import ir.airport.myapplication.Utils.Consts


@Entity(tableName = Consts.TABLE_NAME)
data class MovieClass constructor(
    @PrimaryKey var MovId:String, // column name will be "note_content" instead of "content" in table
    var Title:String,
   // var postxt:String,
    //var covtxt:String,
    var Rate:String,
    var Release:String,
    //var Duration:String,
   var Desc:String

)