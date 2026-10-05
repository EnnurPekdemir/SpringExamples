-- 1. Eklentiyi aktifleştir (eğer aktif değilse)
CREATE EXTENSION IF NOT EXISTS pg_stat_statements;

-- 2. Sorgu istatistiklerini ve çağrı sayılarını görüntüle (Videodaki sorgu)
SELECT query, calls, total_exec_time, min_exec_time, max_exec_time 
FROM pg_stat_statements 
ORDER BY calls DESC;

-- 3. Veritabanındaki arabaları listele
SELECT * FROM cars;

-- 4. İstatistikleri sıfırlamak isterseniz (testleri baştan başlatmak için):
-- SELECT pg_stat_statements_reset();
