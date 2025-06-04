package com.it.shka.courseapp.dagger

import com.it.shka.courseapp.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModuls::class])
interface ApplicationComponent {

    fun  inject(mainActivity: MainActivity)
}