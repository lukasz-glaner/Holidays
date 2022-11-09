insert into holiday (start_date, end_date, approver_id, approved)
values (CAST('2022-10-25' AS Date), CAST('2022-10-26' AS Date), 1L, 'FALSE');
insert into holiday (start_date, end_date, approver_id, approved)
values (CAST('2022-10-24' AS DateTime), CAST('2022-10-25' AS Date), 2L, 'TRUE');
insert into holiday (start_date, end_date, approver_id, approved)
values (CAST('2022-10-23' AS Date), CAST('2022-10-24' AS Date), 3L, 'FALSE');
insert into holiday (start_date, end_date, approver_id, approved)
values (CAST('2022-10-22' AS Date), CAST('2022-10-23' AS Date), 4L, 'TRUE');
