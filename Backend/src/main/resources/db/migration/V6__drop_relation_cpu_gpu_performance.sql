ALTER TABLE public.processors DROP CONSTRAINT fk_3;
ALTER TABLE public.processors DROP COLUMN cpu_performance_id;

ALTER TABLE public.graphics_cards DROP CONSTRAINT fk_7;
ALTER TABLE public.graphics_cards DROP COLUMN gpu_performance_id;