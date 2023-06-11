# GitHub User Search App

The GitHub User Search App is an Android application that allows users to search for GitHub users and view their profiles.

## Features

- Search for GitHub users by username
- View user profile details
- Paginated loading of search results
- Error handling for API failures and empty search results

## Implementation Details

The application is built using the following technologies and architectural patterns:

- Kotlin programming language
- MVVM (Model-View-ViewModel) architecture
- Android Jetpack components (ViewModel, LiveData, Navigation, etc.)
- Retrofit for API communication
- OkHttp for network requests
- Gson for JSON parsing
- Coroutine for asynchronous programming
- Koin for dependency injection
- Jetpack Compose for building the user interface

The application follows a modular structure with separation of concerns. It consists of the following modules:

- `app`: Contains the main Android application code, including the UI and navigation logic.
- `data`: Responsible for data-related operations such as API communication and data mapping.
- `domain`: Contains the business logic and use cases for the application.
- `presentation`: Handles the presentation layer, including ViewModels and UI-related components.

## Running the Application

To run the GitHub User Search App on your local machine, follow these steps:

1. Clone the repository to your local machine:

   ```shell
   git clone <repository-url>
Open the project in Android Studio.

Build and run the application on an Android emulator or physical device.

Please make sure you have the latest version of Android Studio and the necessary Android SDKs installed.

## Configuration
The application requires an internet connection to interact with the GitHub API. Make sure your device is connected to the internet before using the app.

## Contributing
Contributions to the GitHub User Search App are welcome! If you encounter any issues or have suggestions for improvements, please open an issue or submit a pull request.
