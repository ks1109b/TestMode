# TestMode [![Build status](https://ci.appveyor.com/api/projects/status/02kr3kk6l94wgr7a?svg=true)](https://ci.appveyor.com/project/ks1109b/testmode)

### Задача

Протестировать функцию входа через Web интерфейс с использованием Selenide в тестовом режиме.

### Результат

1. Создан дата-класс, в котором описана структура объекта с информацией о пользователе.
1. Создан дата-генератор, который по требованию создает рандомного пользователя, сохраняет его через API и возвращает в тест.
1. Активирован тестовый режим запуска целевого сервиса.
1. Протестирована функция входа через Web интерфейс.

Дополнительно: оценка времени на автоматизацию и времени на проверку тех же сценариев вручную, используя для тестирования интерфейс браузера и Postman для доступа к открытому API:
* Время, затраченное на ручное тестирование (минут): 45
* Время, затраченное на автоматизацию (минут): 35