package com.keio.automapay.application

import android.app.Application
import androidx.annotation.Keep
import com.keio.automapay.di.databaseModul
import com.keio.automapay.di.repositoryModule
import com.keio.automapay.di.useCaseModule
import com.keio.automapay.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

@Keep
class MyApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@MyApplication)
            modules(
                listOf(
                    databaseModul,
                    repositoryModule,
                    useCaseModule,
                    viewModelModule
                )
            )
        }
    }
}