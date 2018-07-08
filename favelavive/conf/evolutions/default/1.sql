# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table atividade (
  id                            bigserial not null,
  atividade_tipo                integer,
  atividade_categoria           integer,
  titulo                        varchar(255),
  descricao                     varchar(255),
  data_atividade                timestamp,
  pessoa_id                     bigint,
  favela_id                     bigint,
  data_criacao                  timestamp not null,
  data_modificacao              timestamp not null,
  constraint ck_atividade_atividade_tipo check (atividade_tipo in (0,1)),
  constraint ck_atividade_atividade_categoria check (atividade_categoria in (0,1,2,3,4)),
  constraint pk_atividade primary key (id)
);

create table contribuicao (
  id                            bigserial not null,
  atividade_id                  bigint,
  pessoa_id                     bigint,
  data_criacao                  timestamp not null,
  data_modificacao              timestamp not null,
  constraint pk_contribuicao primary key (id)
);

create table favela (
  id                            bigserial not null,
  pontuacao                     integer,
  nome                          varchar(255),
  uf                            integer,
  cidade                        varchar(255),
  data_criacao                  timestamp not null,
  data_modificacao              timestamp not null,
  constraint ck_favela_uf check (uf in (0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26)),
  constraint pk_favela primary key (id)
);

create table municipio (
  id                            serial not null,
  codigo                        integer,
  uf                            varchar(255),
  nome                          varchar(255),
  constraint pk_municipio primary key (id)
);

create table pessoa (
  id                            bigserial not null,
  pessoa_tipo                   integer,
  pontuacao                     integer,
  nome                          varchar(255),
  email                         varchar(255),
  senha                         varchar(255),
  genero                        varchar(255),
  url_foto                      varchar(255),
  token                         varchar(255),
  favela_id                     bigint,
  data_nascimento               timestamp,
  data_criacao                  timestamp not null,
  data_modificacao              timestamp not null,
  constraint ck_pessoa_pessoa_tipo check (pessoa_tipo in (0,1)),
  constraint pk_pessoa primary key (id)
);

alter table atividade add constraint fk_atividade_pessoa_id foreign key (pessoa_id) references pessoa (id) on delete restrict on update restrict;
create index ix_atividade_pessoa_id on atividade (pessoa_id);

alter table atividade add constraint fk_atividade_favela_id foreign key (favela_id) references favela (id) on delete restrict on update restrict;
create index ix_atividade_favela_id on atividade (favela_id);

alter table contribuicao add constraint fk_contribuicao_atividade_id foreign key (atividade_id) references atividade (id) on delete restrict on update restrict;
create index ix_contribuicao_atividade_id on contribuicao (atividade_id);

alter table contribuicao add constraint fk_contribuicao_pessoa_id foreign key (pessoa_id) references pessoa (id) on delete restrict on update restrict;
create index ix_contribuicao_pessoa_id on contribuicao (pessoa_id);

alter table pessoa add constraint fk_pessoa_favela_id foreign key (favela_id) references favela (id) on delete restrict on update restrict;
create index ix_pessoa_favela_id on pessoa (favela_id);


# --- !Downs

alter table if exists atividade drop constraint if exists fk_atividade_pessoa_id;
drop index if exists ix_atividade_pessoa_id;

alter table if exists atividade drop constraint if exists fk_atividade_favela_id;
drop index if exists ix_atividade_favela_id;

alter table if exists contribuicao drop constraint if exists fk_contribuicao_atividade_id;
drop index if exists ix_contribuicao_atividade_id;

alter table if exists contribuicao drop constraint if exists fk_contribuicao_pessoa_id;
drop index if exists ix_contribuicao_pessoa_id;

alter table if exists pessoa drop constraint if exists fk_pessoa_favela_id;
drop index if exists ix_pessoa_favela_id;

drop table if exists atividade cascade;

drop table if exists contribuicao cascade;

drop table if exists favela cascade;

drop table if exists municipio cascade;

drop table if exists pessoa cascade;

