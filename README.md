Name-Ayush Khadka
Student Id-s8094524

Final App
This is the end-of-term project for NIT3213 - Android Application Development. Users can log in, get data through an API and see a list of mythology entities. It comprises of three major screens namely: Login, Dashboard and Details.

Key Features
User login using an API.
A dashboard that shows a list of mythology entities.
Detailed view for each entity with more information.


Requirements
Android Studio: You need to download and install Android studio.
API Access: The application utilizes NIT3213 API at https://vu-nit3213-api.onrender.com which handles logins as well as data downloads.
Java 11 or higher: Ensure that your computer has Java 11 or newer versions installed.
Android SDK: Install API level 31 (Android 12) or above in Android SDK.


Libraries Used
Here are the mainstream libraries used in this project:
dependencies {
implementation "androidx.core:core-ktx:1.10.1"
implementation "androidx.appcompat:appcompat:1.6.1"
implementation "com.google.android.material:material:1.9.0"
implementation "androidx.constraintlayout:constraintlayout:2.1.4"
implementation "org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.0"
implementation "org.json:json:20200518"
implementation "androidx.recyclerview:recyclerview:1.3.0"
implementation "androidx.cardview:cardview:1.0.0"
implementation "androidx.lifecycle:lifecycle-runtime-ktx:2.6.1"
}


How to set up the project
Step 1:
First, you’ll need to download cloning this repository and its project files on your computer:
Git hub Link
git clone https://github.com/Ayush000239/Final_app1
Step 2:
Launch Android Studio.
Select file > Open and browse for the project folder by selecting final_app.
Wait for Gradle to synchronize with the project and install any other required packages.
Step 3:
 Setup API Access
This app communicates with NIT3213 API to login and fetch data.
Ensure that the following API URLS are correct before proceeding:

Login: https://vu-nit3213-api.onrender.com/sydney/auth
Data: https://vu-nit3213-api.onrender.com/dashboard/mythology
If necessary, update the URLs in LoginActivity.kt and DashboardActivity.kt file.

Step 4:
Building And Running the App
Connect your android phone or use an emulator.
On android studio click run> run ‘app’ or else press shift + F10 keys at once.
The application will begin on your connected device or emulator.

Step 5:
Using The Application
Login using valid credentials
Once you successfully log in with valid credentials, there will be a dashboard displaying a list of mythology entities.
Click on any entity name you would like more detailed information about

Common Issues
1: Problems synchronizing Gradle: If there are issues while syncing the project, ensure that you have installed proper Android SDK version as you can manage SDKS via SDK Manager in Android Studio.
2: API Problems: If unable to login or fetch data from Apis , double-check the API URL and credentials.
