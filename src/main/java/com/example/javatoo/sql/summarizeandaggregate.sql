select count(*), avg(salary), sum(salary), min(salary), max(salary), count(salary), sum(salary)/count(salary)
from hr.employees;

select department_id, avg(salary)
from hr.employees
group by department_id;

select department_id, job_id, min(salary), avg(salary), max(salary)
from hr.employees
group by department_id, job_id
order by department_id, max(salary) desc;

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

select department_id, job_id, avg(salary), sum(salary)
from hr.employees
group by rollup (department_id, job_id);

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