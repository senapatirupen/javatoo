-- Joining Corresponding Rows from Two or More Tables
--Problem
--You want to return rows from two or more tablesthat have one or more columns in common. For
--example, you may want to join the EMPLOYEESand the DEPARTMENTStable on a common column, but
--not all common columns, and return a list of employees and their department names.
select employee_id, last_name, first_name, department_id, department_name
from employees
join departments using(department_id) ;
--or
select employee_id, last_name, first_name, e.department_id, department_name
from employees e, departments d
where e.department_id = d.department_id
;
--or
select employee_id, last_name, first_name, e.department_id, department_name
from employees e
join departments d
on e.department_id = d.department_id
;

--Stacking Query Results Vertically
--Problem
--You want to combine the results from two SELECT statements into a single result set.
select employee_id, first_name, last_name from employees_act
union
select employee_id, first_name, last_name from employees_new
order by employee_id
;

-- join, left outer join, right outer join, full outer join

--Removing Rows Based on Data in Other Tables
--Problem
--You want to delete rows from a table if corresponding rows exist in a second table. For example, you
--want to delete rows from the EMPLOYEES_RETIREDtable for any employees that exist in the EMPLOYEES
--table.
delete from employees_retired
where employee_id
in (select employee_id from employees)
;
--or
delete from employees_retired er
where exists (
select 1 from employees e
where er.employee_id = e.employee_id
)
;

--Joining on Aggregates
--Problem
--You want to compare values in individual rows to aggregate values computed from the same table; you also
--want to filter rows that are used for the aggregate. For example: you want to find all employees whose salary is
--20% less than the average salary for the company, except for employees who are in the executive department.
select employee_id, last_name, first_name, salary
from employees
where salary < 0.8 * (
select avg(salary)
from employees
where department_id != 90
)
;
--or
select employee_id, last_name, first_name, salary
from employees
where salary = any (
select salary
from employees
where department_id = 90
)
;
--or
select department_id, avg(salary) avg_salary
from employees
group by department_id
having avg(salary) > (
select avg(salary)
from employees
where department_id = 60
)
;

--Finding Rows that Tables Do Not Have in Common
--Problem
--You want to find rows from two queries or tables that the two queries or tables do NOT have in common.
--The analysts have provided you with Venn diagrams and the set notation for retrieving the required
--data, so you must use a combination of Oracle set operators to retrieve the desired result.
select employee_id, first_name, last_name,
count(act_emp_src) act_emp_row_count,
count(new_emp_src) new_emp_row_count
from
(
select ea.*, 1 act_emp_src, to_number(null) new_emp_src
from employees_act ea
union all
select en.*, to_number(null) act_emp_src, 1 new_emp_src
from employees_new en
)
group by employee_id, first_name, last_name
having count(act_emp_src) != count(new_emp_src)
;

--some more
select
s.channel_desc,
regr_intercept(s.total_sold, p.prod_list_price) total_sold_intercept,
regr_slope (s.total_sold, p.prod_list_price) trend_slope,
regr_r2(s.total_sold, p.prod_list_price) r_squared_confidence
from sh.products p,
(select c.channel_desc, s.prod_id, s.time_id, sum(s.quantity_sold) total_sold
from sh.sales s inner join sh.channels c
on s.channel_id = c.channel_id
group by c.channel_desc, s.prod_id, s.time_id) s
where s.prod_id=p.prod_id
and p.prod_category='Electronics'
and s.time_id between to_date('01-JAN-1998') and to_date('31-DEC-1999')
group by s.channel_desc
order by 1;

--some more
select c.channel_desc, s.prod_id, s.time_id, sum(s.quantity_sold) total_sold
from sh.sales s inner join sh.channels c
on s.channel_id = c.channel_id
group by c.channel_desc, s.prod_id, s.time_id;

--some more
create view order_items_subtotal_vw as
select order_id, line_item_id, product_id,
unit_price, quantity,
to_char(unit_price*quantity,'$9,999,999.99') line_total_price
from order_items
;

--Changing Nulls into Real Values
--Problem
--You want to substitute null values in a table or result with a meaningful alternative.
select employee_id, last_name,
nvl(to_char(commission_pct), 'No Commission') as COMMISSION
from hr.employees;

--Paginating Query Results
--Problem
--You need to display query results on web pages, showing a subset of results on each page. Users will be
--able to navigate back and forth through the pages of results.
select product_id, product_name, list_price from
(select prodinfo.*, rownum r
from
(select product_id, product_name, list_price
from oe.product_information
order by product_id) prodinfo
where rownum <= 10)
where r >= 1;

--Testing for the Existence of Data
--Problem
--You would like to compare the data in two related tables, to show where matching data exists, and to
--also show where matching data doesn’t exist.
select department_name
from hr.departments d
where exists
(select e.employee_id
from hr.employees e
where d.manager_id = e.employee_id);
--'exists' and 'not exists'

--Conditional Branching In One SQL Statement
--Problem
--In order to produce a concise result in one query, you need to change the column returned on a row-by-row basis, conditional on a value from another row. You want to avoid awkward mixes of unions,
--subqueries, aggregation, and other inelegant techniques.
select e.employee_id,
case
when old.job_id is null then e.hire_date
else old.end_date end
job_start_date
from hr.employees e left outer join hr.job_history old
on e.employee_id = old.employee_id
where e.department_id = 50
order by e.employee_id;

--or

select e.employee_id, e.hire_date, old.end_date end
from hr.employees e left outer join hr.job_history old
on e.employee_id = old.employee_id
where e.department_id = 50
order by e.employee_id;