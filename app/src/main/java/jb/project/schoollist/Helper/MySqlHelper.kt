package jb.project.schoollist.Helper

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import org.jetbrains.anko.db.*

/**
 * Created by joao.neto on 13/09/2017.
 */
class MySqlHelper(ctx: Context) : ManagedSQLiteOpenHelper(ctx, "mydb") {

    companion object {
        private var instance: MySqlHelper? = null

        @Synchronized
        fun getInstance(ctx: Context): MySqlHelper {
            if (instance == null) {
                instance = MySqlHelper(ctx.applicationContext)
            }
            return instance!!
        }
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.createTable("User", true,
                "_id" to INTEGER + PRIMARY_KEY,
                "email" to TEXT,
                "password" to TEXT)

        db.createTable("Notes", true,
                "_id" to INTEGER + PRIMARY_KEY,
                "user_id" to INTEGER,
                //TODO(verify how to use foreign key in anko) -> FOREIGN_KEY("user_id", "User", "_id"),
                "title" to TEXT,
                "content" to TEXT)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
    }

}

// Access property for Context
val Context.database: MySqlHelper
    get() = MySqlHelper.getInstance(applicationContext)
