package de.marxhendrik.skullandbones.magnetsearch.di

import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.MapKey
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import de.marxhendrik.skullandbones.core.di.scope.FeatureScope
import de.marxhendrik.skullandbones.magnetsearch.domain.MagnetSearchUseCase
import de.marxhendrik.skullandbones.magnetsearch.ui.MagnetSearchFragment
import de.marxhendrik.skullandbones.magnetsearch.ui.MagnetSearchViewModel
import javax.inject.Provider
import kotlin.reflect.KClass

@Module
object MagnetSearchModule {

    @Provides
    @JvmStatic
    fun magnetSearchViewModel(
        factory: ViewModelProvider.Factory,
        fragment: MagnetSearchFragment
    ) =
        fragment.viewModels<MagnetSearchViewModel>(factoryProducer = { factory })

    @Provides
    @JvmStatic
    @FeatureScope
    fun provideViewModelFactory(
        providers: Map<Class<out ViewModel>, @JvmSuppressWildcards Provider<ViewModel>>
    ) = object : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return requireNotNull(providers[modelClass as Class<out ViewModel>]).get() as T
        }
    }

    @Provides
    @IntoMap
    @JvmStatic
    @ViewModelKey(MagnetSearchViewModel::class)
    fun provideViewModel(): ViewModel = MagnetSearchViewModel(MagnetSearchUseCase())

}

@MapKey
@Target(AnnotationTarget.FUNCTION)
annotation class ViewModelKey(
    val value: KClass<out ViewModel>
)
