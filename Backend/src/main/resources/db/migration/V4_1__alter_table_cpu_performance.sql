-- Alterar os campos para NOT NULL
ALTER TABLE cpu_performance ALTER COLUMN single_thread_performance SET NOT NULL;
ALTER TABLE cpu_performance ALTER COLUMN multi_thread_performance SET NOT NULL;