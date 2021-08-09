select employee_id, first_name, last_name, hire_date, salary
from hr.employees
where department_id = 50
and (salary < 2500 or salary > 7500);

select *
from hr.employees
where department_id = 50
and salary < 7500;

--Users want to see data from a query sorted in a particular way. For example, they would like to see
--employees sorted alphabetically by surname, and then first name.
select employee_id, first_name, last_name, hire_date, salary
from hr.employees
where salary > 5000
order by last_name, first_name;

select employee_id, first_name, last_name, hire_date, salary
from hr.employees
where salary > 5000
order by salary desc;

-- Adding Rows to a Table
--You need to add new rows of data to a table. For example, a new employee joins the company, requiring
--his data to be added to the HR.EMPLOYEEStable.
insert into hr.employees
(employee_id, first_name, last_name, email, phone_number, hire_date, job_id,
salary, commission_pct, manager_id, department_id)
values
(207, 'John ', 'Doe ', 'JDOE ', '650.555.8877 ', '25-MAR-2009 ', 'SA_REP ',
3500, 0.25, 145, 80);

--Copying Rows from One Table to Another
insert into hr.employees
(employee_id, first_name, last_name, email, phone_number, hire_date, job_id, salary, commission_pct, manager_id, department_id)
select 210, first_name, last_name, email, phone_number, sysdate, 'IT_PROG', 3500, null, 103, 60
from hr.candidates
where first_name = 'Susan'
and last_name = 'Jones';

--Copying Data in Bulk from One Table to Another
select candidate_id, first_name, last_name, email, phone_number, sysdate, job_id, 3500, null, 'IT_PROG', 103
from hr.candidates;

--Changing Values in a Row
update hr.employees
set salary = salary * 1.05
where department_id = 50;

--Updating Multiple Fields with One Statement
update hr.employees
set job_id = 'ST_MAN',
phone_number = '650.124.9876',
salary = salary * 1.5
where employee_id = 131;

--Removing Unwanted Rows from a Table
delete
from hr.employees
where employee_id = 127;

--Removing All Rows from a Table
truncate table hr.employees;

--Selecting from the Results of Another Query
--Problem
--You need to treat the results of a query as if they were the contents of a table. You don't want to store the
--intermediate results as you need them freshly generated every time you run your query.

select d.department_name
from
(select department_id, department_name
from hr.departments
where location_id != 1700) d;

--Basing a Where Condition on a Query
--Problem
--You need to query the data from a table, but one of your criteria will be dependent on data from another
--table at the time the query runs—and you want to avoid hard-coding criteria. Specifically, you want to
--find all departments with offices in North America for the purposes of reporting.

select department_name
from hr.departments
where location_id in
(select location_id
from hr.locations
where country_id = 'US'
or country_id = 'CA');

--Finding and Eliminating NULLs in Queries
--Problem
--You need to report how many of your employees have a commission percentage as part of their
--remuneration, together with the number that get only a fixed salary. You track this using the
--COMMISSION_PCTfield of the HR.EMPLOYEEStable.

select first_name, last_name
from hr.employees
where commission_pct is null;

select first_name, last_name
from hr.employees
where commission_pct is not null;

--Sorting as a Person Expects
--Problem
--Your textual data has been stored in a mix of uppercase, lowercase, and sentencecase. You need to sort
--this data alphabetically as a person normally would, in a case-insensitive fashion.
update hr.employees
set last_name = 'SMITH'
where employee_id = 171;

select last_name
from hr.employees
order by last_name;

select first_name, last_name
from hr.employees
where last_name like 's%';

--Enabling Other Sorting and Comparison Options
--Problem
--You need to perform case-insensitive comparisons and other sorting operations on textual data that has
--been stored in an assortment of uppercase, lowercase, and sentence case.
alter session set nls_comp='LINGUISTIC';
select first_name, last_name
from hr.employees
where last_name = 'smith';

--Conditional Inserting or Updating Based on Existence
--Problem
--You want to insert rows intoa table with a key identifier that may already be present. If the identifier is
--not present, a new row should be created. If the identifier is present, the other columns for that row
--should be updated with new data, rather than a new row created.

