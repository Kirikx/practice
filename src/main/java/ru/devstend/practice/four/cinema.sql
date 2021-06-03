-- 1. Задача про кинотеатр.
-- У фильма, который идет в кинотеатре, есть название, длительность (пусть будет 60, 90 или 120 минут),
-- цена билета (в разное время и дни может быть разной), время начала сеанса (один фильм может быть
-- показан несколько раз в разное время и с разной ценой билета). Есть информация о купленных билетах
-- (номер билета, на какой сеанс).

-- Задания:
-- - Составить грамотную нормализованную схему хранения этих данных в БД. Внести в нее 4–5 фильмов,
-- расписание на один день и несколько проданных билетов.

-- Нормализация.
-- Фильм:
-- - Название
-- - длительность
-- Сеанс:
-- - фильм
-- - время начала сеанса
-- - цена
-- Билет:
-- - номер
-- - сеанс

create schema cinema;
comment on schema cinema is 'Кинотеатр';

CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

create table cinema.film
(
    id       uuid default uuid_generate_v4() not null
        constraint film_pk
            primary key,
    name     varchar(255)                    not null,
    duration int                             not null
);
comment on table cinema.film is 'Фильм';
comment on column cinema.film.id is 'Идентификатор';
comment on column cinema.film.name is 'Название';
comment on column cinema.film.duration is 'Длительность';
create unique index film_name_uindex
    on cinema.film (name);

create table cinema.session
(
    id         uuid default uuid_generate_v4() not null
        constraint session_pk
            primary key,
    film_id    uuid                            not null
        constraint session_film_fk
            references cinema.film,
    time_begin timestamptz                     not null,
    cost       decimal(10, 2)                  not null
);
comment on table cinema.session is 'Сеанс';
comment on column cinema.session.id is 'Идентификатор';
comment on column cinema.session.film_id is 'Идентификатор фильма';
comment on column cinema.session.time_begin is 'Время начала сеанса';
comment on column cinema.session.cost is 'Цена';

create table cinema.ticket
(
    id         uuid default uuid_generate_v4() not null
        constraint ticket_pk
            primary key,
    session_id uuid                            not null
        constraint ticket_session_fk
            references cinema.session
);
comment on table cinema.ticket is 'Билет';
comment on column cinema.ticket.id is 'Идентификатор/номер билета';
comment on column cinema.ticket.session_id is 'Идентификатор сеанса';

INSERT INTO cinema.film (id, name, duration)
VALUES ('2a8d77e2-ff03-42a4-99da-76bc151abb01', 'Назад в будущее', 90);
INSERT INTO cinema.film (id, name, duration)
VALUES ('e037c835-0488-4781-b8bb-c7d860b97b96', 'Назад в будушее 2', 120);
INSERT INTO cinema.film (id, name, duration)
VALUES ('f56d6119-8d16-47cb-be5c-942fab7b2ba7', 'Назад в будущее 3', 120);
INSERT INTO cinema.film (id, name, duration)
VALUES ('07545408-ce1b-4d73-92d9-69d046be6acb', 'Мстители. Финал', 180);
INSERT INTO cinema.film (id, name, duration)
VALUES ('101d10da-8b8b-454e-92a9-1cf669804f7d', 'Мстители', 160);

INSERT INTO cinema.session (id, film_id, time_begin, cost)
VALUES ('ff5b041b-e2a5-4a08-a238-219c2b6ed3a6', '07545408-ce1b-4d73-92d9-69d046be6acb',
        '2021-06-03 08:00:00.980000', 150.00);
INSERT INTO cinema.session (id, film_id, time_begin, cost)
VALUES ('33470726-01ea-4e41-84f5-d57b2e8fb1d9', '07545408-ce1b-4d73-92d9-69d046be6acb',
        '2021-06-03 09:00:00.980000', 200.00);
INSERT INTO cinema.session (id, film_id, time_begin, cost)
VALUES ('0e868d11-f7bf-4b5c-bbfe-90ae7cff7831', '07545408-ce1b-4d73-92d9-69d046be6acb',
        '2021-06-03 12:00:00.980000', 250.00);
INSERT INTO cinema.session (id, film_id, time_begin, cost)
VALUES ('22b6d322-b38f-47c6-8445-425b7c08c708', '07545408-ce1b-4d73-92d9-69d046be6acb',
        '2021-06-03 14:00:00.980000', 350.00);
INSERT INTO cinema.session (id, film_id, time_begin, cost)
VALUES ('58b3fd20-1cc9-45ee-9259-10b9951934d0', '07545408-ce1b-4d73-92d9-69d046be6acb',
        '2021-06-03 16:00:00.980000', 500.00);
INSERT INTO cinema.session (id, film_id, time_begin, cost)
VALUES ('1a5d7218-5325-40a4-9142-a3929f4edbfb', '07545408-ce1b-4d73-92d9-69d046be6acb',
        '2021-06-03 18:00:00.980000', 750.00);
