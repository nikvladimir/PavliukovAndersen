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
    <img src="https://user-images.githubusercontent.com/71027706/256142595-6109a3aa-091c-436e-8b7f-52276360bb6b.png" alt="Image 1" height="500px">
    <img src="https://user-images.githubusercontent.com/71027706/256148497-29863bd6-5a0c-4b3d-a1fd-132f98758086.png" alt="Image 2" height="500px">
</div>

####
- переключатель смены темы
- FragmentStateAdapter

###
#### Фрагменты: EditText, TextView, RecyclerView и CustomView 

<div style="display: flex; justify-content: center;">
    <img src="https://user-images.githubusercontent.com/71027706/256142620-56e871b5-676b-4696-a776-b924f85aa533.png" alt="Image 1" height="500px">
    <img src="https://user-images.githubusercontent.com/71027706/256142600-2f892400-d283-43a7-992e-65cad2c500aa.png" alt="Image 1" height="500px">
    <img src="https://user-images.githubusercontent.com/71027706/256142608-9518af7f-b6ca-418e-b9b1-218ed8962db2.png" alt="Image 1" height="500px">
</div>

#### 
пример работы с такими элементами как:
- AlertDialog
- TextVIew, EditText, RecyclerView
- Toast and toastTextListener
- setOnClickListener


#### 
##### Работа Сервиса на примере музыкального проигрывателя

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
##### Выход в сеть, работа с многопоточностью и отобржение изображений

<div style="display: flex; justify-content: center;">
    <img src="https://user-images.githubusercontent.com/71027706/256142597-5b2f0293-662b-458a-9a51-07007e4b37ff.png" alt="Image 1" height="500px">
    <img src="https://user-images.githubusercontent.com/71027706/256142588-7ebd3900-c8f9-4048-ac87-c6f41d670a60.png" alt="Image 1" height="500px">
</div>


#### 
- Picasso, Glide
- lifecycle
- retrofit2, okhttp3


## <a name="technologies">Технологии</a>
  
  - Kotlin
  - Firebase
  - Picasso, Glide
  - Dagger 2
  - Clean Architecture
  - Kotlin Coroutines
  - Retrofit2
    
## <a name="setup">Установка</a>
  
    git clone https://github.com/nikvladimir/PavliukovExamples.git
