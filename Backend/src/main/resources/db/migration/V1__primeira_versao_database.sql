-- ************************************** "public".users
CREATE TABLE "public".users
(
    user_id           serial       PRIMARY KEY,
    name              varchar(100) NOT NULL,
    email             varchar(150) NOT NULL,
    password          varchar(255) NOT NULL,
    created_at        timestamptz  NOT NULL,
    updated_at        timestamptz  NULL,
    active_user       bool         NOT NULL
);


-- ************************************** "public".sockets
CREATE TABLE "public".sockets
(
    socket_id serial       PRIMARY KEY,
    name      varchar(100) NOT NULL
);


-- ************************************** "public".processors
CREATE TABLE "public".processors
(
    cpu_id             serial         PRIMARY KEY,
    created_by         integer     	  NOT NULL,
    name               varchar(100)	  NOT NULL,
    brand              varchar(50) 	  NOT NULL,
    socket_id          integer     	  NOT NULL,
    cores              integer        NOT NULL,
    threads            integer        NOT NULL,
    base_clock         decimal(4, 2)  NOT NULL,
    turbo_clock        decimal(4, 2)  NOT NULL,
    tdp                integer        NOT NULL,
    price              decimal(10, 2) NULL,
    component_ranking  decimal(5, 2)  NULL,
    cpu_performance_id integer 		  NULL
);

COMMENT ON COLUMN "public".processors.brand IS 'ENUM';


-- ************************************** "public".cpu_performance
CREATE TABLE "public".cpu_performance
(
    cpu_performance_id   serial     PRIMARY KEY,
    cpu_id               integer    NOT NULL,
    relative_performance integer 	NOT NULL
);


-- ************************************** "public".graphics_cards
CREATE TABLE "public".graphics_cards
(
    gpu_id             serial        PRIMARY KEY,
    created_by         integer		 NOT NULL,
    name               varchar(100)  NOT NULL,
    chipset            varchar(50) 	 NOT NULL,
    brand              varchar(50) 	 NOT NULL,
    base_clock         integer 		 NOT NULL,
    boost_clock        integer 		 NOT NULL,
    memory_size        integer 	     NOT NULL,
    price              decimal(10,2) NULL,
    component_ranking  decimal(5,2)  NULL,
    gpu_performance_id integer       NULL
);

COMMENT ON COLUMN "public".graphics_cards.chipset IS 'ENUM';
COMMENT ON COLUMN "public".graphics_cards.brand IS 'ENUM';


-- ************************************** "public".gpu_performance
CREATE TABLE "public".gpu_performance
(
    gpu_performance_id   serial     PRIMARY KEY,
    gpu_id               integer    NOT NULL,
    relative_performance integer 	NOT NULL
);


-- ************************************** "public".cpu_coolers
CREATE TABLE "public".cpu_coolers
(
    cpu_cooler_id     serial         PRIMARY KEY,
    created_by        integer        NOT NULL,
    name              varchar(100)   NOT NULL,
    manufacturer      varchar(50)    NOT NULL,
    fan_rpm           integer 	     NULL,
    noise_level       decimal(4, 2)  NULL,
    radiator_size     integer 	     NULL,
    price             decimal(10, 2) NULL,
    component_ranking decimal(5, 2)  NULL
);

COMMENT ON COLUMN "public".cpu_coolers.manufacturer IS 'ENUM ou Tabela no DB';


-- ************************************** "public".motherboards
CREATE TABLE "public".motherboards
(
    mobo_id           serial         PRIMARY KEY,
    created_by        integer        NOT NULL,
    name              varchar(100)   NOT NULL,
    brand             varchar(50)    NOT NULL,
    socket_id         integer        NOT NULL,
    chipset           varchar(50)    NOT NULL,
    form_factor       varchar(50)    NOT NULL,
    ram_slots         integer        NOT NULL,
    memory_max        integer        NOT NULL,
    pcie_slots        integer        NOT NULL,
    price             decimal(10, 2) NULL,
    component_ranking decimal(5, 2)  NULL
);

COMMENT ON COLUMN "public".motherboards.brand IS 'ENUM';
COMMENT ON COLUMN "public".motherboards.chipset IS 'ENUM';
COMMENT ON COLUMN "public".motherboards.form_factor IS 'ENUM';