--For our recipe, we’ll assume the HR.COUNTRIES table is to be loaded with amended country details sourced from a NEW_COUNTRIES table.
merge into hr.countries c
using
(select country_id, country_name
from hr.new_countries) nc
on (c.country_id = nc.country_id)
when matched then
update set c.country_name = nc.country_name
when not matched then
insert (c.country_id, c.country_name)
values (nc.country_id, nc.country_name);

--Summarizing the Values in a Column
--Problem
--You need to summarize data in a column in some way. For example, you have been asked to report on
--the average salary paid per employee, as well as the total salary budget, number of employees, highest
--and lowest earners, and more.
select avg(salary)
from hr.employees;

select sum(salary)
from hr.employees;

select count(salary)
from hr.employees;

select min(salary), max(salary)
from hr.employees;

select avg(salary), sum(salary)/count(salary)
from hr.employees;

select count(commission_pct), avg(commission_pct)
from hr.employees;

select count(*), count(commission_pct)
from hr.employees;

--Summarizing Data for Different Groups
--Problem
--You want to summarize data in a column, but you don’t want to summarize over all the rows in a table.
--You want to divide the rows into groups, and then summarize the column separately for each group. For
--example, you need to know the average salary paid per department.
select department_id, avg(salary)
from hr.employees
group by department_id;

--Grouping Data by Multiple Fields
--Problem
--You need to report data grouped by multiple values simultaneously. For example, an HR department
--may need to report on minimum, average, and maximum SALARYby DEPARTMENT_IDand JOB_ID.
select department_id, job_id, min(salary), avg(salary), max(salary)
from hr.employees
group by department_id, job_id
order by department_id, max(salary) desc;

--Ignoring Groups in Aggregate Data Sets
--Problem
--You want to ignore certain groups of data based on the outcome of aggregate functions or grouping
--actions. In effect, you’d really like another WHEREclause to work after the GROUP BYclause, providing
--criteria at the group or aggregate level.
select department_id, job_id, min(salary), avg(salary), max(salary), count(*)
from hr.employees
group by department_id, job_id
having count(*) > 1;

select department_id, job_id, min(salary), avg(salary), max(salary), count(*)
from hr.employees
group by department_id, job_id
having count(*) > 1
and min(salary) between 2500 and 17000
and avg(salary) != 5000
and max(salary)/min(salary) < 2
;

--Aggregating Data at Multiple Levels
--Problem
--You want to find totals, averages, and other aggregate figures, as well as subtotals in various dimensions
--for a report. You want to achieve thiswith as few statements as possible, preferably just one, rather
--than having to issue separate statements to get each intermediate subtotal along the way.
select department_id, job_id, avg(salary), sum(salary)
from hr.employees
group by rollup (department_id, job_id);

select department_id, job_id, manager_id,
extract(year from hire_date) as "START_YEAR", avg(salary)
from hr.employees
group by cube (department_id, job_id, manager_id, extract(year from hire_date));
This recipe results in an examination of average salary infour dimensions!

--Using Aggregate Results in Other Queries
--Problem
--You want to use the output of a complex query involving aggregates and grouping as source data for
--another query.
select * from (
select department_id as "dept", job_id as "job", to_char(hire_date,'YYYY') as
"Start_Year", avg(salary) as "avsal"
from hr.employees
group by rollup (department_id, job_id, to_char(hire_date,'YYYY'))) salcalc
where salcalc.start_year > '1990'
or salcalc.start_year is null
order by 1,2,3,4;

--Counting Members in Groups and Sets
--Problem
--You need to count members of a group, groups of groups, and other set-based collections. You also need
--to include and exclude individual members and groups dynamically based on other data at the same
--time. For instance, you want to count how many jobs each employee has held during their time at the
--organization, based on the number of promotions they’ve had within the company.
select jh.JobsHeld, count(*) as StaffCount
from
(select u.employee_id, count(*) as JobsHeld
from
(select employee_id from hr.employees
union all
select employee_id from hr.job_history) u
group by u.employee_id) jh
group by jh.JobsHeld;