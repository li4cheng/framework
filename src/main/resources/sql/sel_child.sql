drop procedure if exists selectChild;
delimiter $
create procedure selectChild (root_id long)
begin
	declare this_id long;
	declare done int default 0;
	declare root_district cursor for select district.id from district where	district.pid = root_id;
declare continue handler for not found set done = 1;
	set max_sp_recursion_depth = 10;
open root_district;
fetch root_district into this_id;
insert into temp (tmp_id, pid, name, pinyin)
    (select id, pid, name, pinyin from district where id = root_id);
while (done = 0)
	do
		call selectChild (this_id);
fetch root_district into this_id;
end while;
close root_district;
end; $
delimiter ;
