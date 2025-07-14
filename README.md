An example of a modern Android application for movie and series discovery, built with best practices
in mind. This app utilizes Kotlin for concise and safe code, implements the MVVM architecture to
ensure a clear data flow and testability, and employs a modular project structure for improved
organization and reusability of components.

### API Key Setup

To access the TMDb API, an API key is required. Follow these steps to set it up:

1. **Obtain API Key**:
    -
    Visit [TMDb's API documentation](https://developer.themoviedb.org/reference/intro/getting-started)
    to obtain your API key.

2. **Store API Key**:
    - Add the API key to your `local.properties` file in your project directory. Include the
      following line:
      ```
      API_TOKEN=your_api_key_here
      ```
   Replace `your_api_key_here` with the actual key obtained from TMDb.

#