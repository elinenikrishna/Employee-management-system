insert into departments(code, name, cost_center, created_at, updated_at)
values ('ENG','Engineering','CC-1001', utc_timestamp(), utc_timestamp()),
       ('HR','Human Resources','CC-1002', utc_timestamp(), utc_timestamp()),
       ('FIN','Finance','CC-1003', utc_timestamp(), utc_timestamp()),
       ('OPS','Operations','CC-1004', utc_timestamp(), utc_timestamp()),
       ('DATA','Data Platforms','CC-1005', utc_timestamp(), utc_timestamp())
on duplicate key update name = values(name), cost_center = values(cost_center), updated_at = utc_timestamp();
