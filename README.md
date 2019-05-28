# Skull And Bones

This is for Searching Torrents on a certain website. I will not provide any third party links to torrent sites in the
code or otherwise, so if you want to use this app you have to guess which page I use and add the url yourself in the 
code (or later in the preferences)


## General Idea
* search magnet links
* download on synology disk station download manager
* save search template
* notifiy scheduled with search results for template and trigger download

### Phase one : Architecture

The Goal in this Architecture is to try to stick to community standards of MVVM with androidx.arch Components and avoid
common pitfalls and keep the app modularized, unit-testable, scopable and responsibilites well seperated.

- **Modularization**: We use a single MainActivity in the app module which will reflectively add dynamic feature modules
   - All Activities that provide self-contained features will be DFMs
   - DMFs depend on _:app_ and on _:core_
   - Core Module has shared classes and Dependencies
   - common dependencies are added via gradle files that are included in the modules via *apply from:* in the _build.gradle_
   
- **Coroutines**: We use Coroutines for background work
  - An `Executor` class which is delegated to by the `ViewModel` will launch the Coroutine on an io scheduler and post
  the response on the ui thread. The executor also clears any jobs via the `onCleared()` callback of the ViewModel. 
  This  makes sure that no components of the view layer know about background work, they only get the result in form of
  an `Either` class which lets them handle Responses and Errors.
  - The UseCases expose the `suspend` functions or can expose normal functions when they are not interested in the mode
  of execution
  
- **ViewModel**: We use the ViewModel to hold LiveData and the Background Jobs that are being executed by the Executor.
This makes sure that data and execution of tasks can survive configuration change. Our ViewModel will however not 
contain any presentation logic and delegate execution of UseCases to the Executor

- **UiController**: This class contains presentation logic. It is injected into the View and knows the ViewModel. It knows
the UseCases and delegates their execution to the Exeutor of the ViewModel. It also provides and if necessary maps
the LiveData that the ViewModel holds for databinding to the View. This class should be kept clear of View dependencies
so it can be unit tested

- **Databinding**: The View uses two-way Databinding to display data of the UiController and provide data back to the 
UiController for the execution of UseCases

- **Layers**: Each feature is seperated in three layers: _UI, Domain and Data_. However currently only seperated by packages and not by modules
  - Ui (_View and Presentation_): MVVM + UiController
  - Domain (_Business Logic_): UseCases
  - Data (_Persistence, Network_): Repositories/Services and Apis

### Phase two : Functionality

- [x] Iteration 0
  - [x] search a search term and parse result with jsoup library
- [ ] Iteration 1
  - [ ] Search for torrents and display results
  - [ ] Click on a result to open magnet link intent
- [ ] Iteration 2
  - [ ] Send magnet links directly to Synology Disk Station Download Manager (if they have a workable API)
  - [ ] Handle Magnet links from other apps
- [ ] Iteration 3
  - [ ] Configure the url via preferences
  - [ ] Configure the Disk Station url and port via preferences
- [ ] Iteration 4
  - [ ] Save certain properties of a search as a Template (File Size, search Term)
  - [ ] Provide placeholder for things (start with numbers)
- [ ] Iteration 5
  - [ ] Schedule Templates to execute via a notification e.g. once a week
  - [ ] Provide a way to auto-increment placeholder number for each notification
- [ ] Iteration 6
  - [ ] Polish and Phase three stuff

### Phase three : Design

Design will most likely follow after the app is fully functional. The general idea is that a number of fragments can be
loaded into the MainActivity in a Card-like layout. When the Card is clicked it will go full-screen and changes the UI to
show full functionality. The "collapsed" card provides only a summary or something to interface with that also expands
the card. E.g. the "SearchCard" will show a searchbar and when clicked on it will expand and let you type and give
suggestions. Do not expect anything to look especially well-designed though `;)`

- [ ] ui tests when design is closer to finished



Misc TODO's
- [ ] figure out if its possible to share data between DMFs without abusing the Core-Module. E.g. SearchTemplateFragment will display Search Templates
but the search Templates are saved by the :magnetsearch module. Shared Data layer only seems like a good idea if the instances of repo etc. can be shared as well
    - If not possible think about whether those features should be seperated.
- [ ] find or make an icon for the app and the repo
- [ ] kotlin gradle scripts ?!
- [ ] unit tests for domain layer
- [ ] unit tests for UiController