-- ************************************** "public".ram_modules
CREATE TABLE "public".ram_modules
(
    ram_id            serial         PRIMARY KEY,
    created_by        integer        NOT NULL,
    name              varchar(100)   NOT NULL,
    type              varchar(20)    NOT NULL,
    speed             integer        NOT NULL,
    modules           varchar(50)  	 NOT NULL,
    price             decimal(10, 2) NULL,
    component_ranking decimal(5, 2)  NULL
);

COMMENT ON COLUMN "public".ram_modules.type IS 'ENUM';
COMMENT ON COLUMN "public".ram_modules.modules IS 'ENUM';


-- ************************************** "public".storages
CREATE TABLE "public".storages
(
    storage_id        serial         PRIMARY KEY,
    created_by        integer      	 NOT NULL,
    name              varchar(100) 	 NOT NULL,
    capacity          integer        NOT NULL,
    cache             integer 	     NULL,
    type              varchar(20)  	 NOT NULL,
    form_factor       varchar(50)  	 NOT NULL,
    storage_interface varchar(50)  	 NOT NULL,
    price             decimal(10, 2) NULL,
    component_ranking decimal(5, 2)  NULL
);

COMMENT ON COLUMN "public".storages.form_factor IS 'ENUM';
COMMENT ON COLUMN "public".storages.storage_interface IS 'ENUM';


-- ************************************** "public".power_supplies
CREATE TABLE "public".power_supplies
(
    power_supply_id   serial         PRIMARY KEY,
    created_by        integer      	 NOT NULL,
    name              varchar(100) 	 NOT NULL,
    type              varchar(20)  	 NOT NULL,
    efficiency_rating varchar(20)  	 NOT NULL,
    wattage           integer        NOT NULL,
    modular           bool         	 NOT NULL,
    price             decimal(10, 2) NULL,
    component_ranking decimal(5, 2)  NULL
);

COMMENT ON COLUMN "public".power_supplies.type IS 'ENUM';
COMMENT ON COLUMN "public".power_supplies.efficiency_rating IS 'ENUM';


-- ************************************** "public".cases
CREATE TABLE "public".cases
(
    case_id           serial         PRIMARY KEY,
    created_by        integer      	 NOT NULL,
    name              varchar(100) 	 NOT NULL,
    type              varchar(50)  	 NOT NULL,
    color             varchar(20)  	 NOT NULL,
    side_panel        varchar(50)  	 NOT NULL,
    price             decimal(10, 2) NULL,
    component_ranking decimal(5, 2)  NULL
);

COMMENT ON COLUMN "public".cases.type IS 'ENUM';


-- ************************************** "public".processor_motherboard_compatibility
CREATE TABLE "public".processor_socket_compatibility
(
    psc_id    serial   PRIMARY KEY,
    cpu_id    integer  NOT NULL,
    socket_id integer  NOT NULL
);


-- ************************************** "public".cpu_cooler_socket_compatibility
CREATE TABLE "public".cpu_cooler_socket_compatibility
(
    csc_id        serial   PRIMARY KEY,
    compatibility bool     NOT NULL,
    cpu_cooler_id integer  NOT NULL,
    socket_id     integer  NOT NULL
);


-- ************************************** "public".user_builds
CREATE TABLE "public".user_builds
(
    user_build_id   serial           PRIMARY KEY,
    user_id         integer          NOT NULL,
    total_price     decimal(10, 2)   NOT NULL,
    usage_type      varchar(50)      NOT NULL,
    cpu_id          integer          NOT NULL,
    gpu_id          integer          NOT NULL,
    mobo_id         integer          NOT NULL,
    ram_id          integer          NOT NULL,
    storage_id      integer          NOT NULL,
    power_supply_id integer          NOT NULL,
    case_id         integer          NOT NULL,
    cpu_cooler_id   integer          NOT NULL
);


-- Creating Foreign Keys
ALTER TABLE "public".processors ADD CONSTRAINT FK_1 FOREIGN KEY (socket_id) REFERENCES "public".sockets (socket_id);
ALTER TABLE "public".processors ADD CONSTRAINT FK_2 FOREIGN KEY (created_by) REFERENCES "public".users (user_id);
ALTER TABLE "public".processors ADD CONSTRAINT FK_3 FOREIGN KEY (cpu_performance_id) REFERENCES "public".cpu_performance (cpu_performance_id);