INSERT INTO cinema.session (id, film_id, time_begin, cost)
VALUES ('c18d20c8-8679-4d57-af3c-a4dbe31065d2', '07545408-ce1b-4d73-92d9-69d046be6acb',
        '2021-06-03 20:00:00.980000', 750.00);
INSERT INTO cinema.session (id, film_id, time_begin, cost)
VALUES ('94d2bf0b-73f2-4599-bc3e-3d5c62420f58', '07545408-ce1b-4d73-92d9-69d046be6acb',
        '2021-06-03 22:00:00.980000', 700.00);
INSERT INTO cinema.session (id, film_id, time_begin, cost)
VALUES ('08de0389-1dbd-4e0d-a63b-21c50b7dacb2', '07545408-ce1b-4d73-92d9-69d046be6acb',
        '2021-06-03 00:00:00.980000', 500.00);
INSERT INTO cinema.session (id, film_id, time_begin, cost)
VALUES ('e38ff68b-df13-45c9-bfbc-b0105f2cc911', '07545408-ce1b-4d73-92d9-69d046be6acb',
        '2021-06-03 02:00:00.980000', 300.00);

INSERT INTO cinema.ticket (id, session_id)
VALUES ('95b478c1-d0da-4c8c-9933-ad0835ce86d5', 'ff5b041b-e2a5-4a08-a238-219c2b6ed3a6');
INSERT INTO cinema.ticket (id, session_id)
VALUES ('ca73f39a-5742-473f-afd8-c3a39d1c8e90', '33470726-01ea-4e41-84f5-d57b2e8fb1d9');
INSERT INTO cinema.ticket (id, session_id)
VALUES ('3b7d4c8f-c7a4-497b-9948-7fe018597825', '0e868d11-f7bf-4b5c-bbfe-90ae7cff7831');
INSERT INTO cinema.ticket (id, session_id)
VALUES ('cf8539e4-c58d-4ada-9b1e-0b9b035bec95', '22b6d322-b38f-47c6-8445-425b7c08c708');
INSERT INTO cinema.ticket (id, session_id)
VALUES ('3d00b49f-abe5-4048-88c8-0c815ff5d399', '58b3fd20-1cc9-45ee-9259-10b9951934d0');
INSERT INTO cinema.ticket (id, session_id)
VALUES ('ae7c5b00-2be0-4925-a4d2-893fc47c399f', '1a5d7218-5325-40a4-9142-a3929f4edbfb');
INSERT INTO cinema.ticket (id, session_id)
VALUES ('42971f26-bab0-456e-9586-d4cccc5f397b', 'c18d20c8-8679-4d57-af3c-a4dbe31065d2');
INSERT INTO cinema.ticket (id, session_id)
VALUES ('2caa89b5-ff7a-417f-af70-01a4a208cc8a', '94d2bf0b-73f2-4599-bc3e-3d5c62420f58');
INSERT INTO cinema.ticket (id, session_id)
VALUES ('9d688587-89e8-4fce-8a18-b24703e1a2d3', '08de0389-1dbd-4e0d-a63b-21c50b7dacb2');
INSERT INTO cinema.ticket (id, session_id)
VALUES ('9800c34d-df05-42c1-8a00-d3ce662cb4b0', 'e38ff68b-df13-45c9-bfbc-b0105f2cc911');


-- -- Сделать запросы, считающие и выводящие в понятном виде:
-- - ошибки в расписании (фильмы накладываются друг на друга), отсортированные по возрастанию времени.
-- Выводить надо колонки «фильм 1», «время начала», «длительность», «фильм 2», «время начала»,
-- «длительность»;

with temp as (
    select f.name,
           s.time_begin,
           f.duration,
           s.id s_id
    from cinema.film f
             join cinema.session s on f.id = s.film_id
)
select f1.name       "фильм 1",
       f1.time_begin "время начала",
       f1.duration   "длительность",
       f2.name       "фильм 2",
       f2.time_begin "время начала",
       f2.duration   "длительность"
from temp as f1
         join temp as f2
              on f2.time_begin between f1.time_begin and f1.time_begin + (f1.duration || ' minutes')::interval
                  and f1.s_id != f2.s_id;

-- - перерывы 30 минут и более между фильмами — выводить по уменьшению длительности перерыва. Колонки
-- «фильм 1», «время начала», «длительность», «время начала второго фильма», «длительность перерыва»;


-- - список фильмов, для каждого — с указанием общего числа посетителей за все время, среднего числа
-- зрителей за сеанс и общей суммы сборов по каждому фильму (отсортировать по убыванию прибыли).
-- Внизу таблицы должна быть строчка «итого», содержащая данные по всем фильмам сразу;


-- - число посетителей и кассовые сборы, сгруппированные по времени начала фильма: с 9 до 15, с 15 до 18,
-- с 18 до 21, с 21 до 00:00 (сколько посетителей пришло с 9 до 15 часов и т.д.).