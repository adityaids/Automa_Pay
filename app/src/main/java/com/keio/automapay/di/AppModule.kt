package com.keio.automapay.di

import androidx.room.Room
import com.keio.automapay.core.data.AutomaRepository
import com.keio.automapay.core.data.domain.repository.IAutomaRepository
import com.keio.automapay.core.data.domain.usecase.AutomaInteractor
import com.keio.automapay.core.data.domain.usecase.AutomaUsecase
import com.keio.automapay.core.data.source.local.LocalDataSource
import com.keio.automapay.core.data.source.local.room.AutomaDb
import com.keio.automapay.ui.history.ExpenditureViewModel
import com.keio.automapay.ui.main.MainViewModel
import com.keio.automapay.ui.splitbill.SplitBillViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<AutomaUsecase> { AutomaInteractor(get()) }
}
val viewModelModule = module {
    viewModel { MainViewModel(get()) }
    viewModel { ExpenditureViewModel(get()) }
    viewModel { SplitBillViewModel(get()) }
}

val databaseModul = module {
    factory { get<AutomaDb>().automaDao() }
    single {
        Room.databaseBuilder(
            androidContext(),
            AutomaDb::class.java, "automa.db"
        ).fallbackToDestructiveMigration()
            .build()
    }
}

val repositoryModule = module {
    single { LocalDataSource(get()) }
    single<IAutomaRepository> { AutomaRepository(get()) }
}