ALTER TABLE "public".motherboards ADD CONSTRAINT FK_4 FOREIGN KEY (socket_id) REFERENCES "public".sockets (socket_id);
ALTER TABLE "public".motherboards ADD CONSTRAINT FK_5 FOREIGN KEY (created_by) REFERENCES "public".users (user_id);

ALTER TABLE "public".graphics_cards ADD CONSTRAINT FK_6 FOREIGN KEY (created_by) REFERENCES "public".users (user_id);
ALTER TABLE "public".graphics_cards ADD CONSTRAINT FK_7 FOREIGN KEY (gpu_performance_id) REFERENCES "public".gpu_performance (gpu_performance_id);

ALTER TABLE "public".cpu_coolers ADD CONSTRAINT FK_9 FOREIGN KEY (created_by) REFERENCES "public".users (user_id);

ALTER TABLE "public".ram_modules ADD CONSTRAINT FK_10 FOREIGN KEY (created_by) REFERENCES "public".users (user_id);

ALTER TABLE "public".storages ADD CONSTRAINT FK_11 FOREIGN KEY (created_by) REFERENCES "public".users (user_id);

ALTER TABLE "public".power_supplies ADD CONSTRAINT FK_12 FOREIGN KEY (created_by) REFERENCES "public".users (user_id);

ALTER TABLE "public".cases ADD CONSTRAINT FK_13 FOREIGN KEY (created_by) REFERENCES "public".users (user_id);

ALTER TABLE "public".gpu_performance ADD CONSTRAINT FK_8 FOREIGN KEY (gpu_id) REFERENCES "public".graphics_cards (gpu_id);
ALTER TABLE "public".cpu_performance ADD CONSTRAINT FK_8 FOREIGN KEY (cpu_id) REFERENCES "public".processors (cpu_id);

ALTER TABLE "public".cpu_cooler_socket_compatibility ADD CONSTRAINT FK_14 FOREIGN KEY (cpu_cooler_id) REFERENCES "public".cpu_coolers (cpu_cooler_id);
ALTER TABLE "public".cpu_cooler_socket_compatibility ADD CONSTRAINT FK_15 FOREIGN KEY (socket_id) REFERENCES "public".sockets (socket_id);

ALTER TABLE "public".processor_socket_compatibility ADD CONSTRAINT FK_16 FOREIGN KEY (cpu_id) REFERENCES "public".processors (cpu_id);
ALTER TABLE "public".processor_socket_compatibility ADD CONSTRAINT FK_17 FOREIGN KEY (socket_id) REFERENCES "public".sockets (socket_id);

ALTER TABLE "public".user_builds ADD CONSTRAINT FK_18 FOREIGN KEY (cpu_cooler_id) REFERENCES "public".cpu_coolers (cpu_cooler_id);
ALTER TABLE "public".user_builds ADD CONSTRAINT FK_19 FOREIGN KEY (user_id) REFERENCES "public".users (user_id);
ALTER TABLE "public".user_builds ADD CONSTRAINT FK_20 FOREIGN KEY (cpu_id) REFERENCES "public".processors (cpu_id);
ALTER TABLE "public".user_builds ADD CONSTRAINT FK_21 FOREIGN KEY (gpu_id) REFERENCES "public".graphics_cards (gpu_id);
ALTER TABLE "public".user_builds ADD CONSTRAINT FK_22 FOREIGN KEY (mobo_id) REFERENCES "public".motherboards (mobo_id);
ALTER TABLE "public".user_builds ADD CONSTRAINT FK_23 FOREIGN KEY (ram_id) REFERENCES "public".ram_modules (ram_id);
ALTER TABLE "public".user_builds ADD CONSTRAINT FK_24 FOREIGN KEY (storage_id) REFERENCES "public".storages (storage_id);
ALTER TABLE "public".user_builds ADD CONSTRAINT FK_25 FOREIGN KEY (power_supply_id) REFERENCES "public".power_supplies (power_supply_id);
ALTER TABLE "public".user_builds ADD CONSTRAINT FK_26 FOREIGN KEY (case_id) REFERENCES "public".cases (case_id);