# language: ru

@all
@renewalOfAppointments
Функционал: Продление назначений

  Предыстория:
    * переходим на страницу Авторизация
    * авторизуемся в системе с огранизацией "Шебекинская ЦРБ Тест"

  @TEST-2846
  Сценарий: Проверка перехода в раздел "Продление назначений"
    * проверяем, что мы находимся на странице "Журнал пациентов"
    * переходим в Основное меню и выбираем пункт "Продление назначений"
    * проверяем, что мы находимся на странице "Продление назначений"

  @TEST-2851
  Сценарий: Продление назначений. Просмотр назначений
    * проверяем, что мы находимся на странице "Журнал пациентов"
    * переходим в Основное меню и выбираем пункт "Продление назначений"
    * проверяем, что мы находимся на странице "Продление назначений"
    #* устанавливаем фильтр по полю Статус со значением "Ожидает"

  @TEST-2847
  Сценарий: Проверка перехода из раздела "Продление назначений" в "Журнал пациентов" по лого SofTrast
    * проверяем, что мы находимся на странице "Журнал пациентов"
    * переходим в Основное меню и выбираем пункт "Продление назначений"
    * проверяем, что мы находимся на странице "Продление назначений"
    * нажимаем на логотип SofTrust
    * проверяем, что мы находимся на странице "Журнал пациентов"