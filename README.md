# AMmaker
Учебный проект, JAVA.
Программа генерирует коды алкогольныех акцизных марок с заданными параметрами для тестирования кассового ПО.

Версия 1.1:
консольная программа, ожидает ввод алкода либо читает его из системного буфера обмена, переводит в Base36, добавляет ведущие нули, версию ЕГАИС, информацию о регистрации АМ и криптохвост. Выводит на экран и добавляет в буфер пользователя.

TODO:

-возможность генерации АМ с рандомными данными о регистрации и криптохвостом
-возможность передачи нескольких алкодов за раз
-возможность записи результатов в файл
-более дружелюбный UI
-GUI
-запись файла со статусами АМ в УТМ и модуле валидации SET (практически работа с БД)
-возможность задания версии ЕГАИС (рано или поздно понадобится)
-работа с табачным ЕГАИС и т.п.
