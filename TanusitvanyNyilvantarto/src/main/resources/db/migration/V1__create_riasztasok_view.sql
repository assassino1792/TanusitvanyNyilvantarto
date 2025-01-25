CREATE OR REPLACE VIEW riasztasok_view AS
SELECT
    t.id AS tanusitvany_id,
    CASE
        WHEN CURRENT_DATE > t.lejarati_ido THEN 'Lejárt'
        WHEN CURRENT_DATE + INTERVAL '3 days' >= t.lejarati_ido THEN 'Kritikus'
        WHEN CURRENT_DATE + INTERVAL '14 days' >= t.lejarati_ido THEN 'Figyelmeztetés'
        ELSE 'OK'
    END AS riasztas_tipus
FROM Tanusitvanyok t;
