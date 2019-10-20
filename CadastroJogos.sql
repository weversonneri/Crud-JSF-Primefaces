create database CadastroJogos;
use CadastroJogos;

create table cadastro (
	id int auto_increment null primary key,
	titulo varchar (20) ,
    genero varchar(15),
    plataforma varchar(15),
    desenvolvedor varchar(15),
    distribuidora varchar(30),
    lancamento varchar (10)
);

select * from cadastro;
drop table cadastro