# ShiftSummer2020_2
Погодное приложение пишется в рамках ШИФТ ЦФТ лето 2020

1. API погоды: https://www.weatherbit.io/api/weather-current (запланировано)
2. Список городов формируется пользователем и хранится в БД (запланировано) 
3. Данные погоды кешируются в БД, время кеша задается пользователем (запланировано)
4. решение по архитекутурному паатерну принять после стрима по "MV-что?" 24.07 (запланировано)
5. приложение будет single-activity (на фрагментах) для навигации в качестве тренировки использовать Navigation Architecture Component (запланировано)

## На данный момент
1. переход между фрагментами реализован просто через транзакции
2. только базовое отображение списка городов и открытие фрагмента с деталями погоды.
3. частично структуры приложения с разделением по слоям
