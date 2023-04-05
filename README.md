# Yoox Application

## 1 Structure 
The structure of the project follows Clean architecture principles: https://developer.android.com/topic/architecture
The app has 3 layers: data, domain and presentation. 


### 1.0 Data layer
This is the layer of the app responsible with accessing data from one or multiple data sources.
It is composed by 3 layers: api, model and repository.

#### 1.0.0 Api
The api layer contains the apis of one or more api sources

#### 1.0.1 Model
The Model layer contains the models of data retried by data sources.

#### 1.0.2 Repository
It is the connector data layer and domain layer.
The repository layer contains one or more repositories which get data from data sources and has 2 layers:
datasource and mapper.

##### 1.0.2.0 DataSource
Datasource contains one or more datasource interfaces that bring data from data sources such as  apis or databases.

##### 1.0.2.1 Mapper
Mapper layer is responsible for mapping the models retrieved by data sources into domain models.


### 1.1 Domain layer
It is responsible for encapsulating business logic simple or complex, which is reused by view models through usecases.
Domain layer has 4 layers: repository, response, model, response, usecase.

#### 1.1.0 Repository
The repository layer contains one or more interface implemented by the data layer, hence connecting data and domain layers.
It also contain a mapper layer.

##### 1.1.0.1 Mapper
The mapper layer is responsible for mapping domain models from repository into ui models

#### 1.1.1 Response
The response layer contains responses types that are retrieved by the repository. 
Usually this types of responses are success and error.

#### 1.1.2 Model
The model layer contains the domain models which represents data responses mapped by data mappers into domain models.

#### 1.1.3 Usecase
The usecase layer is the interactor between domain layer and presentation layer. 
These usecases are contained in viewmodels and are responsible for getting data from the repository, 
and mapping it into ui models.


### 1.2 Presentation layer(UI Layer)
This layer is resposible for getting data from the domain layer and render it to views for showing it to the user.
It contains multiple layers like: activities, fragments, adapters, model, viewmodels.

#### 1.2.0 Activities
It the layer which contains android activities.

#### 1.2.1 Fragments
It the layer which contains android fragments.

#### 1.2.2 Adapters
It the layer which contains list, recyclerview adapters.

#### 1.2.3 Model
It the layer which contains ui models which will be rendered in views.

#### 1.2.3 Viewmodels
Viewmodels layer contains viewmodels responsible for getting data through usecases and render it to the views.


### 1.3 di
It is the layer which contains modules with providers for dependency injection.


## 2 Schematic flow
- activity 
  - fragment
    - viewmodel
      - usecase
        - repository
          - datasource
            - api or db


## 3 Dependency Injection
For Dependency Injection I used Hilt recommended by Google:
https://developer.android.com/training/dependency-injection

## 4 Testing
I was focused to the essential part of testing. 

### 4.0 Unit Testing
Focused on data mappers and usecases unit testing. 
For unit testing I was using hilt testing support -> https://developer.android.com/training/dependency-injection/hilt-testing
and mockk for mocks -> https://mockk.io/.

### 4.1 Automation Testing
Focused on recycler view test.
For automation testing I was using espresso -> https://developer.android.com/training/testing/espresso.

## 5 Installation requirements
The app is design to fit smartphone devices and not tablets.
The min android os version supported is android 7 Nougat (API 24).
The build was compiled against android 12L (API 32).
Gradle plugin version used was 7.4.

## 6 Navigation
For navigation I used jetpack navigation
https://developer.android.com/guide/navigation

## 7 Design 
For design the google material design was used.

