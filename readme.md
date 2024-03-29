# Sensors Monitor

API для управления сенсорами.

## Стек технологий

* Java 19,
* Spring Boot,
* Spring Security,
* Spring Data JPA,
* JWT

## Инструкция по сборке и запуску

* Сборка выполняется на Java 19.
* Для сборки выполнить команду `gradlew build` в корне проекта.
* Для запуска сервера выполнить команду `gradlew bootRun`.

## Техническое задание

### Авторизация и аутентификация

В системе 2 предустановленных пользователя:

1. admin c ролью Administrator
2. user c ролью Viewer

* Пользователю с ролью _Administrator_ разрешен доступ ко всем формам и разрешены все действиям в приложении.
* Пользователю с ролью _Viewer_ разрешен только доступ к формам **Логин, Таблица датчиков**. На форме **Таблица датчиков**
  разрешены действия, связанные только с просмотром данных, без права добавления, удаления либо изменения записей.
* Для не авторизированных пользователей доступна только форма **Логин**.
* После ввода логина и пароля, авторизированного пользователя система перенаправляет на форму **Таблица датчиков**.

Данные для таблицы берутся посредством запросов с серверной части (данные хранятся в БД).
Пользователю с ролью _Viewer_ и _Administrator_ разрешено просматривать данные таблицы, использовать пейджинг (количество записей
на странице 4), использовать поиск по данным.

**Поиск по данным** представляет из себя поле ввода текста и кнопку поиска. Поиск происходит по частичному совпадению введенного
текста в любом поле записи (включая поля, не показанные в таблице – description).
После окончания поиска в таблице остаются только подходящие записи.

Пользователю с ролью Administrator разрешены дополнительно действия:

* добавление нового датчика (переход на форму 3)
* редактирование выделенного датчика (переход на форму 3)
* удаление выделенного датчика.
  По кнопке logout – пользователь выходит из системы.

### Добавление/редактирование датчиков

* Name – текстовое поле. Обязательное. Валидация – не пустое поле и количество символов не превышает 30.
* Model – текстовое поле. Обязательное. Валидация – не пустое поле и количество символов не превышает 15.
* Range from, to – числовое поле. Диапазон значений датчика, в котором предусмотрена его работа. Валидация – при вводе двух
  значений, from должно быть меньше to. Целые числа.
* Type – список выбора. Предустановленные данные, берутся из БД. Значения - Pressure, Voltage, Temperature, Humidity.
* Unit – список выбора. Предустановленные данные, берутся из БД. Значения - bar, voltage, °С, %.
* Location – местоположение сенсора. Текстовое поле. Валидация не больше 40 символов.
* Description – описание датчика. Валидация не больше 200 символов.