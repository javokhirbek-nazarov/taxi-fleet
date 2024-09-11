insert into taxi(id, number, status, lng, lat)
values (1, 'UZ111', 'BOOKED', 12.12, 12.12),
       (2, 'UZ222', 'BOOKED', 32.12, 42.12),
       (3, 'UA123', 'AVAILABLE', 42.2, 23.45),
       (4, 'UA777', 'AVAILABLE', 47.12, 48.1),
       (5, 'US333', 'AVAILABLE', 62.2, 23.45),
       (6, 'US747', 'BOOKED', 46.62, 88.1),
       (7, 'US141', 'BOOKED', 16.12, 17.12),
       (8, 'US242', 'BOOKED', 72.12, 82.12)
on conflict do nothing;
SELECT setval('taxi_seq', (select max(id) from taxi), true);

insert into booking(id, client, state, created_at)
values (1, 'Adam Smith', 'TAKEN', current_date),
       (2, 'Elena Newman', 'TAKEN', current_date),
       (3, 'Sara Cold', 'NEW', current_date),
       (4, 'Tom Holland', 'TAKEN', current_date)
on conflict do nothing;
SELECT setval('booking_seq', (select max(id) from booking), true);