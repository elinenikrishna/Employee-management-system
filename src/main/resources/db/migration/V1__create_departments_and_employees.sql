create table departments (
    id bigint primary key auto_increment,
    code varchar(30) not null unique,
    name varchar(120) not null,
    cost_center varchar(120),
    created_at timestamp not null default current_timestamp,
    updated_at timestamp not null default current_timestamp on update current_timestamp
);

create table employees (
    id bigint primary key auto_increment,
    employee_number varchar(40) not null unique,
    first_name varchar(80) not null,
    last_name varchar(80) not null,
    email varchar(160) not null unique,
    phone varchar(32),
    status varchar(30) not null,
    job_level varchar(40) not null,
    job_title varchar(120) not null,
    hire_date date not null,
    salary decimal(12,2),
    department_id bigint not null,
    version bigint,
    created_at timestamp not null default current_timestamp,
    updated_at timestamp not null default current_timestamp on update current_timestamp,
    constraint fk_employees_department foreign key (department_id) references departments(id)
);

create index idx_departments_code on departments(code);
create index idx_departments_name on departments(name);
create index idx_employees_employee_number on employees(employee_number);
create index idx_employees_email on employees(email);
create index idx_employees_department_status on employees(department_id, status);
create index idx_employees_last_name on employees(last_name);
create index idx_employees_search_name on employees(last_name, first_name);
