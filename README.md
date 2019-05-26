# Skull And Bones

## General Idea
* search magnet links
* download on synology disk station download manager
* save search template
* notifiy scheduled with search results for template and trigger download

### Phase one : Architecture   (In Progress)

The Goal in this Architecture is to try to stick to community standards of MVVM with androidx.arch Components and avoid
common pitfalls and keep the app modularized, unit-testable, scopable and responsibilites well seperated.

- Modularization: We use a single MainActivity in the app module which will reflectively add dynamic feature modules
   - All Activities that provide self-contained features will be DFMs
   - DMFs depend on :app and on :core
   - Core Module has shared classes and Dependencies
   - dependencies are loaded in the build.gradle via **apply from:**
   
- Coroutines: We use Coroutines for background work
  - An `Executor` class which is delegated to by the `ViewModel` will launch the Coroutine on an io scheduler and post
  the response on the ui thread. The executor also clears any jobs via the `onCleared()` callback of the ViewModel. 
  This  makes sure that no components of the view layer know about background work, they only get the result in form of
  an 'Either' class which lets them handle Responses and Errors.
  - The UseCases expose the `suspend` functions or can expose normal functions when they are not interested in the mode
  of execution
  
- ViewModel: We use the ViewModel to hold LiveData and the Background Jobs that are being executed by the Exector. This
makes sure that we can survive configuration change. Our ViewModel will however not contain any presentation logic and
delegate execution of UseCases to the Executor

- UiController: This class contains presentation logic. It is injected into the View and knows the ViewModel. It knows
the UseCases and delegates their execution to the Exeutor of the ViewModel. It also provides and if necessary maps
the LiveData that the ViewModel holds for databinding to the View. This class should be kept clear of View Dependencies
so it can be unit tested

- Databinding: The View uses two-way Databinding to display data of the UiController and provide Data back to the 
UiController for the execution of UseCases

- Layers: Each feature is seperated in three layers: UI, Domain and Data
  - Ui (View and Presentation): MVVM + UiController
  - Domain (Business Logic): UseCases
  - Data (Persistence, Network): Repositories/Services and Apis



### Phase two : Functionality   (Todo)
### Phase three : Design   (Todo)

