drop procedure if exists sel;
delimiter $
create procedure sel(id long)
begin
create table if not exists temp(tmp_id long, pid long, name varchar (255), pinyin varchar (255));
truncate table temp;
call selectChild(id);
select * from temp;
end; $
delimiter ;
