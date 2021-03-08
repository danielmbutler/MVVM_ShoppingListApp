package com.dbtechprojects.mvvm_shoppinglist

import android.app.Application
import com.dbtechprojects.mvvm_shoppinglist.data.db.ShoppingDatabase
import com.dbtechprojects.mvvm_shoppinglist.data.repositories.ShoppingRepository
import com.dbtechprojects.mvvm_shoppinglist.ui.shoppinglist.ShoppingViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

class ShoppingApplication: Application(), KodeinAware {
    override val kodein: Kodein = Kodein.lazy {
        //Application context in bindingtime
        import(androidXModule(this@ShoppingApplication))
        // instantiate Database, Repository and ViewModel Factory for Dependency Injection
        bind() from singleton { ShoppingDatabase(instance())}
        bind() from singleton { ShoppingRepository(instance())}
        bind() from provider  { ShoppingViewModelFactory(instance())}
    }
}