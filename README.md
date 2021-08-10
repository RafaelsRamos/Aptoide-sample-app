## Technical test for Aptoide

#### Final result

![Sample](https://media.giphy.com/media/wu9jBo4IGU6gfWfPBD/giphy.gif)

### General Architecture
A [MVVM](https://en.wikipedia.org/wiki/Model%E2%80%93view%E2%80%93viewmodel#:~:text=Model%E2%80%93view%E2%80%93viewmodel)
software architecture was used in this product for two main reasons:
1. This sample app requires just displaying data from the REST Api, and by using this architecture, few to none "business" logic is required to make the necessary requirements;
2. MVVM is, by it self a very robust and dynamic pattern, that easly handles all the app-related events (such as switching to landscape, or putting the app on background);

### Technologies used:
* [Navigation Components](https://developer.android.com/guide/navigation) for handling fragments (fragment (singular), in the case of this sample) navigation;
* [Hilt](https://developer.android.com/training/dependency-injection/hilt-android) for dependency intjection;
* [Retrofit](https://square.github.io/retrofit/) as the Http Client for consuming the REST Api;
* [Material Design](https://material.io/design) as a UI/UX guideline library;
* [RXJava](https://github.com/ReactiveX/RxJava) for handling asynchronous API calls;
* [Glide](https://bumptech.github.io/glide/) for image loading;
* [LiveData](https://developer.android.com/topic/libraries/architecture/livedata) to make the communication between View and ViewModel dynamic;

