package uz.almirab.programmingcourses.PrefUtils

import com.orhanobut.hawk.Hawk
import uz.almirab.programmingcourses.CourseBuyingList

object PrefUtils {


    val PREF_COURSE = "prefCourse"
    fun setCourses(item: CourseBuyingList){
        val items = Hawk.get(PREF_COURSE, arrayListOf<Int>())
        if (items.filter { it ==item.id }.firstOrNull()!=null) {
            items.remove(item.id)
        }else{
            items.add(item.id)
        }
        Hawk.put(PREF_COURSE,items)
    }
    fun removeCourses(item: CourseBuyingList){
        val items = Hawk.get(PREF_COURSE, arrayListOf<Int>())
        if (items.filter { it ==item.id }.firstOrNull()!=null) {
            items.remove(item.id)
        }else{
            items.remove(item.id)
        }
        Hawk.put(PREF_COURSE,items)
    }
    fun getCousesList():ArrayList<Int>{
        return Hawk.get(PREF_COURSE, arrayListOf<Int>())
    }
}