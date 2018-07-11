create sequence ZOO_GEN_ANIMAL_ID minvalue 1 start with 1 increment by 1 cache 1;

create table ZOO_ANIMAL (
  animal_id number(10) primary key, -- ZOO_GEN_ANIMAL_ID  
  animal_name varchar2(250) not null unique
);