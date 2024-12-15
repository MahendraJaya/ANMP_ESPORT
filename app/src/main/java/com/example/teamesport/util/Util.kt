import android.content.Context
import com.example.teamesport.model.UserDatabase

val DB_NAME = "esport"


fun buildDb(context: Context): UserDatabase {
    val db = UserDatabase.buildDatabase(context)
    return db
}
