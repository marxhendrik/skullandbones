package de.marxhendrik.skullandbones.magnetsearch.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.MapKey
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import de.marxhendrik.skullandbones.core.di.scope.FeatureScope
import de.marxhendrik.skullandbones.magnetsearch.domain.MagnetSearchUseCase
import de.marxhendrik.skullandbones.magnetsearch.ui.MagnetSearchViewModel
import javax.inject.Provider
import kotlin.reflect.KClass

@Module
object ViewModelFactoryModule {

    @FeatureScope
    @Provides
    @JvmStatic
    @Suppress("UNCHECKED_CAST")
    fun viewModelFactory(
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
    fun viewModelIntoMap(useCase: MagnetSearchUseCase): ViewModel = MagnetSearchViewModel(useCase)

}

@MapKey
@Target(AnnotationTarget.FUNCTION)
annotation class ViewModelKey(
    val value: KClass<out ViewModel>
)
