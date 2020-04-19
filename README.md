I only wrote a unittest for the class GifDetailPopulationFeature.kt I think it encapsulates my approach to unittesting nicely.
I also wrote two instrumented tests that also run as UI/Screenshot tests. To run the screenshot tests simply type `./gradlew eST` on command line.
The source code is available in the usual androidTest Folder.

For the API i choose to work with the giphyAPI. Specifically this endpoint: https://developers.giphy.com/docs/api/endpoint/#trending which returns this object: https://developers.giphy.com/docs/api/schema/#gif-object

I enriched the object with some local fake data to make it more applicable (price for example is totally fake). 




Answers to bonus questions:

1. Regarding engagement metrics, I have expierence using Firebase Analytics to measure things like clicks on menu items etc. So In this case I think it would make sense to have some analytics events around how many items
users scroll through, when they click on a cell in the recyclerview. These are easily measured using whats known as an event from the firebase sdk. It would also be worthy to measure how long users view a particular screen
Overview screen / detail screen. This can be done using a Firebase Analytics trace. Start the trace when the activity/ fragment loads, and end it when the activity fragment is destroyed / popped off back stack.

2. For expanding this app to different product lines. It might make sense to modularize the app a bit more differently. So we have a core module with the skeleton of the activities, viewmodels, and fragments and all the
features installed in them. And each product line could have its own module providing details on things like networking and caching as well as domain specific entities. Finally, yet another common module can be introduced
providing the contracts that all the domain entities adhere to. Then the core module would only rely on these contract entities and NOT the domain specific entities. Each app would just import the relevant modules 
required.


