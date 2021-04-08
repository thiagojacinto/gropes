-- Table: public.empresa

CREATE TABLE public.empresa
(
    id bigint NOT NULL DEFAULT nextval('empresa_id_seq'::regclass),
    autonomo boolean NOT NULL,
    descricao character varying(255) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT empresa_pkey PRIMARY KEY (id)
)

TABLESPACE pg_default;

ALTER TABLE public.empresa
    OWNER to postgres;

-- Table: public.tecnologia

CREATE TABLE public.tecnologia
(
    id bigint NOT NULL DEFAULT nextval('tecnologia_id_seq'::regclass),
    descricao character varying(255) COLLATE pg_catalog."default" NOT NULL,
    relevancia double precision,
    CONSTRAINT tecnologia_pkey PRIMARY KEY (id)
)

TABLESPACE pg_default;

ALTER TABLE public.tecnologia
    OWNER to postgres;

-- Table: public.usuario

CREATE TABLE public.usuario
(
    id bigint NOT NULL DEFAULT nextval('usuario_id_seq'::regclass),
    nascimento timestamp without time zone,
    nome character varying(100) COLLATE pg_catalog."default" NOT NULL,
    score double precision,
    CONSTRAINT usuario_pkey PRIMARY KEY (id)
)

TABLESPACE pg_default;

ALTER TABLE public.usuario
    OWNER to postgres;

-- Table: public.tecnologia_usuario

CREATE TABLE public.tecnologia_usuario
(
    conhece_desde timestamp without time zone,
    estuda_desde timestamp without time zone,
    estudou_ate timestamp without time zone,
    inovatividade double precision,
    id_usuario bigint NOT NULL,
    id_tecnologia bigint NOT NULL,
    CONSTRAINT tecnologia_usuario_pkey PRIMARY KEY (id_tecnologia, id_usuario),
    CONSTRAINT fkhdas3asico50h6rcmdmpxu5oa FOREIGN KEY (id_usuario)
        REFERENCES public.usuario (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE CASCADE,
    CONSTRAINT fkl8wq3rnt4y44l7kafwgnggi60 FOREIGN KEY (id_tecnologia)
        REFERENCES public.tecnologia (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE CASCADE
)

TABLESPACE pg_default;

ALTER TABLE public.tecnologia_usuario
    OWNER to postgres;

-- Table: public.empresa_usuario

CREATE TABLE public.empresa_usuario
(
    id bigint NOT NULL DEFAULT nextval('empresa_usuario_id_seq'::regclass),
    complexidade integer,
    data_fim timestamp without time zone,
    data_ini timestamp without time zone,
    diversidade integer,
    id_empresa bigint NOT NULL,
    id_usuario bigint NOT NULL,
    CONSTRAINT empresa_usuario_pkey PRIMARY KEY (id),
    CONSTRAINT fkas3d10m31o6nm9s3lphkl61o0 FOREIGN KEY (id_empresa)
        REFERENCES public.empresa (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE CASCADE,
    CONSTRAINT fktrmuk4jbp4yuirc5ullsub2yk FOREIGN KEY (id_usuario)
        REFERENCES public.usuario (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE CASCADE
)

TABLESPACE pg_default;

ALTER TABLE public.empresa_usuario
    OWNER to postgres;

-- Table: public.empresa_usuario_item

CREATE TABLE public.empresa_usuario_item
(
    ca double precision,
    data_fim timestamp without time zone,
    data_ini timestamp without time zone,
    det double precision,
    exp integer,
    frequencia integer,
    tecnologia_id bigint NOT NULL,
    empusu_id bigint NOT NULL,
    CONSTRAINT empresa_usuario_item_pkey PRIMARY KEY (empusu_id, tecnologia_id),
    CONSTRAINT fk4fu0o6wgk9drf2nhk84y8bksc FOREIGN KEY (empusu_id)
        REFERENCES public.empresa_usuario (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE CASCADE,
    CONSTRAINT fk6qwdnf1aun57rf29jw7q9lde2 FOREIGN KEY (tecnologia_id)
        REFERENCES public.tecnologia (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE CASCADE
)

TABLESPACE pg_default;

ALTER TABLE public.empresa_usuario_item
    OWNER to postgres;