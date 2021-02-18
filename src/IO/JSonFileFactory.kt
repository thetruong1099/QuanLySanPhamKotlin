package IO

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import model.SanPham
import java.io.FileReader
import java.io.FileWriter

class JSonFileFactory {
    fun luuDuLieu(dsSp:MutableList<SanPham>, path:String):Boolean{
        try {
            var gson=Gson()
            var file = FileWriter(path)
            gson.toJson(dsSp,file)
            file.close()
            return true
        }catch (ex:Exception){
            ex.printStackTrace()
        }
        return false
    }
    fun docDuLieu(path: String):MutableList<SanPham>{
        var dsSp: MutableList<SanPham> = mutableListOf()
        try {
            var gson = Gson()
            var file = FileReader(path)
            dsSp = gson.fromJson(file, object:TypeToken<MutableList<SanPham>>(){}.type)
            file.close()
        }catch (ex:Exception){
            ex.printStackTrace()
        }
        return dsSp
    }
}