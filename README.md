## Содержание
- [Общая информация](#general_info)
- [Основной функционал](#main_app_func)
- [Описание приложения](#description)
- [Технологии](#technologies)
- [Установка](#setup)

## <a name="general_info">Общая информация</a>
  
  - Образовательное приложение для платформы Android, разработанное на языке программирования Kotlin.
  
  - Приложение предназначено для новичков и более опытных Android-разработков, предоставляет демонстрацию основных возможностей, которые можно реализовать в Android-приложениях.
 
  - Способ ознакомиться с различными функциями платформы Android и получить представление о том, как они могут быть использованы в реальных проектах.
  
  - Предоставляет базовые примеры кода для каждой функциональности и является отличным отправным пунктом для тех, кто хочет начать разрабатывать свои собственные Android-приложения на языке Kotlin.

## <a name="main_app_func">Основной функционал</a>
  
######  
1. Навигация:

- Простая и интуитивно понятная навигация по разделам приложения c использованием NavigationView, ViewPager2
- Выбор разделов, связанных с различными аспектами разработки Android-приложений.

######
2. Экраны:

- Демонстрация различных типов экранов: активити, фрагменты, диалоговые окна и т.д.
- Показ обработки жизненного цикла компонентов и управление состояниями.

######
3. Макеты и вёрстка:

- Примеры создания различных макетов с использованием ConstraintLayout, LinearLayout и RelativeLayout.
- Показ применения стилей и тем.

######
4. Взаимодействие с пользователем:

- Демонстрация обработки пользовательских событий: нажатия кнопок, ввод текста и т.д.
- Валидация ввода и обработка ошибок.

######
5. Работа с базами данных:

- Использование SQLite для хранения и извлечения данных.
- [Примеры CRUD-операций (создание, чтение, обновление и удаление данных).]

######
6. Работа с сетью:

- Выполнение HTTP-запросов с использованием Retrofit и Okhttp3.
- Обработка ответов от сервера и отображение данных в приложении.

######
7. Работа с мультимедиа:

- Загрузка и отображение изображений.
- Воспроизведение звука.

######
8. Работа с устройством:

- Доступ к различным сенсорам и датчикам устройства (например, акселерометр, геолокация).
- Работа с камерой и галереей.

######
9. Переход между экранами:

- Примеры использования Intent для перехода между Activity и передачи данных между ними.

######
10. Многопоточность:

- Обработка задач в фоновых потоках с использованием Coroutines.

######
11. Работа с архитектурными компонентами:

- Примеры использования ViewModel, LiveData, Room и других компонентов архитектуры Jetpack.

</details>


## <a name="description">Описание приложения</a>



#### Меню навигации NavigationView и ViewPager2

<div style="display: flex; justify-content: center;">
    <img src="https://github.com/nikvladimir/PavliukovExamples/assets/71027706/e70b4ef0-8896-4763-9c77-683328d9d47b" alt="Image 1" height="500px">
    <img src="https://github.com/nikvladimir/PavliukovExamples/assets/71027706/11c3bcf8-76a3-44aa-a906-53e26d5d6080" alt="Image 2" height="500px">
</div>

####
- переключатель смены темы
- FragmentStateAdapter

###
#### Различные элементы Activity и Fragments

<div style="display: flex; justify-content: center;">
    <img src="https://github.com/nikvladimir/PavliukovExamples/assets/71027706/2606fedb-2cfb-4e95-9690-1a86b5cd4082" alt="Image 3" height="500px">
    <img src="https://github.com/nikvladimir/PavliukovExamples/assets/71027706/11c3bcf8-76a3-44aa-a906-53e26d5d6080" alt="Image 4" height="500px">
    <img src="https://github.com/nikvladimir/PavliukovExamples/assets/71027706/283f293c-3319-48e3-b951-6250b5e12d74" alt="Image 5" height="500px">
    <img src="https://github.com/nikvladimir/PavliukovExamples/assets/71027706/56aa714c-471c-4640-80b6-88d41e2f9e87" alt="Image 6" height="500px">
</div>


#### 
пример работы с такими элементами как:
- EditText с Toast и toastTextListener
- RecyclerView с AlertDialog
- TextVIew, CustomView, setOnClickListener


#### 
##### Работа Сервиса на примере музыкального проигрывателя с реализацией фильтров по исполнителю либо по жанру

<div style="display: flex; justify-content: center;">
    <img src="https://user-images.githubusercontent.com/71027706/256142603-ddcd3581-5f7f-4884-a3cd-458190ddf1f6.png" alt="Image 1" height="500px">
    <img src="https://user-images.githubusercontent.com/71027706/256180981-9ee73347-8f54-41b8-ae69-11e683dcb587.png" alt="Image 1" height="500px">
    <img src="https://user-images.githubusercontent.com/71027706/256142616-bb9254cd-26de-4b7b-bba1-a7a24033c121.png" alt="Image 1" height="500px">
</div>

#### 
- Service
- MediaPlayer
- SQLiteOpenHelper
- Spinner
  
#### 
##### Новостная страничка с фильтрацией новостей по жанрам за вчерашний день

<div style="display: flex; justify-content: center;">
    <img src="https://user-images.githubusercontent.com/71027706/256142597-5b2f0293-662b-458a-9a51-07007e4b37ff.png" alt="Image 1" height="500px">
    <img src="https://user-images.githubusercontent.com/71027706/256142588-7ebd3900-c8f9-4048-ac87-c6f41d670a60.png" alt="Image 1" height="500px">
</div>


#### 
- выход в сеть: retrofit2, okhttp3
- работа с многопоточностью: coroutines
- отобржение изображений: Picasso, Glide
- lifecycle


##### Реализация GoogleMaps с заранее выставленными 5 точками

<div style="display: flex; justify-content: center;">
    <img src="https://github.com/nikvladimir/PavliukovExamples/assets/71027706/90c639bc-595d-402a-8ddb-b6d19953b4da" alt="Image 1" height="500px">
</div>


#### 
- Maps Services, Location Services
- MapView


## <a name="technologies">Технологии</a>
  
  - Kotlin
  - Firebase
  - Picasso, Glide, Maps
  - Dagger 2
  - Clean Architecture
  - Kotlin Coroutines
  - Retrofit2
    
## <a name="setup">Установка</a>
  
    git clone https://github.com/nikvladimir/PavliukovExamples.git
