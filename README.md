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
so it can be unit tested. See more information below in "Why UiController?"

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


### Why UiController?

Because the introduction of a UiController component into the "normal" MVVM that many people are using might be unusual
I want to dedicate an extra explanation as to why I think its necessary. There are multiple reasons for it but let me start
with what I do not like about many examples I have seen, then explain the abilities and limitations of the ViewModel, then explain
some consequences which result from the previous points and finish with explaining why the UiController alleviates some
of these concerns.

#### I. AntiPatterns in common use
1. **ViewModel contains Business logic**: ViewModels inject room databases, repositories, network apis etc. and are used to
map and transform this data to be used in the view layer.
2. **ViewModel/View contains Presentation logic**: There is no good place for presentation logic without an extra class because
the View can not be unit tested and the ViewModel is not allowed to reference views or lifecycle classes
3. **View directly calls ViewModel method** (for example lifecycle)

#### II. ViewModel Abilities and Limitations
Now lets look at what a ViewModel can do and can't do because that leads us to the part "Consequences"
###### A. Abilities
1. **Survive configuration change**: the big "superpower" of the ViewModel is that it survives configuration changes.
Pair that with a pub/sub system like RxJava or LiveData and you can just immediately restore your viewstate again without re-querying your
data data or using clunky mechanisms like onSaveInstanceState(). Tasks that are run in the background while the configuration
change goes on can also be completed and then report their results as soon as the task is finished via LiveData
2. **Inject into multple views**: This is something that I see quoted a lot as a plus for MVVM and I agree, though I will make
a point in "follow up mistakes" as to why this is not a good idea when you do not keep it simple
3. **Clean Up via onCleared**: you get a method for free to clear up any subscriptions when the ViewModel is actually destroyed

###### B. Limitations
1. **No references to View or Lifecycle** As the viewmodel lives longer than the view it can not reference it or a lifecycle as they can also outlive those
and leak fragments or activites (or objects attached to custom lifecycles)

#### III. Consequences
Lets look at some problems that can come up because of the above points and why they are problematic
1. **(Over)Sharing of ViewModel** the Argument that the ViewModel can be shared because it does not reference its view makes sense
and I am in favor of that if the ViewModel is actually used as a ViewModel(Holder) and not as a ViewController / Presenter / Usecase.
   * We **should** be able to reuse our viewstates (UiModels) across different views
   * We **should not** share business logic. Sharing of business logic can be alleviated by using Usecases even in the "classic MVVM" but it is still sharing more than it should:
names of usecases and their invocation. Even if the VM does not know the View we still have an implicit interface/contract
that you be respected. When we  start implementing multiple paths for Business Logic for different views inside the VM
we are implicitly coupling the VM to the views and thus the different views are coupled as well
   * We **should not** share presentation logic: If we want to share our ViewModel to share presentation logic, then we actually want to share a part of our "View"
   and not just Data. At this point it is worth considering if what we really want is maybe two instances of the same view with different business logic, data or
   presentation

2. **Bad Seperation of Concerns** We are talking about a class that is (probably incorrectly) named View**Model**, yet there is
plenty of examples where this class contains Business Logic, Presentation Logic and basically anything else. The problem is that we have no
other place to put these things. But the ViewModel is probably the worst place to put them: If you consider the points above in "Abilities" then
there is quite an obvious use for the ViewModel: Holding View-related state. Nothing else of what we could put in there
is using its strengths while being indifferent to its weaknesses, so why should we put it there?


#### II. UiController

The UiController was an idea to alleviate some of the concerns of the ViewModel usage. It is a new idea and there might be some kinks that have to be
worked out, but it seems to work rather well. It has the following properties:

1. It lives as long as the View, Activity or Fragment
2. It knows the ViewModel or for more transparency just the MutableLiveData the ViewModel exposes
3. It uses 2-way databinding with the View to bind data and views
4. In theory the UiController could reference the view unlike a ViewModel (I am trying to avoid it though)
5. In theory the UiController could inject the lifecycleowner and start/stop executing actions according to lifecycle
6. It is not a framework class and can be unit/tested like any other class
7. It could inject multiple ViewModels, when for example another View wants to share data
7. Responsibilities:
     * Receive Ui Events and execute Usecsases
     * Update UiModel with result from Usecase execution
     * 2-Way-Databinding
     * Mapping from a general UiModel that could be shared across other views, to view-specific data/primitives
        * e.g. translate UiModel to a title String





