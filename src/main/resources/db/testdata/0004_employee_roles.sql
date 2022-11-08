insert into
    employee_role (name, description)
values
    ('ADMIN', 'full privileges'),   -- 1
    ('TEAM_LEADER', 'basic privileges and approving holidays'),   -- 2
    ('EMPLOYEE', 'basic privileges');   -- 3

insert into
    employees_roles (employee_id, role_id)
values
    (1, 1),
    (2, 2),
    (3, 3);