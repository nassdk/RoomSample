package com.test.roomsample.launcher

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.test.roomsample.R
import com.test.roomsample.di.AppComponent
import com.test.roomsample.launcher.di.AppScreenComponent
import com.test.roomsample.library.coreui.navigation.Screens
import me.aartikov.alligator.NavigationContext
import me.aartikov.alligator.NavigationContextBinder
import me.aartikov.alligator.Navigator
import me.aartikov.alligator.navigationfactories.NavigationFactory
import javax.inject.Inject

class AppActivity : AppCompatActivity() {

    @Inject lateinit var navigator: Navigator
    @Inject lateinit var navigationFactory: NavigationFactory
    @Inject lateinit var navigationContextBinder: NavigationContextBinder

    override fun onCreate(savedInstanceState: Bundle?) {
        AppScreenComponent
            .create(activity = this)
            .inject(app = this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.app_screen)
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        bindNavigationContext()

        navigator.reset(Screens.CountryList)
    }

    private fun bindNavigationContext() {
        val navigationContext = NavigationContext.Builder(this, navigationFactory)
            .fragmentNavigation(supportFragmentManager, R.id.appContainer)
            .build()
        navigationContextBinder.bind(navigationContext)
    }

    override fun onPause() {
        navigationContextBinder.unbind(this)
        super.onPause()
    }
}

