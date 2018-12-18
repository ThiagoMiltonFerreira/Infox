﻿-- Comentários
-- a linha a baixo cria um banco de dados
create database dbinfox;
-- a linha abaixo escolhe o banco de dados a ser ultilizado
use dbinfox;
-- o bloco de instruçoes a baixo cria tabela
create table tbusuarios(
iduser int primary key, 
usuario varchar(50) not null,
fone varchar(15),
login varchar(15) not null unique, -- not null nao fica em branco, unique nao se repete
senha varchar(15) not null unique

);
-- comando abaixo descreve a tabela
describe tbusuarios; -- mostra a tabela
-- a linha abaixo insere dados na tabela (CRUD)
-- create -> insert
insert into tbusuarios(iduser,usuario,fone,login,senha)
values(1,'Jose de Assis', '9999-9999','joseassis','123456');

-- a linha abaixo exibe os dados da tabela (CRUD)
-- select 
select*from tbusuarios;

insert into tbusuarios(iduser,usuario,fone,login,senha)
values(2,'Administrador', '9999-9999','admin','admin');
insert into tbusuarios(iduser,usuario,fone,login,senha)
values(3,'Bill Gates', '9999-9999','bill','12345');

-- a linha abaixo modifica dados na tabela(CRUD)
-- update
update tbusuarios set fone='8888-8888' where iduser=2; -- atualize a tabela usuarios modifique o fone onde id for igual a 2

-- a linha abaixo deleta um registro da tabela(CRUD)
-- delete
delete from tbusuarios where iduser=3; 

create table tbclientes(
idcli int primary key auto_increment,
nomecli varchar(50) not null,
endcli varchar(100),
fonecli varchar(50),
emailcli varchar(50)
);

describe tbclientes;

insert into tbclientes(nomecli,endcli,fonecli,emailcli)
values('linus torvalds', 'rua tux, 2015','9999-9999','linus@linux.com');

delete from tbclientes where idcli=5; 

 select * from tbclientes;
 
 
 create table tbos(
 os int primary key auto_increment,
 data_os timestamp default current_timestamp,   -- pega data e hora no momento do insert
 equipamento varchar(150) not null,
 defeito varchar(150) not null,
 servico varchar(150),
 tecnico varchar(30),
 valor decimal(10,2), -- valor decimal com 10 digitos e 2 dps da virgula
 idcli int not null, -- relacionamento com a tabela cliente chave estrangeira
 foreign key(idcli) references tbclientes(idcli) -- cria foreingkey
 );
 
 describe tbos;
 
 insert into tbos(equipamento,defeito,servico,tecnico,valor,idcli)
 values('Notbook', 'Nao liga','Troca da fonte','Zé',87.50,1);
 
  select * from tbos;
  
  
  -- o codigo abaixo tras informaçoes de duas tabelas
  
  select
  O.os,equipamento,defeito,servico,valor, -- seleciona da tabela (OS o O. e uma variavel) os campos ,equipamento,defeito,servico,valor 
  C.nomecli,fonecli -- (C. e uma variavel)seleciona da tabela nomecli nomecli,fonecli
  from tbos as O -- selecione de  tbos a variavel O
  inner join  tbclientes as C-- junta a tabela
  on (O.idcli = C.idcli); -- chave idcli seja igual idcli
  


-- SELECT BANCO ONDE SENHA FOR IGUAL USUARIO E SENHA IGUA SENHA
-- select * from where login = ? and senha = ?;



-- ADD NOVA COLUNA NO BANCO NA TABELA TB USUARIO
alter table tbusuario add column perfil varchar(20) not null;


-- Remove um campo na tabela
alter table tbusuarios drop column perfil;

-- atualiza id do usuario
update tbusuarios set fone='8888-8888' where iduser=2;
describe tbos;

-- a instrucao abaixo faz a selecao e uniao de dados de duas tabelas
-- OSER e uma variavel que contem os campodesejados da tabela OS
-- CLI e outra variavel que contem campos desejados da tabela clientes
select
OSER.os,data_os,tipo,situacao,valor,
CLI.nomecli, fonecli
from tbos as OSER
inner join tb;

SELECT os,data_os,tipo,situacao,equipamento,valor,nomecli,fonecli -- todos campos da duas tabelas
FROM tbos   -- tbos junta com tbclientes onde idcli.idcli for igual a tbclientes.idcli 
INNER JOIN tbclientes ON tbos.idcli = tbclientes.idcli;

select idcli,nomecli,fonecli from tbClientes where nomecli like 'Mi%';

INSERT into tbClientes(idcli,tecnico,valor)
         values(1,"ssss",10);