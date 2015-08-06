## Appistack Droid

An Android soundboard app with Waveform and FFT audion Visualizers. Set up to work with Appistack Rails. Includes Login/Signup.

Not really sure if this repo is clean enough to be used as a template
for android, but there are a lot of great examples in here.

### Configuring For Multiple Environments

#### 1) Configure DNS For Your API

In staging & production, you can connect to your API running in Heroku. In development, you'll be running an emulator and
you'll likely need to use the IP address for the API.

#### 2) Configure Your Environment's Constants File

There are three environments set up in the app's `build.gradle` - development, staging and production. Each build
environment has a Constants.java file set up where environment-specific build settings can be configured.

`BASE_URL` - The API base URL, including the protocol, host and port number.

`ASSETS_URL` - The base URL for your assets. If your assets are stored in multiple locations, you may need
additional configuration keys.

`SIGNUP_CONFIRM_SUCCESS_URL` - The URL passed to DeviseTokenAuth when a new user is created.  When the user
clicks the link in their confirmation email, they will be redirected here after the API confirms their token.

`PASSWORD_RESET_SUCCESS_URL` - The URL passed to DeviseTokenAuth when a user requests a password change.
When the user clicks the link in their password change email, they will be redirected here after the API confirms their token.

There are a few other audio parameters you can configure for each environment.  But really, the app
should be developed on hardware.

### Running the app

I prefer Genymotion, but occasionally use the standard Android Emulator.  Of course, it's always best
to develop on the hardware itself, especially in the case of processing audio.  Running on an emulator significantly
limits both the codecs available to you as well as the sample rate.

### References

- http://developer.android.com/reference/android/media/audiofx/Visualizer.html
- http://github.com/felixpalmer/android-visualizer

