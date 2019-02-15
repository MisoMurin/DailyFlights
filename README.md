# Flights
This project contains an application which loads 5 flights for the current day from Kiwi.com API.
A departure airport is a city within a circle with center in Brno and has a 250km diameter. 
The flights are sorted by departure time. If the Kiwi.com application is installed in a device,
the flight details can be browsed in it.

## Libraries
The project utilizes the Android Architecture Components (LiveData, DataBinding, Navigation) 
and other third party libraries (Retrofit, Mapbox, Coroutines, Koin etc.) to achieve the functionality.

## Make it run
The minimum to run the app is API 21 (Android 5.0 Lollipop).
The project is not compilable as it is. There is 1 string value to be defined:
- *mapboxToken* - to display the Mapbox map
