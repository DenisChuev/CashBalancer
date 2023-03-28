package dc.cashbalancer

import android.app.Application
import androidx.room.Room
import dc.cashbalancer.dao.CashDatabase

class CashApp : Application() {
    companion object {
        lateinit var db: CashDatabase
    }

    override fun onCreate() {
        super.onCreate()

        db = Room.databaseBuilder(this, CashDatabase::class.java, "CashDatabase")
            .fallbackToDestructiveMigration()
            .build()
    }
}