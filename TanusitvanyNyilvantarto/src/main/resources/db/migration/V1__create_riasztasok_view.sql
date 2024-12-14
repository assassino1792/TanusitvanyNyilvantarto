CREATE OR REPLACE VIEW riasztasok_view AS
SELECT
    t.id AS tanusitvany_id,
    CASE
        WHEN CURRENT_DATE > t.lejarati_ido THEN 'EXPIRED'
        WHEN CURRENT_DATE + INTERVAL '3 days' >= t.lejarati_ido THEN 'CRITICAL'
        WHEN CURRENT_DATE + INTERVAL '14 days' >= t.lejarati_ido THEN 'WARNING'
        ELSE 'OK'
    END AS riasztas_tipus
FROM Tanusitvanyok t;
