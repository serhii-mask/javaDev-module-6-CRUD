-- Запит для пошуку робітника з найбальшою заробітньою платою
SELECT name, salary FROM worker
WHERE salary = (SELECT MAX(salary) FROM worker);