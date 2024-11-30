-- Renomear a coluna 'relative_performance' para 'game_performance'
ALTER TABLE cpu_performance RENAME COLUMN relative_performance TO game_performance;

-- Adicionar as colunas para desempenho em single-thread e multi-thread
ALTER TABLE cpu_performance ADD COLUMN single_thread_performance INTEGER;
ALTER TABLE cpu_performance ADD COLUMN multi_thread_performance INTEGER;