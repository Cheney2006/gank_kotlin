package com.cheney.gankkotlin.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.cheney.gankkotlin.base.di.ViewModelFactory
import com.cheney.gankkotlin.base.di.ViewModelKey
import com.cheney.gankkotlin.ui.SessionViewModel
import com.cheney.gankkotlin.ui.category.ArticleViewModel
import com.cheney.gankkotlin.ui.category.CategoryViewModel
import com.cheney.gankkotlin.ui.girl.GirlViewModel
import com.cheney.gankkotlin.ui.home.HomeViewModel
import com.cheney.gankkotlin.ui.login.LoginViewModel
import com.cheney.gankkotlin.ui.my.MyViewModel
import com.cheney.gankkotlin.ui.web.WebViewFragment
import com.cheney.gankkotlin.ui.web.WebViewViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import javax.inject.Inject
import javax.inject.Singleton

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(SessionViewModel::class)
    abstract fun bindSessionViewModel(sessionViewModel: SessionViewModel):ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    abstract fun bindHomeViewModel(homeViewModel: HomeViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CategoryViewModel::class)
    abstract fun bindCategoryViewModel(categoryViewModel: CategoryViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ArticleViewModel::class)
    abstract fun bindArticleViewModel(articleViewModel: ArticleViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(GirlViewModel::class)
    abstract fun bindGirlViewModel(girlViewModel: GirlViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MyViewModel::class)
    abstract fun bindMyViewModel(myViewModel: MyViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel::class)
    abstract fun bindLoginViewModel(loginViewModel: LoginViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(WebViewViewModel::class)
    abstract fun bindWebViewViewModel(webViewViewModel: WebViewViewModel):ViewModel

}