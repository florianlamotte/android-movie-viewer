# MovieViewer

### Libraries
- Dependency injection with Dagger Hilt
- Networking with Retrofit / GSON
- Unit tests with Mockito
- Navigation with AndroidX navigation component and Safe Args
- Image loading with Glide
- Asynchronous calls with coroutines

### Architecture
- Clean architecture
- MVVM with LiveData / ViewBinding

### Improvements to make
- Add a cache system for the movies to use the app offline, using for example a database (e.g. Room) 
or another type of local file storage.
- Add a way for the user to refresh the data (button, pull to refresh, etc.).
- Add UI tests around the happy path and to test navigation.
- Add a system for pagination of the recycler view since we are currently limiting the results to 
the first page returned.
- Eliminating the movie we are currently viewing from the collection of movies related to it.
- Making the domain layer observable could allow for more complex refreshing strategies instead of 
just refreshing the data when the Viewmodel is initialized.
- Put the different features and layers into their own modules to make the app more maintainable and 
testable.