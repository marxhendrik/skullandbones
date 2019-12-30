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

Trying Badoo/Ribs architecture

### Phase two : Functionality

- [x] Iteration 0
  - [x] search a search term and parse result with jsoup library
- [ ] Iteration 1
  - [x] Search for torrents and display results
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
- [ ] kotlin gradle scripts ?!
- [ ] unit tests
